/*
 * Copyright (C) 2015 Telecom Italia S.p.A.
 *
 * This file is part of Metaware.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Author: Marco Terrinoni marco.terrinoni at consoft.it
 */
package com.tilab.fiware.metaware.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.tilab.fiware.metaware.core.SingltProv.INSTANCE;
import com.tilab.fiware.metaware.core.Util;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Algorithm;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DataSource;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Dataset;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.List;
import java.util.Map;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * Authorization request filter implementation class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class AuthRequestFilter implements ContainerRequestFilter {

    private static final Logger log = Logger.getLogger(AuthRequestFilter.class);

    /**
     * Implementation of the filter method.
     *
     * @param crc The ContainerRequestContext object
     * @throws WebApplicationException
     */
    @Override
    public void filter(ContainerRequestContext crc) throws WebApplicationException {
        log.debug("Verb: " + crc.getMethod());
        log.debug("Requested Path: " + crc.getUriInfo().getPath());

        String auth = crc.getHeaderString("Authorization");
        String verb = crc.getMethod();
        String path = crc.getUriInfo().getPath();

        // Check if the basic auth string is sent
        if (auth == null) {
            if (path.equals("api-docs")) {
                return; // get access to swagger interface
            }
            throw new ClientErrorException(Status.UNAUTHORIZED.toString(),
                    Status.UNAUTHORIZED.getStatusCode());
        } else {
            String[] uap = null;

            try {
                uap = Util.decodeBasicAuth(auth);
            } catch (Exception e) {
                log.error(e, e);
            }

            if (uap == null || uap.length != 2) { // empty basic auth string
                throw new ClientErrorException(Status.UNAUTHORIZED.toString(),
                        Status.UNAUTHORIZED.getStatusCode());
            } else {
                User user;

                try {
                    user = INSTANCE.getUserService().getUserByCredentials(
                            uap[0], DigestUtils.sha256Hex(uap[1])); // compare hashes

                    String jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(user);
                    log.debug("Current user: " + jsonMsg);
                } catch (ResourceNotFoundException e) {
                    log.error(e, e);
                    throw new ClientErrorException(Status.UNAUTHORIZED.toString(),
                            Status.UNAUTHORIZED.getStatusCode());
                } catch (JsonProcessingException | ClientErrorException e) {
                    log.error(e, e);
                    throw new ServerErrorException(Status.INTERNAL_SERVER_ERROR.toString(),
                            Status.INTERNAL_SERVER_ERROR.getStatusCode());
                }

                // Security access check based on roles
                if (!securityRoleFilter(user, verb, path)) {
                    log.info(user.getId() + " is not authorized for " + path);
                    throw new ClientErrorException(Status.UNAUTHORIZED.toString(),
                            Status.UNAUTHORIZED.getStatusCode());
                }
            }
        }
    }

    /**
     * Checks if the request can be accepted.
     *
     * @param user The user who performs the request
     * @param verb The HTTP verb of the request
     * @param path The path of the request
     * @return True if the request can be accepted
     */
    public boolean securityRoleFilter(User user, String verb, String path) {
        if (user.isAdmin()) { // initial admin check
            return true; // admin can do anything
        }

        String[] parts;
        parts = path.split("/"); // e.g.: "v1/companies/xyz"

        switch (parts[1]) {
            case "algorithms":
                if (parts.length <= 3) {
                    if (verb.equals("POST")) { // new algorithm insertion
                        return user.isAdmin(); // only admin can create a new algorithm
                    }
                    Algorithm reqAlgorithm = INSTANCE.getAlgorithmService().getAlgorithm(parts[2]);
                    if (reqAlgorithm.getStatus().equals("public") // public algorithm requested or ...
                            || reqAlgorithm.getOwnerId().toHexString().equals(user.getId())) { // the user is the owner
                        return true; // no restriction on public algorithms or owners
                    } else { // is a private algorithm
                        switch (verb) {
                            case "GET": // read an algorithm
                                return checkPerm(reqAlgorithm.getPermissionsIds(), user.getId(), "r"); // true if the user can read the algorithm
                            case "PUT": // modify an algorithm
                                return checkPerm(reqAlgorithm.getPermissionsIds(), user.getId(), "u"); // true if the user can update the algorithm
                            case "DELETE": // delete an algorithm
                                return checkPerm(reqAlgorithm.getPermissionsIds(), user.getId(), "d"); // true if the user can delete the algorithm
                            default:
                                return false;
                        }
                    }
                }
                return false;
            case "companies":
                if (parts.length <= 3) {
                    if (verb.equals("POST") || verb.equals("DELETE")) {
                        return user.isAdmin(); // only admin can create/delete a company
                    }
                    if (user.getCompanyId() != null) { // check if the user has a company
                        return user.getCompanyId().toString().equals(parts[2]);
                    }
                }
                return false;
            case "datasets":
                if (parts.length <= 3) {
                    if (verb.equals("POST")) { // new dataset insertion
                        return user.isAdmin(); // only admin can create a new dataset
                    }
                    Dataset reqDataset = INSTANCE.getDatasetService().getDataset(parts[2]);
                    if (reqDataset.getStatus().equals("public") // public dataset requested or ...
                            || reqDataset.getOwnerId().toHexString().equals(user.getId())) { // the user is the owner
                        return true; // no restriction on public dataset or owners
                    } else { // is a private dataset
                        switch (verb) {
                            case "GET": // read a dataset
                                return checkPerm(reqDataset.getPermissionsIds(), user.getId(), "r"); // true if the user can read the dataset
                            case "PUT": // modify a dataset
                                return checkPerm(reqDataset.getPermissionsIds(), user.getId(), "u"); // true if the user can update the dataset
                            case "DELETE": // delete a dataset
                                return checkPerm(reqDataset.getPermissionsIds(), user.getId(), "d"); // true if the user can delete the dataset
                            default:
                                return false;
                        }
                    }
                }
                return false;
            case "datasources":
                if (parts.length <= 3) {
                    if (verb.equals("POST")) { // new data source insertion
                        return user.isAdmin(); // only admin can create a new data source
                    }
                    DataSource reqDatasource = INSTANCE.getDataSourceService().getDataSource(parts[2]);
                    if (reqDatasource.getStatus().equals("public") // public data source or ...
                            || reqDatasource.getOwnerId().toHexString().equals(user.getId())) { // the user is the owner
                        return true; // no restriction on public data source or owners
                    } else { // is a private data source
                        switch (verb) {
                            case "GET": // read a data source
                                return checkPerm(reqDatasource.getPermissionsIds(), user.getId(), "r"); // true if the user can read the data source
                            case "PUT": // modify a data source
                                return checkPerm(reqDatasource.getPermissionsIds(), user.getId(), "u"); // true if the user can update the data source
                            case "DELETE": // delete a data source
                                return checkPerm(reqDatasource.getPermissionsIds(), user.getId(), "d"); // true if the user can delete the data source
                            default:
                                return false;
                        }
                    }
                }
                return false;
            case "departments":
                if (parts.length <= 3) {
                    if (verb.equals("POST") || verb.equals("DELETE")) {
                        return user.isAdmin(); // only admin can create/delete a department
                    }
                    if (user.getDepartmentId() != null) { // check if the user has a department
                        return user.getDepartmentId().toString().equals(parts[2]);
                    }
                }
                return false;
            case "discoverObjects":
                if (parts.length == 4) { // search based on...
                    if (user.getId().equals(parts[3]) || // ... user Id
                            user.getCompanyId().toHexString().equals(parts[3]) || // ... company id (user in company)
                            user.getDepartmentId().toHexString().equals(parts[3])) { // .. department id (user in department)
                        return true;
                    }
                }
                return false; // no authorization or wrong syntax
            case "users":
                if (parts.length <= 3) { // get specific user
                    if (verb.equals("POST") || verb.equals("DELETE")) {
                        return user.isAdmin(); // only admin can create/delete other users
                    }
                    if (user.getId() != null) {
                        return user.getId().equals(parts[2]); // user can get only its own user
                    }
                }
                return false;
            case "api-docs": // TODO: check this behavior
                return true;
            case "templates":
                log.debug(parts.length);
                if (parts.length <= 3) { // e.g.: v1/templates/dataset || v1/templates (list)
                    if (verb.equals("POST") || verb.equals("DELETE")) { // template creation and removal
                        return user.isAdmin(); // only admins can create or delete a template
                    }
                    return true; // authenticated users can retrieve templates
                }
                return false;
            default:
                return false;
        }
    }

    /**
     * This method checks if the specified user is allowed to perform the action on the current
     * object (dataset, algorithm).
     *
     * The permissions list is analyzed entry by entry, in order to find if the specified user is
     * reported in this list and check if there is a permission match.
     *
     * @param permissionsList the list of permissions for the selected object.
     * @param userId the string of the Id of the user.
     * @param perm the character that identifies the action (can be "r"ead, "u"pdate, or "d"elete)
     * @return true if the user can read, update, or delete the object.
     */
    public boolean checkPerm(List<Permission> permissionsList, String userId, CharSequence perm) {
        for (int i = 0; i < permissionsList.size(); i++) {
            Permission currPerm = new Permission((Map) permissionsList.get(i)); // this explicit declaration is necessary otherwise a BasicDBObject is retrieved (Map)
            if (currPerm.getReferenceId().toHexString().equals(userId)
                    && currPerm.getPerm().contains(perm)) {
                return true;
            }
        }
        return false;
    }
}

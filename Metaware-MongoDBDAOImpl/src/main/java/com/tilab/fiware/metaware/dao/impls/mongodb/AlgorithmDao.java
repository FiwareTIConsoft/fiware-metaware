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
package com.tilab.fiware.metaware.dao.impls.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.ALGORITHMS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.COMPANIES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DEPARTMENTS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Algorithm;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * DAO implementation for Algorithm domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class AlgorithmDao {

    // Logger
    private static final Logger log = Logger.getLogger(AlgorithmDao.class);

    // Messages
    private static final String MSG_DAO_GET_LIST = "Retrieve algorithms' metadata list.";
    private static final String MSG_DAO_GET = "Retrieve algorithm's metadata with Id: ";
    private static final String MSG_DAO_CREATE = "Create new algorithm's metadata.";
    private static final String MSG_DAO_UPSERT = "Upsert algorithm's metadata with Id: ";
    private static final String MSG_DAO_DELETE = "Delete algorithm's metadata with Id: ";
    private static final String MSG_ERR_NOT_FOUND = "Algorithm's metadata not found.";
    private static final String MSG_ERR_NOT_VALID_ID = "Not a valid Id.";
    private static final String MSG_ERR_NOT_VALID_OWNER_ID = "Not a valid owner Id.";
    private static final String MSG_ERR_NOT_VALID_OBJ
            = "Algorithm's metadata object not specified.";
    private static final String MSG_ERR_NOT_VALID_PERMISSION = "Not a valid list of permissions.";

    // MongoDB objects
    private DBCollection algorithmsCollection;
    private DBCursor cursor;

    /**
     * Retrieves the list of algorithms' metadata.
     *
     * @return the list of metadata.
     */
    public List<Algorithm> getAlgorithmsList() {
        log.debug(MSG_DAO_GET_LIST);

        List<Algorithm> algorithmsList = new ArrayList<>();

        algorithmsCollection = INSTANCE.getDatasource().getDbCollection(ALGORITHMS_COLLECTION_NAME);
        algorithmsCollection.setObjectClass(Algorithm.class);
        cursor = algorithmsCollection.find();

        try {
            while (cursor.hasNext()) {
                Algorithm a = (Algorithm) cursor.next();
                algorithmsList.add(a);
            }
        } finally {
            cursor.close();
        }

        return algorithmsList;
    }

    /**
     * Retrieves the selected algorithm's metadata.
     *
     * @param id the Id of the selected algorithm's metadata.
     * @return the selected algorithm's metadata
     */
    public Algorithm getAlgorithm(String id) {
        log.debug(MSG_DAO_GET + id);

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        algorithmsCollection = INSTANCE.getDatasource().getDbCollection(ALGORITHMS_COLLECTION_NAME);
        algorithmsCollection.setObjectClass(Algorithm.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        Algorithm algorithm = (Algorithm) algorithmsCollection.findOne(query);

        if (algorithm == null) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(algorithm);
            log.debug("Selected algorithm's metadata: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return algorithm;
    }

    /**
     * Creates a new algorithm's metadata.
     *
     * @param algorithm the new algorithm's metadata to be saved.
     * @return the Id of the new metadata object.
     */
    public String createAlgorithm(Algorithm algorithm) {
        log.debug(MSG_DAO_CREATE);

        // Set collections
        DBCollection usersCollection = INSTANCE.getDatasource().
                getDbCollection(USERS_COLLECTION_NAME);
        DBCollection departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        DBCollection companiesCollection = INSTANCE.getDatasource().
                getDbCollection(COMPANIES_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        departmentsCollection.setObjectClass(Department.class);
        companiesCollection.setObjectClass(Company.class);

        // Check if the owner field is ok and retrieve the objectId
        ObjectId ownerId = checkOwner(algorithm, usersCollection, departmentsCollection,
                companiesCollection);
        if (ownerId != null) {
            algorithm.setOwnerId(ownerId);
        } else {
            log.error(MSG_ERR_NOT_VALID_OWNER_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OWNER_ID);
        }

        // Check if the permissions field is ok and retrieve the list of permissions
        List<Permission> permissionsList = checkPermissions(algorithm, usersCollection,
                departmentsCollection, companiesCollection);
        if (permissionsList != null) {
            algorithm.setPermissions(permissionsList);
        } else {
            log.error(MSG_ERR_NOT_VALID_PERMISSION);
            throw new BadRequestException(MSG_ERR_NOT_VALID_PERMISSION);
        }

        algorithmsCollection = INSTANCE.getDatasource().getDbCollection(ALGORITHMS_COLLECTION_NAME);
        algorithmsCollection.setObjectClass(Algorithm.class);
        algorithmsCollection.insert(algorithm);

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(algorithm);
            log.debug("New algorithm's metadata: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return algorithm.getId();
    }

    /**
     * Updates the selected algorithm's metadata if exists, otherwise creates a new one.
     *
     * @param id        the Id of the selected algorithm's metadata to be updated.
     * @param algorihtm the dataset's metadata object with the modifications (or the metadata to be
     *                  saved).
     * @return the updated metadata object.
     */
    public Algorithm upsertAlgorithm(String id, Algorithm algorihtm) {
        log.debug(MSG_DAO_UPSERT + id);

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        if (algorihtm == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        // Intercept the possibility to change the Id
        if (algorihtm.containsField("_id")) {
            algorihtm.removeField("_id");
        }

        // Set collections
        DBCollection usersCollection = INSTANCE.getDatasource().
                getDbCollection(USERS_COLLECTION_NAME);
        DBCollection departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        DBCollection companiesCollection = INSTANCE.getDatasource().
                getDbCollection(COMPANIES_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        departmentsCollection.setObjectClass(Department.class);
        companiesCollection.setObjectClass(Company.class);

        // Check if the owner field is ok
        ObjectId ownerId = checkOwner(algorihtm, usersCollection, departmentsCollection,
                companiesCollection);
        if (ownerId != null) {
            algorihtm.setOwnerId(ownerId);
        } else {
            log.error(MSG_ERR_NOT_VALID_OWNER_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OWNER_ID);
        }

        // Check if the permissions field is ok
        List<Permission> permissionsList = checkPermissions(algorihtm, usersCollection,
                departmentsCollection, companiesCollection);
        if (permissionsList != null) {
            algorihtm.setPermissions(permissionsList);
        } else {
            log.error(MSG_ERR_NOT_VALID_PERMISSION);
            throw new BadRequestException(MSG_ERR_NOT_VALID_PERMISSION);
        }

        algorithmsCollection = INSTANCE.getDatasource().getDbCollection(ALGORITHMS_COLLECTION_NAME);
        algorithmsCollection.setObjectClass(Algorithm.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = algorithmsCollection.update(query, algorihtm, true, false); // upply upsert

        String numUpdates = String.valueOf(wRes.getN());
        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(algorihtm);
            log.debug(numUpdates + " algorithm updated: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return algorihtm;
    }

    /**
     * Removes the selected algorithm's metadata.
     *
     * @param id the Id of the selected dataset's metadata.
     */
    public void deleteAlgorithm(String id) {
        log.debug(MSG_DAO_DELETE + id);

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        algorithmsCollection = INSTANCE.getDatasource().getDbCollection(ALGORITHMS_COLLECTION_NAME);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = algorithmsCollection.remove(query);

        if (wRes.getN() == 0) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Checks if the selected owner exists in users, departments, or companies collection; if so,
     * returns the related ObjectId.
     *
     * @param algorithm             the selected algorithm.
     * @param usersCollection       the collection of registered users.
     * @param departmentsCollection the collection of registered departments.
     * @param companiesCollection   the collection of registered companies.
     * @return the Id of the owner of the algorithm if exists, null otherwise.
     */
    private ObjectId checkOwner(Algorithm algorithm, DBCollection usersCollection,
            DBCollection departmentsCollection, DBCollection companiesCollection) {

        BasicDBObject query = new BasicDBObject();

        if (algorithm.getOwner() instanceof ObjectId) { // already stored as ObjectId
            ObjectId ownerId = algorithm.getOwnerId();

            log.debug("Inserted owner Id: " + ownerId.toHexString());

            query.put("_id", ownerId);
        } else if (algorithm.getOwner() instanceof String) { // stored as a String
            String ownerId = algorithm.getOwner().toString();

            if (!ObjectId.isValid(ownerId)) {
                log.error(MSG_ERR_NOT_VALID_OWNER_ID);
                throw new BadRequestException(MSG_ERR_NOT_VALID_OWNER_ID);
            }

            log.debug("Inserted owner Id: " + ownerId);

            query.put("_id", new ObjectId(ownerId));
        } else { // unknown type
            log.error(MSG_ERR_NOT_VALID_OWNER_ID);
            return null;
        }

        ObjectId res;

        // Check if the owner exists in users
        User ownerUser = (User) usersCollection.findOne(query);
        if (ownerUser == null) { // owner is not a user
            // Check if the owner exists in department
            Department ownerDepartment = (Department) departmentsCollection.findOne(query);
            if (ownerDepartment == null) { // owner is not a department
                // Check if the owner exists in company
                Company ownerCompany = (Company) companiesCollection.findOne(query);
                if (ownerCompany == null) { // owner is not a company
                    res = null;
                } else { // the owner is a company
                    res = new ObjectId(ownerCompany.getId());
                }
            } else { // the owner is a department
                res = new ObjectId(ownerDepartment.getId());
            }
        } else { // the owner is a user
            res = new ObjectId(ownerUser.getId());
        }

        // Send back the Id of the owner (can be null)
        return res;
    }

    /**
     * Checks if the selected Ids in permissions (can be user, department, or company) exist in
     * users, departments, or companies collection; if so, returns the list of permissions.
     *
     * @param algorithm             the selected algorithm.
     * @param usersCollection       the collection of registered users.
     * @param departmentsCollection the collection of registered departments.
     * @param companiesCollection   the collection of registered companies.
     * @return permissionsList the list of permissions.
     */
    private List<Permission> checkPermissions(Algorithm algorithm, DBCollection usersCollection,
            DBCollection departmentsCollection, DBCollection companiesCollection) {

        // Check if the field exists
        if (algorithm.getPermissions() == null) {
            log.error(MSG_ERR_NOT_VALID_PERMISSION);
            return null;
        }

        // Prepare lists
        List rawPermissionsList = new ArrayList(algorithm.getPermissions()); // unspecified type generates maven warning
        List<Permission> permissionsList = new ArrayList<>();

        log.debug("Inserted permissions (raw): " + rawPermissionsList.toString());

        // Convert from raw to Permission objects
        for (Object rawCurrPerm : rawPermissionsList) {
            Permission currPerm = new Permission((Map) rawCurrPerm);
            if (!ObjectId.isValid(currPerm.getReference())) {
                log.error(MSG_ERR_NOT_VALID_PERMISSION);
                return null;
            }
            // TODO: insert check for "perm" string too
            currPerm.setReferenceId(new ObjectId(currPerm.getReference())); // transform the id from string to ObjectId
            permissionsList.add(currPerm);
        }

        log.debug("Inserted permissions: " + permissionsList.toString());

        // Make the query
        BasicDBObject query = new BasicDBObject();
        for (Permission currPerm : permissionsList) {
            query.put("_id", currPerm.getReferenceId()); // query by id
            int resNumUser = usersCollection.find(query).count(); // count users
            int resNumDepartment = departmentsCollection.find(query).count(); // count departments
            int resNumCompany = companiesCollection.find(query).count(); // count companies
            if ((resNumUser + resNumDepartment + resNumCompany) == 0) {
                log.error(MSG_ERR_NOT_VALID_PERMISSION);
                return null;
            }
        }

        return permissionsList;
    }
}

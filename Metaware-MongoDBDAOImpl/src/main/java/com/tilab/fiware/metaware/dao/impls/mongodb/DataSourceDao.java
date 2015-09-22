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
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.COMPANIES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DATASOURCES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DEPARTMENTS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DataSource;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * DAO implementation for DataSource domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DataSourceDao {

    // Logger
    private static final Logger log = Logger.getLogger(DataSourceDao.class);

    // Messages
    private static final String MSG_DAO_GET_LIST = "Retrieve data sources' metadata list.";
    private static final String MSG_DAO_GET = "Retrieve data source's metadata with Id: ";
    private static final String MSG_DAO_CREATE = "Create new data source's metadata.";
    private static final String MSG_DAO_UPSERT = "Upsert data source's metadata with Id: ";
    private static final String MSG_DAO_DELETE = "Delete data source's metadata with Id: ";
    private static final String MSG_ERR_NOT_FOUND = "Data source's metadata not found.";
    private static final String MSG_ERR_NOT_VALID_ID = "Not a valid Id.";
    private static final String MSG_ERR_NOT_VALID_OWNER_ID = "Not a valid owner Id.";
    private static final String MSG_ERR_NOT_VALID_OBJ
            = "Data source's metadata object not specified.";
    private static final String MSG_ERR_NOT_VALID_PERMISSIONS = "Not a valid list of permissions.";

    // MongoDB objects
    private DBCollection datasourcesCollection;
    private DBCursor cursor;

    /**
     * Retrieves the list of data-sources' metadata.
     *
     * @return the list of metadata.
     */
    public List<DataSource> getDataSourcesList() {
        log.debug(MSG_DAO_GET_LIST);

        List<DataSource> datasourcesList = new ArrayList<>();

        datasourcesCollection
                = INSTANCE.getDatasource().getDbCollection(DATASOURCES_COLLECTION_NAME);
        datasourcesCollection.setObjectClass(DataSource.class);
        cursor = datasourcesCollection.find();

        try {
            while (cursor.hasNext()) {
                DataSource d = (DataSource) cursor.next();
                datasourcesList.add(d);
            }
        } finally {
            cursor.close();
        }

        return datasourcesList;
    }

    /**
     * Retrieves the spelected data-source's metadata.
     *
     * @param id the Id of the selected data-source's metadata.
     * @return the selected data-source's metadata.
     */
    public DataSource getDataSource(String id) {
        log.debug(MSG_DAO_GET + id);

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        datasourcesCollection
                = INSTANCE.getDatasource().getDbCollection(DATASOURCES_COLLECTION_NAME);
        datasourcesCollection.setObjectClass(DataSource.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        DataSource datasource = (DataSource) datasourcesCollection.findOne(query);

        if (datasource == null) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(datasource);
            log.debug("Selected data source's metadata: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return datasource;
    }

    /**
     * Creates a new data-source's metadata object.
     *
     * @param datasource the new data-source's metadata to be stored.
     * @return the Id of the new data-source object.
     */
    public String createDataSource(DataSource datasource) {
        log.debug(MSG_DAO_CREATE);

        // Set collections
        DBCollection usersCollection
                = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        DBCollection departmentsCollection
                = INSTANCE.getDatasource().getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        DBCollection companiesCollection
                = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        departmentsCollection.setObjectClass(Department.class);
        companiesCollection.setObjectClass(Company.class);

        // Check if the owner field is ok and retrieve the objectId
        ObjectId ownerId = checkOwner(datasource, usersCollection, departmentsCollection,
                companiesCollection);
        if (ownerId != null) {
            datasource.setOwnerId(ownerId);
        } else {
            log.error(MSG_ERR_NOT_VALID_OWNER_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OWNER_ID);
        }

        // Check if the permissions field is ok and retrieve the list of permissions
        List<Permission> permissionsList = checkPermissions(datasource, usersCollection,
                departmentsCollection, companiesCollection);
        if (permissionsList != null) {
            datasource.setPermissions(permissionsList);
        } else {
            log.error(MSG_ERR_NOT_VALID_PERMISSIONS);
            throw new BadRequestException(MSG_ERR_NOT_VALID_PERMISSIONS);
        }

        datasourcesCollection
                = INSTANCE.getDatasource().getDbCollection(DATASOURCES_COLLECTION_NAME);
        datasourcesCollection.setObjectClass(DataSource.class);
        datasourcesCollection.insert(datasource);

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(datasource);
            log.debug("New data source's metadata: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return datasource.getId();
    }

    /**
     * Updates the selected data-source's metadata if exists, otherwise insert the new object.
     *
     * @param id         the Id of the selected data-source's metadata to be updated.
     * @param datasource the data-source's metadata object with the modifications (or the metadata
     *                   to be stored).
     * @return the updates metadata object.
     */
    public DataSource upsertDataSource(String id, DataSource datasource) {
        log.debug(MSG_DAO_UPSERT + id);

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        if (datasource == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        // Intercept the possibility to change the Id
        if (datasource.containsField("_id")) {
            datasource.removeField("_id");
        }

        // Set collections
        DBCollection usersCollection
                = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        DBCollection departmentsCollection
                = INSTANCE.getDatasource().getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        DBCollection companiesCollection
                = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        departmentsCollection.setObjectClass(Department.class);
        companiesCollection.setObjectClass(Company.class);

        // Check if the owner field is ok
        ObjectId ownerId = checkOwner(datasource, usersCollection, departmentsCollection,
                companiesCollection);
        if (ownerId != null) {
            datasource.setOwnerId(ownerId);
        } else { // catch the error
            log.error(MSG_ERR_NOT_VALID_OWNER_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OWNER_ID);
        }

        // Check if the permissions field is ok
        List<Permission> permissionsList = checkPermissions(datasource, usersCollection,
                departmentsCollection, companiesCollection);
        if (permissionsList != null) {
            datasource.setPermissions(permissionsList);
        } else { // catch the error
            log.error(MSG_ERR_NOT_VALID_PERMISSIONS);
            throw new BadRequestException(MSG_ERR_NOT_VALID_PERMISSIONS);
        }

        datasourcesCollection
                = INSTANCE.getDatasource().getDbCollection(DATASOURCES_COLLECTION_NAME);
        datasourcesCollection.setObjectClass(DataSource.class);
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        WriteResult wRes = datasourcesCollection.update(query, datasource, true, false); // upply upsert

        String numUpdates = String.valueOf(wRes.getN());
        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(datasource);
            log.debug(numUpdates + " datasource updated: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return datasource;
    }

    /**
     * Removes the selected data-source's metadata object.
     *
     * @param id the Id of the selected data-source's metadata object.
     */
    public void deleteDataSource(String id) {
        log.debug(MSG_DAO_DELETE + id);

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        datasourcesCollection
                = INSTANCE.getDatasource().getDbCollection(DATASOURCES_COLLECTION_NAME);
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        WriteResult wRes = datasourcesCollection.remove(query);

        if (wRes.getN() == 0) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Checks if the selected owner exists in users, departments, or companies collection; if so,
     * returns the related ObjectId.
     *
     * @param datasource            the selected datasource.
     * @param usersCollection       the collection of registered users.
     * @param departmentsCollection the collection of registered departments.
     * @param companiesCollection   the collection of registered companies.
     * @return the Id of the owner of the datasource if exists, null otherwise.
     */
    private ObjectId checkOwner(DataSource datasource, DBCollection usersCollection,
            DBCollection departmentsCollection, DBCollection companiesCollection) {
        BasicDBObject query = new BasicDBObject();

        if (datasource.getOwner() instanceof ObjectId) { // already stored as ObjectId
            ObjectId ownerId = datasource.getOwnerId();

            log.debug("Inserted owner Id: " + ownerId.toHexString());

            query.put("_id", ownerId);
        } else if (datasource.getOwner() instanceof String) { // stored as a String
            String ownerId = datasource.getOwner().toString();

            if (!ObjectId.isValid(ownerId)) {
                log.error(MSG_ERR_NOT_VALID_OWNER_ID);
                throw new BadRequestException(MSG_ERR_NOT_VALID_OWNER_ID);
            }

            log.debug("Inserted owner Id: " + ownerId);

            query.put("_id", new ObjectId(ownerId));
        } else { // unknown type -> error somehow
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
                Company ownerCompany = (Company) companiesCollection.findOne(query);
                if (ownerCompany == null) { // owner is not a company
                    res = null; // the owner doesn't exist
                } else { // the owner is a company
                    res = new ObjectId(ownerCompany.getId());
                }
            } else { // the owner is a department
                res = new ObjectId(ownerDepartment.getId());
            }
        } else { // the owner is a user
            res = new ObjectId(ownerUser.getId());
        }

        // Send back the Id of the owner (it can be null if the owner doesn't exist)
        return res;
    }

    /**
     * Checks if the selected Ids in permissions (can be user, department, or company) exist in
     * users, departments, or companies collections; if so, returns the list of permissions.
     *
     * @param datasource            the selected data source.
     * @param usersCollection       the collection of registered users.
     * @param departmentsCollection the collection of registered departments.
     * @param companiesCollection   the collection of registered companies.
     * @return permissionsList the list of permissions.
     */
    private List<Permission> checkPermissions(DataSource datasource, DBCollection usersCollection,
            DBCollection departmentsCollection, DBCollection companiesCollection) {
        // Check if the field exists
        if (datasource.getPermissions() == null) {
            return null; // no permissions provided
        }

        // Prepare lists
        List rawPermissionsList = new ArrayList(datasource.getPermissions()); // unspecified type generates maven warning
        List<Permission> permissionsList = new ArrayList<>();

        log.debug("Inserted permissions (raw): " + rawPermissionsList.toString());

        // Convert from raw to Permission objects
        for (Object rawCurrPerm : rawPermissionsList) {
            Permission currPerm = new Permission((Map) rawCurrPerm);
            if (!ObjectId.isValid(currPerm.getReference())) {
                return null; // the associated id isn't valid
            }
            // TODO: insert check for "perm" string too
            currPerm.setReferenceId(new ObjectId(currPerm.getReference())); // transform the id from string to objectid
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
                return null; // found nothing
            }
        }

        return permissionsList;
    }
}

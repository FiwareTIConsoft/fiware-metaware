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
 * Author: Marco Terrinoni <marco.terrinoni at consoft.it>
 */
package com.tilab.fiware.metaware.dao.impls.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.COMPANIES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DATASETS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DEPARTMENTS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Dataset;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DatasetStructure;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * DAO implementation for Dataset domain class.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class DatasetDao {

    // Logger
    private static final Logger log = Logger.getLogger(DatasetDao.class);

    // Messages
    private static final String MSG_DAO_GET_LIST = "Retrive datasets' metadata list.";
    private static final String MSG_DAO_GET = "Retrieve dataset's metadata with Id: ";
    private static final String MSG_DAO_CREATE = "Create new dataset's metadata.";
    private static final String MSG_DAO_UPSERT = "Upsert dataset's metadata with Id: ";
    private static final String MSG_DAO_DELETE = "Delete dataset's metadata with Id: ";
    private static final String MSG_ERR_NOT_FOUND = "Dataset's metadata not found.";
    private static final String MSG_ERR_NOT_VALID_ID = "Not a valid Id.";
    private static final String MSG_ERR_NOT_VALID_OWNER_ID = "Not a valid owner Id.";
    private static final String MSG_ERR_NOT_VALID_OBJ = "Dataset's metadata object not specified.";
    private static final String MSG_ERR_NOT_VALID_USERS_ID = "Not a valid list of users' id.";

    // MongoDB objects
    private DBCollection datasetsCollection;
    private DBCursor cursor;

    /**
     * Retrieves the list of datasets' metadata.
     *
     * @return the list of metadata.
     */
    public List<Dataset> getDatasetsList() {
        log.debug(MSG_DAO_GET_LIST);

        List<Dataset> datasetsList = new ArrayList<>();

        datasetsCollection = INSTANCE.getDatasource().getDbCollection(DATASETS_COLLECTION_NAME);
        datasetsCollection.setObjectClass(Dataset.class);
        datasetsCollection.setInternalClass("structure", DatasetStructure.class);
        cursor = datasetsCollection.find();

        try {
            while (cursor.hasNext()) {
                Dataset d = (Dataset) cursor.next();
                datasetsList.add(d);
            }
        } finally {
            cursor.close();
        }

        return datasetsList;
    }

    /**
     * Retrieves the selected dataset's metadata.
     *
     * @param id the Id of the selected dataset's metadata.
     * @return the selected dataset's metadata.
     */
    public Dataset getDataset(String id) {
        log.debug(MSG_DAO_GET + id);

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        datasetsCollection = INSTANCE.getDatasource().getDbCollection(DATASETS_COLLECTION_NAME);
        datasetsCollection.setObjectClass(Dataset.class);
        datasetsCollection.setInternalClass("structure", DatasetStructure.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        Dataset dataset = (Dataset) datasetsCollection.findOne(query);

        if (dataset == null) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(dataset);
            log.debug("Selected dataset's metadata: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return dataset;
    }

    /**
     * Creates a new dataset's metadata.
     *
     * @param dataset the new dataset's metadata to be saved.
     * @return the Id of the new metadata object.
     */
    public String createDataset(Dataset dataset) {
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
        ObjectId ownerId = checkOwner(dataset, usersCollection, departmentsCollection,
                companiesCollection);
        if (ownerId != null) {
            dataset.setOwnerId(ownerId);
        } else {
            log.error(MSG_ERR_NOT_VALID_OWNER_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OWNER_ID);
        }

        // Check if the users field is ok and retrieve the objectIds list
        List<Permission> permissionsList = checkPermissions(dataset, usersCollection,
                departmentsCollection, companiesCollection);
        if (permissionsList != null) {
            dataset.setPermissions(permissionsList);
        } else {
            log.error(MSG_ERR_NOT_VALID_USERS_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_USERS_ID);
        }

        datasetsCollection = INSTANCE.getDatasource().getDbCollection(DATASETS_COLLECTION_NAME);
        datasetsCollection.setObjectClass(Dataset.class);
        datasetsCollection.insert(dataset);

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(dataset);
            log.debug("New dataset's metadata: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return dataset.getId();
    }

    /**
     * Updates the selected dataset's metadata if exists, otherwise creates a new one.
     *
     * @param id      the Id of the selected dataset's metadata to be updated.
     * @param dataset the dataset's metadata object with the modifications (or the metadata to be
     *                saved)
     * @return the updated metadata object.
     */
    public Dataset upsertDataset(String id, Dataset dataset) {
        log.debug(MSG_DAO_UPSERT);

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        if (dataset == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        // Intecept the possibility to change the Id
        if (dataset.containsField("_id")) {
            dataset.removeField("_id");
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

        // Check if the owner field is ok and retrieve the objectId
        ObjectId ownerId = checkOwner(dataset, usersCollection, departmentsCollection,
                companiesCollection);
        if (ownerId != null) {
            dataset.setOwnerId(ownerId);
        } else {
            log.error(MSG_ERR_NOT_VALID_OWNER_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OWNER_ID);
        }

        // Check if the users field is ok and retrieve the objectIds list
        List<Permission> permissionsList = checkPermissions(dataset, usersCollection,
                departmentsCollection, companiesCollection);
        if (permissionsList != null) {
            dataset.setPermissions(permissionsList);
        } else {
            log.error(MSG_ERR_NOT_VALID_USERS_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_USERS_ID);
        }

        datasetsCollection = INSTANCE.getDatasource().getDbCollection(DATASETS_COLLECTION_NAME);
        datasetsCollection.setObjectClass(Dataset.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = datasetsCollection.update(query, dataset, true, false); // selection criteria, modifications to apply, upsert, multi-document

        String numUpdates = String.valueOf(wRes.getN());
        String jsonMsg;
        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(dataset);
            log.debug(numUpdates + " dataset updated: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return dataset;
    }

    /**
     * Removes the selected dataset's metadata.
     *
     * @param id the Id of the selected dataset's metadata.
     */
    public void deleteDataset(String id) {
        log.debug(MSG_DAO_DELETE);

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        datasetsCollection = INSTANCE.getDatasource().getDbCollection(DATASETS_COLLECTION_NAME);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = datasetsCollection.remove(query);

        if (wRes.getN() == 0) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Checks if the selected owner exists in users, departments, or companies collection; if so,
     * returns the related ObjectId.
     *
     * @param dataset               the selected dataset.
     * @param usersCollection       the collection of registered users.
     * @param departmentsCollection the collection of registered departments.
     * @param companiesCollection   the collection of registered companies.
     * @return the Id of the owner of the dataset.
     */
    private ObjectId checkOwner(Dataset dataset, DBCollection usersCollection,
            DBCollection departmentsCollection, DBCollection companiesCollection) {

        BasicDBObject query = new BasicDBObject();

        if (dataset.getOwner() instanceof ObjectId) { // already stored as ObjectId
            ObjectId ownerId = dataset.getOwnerId();

            log.debug("Inserted owner Id: " + ownerId.toString());

            query.put("_id", ownerId);
        } else if (dataset.getOwner() instanceof String) { // stored as a String
            String ownerId = dataset.getOwner().toString();

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
            // Check if the owner exists in departments
            Department ownerDepartment = (Department) departmentsCollection.findOne(query);
            if (ownerDepartment == null) { // owner is not a department
                // Check if the owner exists in companies
                Company ownerCompany = (Company) companiesCollection.findOne(query);
                if (ownerCompany == null) { // owner is not a company
                    res = null;
                } else { // the owner is a company
                    res = new ObjectId(ownerCompany.getId());
                }
            } else { // the owner is a department
                res = new ObjectId(ownerDepartment.getId());
            }
        } else { // the owner is a company
            res = new ObjectId(ownerUser.getId());
        }

        // Send back the Id of the owner (can be null);
        return res;
    }

    /**
     * Checks if the selected users (can be user, department, or company) exist in users,
     * departments, or companies collection; if so, return the list of ObjectIds.
     *
     * @param dataset               the selected dataset
     * @param usersCollection       the collection of registered users.
     * @param departmentsCollection the collection of registered departments.
     * @param companiesCollection   the collection of registered companies.
     * @return idList the list of Ids.
     */
    private List<Permission> checkPermissions(Dataset dataset, DBCollection usersCollection,
            DBCollection departmentsCollection, DBCollection companiesCollection) {

        // Check if the field exists
        if (dataset.getPermissions() == null) {
            return null;
        }

        // Prepare lists
        List rawPermissionsList = new ArrayList(dataset.getPermissions());
        List<Permission> permissionsList = new ArrayList();

        log.debug("Inserted permissions (raw): " + rawPermissionsList.toString());

        // Convert from raw to Permission objects
        for (Object rawCurrPerm : rawPermissionsList) {
            Permission currPerm = new Permission((Map) rawCurrPerm);
            if (!ObjectId.isValid(currPerm.getReference())) {
                return null;
            }
            // TODO: insert check for "perm" string too
            currPerm.setReferenceId(new ObjectId(currPerm.getReference()));
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
                return null;
            }
        }

        return permissionsList;
    }

}

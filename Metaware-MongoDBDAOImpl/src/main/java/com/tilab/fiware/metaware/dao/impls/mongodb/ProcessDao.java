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
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DEPARTMENTS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.PROCESSES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Process;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.ProcessingBlock;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * DAO implementation for Process domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class ProcessDao {

    // Logger
    private static final Logger log = Logger.getLogger(ProcessDao.class);

    // Messages
    private static final String MSG_DAO_GET_LIST = "Retrieve processes' metadata list.";
    private static final String MSG_DAO_GET = "Retrieve process' metadata with Id: ";
    private static final String MSG_DAO_CREATE = "Create new process'metadata.";
    private static final String MSG_DAO_UPSERT = "Upsert process's metadata with Id: ";
    private static final String MSG_DAO_DELETE = "Delete process's metadata with Id: ";
    private static final String MSG_ERR_NOT_FOUND = "Process's metadata not found.";
    private static final String MSG_ERR_NOT_VALID_ID = "Not a valid Id.";
    private static final String MSG_ERR_NOT_VALID_OWNER_ID = "Not a valid owner Id.";
    private static final String MSG_ERR_NOT_VALID_OBJ = "Process's metadata object not specified.";
    private static final String MSG_ERR_NOT_VALID_PERMISSION = "Not a valid list of permissions.";

    // MongoDB objects
    private DBCollection processesCollection;
    private DBCursor cursor;

    /**
     * Retrieves the list of processes' metadata.
     *
     * @return the list of metadata.
     */
    public List<Process> getProcessesList() {
        log.debug(MSG_DAO_GET_LIST);

        List<Process> processesList = new ArrayList<>();

        // Set the collection
        processesCollection = INSTANCE.getDatasource().getDbCollection(PROCESSES_COLLECTION_NAME);
        processesCollection.setObjectClass(Process.class);
        processesCollection.setInternalClass("processingBlocks", ProcessingBlock.class);

        // Make the query
        cursor = processesCollection.find();

        try {
            while (cursor.hasNext()) {
                Process p = (Process) cursor.next();
                processesList.add(p);
            }
        } finally {
            cursor.close();
        }

        return processesList;
    }

    /**
     * Retrieves the selected process' metadata.
     *
     * @param id the Id of the selected process' metadata.
     * @return the selected process' metadata.
     */
    public Process getProcess(String id) {
        log.debug(MSG_DAO_GET + id);

        // Check Id validity
        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        // Set the collection
        processesCollection = INSTANCE.getDatasource().getDbCollection(PROCESSES_COLLECTION_NAME);
        processesCollection.setObjectClass(Process.class);
        processesCollection.setInternalClass("processingBlocks", ProcessingBlock.class);

        // Make the query
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        Process process = (Process) processesCollection.findOne(query);

        if (process == null) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException(MSG_ERR_NOT_FOUND);
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(process);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return process;
    }

    /**
     * Creates a new process' metadata object.
     *
     * @param process the new process' metadata object to be saved.
     * @return the Id of the new inserted process' metadata object.
     */
    public String createProcess(Process process) {
        log.debug(MSG_DAO_CREATE);

        // Set the collections
        DBCollection usersCollection = INSTANCE.getDatasource().getDbCollection(
                USERS_COLLECTION_NAME);
        DBCollection departmentsCollection = INSTANCE.getDatasource().getDbCollection(
                DEPARTMENTS_COLLECTION_NAME);
        DBCollection companiesCollection = INSTANCE.getDatasource().getDbCollection(
                COMPANIES_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        departmentsCollection.setObjectClass(Department.class);
        companiesCollection.setObjectClass(Company.class);

        // Check if the owner field is ok and retrieve the ObjectId
        ObjectId ownerId = checkOwner(process, usersCollection, departmentsCollection,
                companiesCollection);
        if (ownerId != null) {
            process.setOwnerId(ownerId);
        } else {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        // Check if the permissions field is ok and retrieve the list of permissions
        List<Permission> permissionsList = checkPermissions(process, usersCollection,
                departmentsCollection, companiesCollection);
        if (permissionsList != null) {
            process.setPermissions(permissionsList);
        } else {
            log.error(MSG_ERR_NOT_VALID_PERMISSION);
            throw new BadRequestException(MSG_ERR_NOT_VALID_PERMISSION);
        }

        processesCollection = INSTANCE.getDatasource().getDbCollection(PROCESSES_COLLECTION_NAME);
        processesCollection.setObjectClass(Process.class);
        processesCollection.insert(process);

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(process);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return process.getId();
    }

    /**
     * Updates the selected process' metadata object if exists, otherwise creates a new one.
     *
     * @param id      the Id of the selected process' metadata object to be updated.
     * @param process the process' metadata object with the modifications.
     * @return the updated process' metadata.
     */
    public Process upsertProcess(String id, Process process) {
        log.debug(MSG_DAO_UPSERT + id);

        // Initial checks
        if (!ObjectId.isValid(id)) { // check id string
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }
        if (process == null) { // check if process param exists
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }
        if (process.containsField("_id")) { // intercept the possibility to change the Id
            process.removeField("_id");
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
        ObjectId ownerId = checkOwner(process, usersCollection, departmentsCollection,
                companiesCollection);
        if (ownerId != null) {
            process.setOwnerId(ownerId);
        } else {
            log.error(MSG_ERR_NOT_VALID_OWNER_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OWNER_ID);
        }

        // Check if the users field is ok and retrieve the objectIds list
        List<Permission> permissionsList = checkPermissions(process, usersCollection,
                departmentsCollection, companiesCollection);
        if (permissionsList != null) {
            process.setPermissions(permissionsList);
        } else {
            log.error(MSG_ERR_NOT_VALID_PERMISSION);
            throw new BadRequestException(MSG_ERR_NOT_VALID_PERMISSION);
        }

        // Perform the update
        processesCollection = INSTANCE.getDatasource().getDbCollection(PROCESSES_COLLECTION_NAME);
        processesCollection.setObjectClass(Process.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = processesCollection.update(query, process, true, false); // selection criteria, modifications to apply, upsert, multi-document

        // Output production
        String numUpdates = String.valueOf(wRes.getN()); // wRes.getN returns the number of updated objects
        String jsonMsg;
        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(process);
            log.debug(numUpdates + " processes updated: " + process);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return process;
    }

    /**
     * Removes the selected process' metadata object.
     *
     * @param id the Id of the selected process' metadata object.
     */
    public void deleteProcess(String id) {
        log.debug(MSG_DAO_DELETE + id);

        // Check passed Id
        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        processesCollection = INSTANCE.getDatasource().getDbCollection(PROCESSES_COLLECTION_NAME);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = processesCollection.remove(query);

        // Check the number of deleted objects
        if (wRes.getN() == 0) { // if 0 then the query found nothing
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException(MSG_ERR_NOT_FOUND);
        }
    }

    /**
     * Checks if the selected owner exists in users, departments, or companies collection; if so,
     * returns the related ObjectId.
     *
     * @param process               the selected process.
     * @param usersCollection       the collection of registered users.
     * @param departmentsCollection the collection of registered departments.
     * @param companiesCollection   the collection of registered companies.
     * @return the Id of the owner of the process if exists, null otherwise.
     */
    private ObjectId checkOwner(Process process, DBCollection usersCollection,
            DBCollection departmentsCollection, DBCollection companiesCollection) {
        BasicDBObject query = new BasicDBObject();

        if (process.getOwner() instanceof ObjectId) { // already stored as ObjectId
            ObjectId ownerId = process.getOwnerId();

            log.debug("Inserted owner Id: " + ownerId.toHexString());

            query.put("_id", ownerId);
        } else if (process.getOwner() instanceof String) { // stored as a String
            String ownerId = process.getOwner().toString();

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
     * @param process               the selected process.
     * @param usersCollection       the collection of registered users.
     * @param departmentsCollection the collection of registered departments.
     * @param companiesCollection   the collection of registered companies.
     * @return permissionsList the list of permissions.
     */
    private List<Permission> checkPermissions(Process process, DBCollection usersCollection,
            DBCollection departmentsCollection, DBCollection companiesCollection) {
        // Check if the field exists
        if (process.getPermissions() == null) {
            log.error(MSG_ERR_NOT_VALID_PERMISSION);
            return null;
        }

        // Prepare lists
        List rawPermissionsList = new ArrayList(process.getPermissions()); // uspecified type generates maven warning
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
            currPerm.setReferenceId(new ObjectId(currPerm.getReference())); // transform the Id from string to ObjectId

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

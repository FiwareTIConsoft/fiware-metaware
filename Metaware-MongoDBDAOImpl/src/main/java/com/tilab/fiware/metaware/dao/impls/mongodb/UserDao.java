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
import com.mongodb.DuplicateKeyException;
import com.mongodb.WriteResult;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.COMPANIES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DEPARTMENTS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * DAO implementation for User domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class UserDao {

    // Logger
    private static final Logger log = Logger.getLogger(UserDao.class);

    // Messages
    private static final String MSG_DAO_GET_LIST = "Retrieve users list.";
    private static final String MSG_DAO_GET = "Retrieve user with Id: ";
    private static final String MSG_DAO_GET_BY_CREDENTIALS = "Find user by credentials.";
    private static final String MSG_DAO_CREATE = "Create new user.";
    private static final String MSG_DAO_UPSERT = "Upsert user with Id: ";
    private static final String MSG_DAO_DELETE = "Delete user with Id: ";
    private static final String MSG_ERR_NOT_FOUND = "User not found.";
    private static final String MSG_ERR_DUPLICATE_KEY = "Email address or username already used.";
    private static final String MSG_ERR_NOT_VALID_ID = "Not a valid Id.";
    private static final String MSG_ERR_NOT_VALID_COMPANY_ID = "Not a valid company Id.";
    private static final String MSG_ERR_NOT_VALID_DEPARTMENT_ID = "Not a valid department Id.";
    private static final String MSG_ERR_NOT_VALID_OBJ = "User object not specified.";
    private static final String MSG_WARN_NO_COMPANY = "No company specified.";
    private static final String MSG_WARN_NO_DEPARTMENT = "No department specified.";

    // MongoDB objects
    private DBCollection usersCollection;
    private DBCursor cursor;

    /**
     * Retrieves the list of users.
     *
     * @return the list of users.
     */
    public List<User> getUsersList() {
        log.debug(MSG_DAO_GET_LIST);

        List<User> usersList = new ArrayList<>();

        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        cursor = usersCollection.find();

        try {
            while (cursor.hasNext()) {
                User u = (User) cursor.next();
                usersList.add(u);
            }
        } finally {
            cursor.close();
        }

        return usersList;
    }

    /**
     * Retrieves the selected user.
     *
     * @param id the Id of the selected user.
     * @return the selected user object.
     */
    public User getUser(String id) {
        log.debug(MSG_DAO_GET + id + " .");

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        User user = (User) usersCollection.findOne(query);

        if (user == null) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(user);
            log.debug("Requested user: " + jsonMsg); // print res in json format
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return user;
    }

    /**
     * Checks if a user with the specified credentials exists.
     *
     * @param username the username of the user to be searched.
     * @param password the password of the user to be searched.
     * @return the user with the specified credentials.
     */
    public User getUserByCredentials(String username, String password) {
        log.debug(MSG_DAO_GET_BY_CREDENTIALS);

        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        BasicDBObject query = new BasicDBObject();
        query.put("username", username);
        User user = (User) usersCollection.findOne(query);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new user
     *
     * @param user the new user to be saved.
     * @return the Id of the new user.
     */
    public String createUser(User user) {
        log.debug(MSG_DAO_CREATE);

        if (user == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        // Setup MongoDB objects
        BasicDBObject query = new BasicDBObject();

        // Check company Id
        if (user.getCompany() instanceof ObjectId) { // ObjectId inserted
            ObjectId company_id = user.getCompanyId();
            log.debug("Inserted company id: " + company_id.toHexString());

            // Setup query object
            query.put("_id", company_id);
            // Execute the query and associate the final companyId
            user.setCompanyId(new ObjectId(retrieveCompany(query).getId()));
        } else if (user.getCompany() instanceof String) { // if a string has been inserted
            String company_id = user.getCompany().toString();

            if (company_id != null && company_id.isEmpty()) {
                log.debug(MSG_WARN_NO_COMPANY); // allow creation of users without company
            } else {
                // Check if the inserted Id is valid
                if (!ObjectId.isValid(company_id)) {
                    log.error(MSG_ERR_NOT_VALID_COMPANY_ID);
                    throw new BadRequestException(MSG_ERR_NOT_VALID_COMPANY_ID);
                }
                log.debug("Inserted company id: " + company_id);

                // Setup query object
                query.put("_id", new ObjectId(company_id));
                // Execute the query and associate the final companyId
                user.setCompanyId(new ObjectId(retrieveCompany(query).getId()));
            }
        } else {
            log.error(MSG_ERR_NOT_VALID_COMPANY_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_COMPANY_ID);
        }

        // Check department id
        if (user.getDepartment() instanceof ObjectId) {
            ObjectId department_id = user.getDepartmentId();

            log.debug("Inserted department id: " + department_id.toHexString());

            // Setup query object
            query.put("_id", department_id);
            // Execute the query and associate the final departmentId
            user.setDepartmentId(new ObjectId(retrieveDepartment(query).getId()));
        } else if (user.getDepartment() instanceof String) { // if a string has been inserted
            String department_id = user.getDepartment().toString();

            if (department_id != null && department_id.isEmpty()) { // check if empty string
                log.debug(MSG_WARN_NO_DEPARTMENT); // allow creation of users without department
            } else {
                if (!ObjectId.isValid(department_id)) {
                    log.error(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
                    throw new BadRequestException(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
                }

                log.debug("Inserted department id: " + department_id);

                // Setup query object
                query.put("_id", new ObjectId(department_id));
                // Execute the query and associate the final departmentId
                user.setDepartmentId(new ObjectId(retrieveDepartment(query).getId()));
            }
        } else {
            log.error(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
        }

        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        try {
            usersCollection.insert(user);
        } catch (DuplicateKeyException e) {
            log.error(MSG_ERR_DUPLICATE_KEY, e);
            throw new BadRequestException(MSG_ERR_DUPLICATE_KEY);
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(user);
            log.debug("New user: " + jsonMsg); // print res in json format
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return user.getId();
    }

    /**
     * Update the selected user if exists, otherwise create a new one.
     *
     * @param id   the Id of the selected user to be updated.
     * @param user the user object with the modifications (or the new user to be saved).
     * @return the updated user object.
     */
    public User upsertUser(String id, User user) {
        log.debug(MSG_DAO_UPSERT + id + ".");

        // Intercept the possibility to change the Id
        if (user.containsField("_id")) {
            user.removeField("_id");
        }

        // Check if the user object is sent
        if (user == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        // Setup MongoDB query
        BasicDBObject query = new BasicDBObject();

        // Check if the inserted company Id is valid
        if (user.getCompany() instanceof ObjectId) { // already an ObjectId
            ObjectId company_id = user.getCompanyId();

            log.debug("Inserted company id: " + company_id.toHexString());

            // Setup query object
            query.put("_id", company_id);
            // Execute the query and associate the final companyId
            user.setCompanyId(new ObjectId(retrieveCompany(query).getId()));
        } else if (user.getCompany() instanceof String) { // stored as a String
            String company_id = user.getCompany().toString();

            if (company_id != null && company_id.isEmpty()) { // empty string
                log.debug(MSG_WARN_NO_COMPANY); // ok but warning
            } else {
                // Check if the inserted Id is valid
                if (!ObjectId.isValid(id)) {
                    log.error(MSG_ERR_NOT_VALID_ID);
                    throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
                }

                log.debug("Inserted company id: " + company_id);

                // Setup query object
                query.put("_id", new ObjectId(company_id));
                // Execute the query and associate the final companyId
                user.setCompanyId(new ObjectId(retrieveCompany(query).getId()));
            }
        } else {
            log.error(MSG_ERR_NOT_VALID_COMPANY_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_COMPANY_ID);
        }

        // Check if the inserted department Id is valid
        if (user.getDepartment() instanceof ObjectId) { // already an ObjectId
            ObjectId department_id = user.getDepartmentId();

            log.debug("Inserted department id: " + department_id.toHexString());

            // Setup query object
            query.put("_id", department_id);
            // Execute the query and associate the final departmentId
            user.setDepartmentId(new ObjectId(retrieveDepartment(query).getId()));
        } else if (user.getDepartment() instanceof String) { // stored as a string
            String department_id = user.getDepartment().toString();

            if (department_id != null && department_id.isEmpty()) { // empty string
                log.debug(MSG_WARN_NO_DEPARTMENT); // ok but warn
            } else {
                if (!ObjectId.isValid(department_id)) {
                    log.error(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
                    throw new BadRequestException(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
                }

                log.debug("Inserted department id: " + department_id);

                // Setup query object
                query.put("_id", new ObjectId(department_id));
                // Execute the query and associate the final departmentId
                user.setDepartmentId(new ObjectId(retrieveDepartment(query).getId()));
            }
        } else {
            log.error(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
        }

        // Perform the upsert
        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        query.put("_id", new ObjectId(id));
        User storedUser = (User) usersCollection.findOne(query);
        // Force to maintain the same role if not admin - to change normal user to admin, a new user has to be created
        if (!storedUser.getRole().equals("admin")) {
            user.setRole(storedUser.getRole());
        }
        WriteResult wRes = usersCollection.update(query, user, true, false); // selection criteria, modifications to apply, upsert, multi-document update

        String numUpdates;
        String jsonMsg;

        try {
            numUpdates = String.valueOf(wRes.getN());
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(user);
            log.debug(numUpdates + " user updated: " + jsonMsg); // print res in json format
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return user;
    }

    /**
     * Remove the selected user.
     *
     * @param id the Id of the selected user.
     */
    public void deleteUser(String id) {
        log.debug(MSG_DAO_DELETE + id + ".");

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = usersCollection.remove(query);

        if (wRes.getN() == 0) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Use the passed query to retrieve the selected Company from the companies collection.
     *
     * @param query the BasicDBObject containing the query to be executed.
     * @return the selected Company, null if not found.
     */
    private Company retrieveCompany(BasicDBObject query) {
        // Setup MongoDB objects
        DBCollection companyCollection = INSTANCE.getDatasource().
                getDbCollection(COMPANIES_COLLECTION_NAME);
        companyCollection.setObjectClass(Company.class);

        // Execute the query
        Company resCompany = (Company) companyCollection.findOne(query);

        if (resCompany == null) { // selected company doesn't exist
            log.error(MSG_ERR_NOT_VALID_COMPANY_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_COMPANY_ID);
        } else {
            String debugMsg;

            try {
                debugMsg = INSTANCE.getObjectMapper().writeValueAsString(resCompany);
                log.debug("Related company: " + debugMsg); // print res in json format
            } catch (JsonProcessingException e) {
                log.error(e, e);
            }
        }

        return resCompany;
    }

    /**
     * Use the passed query to retrieve the selected Department from the departments collection.
     *
     * @param query the BasicDBObject containing the query to be executed.
     * @return the selected Department, null if not found.
     */
    private Department retrieveDepartment(BasicDBObject query) {
        // Initialize MongoDB objects
        DBCollection departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        departmentsCollection.setObjectClass(Department.class);

        // Execute the query
        Department resDepartment = (Department) departmentsCollection.findOne(query);

        if (resDepartment == null) { // selected department doesn't exist
            log.error(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_DEPARTMENT_ID);
        } else {
            String debugMsg;

            try {
                debugMsg = INSTANCE.getObjectMapper().writeValueAsString(resDepartment);
                log.debug("Related department: " + debugMsg); // print res in json format
            } catch (JsonProcessingException e) {
                log.error(e, e);
            }
        }

        return resDepartment;
    }
}

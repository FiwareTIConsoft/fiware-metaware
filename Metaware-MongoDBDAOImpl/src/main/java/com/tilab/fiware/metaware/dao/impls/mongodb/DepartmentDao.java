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
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * DAO implementation for Department domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DepartmentDao {

    // Logger
    private static final Logger log = Logger.getLogger(DepartmentDao.class);

    // Messages
    private static final String MSG_DAO_GET_LIST = "Retrieve departments list.";
    private static final String MSG_DAO_GET = "Retrieve department with Id: ";
    private static final String MSG_DAO_CREATE = "Create new department.";
    private static final String MSG_DAO_UPSERT = "Upsert department with Id: ";
    private static final String MSG_DAO_DELETE = "Delete department with Id: ";
    private static final String MSG_ERR_NOT_FOUND = "Department not found.";
    private static final String MSG_ERR_DUPLICATE_KEY = "Specified Id already used.";
    private static final String MSG_ERR_NOT_VALID_ID = "Not a valid Id.";
    private static final String MSG_ERR_NOT_VALID_COMPANY_ID = "Not a valid company Id.";
    private static final String MSG_ERR_NOT_VALID_OBJ = "Department object not specified.";
    private static final String MSG_WARN_NO_COMPANY = "No company specified.";

    // MongoDB objects
    private DBCollection departmentsCollection;
    private DBCursor cursor;

    /**
     * Retrieves the list of departments.
     *
     * @return the list of departments.
     */
    public List<Department> getDepartmentsList() {
        log.debug(MSG_DAO_GET_LIST);

        List<Department> departmentsList = new ArrayList<>();

        departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        departmentsCollection.setObjectClass(Department.class);
        cursor = departmentsCollection.find();

        try {
            while (cursor.hasNext()) {
                Department d = (Department) cursor.next();
                departmentsList.add(d);
            }
        } finally {
            cursor.close();
        }

        return departmentsList;
    }

    /**
     * Retrieves the selected department.
     *
     * @param id the Id of the selected department.
     * @return the selected department object.
     */
    public Department getDepartment(String id) {
        log.debug(MSG_DAO_GET + id + " .");

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        departmentsCollection.setObjectClass(Department.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        Department department = (Department) departmentsCollection.findOne(query);

        if (department == null) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(department);
            log.debug("Requested department: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return department;
    }

    /**
     * Create a new department.
     *
     * @param department the new department to be saved
     * @return the Id of the new department.
     */
    public String createDepartment(Department department) {
        log.debug(MSG_DAO_CREATE);

        if (department == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        BasicDBObject query = new BasicDBObject();

        if (department.getCompany() instanceof String) {
            String company_id = department.getCompany().toString();

            if (!ObjectId.isValid(company_id)) {
                log.error(MSG_ERR_NOT_VALID_COMPANY_ID);
                throw new BadRequestException(MSG_ERR_NOT_VALID_COMPANY_ID);
            }

            log.debug("Inserted company Id: " + company_id);

            query.put("_id", new ObjectId(company_id));
        } else if (department.getCompany() instanceof ObjectId) {
            ObjectId company_id = department.getCompanyId();

            log.debug("Inserted company Id: " + company_id.toString());

            query.put("_id", company_id);
        } else {
            log.error(MSG_ERR_NOT_VALID_COMPANY_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_COMPANY_ID);
        }

        DBCollection companyCollection = INSTANCE.getDatasource().
                getDbCollection(COMPANIES_COLLECTION_NAME);
        companyCollection.setObjectClass(Company.class);

        // Check if the company exists
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

        department.setCompanyId(new ObjectId(resCompany.getId()));

        departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        departmentsCollection.setObjectClass(Department.class);
        try {
            departmentsCollection.insert(department);
        } catch (DuplicateKeyException e) {
            log.error(MSG_ERR_DUPLICATE_KEY, e);
            throw new BadRequestException(MSG_ERR_DUPLICATE_KEY);
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(department);
            log.debug("New department: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return department.getId();
    }

    /**
     * Update the selected department if exists, otherwise create a new one.
     *
     * @param id         the Id of the selected department to be updated.
     * @param department the department object with the modifications (or the department to be
     *                   saved).
     * @return the updated department object.
     */
    public Department upsertDepartment(String id, Department department) {
        log.debug(MSG_DAO_UPSERT + id + ".");

        // Intercept the possibility to change the Id
        if (department.containsField("_id")) {
            department.removeField("_id");
        }

        // Check if a department object is sent
        if (department == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        // Setup MongoDB objects
        DBCollection companyCollection = INSTANCE.getDatasource().
                getDbCollection(COMPANIES_COLLECTION_NAME);
        companyCollection.setObjectClass(Company.class);
        BasicDBObject query = new BasicDBObject();

        // Check if the inserted company Id is valid
        if (department.getCompany() instanceof ObjectId) { // type is already ObjectId
            ObjectId company_id = department.getCompanyId();

            log.debug("Inserted company Id: " + company_id.toString());

            query.put("_id", company_id); // setup query object
        } else if (department.getCompany() instanceof String) { // type is String
            String company_id = department.getCompany().toString();

            // Check if no Id is specified (empty string)
            if (company_id != null && company_id.isEmpty()) {
                log.error(MSG_ERR_NOT_VALID_COMPANY_ID);
                throw new BadRequestException(MSG_ERR_NOT_VALID_COMPANY_ID);
            } else {
                if (!ObjectId.isValid(company_id)) { // check if Id string is valid
                    log.error(MSG_ERR_NOT_VALID_COMPANY_ID);
                    throw new BadRequestException(MSG_ERR_NOT_VALID_COMPANY_ID);
                }

                log.debug("Inserted company Id: " + company_id);

                query.put("_id", new ObjectId(company_id)); // setup query object
            }
        } else {
            log.error(MSG_ERR_NOT_VALID_COMPANY_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_COMPANY_ID);
        }

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

        // Associate companyId to department
        department.setCompanyId(new ObjectId(resCompany.getId()));

        departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        departmentsCollection.setObjectClass(Department.class);

        query.put("_id", new ObjectId(id)); // associate the Id
        WriteResult wRes = departmentsCollection.update(query, department, true, false); // upsert

        String numUpdates;
        String jsonMsg;

        try {
            numUpdates = String.valueOf(wRes.getN());
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(department);
            log.debug(numUpdates + " department updated: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return department;
    }

    /**
     * Remove the selected department.
     *
     * @param id the Id of the selected department.
     */
    public void deleteDepartment(String id) {
        log.debug(MSG_DAO_DELETE + id + ".");

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = departmentsCollection.remove(query);

        if (wRes.getN() == 0) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }
    }
}

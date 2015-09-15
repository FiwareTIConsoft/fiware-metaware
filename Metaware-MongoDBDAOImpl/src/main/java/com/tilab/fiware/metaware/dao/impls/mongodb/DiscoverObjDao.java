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

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DEPARTMENTS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.COMPANIES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.ALGORITHMS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DATASETS_COLLECTION_NAME;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Algorithm;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Dataset;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * DAO implementation for Object Discover functionality
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DiscoverObjDao {

    // Logger
    private static final Logger log = Logger.getLogger(DiscoverObjDao.class);

    // Messages
    private static final String MSG_DAO_DISCV_USABLE = "Discover metadata objects usable by: ";
    private static final String MSG_DAO_DISCV_OWNED = "Discover metadata objects owned by: ";
    private static final String MSG_ERR_NOT_VALID_ID = "Not a valid id.";
    private static final String MSG_DBG_IS_USER
            = "Search for a user (consider also department and company).";
    private static final String MSG_DBG_IS_DEPARTMENT
            = "Search for a department (consider also company).";
    private static final String MSG_DBG_IS_COMPANY = "Search for a company.";

    // MongoDB objects
    private DBCollection algorithmsCollection;
    private DBCollection datasetsCollection;
    //...
    private DBCursor cursorDataset;
    private DBCursor cursorAlgorithm;

    /**
     * Discover the objects that can be used.
     *
     * The algorithm is the following: first check that requestorId is either a user, a department,
     * or a company, then build the query by following this assumptions: 1) if requestedId is user,
     * then extract from the user the department and the company; so build a query that includes
     * also objects usable by this department and this company. 2) if requestedId is department,
     * then extract from the department the company and so build a query that includes also object
     * usable by this company. 3) if requestedId is a company, than maintain the basic query with
     * this company.
     *
     * The query is built in this way: find the objects in which the field "permissions" contains an
     * object that has the field "referenceId" that is equal at least to one element from the array
     * of Ids.
     *
     * @param requestedId the Id of the user, department, or company that can use the objects.
     * @return the result of the query.
     */
    public List discoverUsable(String requestedId) {
        log.debug(MSG_DAO_DISCV_USABLE + requestedId);

        if (!ObjectId.isValid(requestedId)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        User reqUser;
        DBCollection usersCollection;
        Department reqDepartment;
        DBCollection departmentsCollection;
        Company reqCompany;
        DBCollection companiesCollection;

        // Query for user, department, or company entities
        BasicDBObject query;

        // Query for objects (algorithms, datasets), containts field "status" specification ("public")
        List<BasicDBObject> queryObjects = new ArrayList<>();
        queryObjects.add(new BasicDBObject("status", "public")); // include public objects

        query = new BasicDBObject();
        query.put("_id", new ObjectId(requestedId));

        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        reqUser = (User) usersCollection.findOne(query);
        if (reqUser != null) {
            // requestedId represents a User
            log.debug(MSG_DBG_IS_USER);
            List<ObjectId> iDs = new ArrayList<>();
            iDs.add(new ObjectId(reqUser.getId())); // search for user
            iDs.add(reqUser.getDepartmentId()); // search for user's department
            iDs.add(reqUser.getCompanyId()); // search for user's company
            // Build the query
            BasicDBObject innerQuery = new BasicDBObject("permissions",
                    new BasicDBObject("$elemMatch",
                            new BasicDBObject("referenceId",
                                    new BasicDBObject("$in", iDs))));
            // ...meaning: find in feald "permissions" if there is an object that has the field
            // "referenceId" that contains at least one element from the array "iDs"
            queryObjects.add(innerQuery); // include the inner query in objects query
        } else {
            departmentsCollection = INSTANCE.getDatasource().getDbCollection(DEPARTMENTS_COLLECTION_NAME);
            departmentsCollection.setObjectClass(Department.class);
            reqDepartment = (Department) departmentsCollection.findOne(query);
            if (reqDepartment != null) {
                // requestedId represents a Department
                log.debug(MSG_DBG_IS_DEPARTMENT);
                List<ObjectId> iDs = new ArrayList();
                iDs.add(new ObjectId(reqDepartment.getId())); // search for department
                iDs.add(reqDepartment.getCompanyId()); // search for department's company
                // Build the query
                BasicDBObject innerQuery = new BasicDBObject("permissions",
                        new BasicDBObject("$elemMatch",
                                new BasicDBObject("referenceId",
                                        new BasicDBObject("$in", iDs))));
                queryObjects.add(innerQuery); // include the inner query in objects query
            } else {
                companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
                companiesCollection.setObjectClass(Company.class);
                reqCompany = (Company) companiesCollection.findOne(query);
                if (reqCompany != null) {
                    // requestedId represents a Company
                    log.debug(MSG_DBG_IS_COMPANY);
                    // Build the query
                    BasicDBObject innerQuery = new BasicDBObject("permissions",
                            new BasicDBObject("$elemMatch",
                                    new BasicDBObject("referenceId", new ObjectId(requestedId))));  // search for company
                    queryObjects.add(innerQuery); // include the inner query in objects query
                } else {
                    // unrecognized Id
                    log.error(MSG_ERR_NOT_VALID_ID);
                    //throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
                    // unrecognized Id - return only public objects
                }
            }
        }

        log.debug("Final query: " + queryObjects.toString());

        return makeQuery(queryObjects);
    }

    /**
     * Discover the objects that are owned.
     *
     * @param ownerId the Id of the owner (user, department, or company that owns the objects).
     * @return the result of the query.
     */
    public List discoverOwned(String ownerId) {
        log.debug(MSG_DAO_DISCV_OWNED + ownerId);

        if (!ObjectId.isValid(ownerId)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        // TODO: discuss about the algorithm to use in this method.
        List<BasicDBObject> query = new ArrayList<>();
        query.add(new BasicDBObject("owner", new ObjectId(ownerId)));

        return makeQuery(query);
    }

    /**
     * This method performs the real query on the DB and collects the result.
     *
     * @param query the query to be executed.
     * @return the result of the query as a List.
     */
    private List makeQuery(List<BasicDBObject> query) {
        List result = new ArrayList();

        // Find from Algorithm
        algorithmsCollection = INSTANCE.getDatasource().getDbCollection(ALGORITHMS_COLLECTION_NAME);
        algorithmsCollection.setObjectClass(Algorithm.class);
        cursorAlgorithm = algorithmsCollection.find(new BasicDBObject("$or", query)); // or condition for referenceId and public status

        try {
            while (cursorAlgorithm.hasNext()) {
                Algorithm a = (Algorithm) cursorAlgorithm.next();
                result.add(a); // insert in result list
            }
        } finally {
            cursorAlgorithm.close();
        }

        // Find from Dataset
        datasetsCollection = INSTANCE.getDatasource().getDbCollection(DATASETS_COLLECTION_NAME);
        datasetsCollection.setObjectClass(Dataset.class);
        cursorDataset = datasetsCollection.find(new BasicDBObject("$or", query));

        try {
            while (cursorDataset.hasNext()) {
                Dataset d = (Dataset) cursorDataset.next();
                result.add(d); // insert in result list
            }
        } finally {
            cursorDataset.close();
        }

        return result;
    }

}

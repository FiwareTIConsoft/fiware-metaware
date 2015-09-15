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
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * DAO implementation for Company domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class CompanyDao {

    // Logger
    private static final Logger log = Logger.getLogger(CompanyDao.class);

    // Messages
    private static final String MSG_DAO_GET_LIST = "Retrieve companies list.";
    private static final String MSG_DAO_GET = "Retrieve company with Id: ";
    private static final String MSG_DAO_CREATE = "Create new company.";
    private static final String MSG_DAO_UPSERT = "Upsert company with Id: ";
    private static final String MSG_DAO_DELETE = "Delete company with Id: ";
    private static final String MSG_ERR_NOT_FOUND = "Company not found.";
    private static final String MSG_ERR_DUPLICATE_KEY = "Specified Id already used.";
    private static final String MSG_ERR_NOT_VALID_ID = "Not a valid Id.";
    private static final String MSG_ERR_NOT_VALID_OBJ = "Company object not specified.";

    // MongoDB objects
    private DBCollection companiesCollection;
    private DBCursor cursor;

    /**
     * Retrieves the list of companies.
     *
     * @return the list of companies.
     */
    public List<Company> getCompaniesList() {
        log.debug(MSG_DAO_GET_LIST);

        List<Company> companiesList = new ArrayList<>();

        companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        companiesCollection.setObjectClass(Company.class);
        cursor = companiesCollection.find();

        try {
            while (cursor.hasNext()) {
                Company c = (Company) cursor.next();
                companiesList.add(c);
            }
        } finally {
            cursor.close();
        }

        return companiesList;
    }

    /**
     * Retrieves the selected company.
     *
     * @param id the Id of the selected company.
     * @return the selected company object.
     */
    public Company getCompany(String id) {
        log.debug(MSG_DAO_GET + id + " .");

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        companiesCollection.setObjectClass(Company.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        Company company = (Company) companiesCollection.findOne(query);

        if (company == null) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(company);
            log.debug("Requested company: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return company;
    }

    /**
     * Creates a new company.
     *
     * @param company the new company to be saved.
     * @return the Id of the new company.
     */
    public String createCompany(Company company) {
        log.debug(MSG_DAO_CREATE);

        if (company == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        companiesCollection.setObjectClass(Company.class);
        try {
            companiesCollection.insert(company);
        } catch (DuplicateKeyException e) {
            log.error(MSG_ERR_DUPLICATE_KEY, e);
            throw new BadRequestException(MSG_ERR_DUPLICATE_KEY);
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(company);
            log.debug("New company: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return company.getId();
    }

    /**
     * Update the selected company if exists, otherwise create a new one.
     *
     * @param id      the Id of the selected company to be updated.
     * @param company the company object with the modifications (or the company to be saved).
     * @return the updated company object.
     */
    public Company upsertCompany(String id, Company company) {
        log.debug(MSG_DAO_UPSERT + id + ".");

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }
        if (company == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        // Intercept the possibility to change the Id
        if (company.containsField("_id")) {
            company.removeField("_id");
        }

        companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        companiesCollection.setObjectClass(Company.class);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = companiesCollection.update(query, company, true, false); // selection criteria, modifications to apply, upsert, multi-document update

        String jsonMsg;
        String numUpdates;

        try {
            numUpdates = String.valueOf(wRes.getN());
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(company);
            log.debug(numUpdates + " company updated: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return company;
    }

    /**
     * Remove the selected company.
     *
     * @param id the Id of the selected company.
     */
    public void deleteCompany(String id) {
        log.debug(MSG_DAO_DELETE + id + ".");

        if (!ObjectId.isValid(id)) {
            log.error(MSG_ERR_NOT_VALID_ID);
            throw new BadRequestException(MSG_ERR_NOT_VALID_ID);
        }

        companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        WriteResult wRes = companiesCollection.remove(query);

        if (wRes.getN() == 0) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }
    }
}

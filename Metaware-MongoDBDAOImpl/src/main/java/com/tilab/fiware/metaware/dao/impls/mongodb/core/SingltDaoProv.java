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
package com.tilab.fiware.metaware.dao.impls.mongodb.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tilab.fiware.metaware.dao.exception.InternalErrorException;
import com.tilab.fiware.metaware.dao.impls.mongodb.AlgorithmDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.CompanyDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.DatasetDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.DepartmentDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.DiscoverObjDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.TemplateDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.UserDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.DataSourceDao;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Singleton class for MongoDB DAO implementation.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public enum SingltDaoProv {

    INSTANCE();

    private static final Logger log = Logger.getLogger(SingltDaoProv.class);

    // Application properties
    private Properties props = null;

    // Jackson object mapper
    private CustomObjectMapper objectMapper = null;

    // Datasource
    private DataSourceProvider datasource = null;

    // DAO
    private AlgorithmDao algorithmDao = null;
    private CompanyDao companyDao = null;
    private DatasetDao datasetDao = null;
    private DataSourceDao dataSourceDao = null;
    private DepartmentDao departmentDao = null;
    private DiscoverObjDao discoverObjDao = null;
    private TemplateDao templateDao = null;
    private UserDao userDao = null;

    // Messages
    private static final String MSG_DAO_OBJECTS_CREATION_START = "Creating DAO objects...";
    private static final String MSG_DAO_OBJECTS_CREATION_OK = "DAO objects successfully created.";
    private static final String MSG_DAO_OBJECT_CREATION_FAILED
            = "Error during DAO objects creation.";

    /**
     * Initializes DAO objects and create the DB connection.
     *
     * @param props needed properties.
     */
    public void createDaoObjects(Properties props) {
        log.info(MSG_DAO_OBJECTS_CREATION_START);

        try {
            // Jackson object mapper
            objectMapper = new CustomObjectMapper();

            // Allow null fields in JSON serialization
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

            this.props = props;

            // Datasource
            createDatasource();

            // Intantiation of specific implementing classes
            algorithmDao = new AlgorithmDao();
            companyDao = new CompanyDao();
            datasetDao = new DatasetDao();
            dataSourceDao = new DataSourceDao();
            departmentDao = new DepartmentDao();
            discoverObjDao = new DiscoverObjDao();
            templateDao = new TemplateDao();
            userDao = new UserDao();

            log.info(MSG_DAO_OBJECTS_CREATION_OK);
        } catch (Exception e) {
            log.error(MSG_DAO_OBJECT_CREATION_FAILED, e);
            throw new InternalErrorException(MSG_DAO_OBJECT_CREATION_FAILED);
        }
    }

    /**
     * Remove all DAO objects and close the connection to DB.
     */
    public void closeDaoObjects() {
        datasource.closeDBObjects(); // just close the DB connection
    }

    /**
     * Creates the connection with the DB.
     *
     * @throws UnknownHostException
     */
    private void createDatasource() throws UnknownHostException {
        datasource = null;
        datasource = new DataSourceProvider(props);
    }

    /**
     * Gets the datasource.
     *
     * @return datasource
     */
    public DataSourceProvider getDatasource() {
        return datasource;
    }

    /**
     * Gets the specified string property.
     *
     * @param propertyName the name of the selected property (key string).
     * @return the selected property as a string.
     */
    public String getStringProperty(String propertyName) {
        return props.getProperty(propertyName);
    }

    /**
     * Gets the specified string property.
     *
     * @param propertyName the name of the selected property (key string).
     * @return the selected property as an integer.
     * @throws NumberFormatException
     */
    public int getIntProperty(String propertyName) throws NumberFormatException {
        return Integer.parseInt(props.getProperty(propertyName));
    }

    /**
     * Gets the object mapper.
     *
     * @return objectMapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     *
     * @return
     */
    public AlgorithmDao getAlgorithmDao() {
        return algorithmDao;
    }

    /**
     * Gets the DAO of the Company.
     *
     * @return companyDao
     */
    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    /**
     * Gets the DAO of the Dataset.
     *
     * @return datasetDao
     */
    public DatasetDao getDatasetDao() {
        return datasetDao;
    }

    /**
     * Gets the DAO of the DataSource.
     *
     * @return dataSourceDao.
     */
    public DataSourceDao getDataSourceDao() {
        return dataSourceDao;
    }

    /**
     * Gets the DAO of the Department.
     *
     * @return departmentDao
     */
    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    /**
     * Gets the DAO of the Template.
     *
     * @return templateDao
     */
    public TemplateDao getTemplateDao() {
        return templateDao;
    }

    /**
     * Gets the DAO of the User.
     *
     * @return userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Gets the DAO of the DiscoverObj
     *
     * @return
     */
    public DiscoverObjDao getDiscoverObjDao() {
        return discoverObjDao;
    }

}

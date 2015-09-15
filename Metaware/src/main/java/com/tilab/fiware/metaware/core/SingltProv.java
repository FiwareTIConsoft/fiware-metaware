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
package com.tilab.fiware.metaware.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tilab.fiware.metaware.dao.exception.InternalErrorException;
import com.tilab.fiware.metaware.dao.impls.mongodb.core.CustomObjectMapper;
import com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv;
import com.tilab.fiware.metaware.service.AlgorithmService;
import com.tilab.fiware.metaware.service.CompanyService;
import com.tilab.fiware.metaware.service.DataSourceService;
import com.tilab.fiware.metaware.service.DatasetService;
import com.tilab.fiware.metaware.service.DepartmentService;
import com.tilab.fiware.metaware.service.DiscoverObjService;
import com.tilab.fiware.metaware.service.ProcessService;
import com.tilab.fiware.metaware.service.TemplateService;
import com.tilab.fiware.metaware.service.UserService;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Singleton class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public enum SingltProv {

    INSTANCE;

    // Logger
    private final Logger log = Logger.getLogger(SingltProv.class.getName());

    // Application properties
    private MetawareProp mwProp = null;
    private Properties customProp = null;

    // Jackson object mapper
    private CustomObjectMapper objectMapper = null;

    // Service classes
    private AlgorithmService algorithmService = null;
    private CompanyService companyService = null;
    private DatasetService datasetService = null;
    private DataSourceService datasourceService = null;
    private DepartmentService departmentService = null;
    private DiscoverObjService discoverObjService = null;
    private TemplateService templateService = null;
    private UserService userService = null;
    private ProcessService processService = null;
    //...

    // Messages
    private static final String MSG_CORE_OBJECTS_CREATION_START = "Creating core objects...";
    private static final String MSG_CORE_OBJECTS_CREATION_OK = "Core objects successfully created.";
    private static final String MSG_CORE_OBJECTS_CREATION_ERROR
            = "Error during core objects creation.";
    private static final String MSG_CONNECTING_TO_DB = "DB connection...";
    private static final String MSG_SUCCESSFULLY_DB_CONNECTION = "DB connected.";
    private static final String MSG_DISCONNECTING_FROM_DB = "DB disconnection...";
    private static final String MSG_SUCCESSFULLY_DISCONNECTED_FROM_DB = "DB disconnected.";

    SingltProv() {
    }

    /**
     * Initializes the core objects.
     */
    public void createCoreObjects() {
        log.info(MSG_CORE_OBJECTS_CREATION_START);

        try {
            // Properties file loading and Properties object initialization
            mwProp = new MetawareProp();

            // Jackson object mapper
            objectMapper = new CustomObjectMapper();

            // Allow null fields in JSON serialization
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

            // DB datasource creation (DB connection)
            log.info(MSG_CONNECTING_TO_DB);
            if (customProp == null) {
                SingltDaoProv.INSTANCE.createDaoObjects(mwProp.getDsProperties());
            } else {
                SingltDaoProv.INSTANCE.createDaoObjects(customProp);
            }
            log.info(MSG_SUCCESSFULLY_DB_CONNECTION);

            // Services
            algorithmService = new AlgorithmService();
            companyService = new CompanyService();
            datasetService = new DatasetService();
            datasourceService = new DataSourceService();
            departmentService = new DepartmentService();
            discoverObjService = new DiscoverObjService();
            templateService = new TemplateService();
            userService = new UserService();
            processService = new ProcessService();

            log.info(MSG_CORE_OBJECTS_CREATION_OK);
        } catch (Exception e) {
            log.error(MSG_CORE_OBJECTS_CREATION_ERROR, e);
            throw new InternalErrorException(MSG_CORE_OBJECTS_CREATION_ERROR);
        }
    }

    /**
     * Detaches the core objects.
     */
    public void closeCoreObjects() {
        log.info(MSG_DISCONNECTING_FROM_DB);
        SingltDaoProv.INSTANCE.closeDaoObjects();
        log.info(MSG_SUCCESSFULLY_DISCONNECTED_FROM_DB);
    }

    public void setManualProperties(Properties customProp) {
        this.customProp = customProp;
    }

    /**
     * Returns the value of the passed property name as a String.
     *
     * @param propertyName The name of the selected property
     * @return The String of the selected property
     */
    public String getStringProperty(String propertyName) {
        return mwProp.getStringProperty(propertyName);
    }

    /**
     * Returns the value of the passed property name as an integer.
     *
     * @param propertyName The name of the selected property
     * @return The selected property as an integer.
     * @throws NumberFormatException
     */
    public int getIntProperty(String propertyName) throws NumberFormatException {
        return mwProp.getIntProperty(propertyName);
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
     * Gets the service class for Algorithm
     *
     * @return algorithmService
     */
    public AlgorithmService getAlgorithmService() {
        return algorithmService;
    }

    /**
     * Gets the service class for Company.
     *
     * @return companyService
     */
    public CompanyService getCompanyService() {
        return companyService;
    }

    /**
     * Gets the service class for Dataset.
     *
     * @return datasetService
     */
    public DatasetService getDatasetService() {
        return datasetService;
    }

    /**
     * Gets the service class for DataSource.
     *
     * @return datasourceService
     */
    public DataSourceService getDataSourceService() {
        return datasourceService;
    }

    /**
     * Gets the service class for Department.
     *
     * @return departmentService
     */
    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    /**
     * Gets the service class for DiscoveryObjService.
     *
     * @return discoverObjService
     */
    public DiscoverObjService getDiscoverObjService() {
        return discoverObjService;
    }

    /**
     * Gets the service class for Template.
     *
     * @return templateService
     */
    public TemplateService getTemplateService() {
        return templateService;
    }

    /**
     * Gets the service class for User.
     *
     * @return userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets the service class for Process.
     *
     * @return processService
     */
    public ProcessService getProcessService() {
        return processService;
    }
}

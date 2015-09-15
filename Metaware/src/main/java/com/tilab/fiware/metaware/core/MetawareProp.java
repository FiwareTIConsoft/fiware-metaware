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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * This class handles the prototypeWorker.properties file.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class MetawareProp {

    private static final Logger log = Logger.getLogger(MetawareProp.class);

    private Properties prop;
    private Properties dsProps = null;

    // Property keys
    public static final String PROPERTIES_FILE_NAME = "metaware.properties";
    public static final String KEY_DB_HOST = "db.host";
    public static final String KEY_DB_PORT = "db.port";
    public static final String KEY_DB_NAME = "db.name";
    public static final String KEY_DB_USERNAME = "db.username";
    public static final String KEY_DB_PASSWORD = "db.password";
    //...

    // Messages
    private static final String MSG_START_PROP_LOAD = "Loading Metaware properties...";
    private static final String MSG_END_PROP_LOAD = "Metaware properties successfully loaded.";

    public MetawareProp() {
        log.info(MSG_START_PROP_LOAD);

        try {
            prop = new Properties();
            InputStream in = getClass().getResourceAsStream("/" + PROPERTIES_FILE_NAME);
            prop.load(in);

            setDatasourceProps();
            log.info(MSG_END_PROP_LOAD);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Includes the properties for the datasource.
     */
    private void setDatasourceProps() {
        dsProps = new Properties();
        dsProps.put(KEY_DB_HOST, this.getStringProperty(KEY_DB_HOST));
        dsProps.put(KEY_DB_PORT, this.getStringProperty(KEY_DB_PORT));
        dsProps.put(KEY_DB_NAME, this.getStringProperty(KEY_DB_NAME));
        dsProps.put(KEY_DB_USERNAME, this.getStringProperty(KEY_DB_USERNAME));
        dsProps.put(KEY_DB_PASSWORD, this.getStringProperty(KEY_DB_PASSWORD));
        //...
    }

    /**
     * Returns the value of the passed property name as a String.
     *
     * @param propertyName The name of the selected property
     * @return The String containing the selected property
     */
    public String getStringProperty(String propertyName) {
        return prop.getProperty(propertyName);
    }

    /**
     * It returns the value of the passed property name as an integer.
     *
     * @param propertyName The name of the selected property
     * @return The number containing the selected property
     * @throws NumberFormatException
     */
    public int getIntProperty(String propertyName) throws NumberFormatException {
        return Integer.parseInt(prop.getProperty(propertyName));
    }

    /**
     * Returns the properties object.
     *
     * @return dsProps - The properties of the datasources
     */
    public Properties getDsProperties() {
        return dsProps;
    }
}

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
package com.tilab.fiware.metaware.dao.impls.mongodb.core;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
//import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.KEY_DB_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.KEY_DB_HOST;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.KEY_DB_PORT;
//import static com.tilab.fiware.metaware.dao.DaoCommonConstants.KEY_DB_USERNAME;
//import static com.tilab.fiware.metaware.dao.DaoCommonConstants.KEY_DB_PASSWORD;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import com.tilab.fiware.metaware.dao.exception.DBConnectionException;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Datasource methods exposition.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DataSourceProvider {

    // Logger
    private static final Logger log = Logger.getLogger(DataSourceProvider.class);

    // Messages
    private static final String MSG_OPENING_DB_CONNECTION = "Opening DB connection...";
    private static final String MSG_DB_CONNECTION_OPENED = "DB connected.";
    private static final String MSG_ERR_DB_CONNECTION = "DB connection failed.";
    private static final String MSG_CLOSING_DB_CONNECTION = "Closing DB connection...";
    private static final String MSG_DB_CONNECTION_CLOSED = "DB connection closed.";

    private MongoClient mongoClient = null;
    private ServerAddress serverAddress = null;
    //private MongoCredential mongoCredential = null;
    private DB db = null;

    /**
     * Initializes the datasource provider.
     *
     * @param props
     * @throws UnknownHostException
     */
    DataSourceProvider(Properties props) throws UnknownHostException { // opening mongodb connection
        log.info(MSG_OPENING_DB_CONNECTION);

        serverAddress = new ServerAddress(props.getProperty(KEY_DB_HOST),
                Integer.parseInt(props.getProperty(KEY_DB_PORT)));

//        mongoCredential = new MongoCredential.createCredential(KEY_DB_USERNAME, KEY_DB_NAME,
//                KEY_DB_PASSWORD.toCharArray());
//        mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential));
        mongoClient = new MongoClient(serverAddress);
        db = mongoClient.getDB(props.getProperty(KEY_DB_NAME));

        try {
            db.getCollectionNames();
        } catch (MongoException e) {
            log.error(MSG_ERR_DB_CONNECTION, e);
            throw new DBConnectionException();
        }

        log.info(MSG_DB_CONNECTION_OPENED);

        // Specify additional unique fields
        db.getCollection(USERS_COLLECTION_NAME).createIndex(new BasicDBObject("username", 1),
                new BasicDBObject("unique", true));
    }

    /**
     * Closes the connection to the datasource provider.
     */
    protected void closeDBObjects() {
        log.info(MSG_CLOSING_DB_CONNECTION);
        mongoClient.close();
        log.info(MSG_DB_CONNECTION_CLOSED);
    }

    /**
     * Retrieves a connection to MongoDB.
     *
     * @return the db object connection.
     */
    public DB getDbConnection() {
        return db;
    }

    /**
     * Returns the specified DB collection (by name).
     *
     * @param collectionName the name of the selected collection.
     * @return the MongoDB collection.
     */
    public DBCollection getDbCollection(String collectionName) {
        return db.getCollection(collectionName);
    }

}

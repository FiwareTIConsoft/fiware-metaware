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

import com.mongodb.DBCollection;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DATASOURCES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DataSource;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DataSourceDaoTest {

    private static Properties testProperties;

    // MongoDB objects
    private static DBCollection datasourcesCollection;
    private static DBCollection usersCollection;

    // Temporary data
    private static DataSource datas1, datas2;
    private static Permission perm1, perm2, perm3;
    private static User user1, user2;

    // Messages
    private static final String RES_TYPE_TABLE = "table";
    private static final String RES_TYPE_QUERY = "query";

    public DataSourceDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test

        INSTANCE.createDaoObjects(testProperties);

        // Create 2 users
        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        user1 = new User("user test name 1", "user test surname 1", "user1@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(), new ObjectId(), "usernametestalgo1",
                "secret", "");
        user2 = new User("user test name 2", "user test surname 2", "user2@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(), new ObjectId(), "usernametestalgo2",
                "secret", "");

        // Create 2 data sources (moved to setUp to obtain users ids)
        datasourcesCollection = INSTANCE.getDatasource()
                .getDbCollection(DATASOURCES_COLLECTION_NAME);
        datasourcesCollection.setObjectClass(DataSource.class);
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeDaoObjects();
    }

    @Before
    public void setUp() {
        // Insert 2 users
        List users = new ArrayList(); // unspecified type generates maven warning (but necessary for mongodb insert)
        users.add(user1);
        users.add(user2);
        usersCollection.insert(users);

        // Create 3 permissions
        perm1 = new Permission(new ObjectId(user1.getId()), "rud"); // user1 can read, update, and delete
        perm2 = new Permission(new ObjectId(user1.getId()), "r"); // user1 can read
        perm3 = new Permission(new ObjectId(user2.getId()), "rud"); // user2 can read, update, and delete
        List<Permission> permissionsDatas1 = new ArrayList<>();
        permissionsDatas1.add(perm1);
        permissionsDatas1.add(perm3);
        List<Permission> permissionsDatas2 = new ArrayList<>();
        permissionsDatas2.add(perm2);

        // Create 2 data sources
        datas1 = new DataSource("datasource test 1", "this is just a test 1", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, permissionsDatas1, new ObjectId(user1.getId()),
                "test status", "test subtype", "jdbc:mysql://localhost/test/one", "testUsernameOne",
                "superSecretOne", RES_TYPE_QUERY, "SELECT * FROM TEST");
        datas2 = new DataSource("datasource test 2", "this is just a test 2", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, permissionsDatas2, new ObjectId(user2.getId()),
                "test status", "test subtype", "jdbc:mysql://localhost/test/two", "testUsernameTwo",
                "superSecretTwo", RES_TYPE_QUERY, "SELECT * FROM TEST");

        // Insert 2 data sources
        List datasources = new ArrayList();  // unspecified type generates maven warning (but necessary for mongodb insert)
        datasources.add(datas1);
        datasources.add(datas2);
        datasourcesCollection.insert(datasources);
    }

    @After
    public void tearDown() {
        datasourcesCollection.drop();
        usersCollection.drop();
    }

    /**
     * Test of getDataSourcesList method, of class DataSourceDao.
     */
    @Test
    public void testGetDataSourcesList() {
        System.out.println("getDataSourcesList");
        DataSourceDao instance = new DataSourceDao();
        List<DataSource> expResult = new ArrayList<>();
        expResult.add(datas1);
        expResult.add(datas2);
        List<DataSource> result = instance.getDataSourcesList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getDataSource method, of class DataSourceDao.
     */
    @Test
    public void testGetDataSource() {
        System.out.println("getDataSource");
        String id = datas1.getId();
        DataSourceDao instance = new DataSourceDao();
        DataSource expResult = datas1;
        DataSource result = instance.getDataSource(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createDataSource method, of class DataSourceDao.
     */
    @Test
    public void testCreateDataSource() {
        System.out.println("createDataSource");
        List<Permission> permissionsDatas = new ArrayList<>();
        permissionsDatas.add(perm2);
        DataSource datasource = new DataSource("datasource test", "this is just a test",
                "test type", Long.MIN_VALUE, Long.MIN_VALUE, permissionsDatas,
                new ObjectId(user1.getId()), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        DataSourceDao instance = new DataSourceDao();
        String result = instance.createDataSource(datasource);
        assertTrue(ObjectId.isValid(result));
    }

    /**
     * Test of upsertDataSource method, of class DataSourceDao.
     */
    @Test
    public void testUpsertDataSource() {
        System.out.println("upsertDataSource");
        String id = datas1.getId();
        datas1.setDescription("this is a new dedicated test description");
        datas1.setStatus("public");
        DataSourceDao instance = new DataSourceDao();
        DataSource expResult = datas1;
        DataSource result = instance.upsertDataSource(id, datas1);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteDataSource method, of class DataSourceDao.
     */
    @Test
    public void testDeleteDataSource() {
        System.out.println("deleteDataSource");
        String id = datas1.getId();
        DataSourceDao instance = new DataSourceDao();
        instance.deleteDataSource(id);
    }

}

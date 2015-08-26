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
package com.tilab.fiware.metaware.dao.impls.mongodb;

import com.mongodb.DBCollection;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DATASETS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Dataset;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DatasetStructure;
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
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class DatasetDaoTest {

    private static Properties testProperties;

    // MongoDB objects
    private static DBCollection datasetsCollection;
    private static DBCollection usersCollection;

    // Temporary data
    private static Dataset data1, data2;
    private static Permission perm1, perm2, perm3;
    private static User user1, user2;

    public DatasetDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test

        INSTANCE.createDaoObjects(testProperties);

        // Create 3 users
        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        user1 = new User("user test name 1", "user test surname 1", "userr1@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(), new ObjectId(),
                "usernametestdataset1", "secret", "");
        user2 = new User("user test name 2", "user test surname 2", "userr2@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(), new ObjectId(),
                "usernametestdataset2", "secret", "");

        // Create 2 datasets (moved to setUp to obtain ux's Id)
        datasetsCollection = INSTANCE.getDatasource().getDbCollection(DATASETS_COLLECTION_NAME);
        datasetsCollection.setObjectClass(Dataset.class);
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeDaoObjects();
    }

    @Before
    public void setUp() {
        // Insert 2 users
        List users = new ArrayList();
        users.add(user1);
        users.add(user2);
        usersCollection.insert(users);

        // Create 3 permissions
        perm1 = new Permission(new ObjectId(user1.getId()), "rud"); // user1 can read, update, and delete
        perm2 = new Permission(new ObjectId(user1.getId()), "r"); // user1 can read
        perm3 = new Permission(new ObjectId(user2.getId()), "rud"); // user2 can read, update, and delete
        List<Permission> permissionsData1 = new ArrayList<>();
        permissionsData1.add(perm1);
        permissionsData1.add(perm3);
        List<Permission> permissionsData2 = new ArrayList<>();
        permissionsData2.add(perm2);

        // Create 3 datasets
        data1 = new Dataset("dataset test name 1", "dataset test description 1", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, permissionsData1, new ObjectId(user2.getId()),
                "test status", true, new DatasetStructure());
        data2 = new Dataset("dataset test name 2", "dataset test description 2", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, permissionsData2, new ObjectId(user1.getId()),
                "test status", true, new DatasetStructure());

        // Insert 3 datasets
        List datasets = new ArrayList();
        datasets.add(data1);
        datasets.add(data2);
        datasetsCollection.insert(datasets);
    }

    @After
    public void tearDown() {
        datasetsCollection.drop();
        usersCollection.drop();
    }

    /**
     * Test of getDatasetsList method, of class DatasetDao.
     */
    @Test
    public void testGetDatasetsList() {
        System.out.println("getDatasetsList");
        DatasetDao instance = new DatasetDao();
        List<Dataset> expResult = new ArrayList<>();
        expResult.add(data1);
        expResult.add(data2);
        List<Dataset> result = instance.getDatasetsList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getDataset method, of class DatasetDao.
     */
    @Test
    public void testGetDataset() {
        System.out.println("getDataset");
        String id = data1.getId();
        DatasetDao instance = new DatasetDao();
        Dataset expResult = data1;
        Dataset result = instance.getDataset(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createDataset method, of class DatasetDao.
     */
    @Test
    public void testCreateDataset() {
        System.out.println("createDataset");
        List<Permission> permissionsData = new ArrayList<>();
        permissionsData.add(perm2);
        Dataset dataset = new Dataset("dataset test name", "dataset test description", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, permissionsData, new ObjectId(user1.getId()),
                "test status", true, new DatasetStructure());
        DatasetDao instance = new DatasetDao();
        String result = instance.createDataset(dataset);
        assertTrue(ObjectId.isValid(result));
    }

    /**
     * Test of upsertDataset method, of class DatasetDao.
     */
    @Test
    public void testUpsertDataset() {
        System.out.println("upsertDataset");
        String id = data1.getId();
        data1.setName("this is a new name for algorithm");
        data1.setStatus("new status");
        DatasetDao instance = new DatasetDao();
        Dataset expResult = data1;
        Dataset result = instance.upsertDataset(id, data1);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteDataset method, of class DatasetDao.
     */
    @Test
    public void testDeleteDataset() {
        System.out.println("deleteDataset");
        String id = data2.getId();
        DatasetDao instance = new DatasetDao();
        instance.deleteDataset(id);
    }

}

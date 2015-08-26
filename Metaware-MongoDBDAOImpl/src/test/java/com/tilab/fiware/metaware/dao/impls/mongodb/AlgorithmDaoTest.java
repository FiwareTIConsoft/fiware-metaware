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
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.ALGORITHMS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Algorithm;
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
public class AlgorithmDaoTest {

    private static Properties testProperties;

    // MongoDB objects
    private static DBCollection algorithmsCollection;
    private static DBCollection usersCollection;

    // Temporary data
    private static Algorithm algo1, algo2;
    private static Permission perm1, perm2, perm3;
    private static User user1, user2;

    public AlgorithmDaoTest() {
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
                "Via Reiss Romoli, 274 Torino", new ObjectId(), new ObjectId(), "usernametestalgo1", "secret", "");
        user2 = new User("user test name 2", "user test surname 2", "user2@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(), new ObjectId(), "usernametestalgo2", "secret", "");

        // Create 2 algorithms (moved to setUp to obtain users ids)
        algorithmsCollection = INSTANCE.getDatasource().getDbCollection(ALGORITHMS_COLLECTION_NAME);
        algorithmsCollection.setObjectClass(Algorithm.class);
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
        List<Permission> permissionsAlgo1 = new ArrayList<>();
        permissionsAlgo1.add(perm1);
        permissionsAlgo1.add(perm3);
        List<Permission> permissionsAlgo2 = new ArrayList<>();
        permissionsAlgo2.add(perm2);

        // Create 2 algorithms
        algo1 = new Algorithm("algorithm test name 1", "algorithm test description 1", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, permissionsAlgo1, new ObjectId(user1.getId()),
                "status test 1", "model test 1", "sub-model test 1", "hive query test 1",
                Long.MIN_VALUE, 42, "algo.test.one.com");
        algo2 = new Algorithm("algorithm test name 2", "algorithm test description 2", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, permissionsAlgo2, new ObjectId(user2.getId()),
                "status test 2", "model test 2", "sub-model test 2", "hive query test 2",
                Long.MIN_VALUE, 42, "algo.test.two.com");

        // Insert 2algorithms
        List algorithms = new ArrayList(); // unspecified type generates maven warning (but necessary for mongodb insert)
        algorithms.add(algo1);
        algorithms.add(algo2);
        algorithmsCollection.insert(algorithms);
    }

    @After
    public void tearDown() {
        algorithmsCollection.drop();
        usersCollection.drop();
    }

    /**
     * Test of getAlgorithmsList method, of class AlgorithmDao.
     */
    @Test
    public void testGetAlgorithmsList() {
        System.out.println("getAlgorithmsList");
        AlgorithmDao instance = new AlgorithmDao();
        List<Algorithm> expResult = new ArrayList<>();
        expResult.add(algo1);
        expResult.add(algo2);
        List<Algorithm> result = instance.getAlgorithmsList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getAlgorithm method, of class AlgorithmDao.
     */
    @Test
    public void testGetAlgorithm() {
        System.out.println("getAlgorithm");
        String id = algo1.getId();
        AlgorithmDao instance = new AlgorithmDao();
        Algorithm expResult = algo1;
        Algorithm result = instance.getAlgorithm(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createAlgorithm method, of class AlgorithmDao.
     */
    @Test
    public void testCreateAlgorithm() {
        System.out.println("createAlgorithm");
        List<Permission> permissionsAlgo = new ArrayList<>();
        permissionsAlgo.add(perm2);
        Algorithm algorithm = new Algorithm("algorithm test name", "algorithm test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, permissionsAlgo,
                new ObjectId(user1.getId()), "status test", "model test", "sub-model test",
                "hive query test", Long.MIN_VALUE, 42, "algo.test.com");
        AlgorithmDao instance = new AlgorithmDao();
        String result = instance.createAlgorithm(algorithm);
        assertTrue(ObjectId.isValid(result));
    }

    /**
     * Test of upsertAlgorithm method, of class AlgorithmDao.
     */
    @Test
    public void testUpsertAlgorithm() {
        System.out.println("upsertAlgorithm");
        String id = algo1.getId();
        algo1.setDescription("this is a new dedicated description");
        algo1.setModel("an update model is stored here");
        AlgorithmDao instance = new AlgorithmDao();
        Algorithm expResult = algo1;
        Algorithm result = instance.upsertAlgorithm(id, algo1);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteAlgorithm method, of class AlgorithmDao.
     */
    @Test
    public void testDeleteAlgorithm() {
        System.out.println("deleteAlgorithm");
        String id = algo2.getId();
        AlgorithmDao instance = new AlgorithmDao();
        instance.deleteAlgorithm(id);
    }

}

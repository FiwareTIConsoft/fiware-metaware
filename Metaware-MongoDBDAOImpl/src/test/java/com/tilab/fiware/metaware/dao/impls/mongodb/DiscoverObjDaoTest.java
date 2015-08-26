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
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.COMPANIES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DATASETS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DEPARTMENTS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Algorithm;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Dataset;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DatasetStructure;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
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
public class DiscoverObjDaoTest {

    private static Properties testProperties;

    // MongoDB objects
    private static DBCollection usersCollection;
    private static DBCollection departmentsCollection;
    private static DBCollection companiesCollection;
    private static DBCollection algorithmsCollection;
    private static DBCollection datasetsCollection;

    // Temporary data
    private static User user1, user2, user3;
    private static Department dep1, dep2, dep3;
    private static Company comp1, comp2;
    private static Permission perm1, perm2, perm3, perm4, perm5, perm6;
    private static Dataset data1, data2, data3;
    private static Algorithm algo1, algo2, algo3;

    public DiscoverObjDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test

        INSTANCE.createDaoObjects(testProperties);

        // Create 2 companies
        companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        companiesCollection.setObjectClass(Company.class);
        comp1 = new Company("company test name 1", "company test description 1",
                "company1@test.com", "123456", "Via Reiss Romoli, 274 Torino", "company.one.com");
        comp2 = new Company("company test name 2", "company test description 2",
                "company2@test.com", "123456", "Via Reiss Romoli, 274 Torino", "company.two.com");
        List companies = new ArrayList();
        companies.add(comp1);
        companies.add(comp2);
        companiesCollection.insert(companies);

        // Create 2 departments
        departmentsCollection = INSTANCE.getDatasource()
                .getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        departmentsCollection.setObjectClass(Department.class);
        dep1 = new Department("department test name 1", "department test description 1",
                new ObjectId(comp1.getId()), "department1@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", "department.one.com");
        dep2 = new Department("department test name 2", "department test description 2",
                new ObjectId(comp2.getId()), "department2@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", "department.two.com");
        dep3 = new Department("department test name 3", "department test description 3",
                new ObjectId(comp2.getId()), "department3@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", "department.three.com");
        List departments = new ArrayList();
        departments.add(dep1);
        departments.add(dep2);
        departments.add(dep3);
        departmentsCollection.insert(departments);

        // Create 3 users
        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        user1 = new User("user test name 1", "user test surname 1", "user1@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(comp1.getId()),
                new ObjectId(dep1.getId()), "usernametest1", "", "");
        user2 = new User("user test name 2", "user test surname 2", "user2@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(comp2.getId()),
                new ObjectId(dep2.getId()), "usernametest2", "", "");
        user3 = new User("user test name 3", "user test surname 3", "user3@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(comp2.getId()),
                new ObjectId(dep3.getId()), "usernametest3", "", "");
        List users = new ArrayList();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        usersCollection.insert(users);

        // Create 6 permissions
        perm1 = new Permission(new ObjectId(user1.getId()), "rud");
        perm2 = new Permission(new ObjectId(user2.getId()), "rud");
        perm3 = new Permission(new ObjectId(user3.getId()), "rud");
        //perm4 = new Permission();
        //perm5 = new Permission();
        //perm6 = new Permission();

        // Create 3 algorithms
        algorithmsCollection = INSTANCE.getDatasource().getDbCollection(ALGORITHMS_COLLECTION_NAME);
        algorithmsCollection.setObjectClass(Algorithm.class);

        algo1 = new Algorithm("algo test name 1", "algo test description 1", "test type", // usable by user2 and owned by user1
                Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<>(Arrays.asList(perm2)),
                new ObjectId(user1.getId()), "private", "test model", "test sub-model",
                "test hive query", Long.MIN_VALUE, 1, "test.url.com");
        algo2 = new Algorithm("algo test name 2", "algo test description 2", "test", // usable by user1 and owned by user3
                Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<>(Arrays.asList(perm1)),
                new ObjectId(user3.getId()), "private", "test model", "test sub-model",
                "test hive query", Long.MIN_VALUE, 2, "test.url.com");
        algo3 = new Algorithm("algo test name 3", "algo test description 3", "test", // usable by user2 and owned by user3
                Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<>(Arrays.asList(perm2)),
                new ObjectId(user3.getId()), "private", "test model", "test sub-model",
                "test hive query", Long.MIN_VALUE, 2, "test.url.com");
        List algorithms = new ArrayList();
        algorithms.add(algo1);
        algorithms.add(algo2);
        algorithms.add(algo3);
        algorithmsCollection.insert(algorithms);

        // Create 3 datasets
        datasetsCollection = INSTANCE.getDatasource().getDbCollection(DATASETS_COLLECTION_NAME);
        datasetsCollection.setObjectClass(Dataset.class);

        data1 = new Dataset("dataset test name 1", "dataset test description 1", "test type", // usable by user1 and owned by user2
                Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<>(Arrays.asList(perm3)),
                new ObjectId(user2.getId()), "private", true, new DatasetStructure());
        data2 = new Dataset("dataset test name 2", "dataset test description 2", "test type", // usable by user2 and owned by user3
                Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<>(Arrays.asList(perm2)),
                new ObjectId(user3.getId()), "private", true, new DatasetStructure());
        data3 = new Dataset("dataset test name 3", "dataset test description 3", "test type", // public and owned by user3
                Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<>(Arrays.asList(perm2)), // test for empty array
                new ObjectId(user3.getId()), "public", true, new DatasetStructure()); // is a public dataset, usable by everyone
        List datasets = new ArrayList();
        datasets.add(data1);
        datasets.add(data2);
        datasets.add(data3);
        datasetsCollection.insert(datasets);

    }

    @AfterClass
    public static void tearDownClass() {
        algorithmsCollection.drop();
        datasetsCollection.drop();
        usersCollection.drop();

        INSTANCE.closeDaoObjects();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of discoveryUsable method, of class DiscoverObjDao.
     */
    @Test
    public void testDiscoverUsable() {
        System.out.println("discoverUsable");
        DiscoverObjDao instance = new DiscoverObjDao();
        List expResultList = new ArrayList();
        expResultList.add(algo1);
        expResultList.add(algo3);
        expResultList.add(data2);
        expResultList.add(data3);
        List result = instance.discoverUsable(user2.getId());
        assertTrue(expResultList.containsAll(result) && result.containsAll(expResultList));
    }

    /**
     * Test of discoveryUsable method, of class DiscoverObjDao.
     */
    @Test
    public void testDiscoverUsableNull() {
        System.out.println("discoverUsable public only");
        String id = new ObjectId().toHexString();
        List expResultList = new ArrayList();
        expResultList.add(data3);
        DiscoverObjDao instance = new DiscoverObjDao();
        System.out.println(id);
        List result = instance.discoverUsable(id);
        assertTrue(expResultList.containsAll(result) && result.containsAll(expResultList));
    }

    /**
     * Test of discoveryUsable method, of class DiscoverObjDao.
     */
    @Test
    public void testDiscoverUsableFindOneAlgorithm() {
        System.out.println("discoverUsable find one algorithm and public");
        DiscoverObjDao instance = new DiscoverObjDao();
        List expResultList = new ArrayList<>();
        expResultList.add(algo2);
        expResultList.add(data3);
        List<Algorithm> result = instance.discoverUsable(user1.getId());
        assertEquals(expResultList, result);
    }

    /**
     * Test of discoveryUsable method, of class DiscoverObjDao.
     */
    @Test
    public void testDiscoverUsableFindOneDataset() {
        System.out.println("discoverUsable find one dataset and public");
        DiscoverObjDao instance = new DiscoverObjDao();
        List<Dataset> expResultList = new ArrayList<>();
        expResultList.add(data1);
        expResultList.add(data3);
        List<Dataset> result = instance.discoverUsable(user3.getId());
        assertEquals(expResultList, result);
    }

    /**
     * Test of discoveryOwned method, of class DiscoverObjDao.
     */
    @Test
    public void testDiscoverOwned() {
        System.out.println("discoverOwned");
        DiscoverObjDao instance = new DiscoverObjDao();
        List expResultList = new ArrayList();
        expResultList.add(algo2);
        expResultList.add(algo3);
        expResultList.add(data2);
        expResultList.add(data3);
        List result = instance.discoverOwned(user3.getId());
        assertTrue(expResultList.containsAll(result) && result.containsAll(expResultList));
    }

    /**
     * Test of discoveryOwned method, of class DiscoverObjDao.
     */
    @Test(expected = BadRequestException.class)
    public void testDiscoverOwnedNull() {
        System.out.println("discoverOwned null");
        String id = "";
        DiscoverObjDao instance = new DiscoverObjDao();
        instance.discoverOwned(id);
    }

    /**
     * Test of discoveryOwned method, of class DiscoverObjDao.
     */
    @Test
    public void testDiscoverOwnedFindOneAlgorithm() {
        System.out.println("discoverOwned find one algorithm");
        DiscoverObjDao instance = new DiscoverObjDao();
        List<Algorithm> expResultList = new ArrayList<>();
        expResultList.add(algo1);
        List<Algorithm> result = instance.discoverOwned(user1.getId());
        assertEquals(expResultList, result);
    }

    /**
     * Test of discoveryOwned method, of class DiscoverObjDao.
     */
    @Test
    public void testDiscoverOwnedFindOneDataset() {
        System.out.println("discoverOwned find one dataset");
        DiscoverObjDao instance = new DiscoverObjDao();
        List<Dataset> expResultList = new ArrayList<>();
        expResultList.add(data1);
        List<Dataset> result = instance.discoverOwned(user2.getId());
        assertEquals(expResultList, result);
    }

}

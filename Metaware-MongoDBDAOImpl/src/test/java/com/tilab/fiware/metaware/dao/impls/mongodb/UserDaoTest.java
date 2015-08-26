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
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.COMPANIES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DEPARTMENTS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
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
public class UserDaoTest {

    private static Properties testProperties;

    // MongoDB objects
    private static DBCollection companiesCollection;
    private static DBCollection departmentsCollection;
    private static DBCollection usersCollection;

    // Temporary data
    private static Company comp1;
    private static Department dep1;
    private static User user1, user2;

    public UserDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test

        INSTANCE.createDaoObjects(testProperties);

        // Create 1 company
        companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        companiesCollection.setObjectClass(Company.class);
        comp1 = new Company("company test name 1", "company test description 1",
                "company1@test.com", "123456", "Via Reiss Romoli, 274 Torino", "company.one.test");

        // Create 1 department (moved to setUp to obtain comp1.Id)
        departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        departmentsCollection.setObjectClass(Department.class);

        // Create 2 users (moved to setUp to obtain Ids from comp1 and dep1)
        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeDaoObjects();
    }

    @Before
    public void setUp() {
        // Insert 1 collection
        companiesCollection.insert(comp1);

        // Create 1 department
        dep1 = new Department("dep test name 1", "dep test description 1",
                new ObjectId(comp1.getId()), "dep.one@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", "http://www.dep.test.one.com");

        // Insert 1 department
        departmentsCollection.insert(dep1);

        // Create 2 users
        user1 = new User("user test name 1", "user test surname 1", "user1@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(comp1.getId()),
                new ObjectId(dep1.getId()), "usernametest1", "secret", "");
        user2 = new User("user test name 2", "user test surname 2", "user2@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(comp1.getId()),
                new ObjectId(dep1.getId()), "usernametest", "secret", "");

        // Insert 2 users
        List users = new ArrayList();
        users.add(user1);
        users.add(user2);
        usersCollection.insert(users);
    }

    @After
    public void tearDown() {
        companiesCollection.drop();
        departmentsCollection.drop();
        usersCollection.drop();
    }

    /**
     * Test of getUsersList method, of class UserDao.
     */
    @Test
    public void testGetUsersList() {
        System.out.println("getUsersList");
        UserDao instance = new UserDao();
        List<User> expResult = new ArrayList<>();
        expResult.add(user1);
        expResult.add(user2);
        List<User> result = instance.getUsersList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getUser method, of class UserDao.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        String id = user1.getId();
        UserDao instance = new UserDao();
        User expResult = user1;
        User result = instance.getUser(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserByCredentials method, of class UserDao.
     */
    @Test
    public void testGetUserByCredentials() {
        System.out.println("getUserByCredentials");
        String username = user1.getUsername();
        String password = user1.getPassword();
        UserDao instance = new UserDao();
        User expResult = user1;
        User result = instance.getUserByCredentials(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of createUser method, of class UserDao.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        User user = new User("user test name 1", "user test surname 1", "user1@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(comp1.getId()),
                new ObjectId(dep1.getId()), "usernametestnew1", "secret", "");
        UserDao instance = new UserDao();
        String result = instance.createUser(user);
        assertTrue(ObjectId.isValid(result));
    }

    /**
     * Test of upsertUser method, of class UserDao.
     */
    @Test
    public void testUpsertUser() {
        System.out.println("upsertUser");
        String id = user1.getId();
        user1.setRole("super cool guy");
        user1.setSurname("Stark");
        UserDao instance = new UserDao();
        User expResult = user1;
        User result = instance.upsertUser(id, user1);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteUser method, of class UserDao.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String id = user2.getId();
        UserDao instance = new UserDao();
        instance.deleteUser(id);
    }

}

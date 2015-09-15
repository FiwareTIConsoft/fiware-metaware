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
package com.tilab.fiware.metaware.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.tilab.fiware.metaware.core.SingltProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
//import org.json.JSONException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class UserServiceTest {

    private static Properties testProperties;

    // Temporary data
    private static String userId1, userId2, compId, depId;
    private static Company comp;
    private static Department dep;
    private static User user1, user2;

    public UserServiceTest() {
        INSTANCE.createCoreObjects();
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test
        INSTANCE.setManualProperties(testProperties);

        INSTANCE.createCoreObjects();

        // Object definition
        comp = new Company("company test name", "company test description", "company@test.com",
                "123456", "Via Reiss Romoli, 274 Torino", "http://company.test.com");
        dep = new Department("department test name", "department test description", null, // company id is set after
                "dep@test.com", "123456", "Via Reiss Romoli, 274 Torino", "http://dep.test.com");
        user1 = new User("user test name 1", "user test surname 1", "user.one@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", null, null, "usernametestone", "secret", "basic"); // coompany is and department is are set after
        user2 = new User("user test name 2", "user test surname 2", "user.two@test.com", "654321",
                "Via Reiss Romoli, 274 Torino", null, null, "usernametesttwo", "supersecret",
                "basic"); // coompany is and department is are set after
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeCoreObjects();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUserByCredentials method, of class UserService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testGetUserByCredentials() throws JsonProcessingException, NoSuchAlgorithmException {
        System.out.println("getUserByCredentials");
        UserService instance = new UserService();
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep.setCompany(compId);
        depId = INSTANCE.getDepartmentService().createDepartment(dep);
        user1.setDepartment(depId);
        user1.setCompany(compId);
        userId1 = instance.createUser(user1);
        user2.setDepartment(depId);
        user2.setCompany(compId);
        userId2 = instance.createUser(user2);

        String username = user2.getUsername();
        String password = user2.getPassword();
        User expResult = user2;
        User result = instance.getUserByCredentials(username, password);
        assertEquals(expResult, result);

        INSTANCE.getCompanyService().deleteCompany(compId);
        INSTANCE.getDepartmentService().deleteDepartment(depId);
        instance.deleteUser(userId1);
        instance.deleteUser(userId2);
    }

    /**
     * Test of getUsersList method, of class UserService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testGetUsersList() throws JsonProcessingException, NoSuchAlgorithmException {
        System.out.println("getUsersList");
        UserService instance = INSTANCE.getUserService();
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep.setCompany(compId);
        depId = INSTANCE.getDepartmentService().createDepartment(dep);
        user1.setDepartment(depId);
        user1.setCompany(compId);
        userId1 = instance.createUser(user1);
        user2.setDepartment(depId);
        user2.setCompany(compId);
        userId2 = instance.createUser(user2);

        List<User> expResult = new ArrayList<>();
        expResult.add(user1);
        expResult.add(user2);
        List<User> result = instance.getUsersList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));

        INSTANCE.getCompanyService().deleteCompany(compId);
        INSTANCE.getDepartmentService().deleteDepartment(depId);
        instance.deleteUser(userId1);
        instance.deleteUser(userId2);
    }

    /**
     * Test of getUser method, of class UserService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testGetUser() throws JsonProcessingException, NoSuchAlgorithmException {
        System.out.println("getUser");
        UserService instance = new UserService();
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep.setCompany(compId);
        depId = INSTANCE.getDepartmentService().createDepartment(dep);
        user1.setDepartment(depId);
        user1.setCompany(compId);
        userId1 = instance.createUser(user1);

        String id = userId1;
        User expResult = user1;
        User result = instance.getUser(id);
        assertEquals(expResult, result);

        INSTANCE.getCompanyService().deleteCompany(compId);
        INSTANCE.getDepartmentService().deleteDepartment(depId);
        instance.deleteUser(userId1);
    }

    /**
     * Test of createUser method, of class UserService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testCreateUser() throws JsonProcessingException, NoSuchAlgorithmException {
        System.out.println("createUser");
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep.setCompany(compId);
        depId = INSTANCE.getDepartmentService().createDepartment(dep);

        User user = new User("user test name", "user test surname", "user@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", null,
                null, "usertest", "supersecret", "basic"); // coompany is and department is are set after;
        user.setCompany(compId);
        user.setDepartment(depId);
        UserService instance = new UserService();
        User expResult = user;
        String userId = instance.createUser(user);
        User result = instance.getUser(userId);
        assertEquals(expResult, result);

        INSTANCE.getCompanyService().deleteCompany(compId);
        INSTANCE.getDepartmentService().deleteDepartment(depId);
        instance.deleteUser(userId);
    }

    /**
     * Test of upsertUser method, of class UserService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testUpsertUser() throws JsonProcessingException, NoSuchAlgorithmException {
        System.out.println("upsertUser");
        UserService instance = new UserService();
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep.setCompany(compId);
        depId = INSTANCE.getDepartmentService().createDepartment(dep);
        User user = new User("user test name", "user test surname", "user@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", null,
                null, "usertest", "supersecret", "basic");
        user.setCompany(compId); // allowing the possibility to insert user with no company, this instruction will not be necessary anymore
        user.setDepartment(depId); // allowing the possibility to insert user with no department, this instruction will not be necessary anymore
        String userId = instance.createUser(user);

        User expResult = new User();
        expResult.setName(user.getName());
        expResult.setSurname("Sir Test");
        expResult.setEmail("sir.test@test.com");
        expResult.setPhone(user.getPhone());
        expResult.setCompany(compId);
        expResult.setDepartment(depId);
        expResult.setUsername("sirtest");
        expResult.setPassword("ubersecret");
        expResult.setRole(user.getRole());
        User result = instance.upsertUser(userId, expResult);
        assertEquals(expResult, result);

        INSTANCE.getCompanyService().deleteCompany(compId);
        INSTANCE.getDepartmentService().deleteDepartment(depId);
        instance.deleteUser(userId);
    }

    /**
     * Test of deleteUser method, of class UserService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testDeleteUser() throws JsonProcessingException, NoSuchAlgorithmException {
        System.out.println("deleteUser");
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep.setCompany(compId);
        depId = INSTANCE.getDepartmentService().createDepartment(dep);
        UserService instance = new UserService();
        User user = new User("user test name", "user test surname", "user@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", null,
                null, "usertest", "supersecret", "basic");
        user.setCompany(compId); // allowing the possibility to insert user with no company, this instruction will not be necessary anymore
        user.setDepartment(depId); // allowing the possibility to insert user with no department, this instruction will not be necessary anymore
        String userId = instance.createUser(user);

        instance.deleteUser(userId);

        INSTANCE.getCompanyService().deleteCompany(compId);
        INSTANCE.getDepartmentService().deleteDepartment(depId);
    }

}

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
package com.tilab.fiware.metaware.dao.impls.mongodb.domain;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class UserTest {

    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        String expResult = null;
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "testName";
        User instance = new User(expResult, "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "New Test Name";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getSurname method, of class User.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        String expResult = "testSurname";
        User instance = new User("testName", expResult, "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        String result = instance.getSurname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSurname method, of class User.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String surname = "New Test Surname";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        instance.setSurname(surname);
        assertEquals(surname, instance.getSurname());
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "test@test.com";
        User instance = new User("testName", "testSurname", expResult, "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "new.test@test.com";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of getPhone method, of class User.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        String expResult = "123456789";
        User instance = new User("testName", "testSurname", "test@test.com", expResult,
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhone method, of class User.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "989898989898";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        instance.setPhone(phone);
        assertEquals(phone, instance.getPhone());
    }

    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "Via Reiss Romoli, 274, 10148 Torino";
        User instance = new User("testName", "testSurname", "test@test.com", "1234567899",
                expResult, new ObjectId(), new ObjectId(), "testUsername", "testPassword", "test");
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "Via Pio VII, 127, 10127 Torino";
        User instance = new User("testName", "testSurname", "test@test.com", "1234567899",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "test");
        instance.setAddress(address);
        assertEquals(address, instance.getAddress());
    }

    /**
     * Test of getCompany method, of class User.
     */
    @Test
    public void testGetCompany() {
        System.out.println("getCompany");
        Object expResult = new ObjectId("5508072e5e717969eaecb2a7");
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId("5508072e5e717969eaecb2a7"),
                new ObjectId(), "testUsername", "testPassword", "testRole");
        Object result = instance.getCompany();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompany method, of class User.
     */
    @Ignore
    public void testSetCompany() {
        System.out.println("setCompany");
        String company_id = "";
        User instance = new User();
        instance.setCompany(company_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompanyId method, of class User.
     */
    @Test
    public void testGetCompanyId() {
        System.out.println("getCompanyId");
        ObjectId expResult = new ObjectId();
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", expResult, new ObjectId(), "testUsername",
                "testPassword", "testRole");
        ObjectId result = instance.getCompanyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompanyId method, of class User.
     */
    @Test
    public void testSetCompanyId() {
        System.out.println("setCompanyId");
        ObjectId company_id = new ObjectId();
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", null, new ObjectId(), "testUsername",
                "testPassword", "testRole");
        instance.setCompanyId(company_id);
        ObjectId result = instance.getCompanyId();
        assertEquals(company_id, result);
    }

    /**
     * Test of getDepartment method, of class User.
     */
    @Test
    public void testGetDepartment() {
        System.out.println("getDepartment");
        Object expResult = new ObjectId("5508072e5e71796fffffb2a7");
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(),
                new ObjectId("5508072e5e71796fffffb2a7"), "testUsername", "testPassword",
                "testRole");
        Object result = instance.getDepartment();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDepartment method, of class User.
     */
    @Ignore
    public void testSetDepartment() {
        System.out.println("setDepartment");
        String department_id = "";
        User instance = new User();
        instance.setDepartment(department_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepartmentId method, of class User.
     */
    @Test
    public void testGetDepartmentId() {
        System.out.println("getDepartmentId");
        ObjectId expResult = new ObjectId();
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), expResult, "testUsername",
                "testPassword", "testRole");
        ObjectId result = instance.getDepartmentId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDepartmentId method, of class User.
     */
    @Test
    public void testSetDepartmentId() {
        System.out.println("setDepartmentId");
        ObjectId department_id = new ObjectId();
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), null, "testUsername",
                "testPassword", "testRole");
        instance.setDepartmentId(department_id);
        ObjectId result = instance.getDepartmentId();
        assertEquals(department_id, result);
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        String expResult = "testUsername";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(), expResult,
                "testPassword", "testRole");
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "newSuperMagicTestUsername";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expResult = "testPassword";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", expResult, "testRole");
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "testPassword";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "", "testRole");
        instance.setPassword(password);
        String result = instance.getPassword();
        assertEquals(password, result);
    }

    /**
     * Test of getRole method, of class User.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        String expResult = "testRole";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", expResult);
        String result = instance.getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRole method, of class User.
     */
    @Test
    public void testSetRole() {
        System.out.println("setRole");
        String role = "new role";
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "testRole");
        instance.setRole(role);
        assertEquals(role, instance.getRole());
    }

    /**
     * Test of isAdmin method, of class User.
     */
    @Test
    public void testIsAdmin() {
        System.out.println("isAdmin");
        User instance = new User("testName", "testSurname", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", new ObjectId(), new ObjectId(),
                "testUsername", "testPassword", "admin");
        boolean expResult = true;
        boolean result = instance.isAdmin();
        assertEquals(expResult, result);
    }

}

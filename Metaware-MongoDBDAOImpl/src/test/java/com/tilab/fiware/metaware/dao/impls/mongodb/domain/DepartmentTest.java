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
public class DepartmentTest {

    public DepartmentTest() {
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
     * Test of getId method, of class Department.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        String expResult = null;
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Department.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "testName";
        Department instance = new Department(expResult, "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Department.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "new test name";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getDescription method, of class Department.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "testDescription";
        Department instance = new Department("testName", expResult, new ObjectId(), "test@test.com",
                "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Department.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "test new description";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    /**
     * Test of getCompany method, of class Department.
     */
    @Test
    public void testGetCompany() {
        System.out.println("getCompany");
        Object expResult = new ObjectId("5508072e5e717969eaecb2a7");
        Department instance = new Department("testName", "testDescription",
                new ObjectId("5508072e5e717969eaecb2a7"), "test@test.com", "123456789",
                "Via Pio VII, 127, 10127 Torino", "http://test.com");
        Object result = instance.getCompany();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompany method, of class Department.
     */
    @Ignore
    public void testSetCompany() {
        System.out.println("setCompany");
        String company_id = "";
        Department instance = new Department();
        instance.setCompany(company_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompanyId method, of class Department.
     */
    @Test
    public void testGetCompanyId() {
        System.out.println("getCompany");
        ObjectId expResult = new ObjectId();
        Department instance = new Department("testName", "testDescription", expResult,
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        ObjectId result = instance.getCompanyId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompanyId method, of class Department.
     */
    @Test
    public void testSetCompanyId() {
        System.out.println("setCompanyId");
        ObjectId company_id = new ObjectId();
        Department instance = new Department("testName", "testDescription", null, "test@test.com",
                "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        instance.setCompanyId(company_id);
        ObjectId result = instance.getCompanyId();
        assertEquals(company_id, result);
    }

    /**
     * Test of getEmail method, of class Department.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "test@test.com";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", expResult);
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Department.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "new.test@test.com";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of getPhone method, of class Department.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        String expResult = "123456789";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", expResult, "Via Pio VII, 127, 10127 Torino", "http://test.com");
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhone method, of class Department.
     */
    @Test
    public void testSetPhone() {
        System.out.println("getPhone");
        String phone = "987987987987";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        instance.setPhone(phone);
        assertEquals(phone, instance.getPhone());
    }

    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "Via Pio VII, 127, 10127 Torino";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", expResult, "http://test.com");
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "Via Pio VII, 127, 10127 Torino";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Reiss Romoli, 274, 10148 Torino",
                "http://test.com");
        instance.setAddress(address);
        assertEquals(address, instance.getAddress());
    }

    /**
     * Test of getUrl method, of class Department.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        String expResult = "http://test.com";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", expResult);
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUrl method, of class Department.
     */
    @Test
    public void testSetUrl() {
        System.out.println("setUrl");
        String url = "http://test.com/newtest";
        Department instance = new Department("testName", "testDescription", new ObjectId(),
                "test@test.com", "123456789", "Via Pio VII, 127, 10127 Torino", "http://test.com");
        instance.setUrl(url);
        assertEquals(url, instance.getUrl());
    }

}

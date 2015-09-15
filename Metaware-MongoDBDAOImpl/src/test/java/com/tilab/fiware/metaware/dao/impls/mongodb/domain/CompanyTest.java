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
public class CompanyTest {

    public CompanyTest() {
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
     * Test of getId method, of class Company.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Company instance = new Company("testName", "testDescription", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        String expResult = null;
        String result = instance.getId();
        assertEquals(expResult, result); // id is assigned by mongodb only
    }

    /**
     * Test of getName method, of class Company.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "testName";
        Company instance = new Company(expResult, "testDescription", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Company.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "testDescription";
        Company instance = new Company("testName", expResult, "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Company.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "test@test.com";
        Company instance = new Company("testName", "testDescription", expResult, "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhone method, of class Company.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        String expResult = "123456789";
        Company instance = new Company("testName", "testDescription", "test@test.com", expResult,
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "Via Reiss Romoli, 274, 10148 Torino";
        Company instance = new Company("testName", "testDescription", "test@test.com", "123456789",
                expResult, "http://www.test.com");
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUrl method, of class Company.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        String expResult = "http://www.test.com";
        Company instance = new Company("testName", "testDescription", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", expResult);
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Company.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "new test name";
        Company instance = new Company("testName", "testDescription", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of setDescription method, of class Company.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "new test description";
        Company instance = new Company("testName", "testDescription", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    /**
     * Test of setEmail method, of class Company.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "new.test.description@test.com";
        Company instance = new Company("testName", "testDescription", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of setPhone method, of class Company.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "987987987";
        Company instance = new Company("testName", "testDescription", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        instance.setPhone(phone);
        assertEquals(phone, instance.getPhone());
    }

    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "Via Pio VII, 127, 10127 Torino";
        Company instance = new Company("testName", "testDescription", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        instance.setAddress(address);
        assertEquals(address, instance.getAddress());
    }

    /**
     * Test of setUrl method, of class Company.
     */
    @Test
    public void testSetUrl() {
        System.out.println("setUrl");
        String url = "http://www.new.test.com";
        Company instance = new Company("testName", "testDescription", "test@test.com", "123456789",
                "Via Reiss Romoli, 274, 10148 Torino", "http://www.test.com");
        instance.setUrl(url);
        assertEquals(url, instance.getUrl());
    }

}

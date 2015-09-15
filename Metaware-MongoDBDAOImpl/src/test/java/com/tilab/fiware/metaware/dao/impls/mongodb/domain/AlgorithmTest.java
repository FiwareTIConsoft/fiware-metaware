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

import java.util.ArrayList;
import java.util.List;
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
public class AlgorithmTest {

    public AlgorithmTest() {
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
     * Test of getId method, of class Algorithm.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        String expResult = null; // set by mongodb only when insert
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Algorithm.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "test name";
        Algorithm instance = new Algorithm(expResult, "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Algorithm.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "modified test name";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of getDescription method, of class Algorithm.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "test description";
        Algorithm instance = new Algorithm("test name", expResult, "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Algorithm.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "modified test description";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        instance.setDescription(description);
        String result = instance.getDescription();
        assertEquals(description, result);
    }

    /**
     * Test of getType method, of class Algorithm.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "test type";
        Algorithm instance = new Algorithm("test name", "test description", expResult,
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Algorithm.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "modified test type";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        instance.setType(type);
        String result = instance.getType();
        assertEquals(type, result);
    }

    /**
     * Test of getCreationDate method, of class Algorithm.
     */
    @Test
    public void testGetCreationDate() {
        System.out.println("getCreationDate");
        Long expResult = Long.MIN_VALUE;
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                expResult, Long.MIN_VALUE, null, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        Long result = instance.getCreationDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCreationDate method, of class Algorithm.
     */
    @Test
    public void testSetCreationDate() {
        System.out.println("setCreationDate");
        Long creationDate = 1428914959L;
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        instance.setCreationDate(creationDate);
        Long result = instance.getCreationDate();
        assertEquals(creationDate, result);
    }

    /**
     * Test of getLastModifiedDate method, of class Algorithm.
     */
    @Test
    public void testGetLastModifiedDate() {
        System.out.println("getLastModifiedDate");
        Long expResult = Long.MIN_VALUE;
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, expResult, null, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        Long result = instance.getLastModifiedDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastModifiedDate method, of class Algorithm.
     */
    @Test
    public void testSetLastModifiedDate() {
        System.out.println("setLastModifiedDate");
        Long lastModifiedDate = 1428915116L;
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        instance.setLastModifiedDate(lastModifiedDate);
        Long result = instance.getLastModifiedDate();
        assertEquals(lastModifiedDate, result);
    }

    /**
     * Test of getPermissions method, of class Algorithm.
     */
    @Ignore
    public void testGetPermissions() {
        System.out.println("getPermissions");
        Algorithm instance = new Algorithm();
        List<Object> expResult = null;
        List<Object> result = instance.getPermissions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPermissionsIds method, of class Algorithm.
     */
    @Test
    public void testGetPermissionsIds() {
        System.out.println("getPermissionsIds");
        Permission perm1 = new Permission(new ObjectId(), "rud");
        Permission perm2 = new Permission(new ObjectId(), "ru");
        List<Permission> expResult = new ArrayList<>();
        expResult.add(perm1);
        expResult.add(perm2);
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, expResult, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        List<Permission> result = instance.getPermissionsIds();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult)); // same elements
    }

    /**
     * Test of setPermissions method, of class Algorithm.
     */
    @Test
    public void testSetPermissions() {
        System.out.println("setPermissions");
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        Permission perm1 = new Permission(new ObjectId(), "rud");
        Permission perm2 = new Permission(new ObjectId(), "ru");
        List<Permission> permissions = new ArrayList<>();
        permissions.add(perm1);
        permissions.add(perm2);
        instance.setPermissions(permissions);
        List<Permission> result = instance.getPermissionsIds();
        assertTrue(permissions.containsAll(result) && result.containsAll(permissions)); // same elements
    }

    /**
     * Test of getOwner method, of class Algorithm.
     */
    @Test
    public void testGetOwner() {
        System.out.println("getOwner");
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId("507f191e810c19729de860ea"),
                "test status", "test model", "test sub model", "test hive query", Long.MIN_VALUE,
                42, "http://test.algo.com/");
        Object expResult = new ObjectId("507f191e810c19729de860ea");
        Object result = instance.getOwner();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOwnerId method, of class Algorithm.
     */
    @Test
    public void testGetOwnerId() {
        System.out.println("getOwnerId");
        ObjectId expResult = new ObjectId();
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, expResult, "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        ObjectId result = instance.getOwnerId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwner method, of class Algorithm.
     */
    @Test
    public void testSetOwner() {
        System.out.println("setOwner");
        String owner = new ObjectId().toHexString();
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, null, "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setOwner(owner);
        String result = instance.getOwnerId().toHexString();
        assertEquals(owner, result);
    }

    /**
     * Test of setOwnerId method, of class Algorithm.
     */
    @Test
    public void testSetOwnerId() {
        System.out.println("setOwner");
        ObjectId owner = null;
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setOwnerId(owner);
        Object result = instance.getOwner();
        assertEquals(owner, result);
    }

    /**
     * Test of getStatus method, of class Algorithm.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        String expResult = "test status";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), expResult, "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Algorithm.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "modified test status";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setStatus(status);
        String result = instance.getStatus();
        assertEquals(status, result);
    }

    /**
     * Test of getModel method, of class Algorithm.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        String expResult = "test model";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", expResult,
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        String result = instance.getModel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setModel method, of class Algorithm.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        String model = "modified test model";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setModel(model);
        String result = instance.getModel();
        assertEquals(model, result);
    }

    /**
     * Test of getSubModel method, of class Algorithm.
     */
    @Test
    public void testGetSubModel() {
        System.out.println("getSubModel");
        String expResult = "test sub model";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                expResult, "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        String result = instance.getSubModel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSubModel method, of class Algorithm.
     */
    @Test
    public void testSetSubModel() {
        System.out.println("setSubModel");
        String subModel = "modified test sub model";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setSubModel(subModel);
        String result = instance.getSubModel();
        assertEquals(subModel, result);
    }

    /**
     * Test of getHiveQuery method, of class Algorithm.
     */
    @Test
    public void testGetHiveQuery() {
        System.out.println("getHiveQuery");
        String expResult = "test hive query";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", expResult, Long.MIN_VALUE, 42, "http://test.algo.com/");
        String result = instance.getHiveQuery();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHiveQuery method, of class Algorithm.
     */
    @Test
    public void testSetHiveQuery() {
        System.out.println("setHiveQuery");
        String hiveQuery = "modified test hive query";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setHiveQuery(hiveQuery);
        String result = instance.getHiveQuery();
        assertEquals(hiveQuery, result);
    }

    /**
     * Test of getElapsedTime method, of class Algorithm.
     */
    @Test
    public void testGetElapsedTime() {
        System.out.println("getElapsedTime");
        Long expResult = Long.MIN_VALUE;
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", expResult, 42, "http://test.algo.com/");
        Long result = instance.getElapsedTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElapsedTime method, of class Algorithm.
     */
    @Test
    public void testSetElapsedTime() {
        System.out.println("setElapsedTime");
        Long elapsedTime = 1428915847L;
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setElapsedTime(elapsedTime);
        Long result = instance.getElapsedTime();
        assertEquals(elapsedTime, result);
    }

    /**
     * Test of getRunNumber method, of class Algorithm.
     */
    @Test
    public void testGetRunNumber() {
        System.out.println("getRunNumber");
        int expResult = 42;
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, expResult,
                "http://test.algo.com/");
        int result = instance.getRunNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRunNumber method, of class Algorithm.
     */
    @Test
    public void testSetRunNumber() {
        System.out.println("setRunNumber");
        int runNumber = 0;
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setRunNumber(runNumber);
        int result = instance.getRunNumber();
        assertEquals(runNumber, result);
    }

    /**
     * Test of getLogUrl method, of class Algorithm.
     */
    @Test
    public void testGetLogUrl() {
        System.out.println("getLogUrl");
        String expResult = "http://test.algo.com/";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, expResult);
        String result = instance.getLogUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogUrl method, of class Algorithm.
     */
    @Test
    public void testSetLogUrl() {
        System.out.println("setLogUrl");
        String logUrl = "http://test.algo.com/modified";
        Algorithm instance = new Algorithm("test name", "test description", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test model",
                "test sub model", "test hive query", Long.MIN_VALUE, 42, "http://test.algo.com/");
        instance.setLogUrl(logUrl);
        String result = instance.getLogUrl();
        assertEquals(logUrl, result);
    }

}

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
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class ProcessTest {

    public ProcessTest() {
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
     * Test of getId method, of class Process.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        String expResult = null;
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Process.
     */
    @Ignore
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        Process instance = new Process();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Process.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "process test name";
        Process instance = new Process(expResult, "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Process.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "modified process test name";
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of getDescription method, of class Process.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "process test description";
        Process instance = new Process("process test name", expResult,
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Process.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "updated process test description";
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setDescription(description);
        String result = instance.getDescription();
        assertEquals(description, result);
    }

    /**
     * Test of getType method, of class Process.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "test";
        Process instance = new Process("process test name", "process test description",
                expResult, Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(),
                new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Process.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "new test";
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setType(type);
        String result = instance.getType();
        assertEquals(type, result);
    }

    /**
     * Test of getCreationDate method, of class Process.
     */
    @Test
    public void testGetCreationDate() {
        System.out.println("getCreationDate");
        Long expResult = Long.MIN_VALUE;
        Process instance = new Process("process test name", "process test description",
                "test", expResult, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        Long result = instance.getCreationDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCreationDate method, of class Process.
     */
    @Test
    public void testSetCreationDate() {
        System.out.println("setCreationDate");
        Long creationDate = 1428916344L;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setCreationDate(creationDate);
        Long result = instance.getCreationDate();
        assertEquals(creationDate, result);
    }

    /**
     * Test of getLastModifiedDate method, of class Process.
     */
    @Test
    public void testGetLastModifiedDate() {
        System.out.println("getLastModifiedDate");
        Long expResult = Long.MIN_VALUE;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, expResult, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        Long result = instance.getLastModifiedDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastModifiedDate method, of class Process.
     */
    @Test
    public void testSetLastModifiedDate() {
        System.out.println("setLastModifiedDate");
        Long lastModifiedDate = 1428916381L;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setLastModifiedDate(lastModifiedDate);
        Long result = instance.getLastModifiedDate();
        assertEquals(lastModifiedDate, result);
    }

    /**
     * Test of getPermissions method, of class Process.
     */
    @Ignore
    public void testGetPermissions() {
        System.out.println("getPermissions");
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        List<Object> expResult = null;
        List<Object> result = instance.getPermissions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPermissionsIds method, of class Process.
     */
    @Test
    public void testGetPermissionsIds() {
        System.out.println("getPermissionsIds");
        Permission perm1 = new Permission(new ObjectId(), "rud");
        Permission perm2 = new Permission(new ObjectId(), "ru");
        List<Permission> expResult = new ArrayList<>();
        expResult.add(perm1);
        expResult.add(perm2);
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, expResult, new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        List<Permission> result = instance.getPermissionsIds();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult)); // same elements
    }

    /**
     * Test of setPermissions method, of class Process.
     */
    @Test
    public void testSetPermissions() {
        System.out.println("setPermissions");
        Permission perm1 = new Permission(new ObjectId(), "rud");
        Permission perm2 = new Permission(new ObjectId(), "ru");
        List<Permission> permissions = new ArrayList<>();
        permissions.add(perm1);
        permissions.add(perm2);
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setPermissions(permissions);
        List<Permission> result = instance.getPermissionsIds();
        assertTrue(permissions.containsAll(result) && result.containsAll(permissions)); // same elements
    }

    /**
     * Test of getOwner method, of class Process.
     */
    @Test
    public void testGetOwner() {
        System.out.println("getOwner");
        Object expResult = new ObjectId("507f191e810c19729de860ea");
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(
                        "507f191e810c19729de860ea"),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        Object result = instance.getOwner();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwner method, of class Process.
     */
    @Test
    public void testSetOwner() {
        System.out.println("setOwner");
        String owner = new ObjectId().toHexString();
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setOwner(owner);
        String result = instance.getOwnerId().toHexString();
        assertEquals(owner, result);
    }

    /**
     * Test of getOwnerId method, of class Process.
     */
    @Test
    public void testGetOwnerId() {
        System.out.println("getOwnerId");
        ObjectId expResult = new ObjectId();
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), expResult,
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        ObjectId result = instance.getOwnerId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwnerId method, of class Process.
     */
    @Test
    public void testSetOwnerId() {
        System.out.println("setOwnerId");
        ObjectId owner = new ObjectId();
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setOwnerId(owner);
        ObjectId result = instance.getOwnerId();
        assertEquals(owner, result);
    }

    /**
     * Test of getStatus method, of class Process.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        String expResult = "test status";
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                expResult, "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Process.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "updated status";
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setStatus(status);
        String result = instance.getStatus();
        assertEquals(status, result);
    }

    /**
     * Test of getFrequence method, of class Process.
     */
    @Test
    public void testGetFrequence() {
        System.out.println("getFrequence");
        String expResult = "daily";
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", expResult, Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        String result = instance.getFrequence();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFrequence method, of class Process.
     */
    @Test
    public void testSetFrequence() {
        System.out.println("setFrequence");
        String frequence = "hourly";
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setFrequence(frequence);
        String result = instance.getFrequence();
        assertEquals(frequence, result);
    }

    /**
     * Test of getStartingTime method, of class Process.
     */
    @Test
    public void testGetStartingTime() {
        System.out.println("getStartingTime");
        Long expResult = Long.MIN_VALUE;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", expResult, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        Long result = instance.getStartingTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartingTime method, of class Process.
     */
    @Test
    public void testSetStartingTime() {
        System.out.println("setStartingTime");
        Long startingTime = Long.MAX_VALUE;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setStartingTime(startingTime);
        Long result = instance.getStartingTime();
        assertEquals(startingTime, result);
    }

    /**
     * Test of getLastRunTime method, of class Process.
     */
    @Test
    public void testGetLastRunTime() {
        System.out.println("getLastRunTime");
        Long expResult = Long.MIN_VALUE;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, expResult, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        Long result = instance.getLastRunTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastRunTime method, of class Process.
     */
    @Test
    public void testSetLastRunTime() {
        System.out.println("setLastRunTime");
        Long lastRunTime = Long.MAX_VALUE;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setLastRunTime(lastRunTime);
        Long result = instance.getLastRunTime();
        assertEquals(lastRunTime, result);
    }

    /**
     * Test of getRunNumber method, of class Process.
     */
    @Test
    public void testGetRunNumber() {
        System.out.println("getRunNumber");
        Long expResult = 10000L;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, expResult,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        Long result = instance.getRunNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRunNumber method, of class Process.
     */
    @Test
    public void testSetRunNumber() {
        System.out.println("setRunNumber");
        Long runNumber = 15L;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setRunNumber(runNumber);
        Long result = instance.getRunNumber();
        assertEquals(runNumber, result);
    }

    /**
     * Test of getProcessingBlocksObj method, of class Process.
     */
    @Ignore
    public void testGetProcessingBlocksObj() {
        System.out.println("getProcessingBlocksObj");
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        List<Object> expResult = null;
        List<Object> result = instance.getProcessingBlocksObj();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProcessingBlocks method, of class Process.
     */
    @Test
    public void testGetProcessingBlocks() {
        System.out.println("getProcessingBlocks");
        List<ProcessingBlock> expResult = new ArrayList<>();
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                expResult, "http://create.process.test");
        List<ProcessingBlock> result = instance.getProcessingBlocks();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult)); // same elements
    }

    /**
     * Test of setProcessingBlocks method, of class Process.
     */
    @Ignore
    public void testSetProcessingBlocks() {
        System.out.println("setProcessingBlocks");
        List<ProcessingBlock> processingBlocks = null;
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setProcessingBlocks(processingBlocks);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogUrl method, of class Process.
     */
    @Test
    public void testGetLogUrl() {
        System.out.println("getLogUrl");
        String expResult = "http://create.process.test";
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), expResult);
        String result = instance.getLogUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogUrl method, of class Process.
     */
    @Test
    public void testSetLogUrl() {
        System.out.println("setLogUrl");
        String logUrl = "http://new.create.process.test";
        Process instance = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<Permission>(), new ObjectId(),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://create.process.test");
        instance.setLogUrl(logUrl);
        String result = instance.getLogUrl();
        assertEquals(logUrl, result);
    }

}

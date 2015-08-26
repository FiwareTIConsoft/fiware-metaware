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
public class DatasetTest {

    public DatasetTest() {
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
     * Test of getId method, of class Dataset.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        String expResult = null;
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Dataset.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "test name";
        Dataset instance = new Dataset(expResult, "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Dataset.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "modified test name";
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of getDescription method, of class Dataset.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "test description";
        Dataset instance = new Dataset("test name", expResult, "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Dataset.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "modified test description";
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        instance.setDescription(description);
        String result = instance.getDescription();
        assertEquals(description, result);
    }

    /**
     * Test of getType method, of class Dataset.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "test type";
        Dataset instance = new Dataset("test name", "test description", expResult, Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Dataset.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "modified test type";
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        instance.setType(type);
        String result = instance.getType();
        assertEquals(type, result);
    }

    /**
     * Test of getCreationDate method, of class Dataset.
     */
    @Test
    public void testGetCreationDate() {
        System.out.println("getCreationDate");
        Long expResult = Long.MIN_VALUE;
        Dataset instance = new Dataset("test name", "test description", "test type", expResult,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        Long result = instance.getCreationDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCreationDate method, of class Dataset.
     */
    @Test
    public void testSetCreationDate() {
        System.out.println("setCreationDate");
        Long creationDate = 1428916344L;
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        instance.setCreationDate(creationDate);
        Long result = instance.getCreationDate();
        assertEquals(creationDate, result);
    }

    /**
     * Test of getLastModifiedDate method, of class Dataset.
     */
    @Test
    public void testGetLastModifiedDate() {
        System.out.println("getLastModifiedDate");
        Long expResult = Long.MIN_VALUE;
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                expResult, null, new ObjectId(), "test status", true, null);
        Long result = instance.getLastModifiedDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastModifiedDate method, of class Dataset.
     */
    @Test
    public void testSetLastModifiedDate() {
        System.out.println("setLastModifiedDate");
        Long lastModifiedDate = 1428916381L;
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        instance.setLastModifiedDate(lastModifiedDate);
        Long result = instance.getLastModifiedDate();
        assertEquals(lastModifiedDate, result);
    }

    /**
     * Test of getPermissions method, of class Dataset.
     */
    @Ignore
    public void testGetPermissions() {
        System.out.println("getPermissions");
        Dataset instance = new Dataset();
        List<Object> expResult = null;
        List<Object> result = instance.getPermissions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPermissionsIds method, of class Dataset.
     */
    @Test
    public void testGetPermissionsIds() {
        System.out.println("getPermissionsIds");
        Permission perm1 = new Permission(new ObjectId(), "rud");
        Permission perm2 = new Permission(new ObjectId(), "ru");
        List<Permission> expResult = new ArrayList<>();
        expResult.add(perm1);
        expResult.add(perm2);
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, expResult, new ObjectId(), "test status", true, null);
        List<Permission> result = instance.getPermissionsIds();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult)); // same elements
    }

    /**
     * Test of setPermissions method, of class Dataset.
     */
    @Test
    public void testSetPermissions() {
        System.out.println("setPermissions");
        Permission perm1 = new Permission(new ObjectId(), "rud");
        Permission perm2 = new Permission(new ObjectId(), "ru");
        List<Permission> permissions = new ArrayList<>();
        permissions.add(perm1);
        permissions.add(perm2);
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        instance.setPermissions(permissions);
        List<Permission> result = instance.getPermissionsIds();
        assertTrue(permissions.containsAll(result) && result.containsAll(permissions)); // same elements
    }

    /**
     * Test of getOwner method, of class Dataset.
     */
    @Test
    public void testGetOwner() {
        System.out.println("getOwner");
        Object expResult = new ObjectId("507f191e810c19729de860ea");
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId("507f191e810c19729de860ea"), "test status", true,
                null);
        Object result = instance.getOwner();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOwnerId method, of class Dataset.
     */
    @Test
    public void testGetOwnerId() {
        System.out.println("getOwnerId");
        ObjectId expResult = new ObjectId();
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, expResult, "test status", true, null);
        ObjectId result = instance.getOwnerId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwnerId method, of class Dataset.
     */
    @Test
    public void testSetOwner() {
        System.out.println("setOwner");
        ObjectId owner = new ObjectId();
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true,
                null);
        instance.setOwnerId(owner);
        ObjectId result = instance.getOwnerId();
        assertEquals(owner, result);
    }

    /**
     * Test of getStatus method, of class Dataset.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        String expResult = "test status";
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), expResult, true, null);
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Dataset.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "modified test status";
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        instance.setStatus(status);
        String result = instance.getStatus();
        assertEquals(status, result);
    }

    /**
     * Test of isReadOnly method, of class Dataset.
     */
    @Test
    public void testIsReadOnly() {
        System.out.println("isReadOnly");
        boolean expResult = true;
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", expResult, null);
        boolean result = instance.isReadOnly();
        assertEquals(expResult, result);
    }

    /**
     * Test of setReadOnly method, of class Dataset.
     */
    @Test
    public void testSetReadOnly() {
        System.out.println("setReadOnly");
        boolean readOnly = false;
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        instance.setReadOnly(readOnly);
        boolean result = instance.isReadOnly();
        assertEquals(readOnly, result);
    }

    /**
     * Test of getStructure method, of class Dataset.
     */
    @Test
    public void testGetStructure() {
        System.out.println("getStructure");
        DatasetStructure expResult = new DatasetStructure();
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, expResult);
        DatasetStructure result = instance.getStructure();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStructure method, of class Dataset.
     */
    @Test
    public void testSetStructure() {
        System.out.println("setStructure");
        DatasetStructure structure = new DatasetStructure();
        Dataset instance = new Dataset("test name", "test description", "test type", Long.MIN_VALUE,
                Long.MIN_VALUE, null, new ObjectId(), "test status", true, null);
        instance.setStructure(structure);
        DatasetStructure result = instance.getStructure();
        assertEquals(structure, result);
    }

}

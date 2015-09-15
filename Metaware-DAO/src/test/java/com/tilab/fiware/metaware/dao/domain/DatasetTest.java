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
package com.tilab.fiware.metaware.dao.domain;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
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
     * Test of getName method, of class Dataset.
     */
    @Ignore
    public void testGetName() {
        System.out.println("getName");
        Dataset instance = new Dataset();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Dataset.
     */
    @Ignore
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Dataset instance = new Dataset();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Dataset.
     */
    @Ignore
    public void testGetDescription() {
        System.out.println("getDescription");
        Dataset instance = new Dataset();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Dataset.
     */
    @Ignore
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        Dataset instance = new Dataset();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Dataset.
     */
    @Ignore
    public void testGetType() {
        System.out.println("getType");
        Dataset instance = new Dataset();
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class Dataset.
     */
    @Ignore
    public void testSetType() {
        System.out.println("setType");
        String type = "";
        Dataset instance = new Dataset();
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreationDate method, of class Dataset.
     */
    @Ignore
    public void testGetCreationDate() {
        System.out.println("getCreationDate");
        Dataset instance = new Dataset();
        Date expResult = null;
        Date result = instance.getCreationDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCreationDate method, of class Dataset.
     */
    @Ignore
    public void testSetCreationDate() {
        System.out.println("setCreationDate");
        Date creationDate = null;
        Dataset instance = new Dataset();
        instance.setCreationDate(creationDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastModifiedDate method, of class Dataset.
     */
    @Ignore
    public void testGetLastModifiedDate() {
        System.out.println("getLastModifiedDate");
        Dataset instance = new Dataset();
        Date expResult = null;
        Date result = instance.getLastModifiedDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastModifiedDate method, of class Dataset.
     */
    @Ignore
    public void testSetLastModifiedDate() {
        System.out.println("setLastModifiedDate");
        Date lastModifiedDate = null;
        Dataset instance = new Dataset();
        instance.setLastModifiedDate(lastModifiedDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class Dataset.
     */
    @Ignore
    public void testGetUsers() {
        System.out.println("getUsers");
        Dataset instance = new Dataset();
        List<User> expResult = null;
        List<User> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUser method, of class Dataset.
     */
    @Ignore
    public void testSetUser() {
        System.out.println("setUser");
        List<User> users = null;
        Dataset instance = new Dataset();
        instance.setUser(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwner method, of class Dataset.
     */
    @Ignore
    public void testGetOwner() {
        System.out.println("getOwner");
        Dataset instance = new Dataset();
        User expResult = null;
        User result = instance.getOwner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwner method, of class Dataset.
     */
    @Ignore
    public void testSetOwner() {
        System.out.println("setOwner");
        User owner = null;
        Dataset instance = new Dataset();
        instance.setOwner(owner);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class Dataset.
     */
    @Ignore
    public void testGetStatus() {
        System.out.println("getStatus");
        Dataset instance = new Dataset();
        String expResult = "";
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class Dataset.
     */
    @Ignore
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "";
        Dataset instance = new Dataset();
        instance.setStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isReadOnly method, of class Dataset.
     */
    @Ignore
    public void testIsReadOnly() {
        System.out.println("isReadOnly");
        Dataset instance = new Dataset();
        boolean expResult = false;
        boolean result = instance.isReadOnly();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReadOnly method, of class Dataset.
     */
    @Ignore
    public void testSetReadOnly() {
        System.out.println("setReadOnly");
        boolean readOnly = false;
        Dataset instance = new Dataset();
        instance.setReadOnly(readOnly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStructure method, of class Dataset.
     */
    @Ignore
    public void testGetStructure() {
        System.out.println("getStructure");
        Dataset instance = new Dataset();
        DatasetStructure expResult = null;
        DatasetStructure result = instance.getStructure();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStructure method, of class Dataset.
     */
    @Ignore
    public void testSetStructure() {
        System.out.println("setStructure");
        DatasetStructure structure = null;
        Dataset instance = new Dataset();
        instance.setStructure(structure);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

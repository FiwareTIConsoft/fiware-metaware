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
import java.util.Arrays;
import java.util.List;
import org.bson.types.ObjectId;
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
public class DataSourceTest {

    private static final String RES_TYPE_TABLE = "table";
    private static final String RES_TYPE_QUERY = "query";

    public DataSourceTest() {
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
     * Test of getId method, of class DataSource.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String result = instance.getId();
        assertNull(result); // the _id is automatically assigned by mongodb
    }

    /**
     * Test of getName method, of class DataSource.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = "datasource test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class DataSource.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "updated datasource test name";
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of getDescription method, of class DataSource.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = "this is just a test";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class DataSource.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "updated description of datasource test";
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setDescription(description);
        String result = instance.getDescription();
        assertEquals(description, result);
    }

    /**
     * Test of getType method, of class DataSource.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = "test type";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class DataSource.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "new test type";
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setType(type);
        String result = instance.getType();
        assertEquals(type, result);
    }

    /**
     * Test of getCreationDate method, of class DataSource.
     */
    @Test
    public void testGetCreationDate() {
        System.out.println("getCreationDate");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        Long expResult = Long.MIN_VALUE;
        Long result = instance.getCreationDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCreationDate method, of class DataSource.
     */
    @Test
    public void testSetCreationDate() {
        System.out.println("setCreationDate");
        Long creationDate = 1436783185L;
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setCreationDate(creationDate);
        Long result = instance.getCreationDate();
        assertEquals(creationDate, result);
    }

    /**
     * Test of getLastModifiedDate method, of class DataSource.
     */
    @Test
    public void testGetLastModifiedDate() {
        System.out.println("getLastModifiedDate");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        Long expResult = Long.MIN_VALUE;
        Long result = instance.getLastModifiedDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastModifiedDate method, of class DataSource.
     */
    @Test
    public void testSetLastModifiedDate() {
        System.out.println("setLastModifiedDate");
        Long lastModifiedDate = 1436783245L;
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setLastModifiedDate(lastModifiedDate);
        Long result = instance.getLastModifiedDate();
        assertEquals(lastModifiedDate, result);
    }

    /**
     * Test of getPermissions method, of class DataSource.
     */
    @Test
    public void testGetPermissions() {
        System.out.println("getPermissions");
        Permission p1 = new Permission(new ObjectId(), "rud");
        Permission p2 = new Permission(new ObjectId(), "r");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, Arrays.asList(p1, p2), new ObjectId(),
                "test status", "test subtype", "jdbc:mysql://localhost/test", "testUsername",
                "superSecret", RES_TYPE_QUERY, "SELECT * FROM TEST");
        List expResult = new ArrayList();
        expResult.add(p1);
        expResult.add(p2);
        List result = instance.getPermissions();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult)); // same elements
    }

    /**
     * Test of getPermissionsIds method, of class DataSource.
     */
    @Test
    public void testGetPermissionsIds() {
        System.out.println("getPermissionsIds");
        Permission p1 = new Permission(new ObjectId(), "rud");
        Permission p2 = new Permission(new ObjectId(), "r");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, Arrays.asList(p1, p2), new ObjectId(),
                "test status", "test subtype", "jdbc:mysql://localhost/test", "testUsername",
                "superSecret", RES_TYPE_QUERY, "SELECT * FROM TEST");
        List<Permission> expResult = new ArrayList<>();
        expResult.add(p1);
        expResult.add(p2);
        List<Permission> result = instance.getPermissionsIds();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult)); // same elements
    }

    /**
     * Test of setPermissions method, of class DataSource.
     */
    @Test
    public void testSetPermissions() {
        System.out.println("setPermissions");
        List<Permission> permissions = new ArrayList();
        Permission p1 = new Permission(new ObjectId(), "rud");
        Permission p2 = new Permission(new ObjectId(), "r");
        permissions.add(p1);
        permissions.add(p2);
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setPermissions(permissions);
        List<Permission> result = instance.getPermissionsIds();
        assertTrue(permissions.containsAll(result) && result.containsAll(permissions)); // same elements
    }

    /**
     * Test of getOwner method, of class DataSource.
     */
    @Test
    public void testGetOwner() {
        System.out.println("getOwner");
        ObjectId owner = new ObjectId();
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, owner, "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = owner.toHexString();
        String result = instance.getOwner().toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwner method, of class DataSource.
     */
    @Test
    public void testSetOwner() {
        System.out.println("setOwner");
        ObjectId owner = new ObjectId();
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setOwner(owner.toHexString());
        String result = instance.getOwner().toString();
        assertEquals(owner, result);
    }

    /**
     * Test of getOwnerId method, of class DataSource.
     */
    @Test
    public void testGetOwnerId() {
        System.out.println("getOwnerId");
        ObjectId owner = new ObjectId();
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, owner, "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        ObjectId expResult = owner;
        ObjectId result = instance.getOwnerId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwnerId method, of class DataSource.
     */
    @Test
    public void testSetOwnerId() {
        System.out.println("setOwnerId");
        ObjectId owner = new ObjectId();
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setOwnerId(owner);
        ObjectId result = instance.getOwnerId();
        assertEquals(owner, result);
    }

    /**
     * Test of getStatus method, of class DataSource.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = "test status";
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class DataSource.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "new test status";
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setStatus(status);
        String result = instance.getStatus();
        assertEquals(status, result);
    }

    /**
     * Test of getSubtype method, of class DataSource.
     */
    @Test
    public void testGetSubtype() {
        System.out.println("getSubtype");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = "test subtype";
        String result = instance.getSubtype();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSubtype method, of class DataSource.
     */
    @Test
    public void testSetSubtype() {
        System.out.println("setSubtype");
        String subtype = "new test sub-type";
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setSubtype(subtype);
        String result = instance.getSubtype();
        assertEquals(subtype, result);
    }

    /**
     * Test of getURL method, of class DataSource.
     */
    @Test
    public void testGetURL() {
        System.out.println("getURL");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = "jdbc:mysql://localhost/test";
        String result = instance.getURL();
        assertEquals(expResult, result);
    }

    /**
     * Test of setURL method, of class DataSource.
     */
    @Test
    public void testSetURL() {
        System.out.println("setURL");
        String URL = "jdbc:mysql://localhost/new_test_location";
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setURL(URL);
        String result = instance.getURL();
        assertEquals(URL, result);
    }

    /**
     * Test of getUsername method, of class DataSource.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = "testUsername";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class DataSource.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "updatedTestUsername";
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setUsername(username);
        String result = instance.getUsername();
        assertEquals(username, result);
    }

    /**
     * Test of getPassword method, of class DataSource.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = "superSecret";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class DataSource.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "ullneverfindmypassword";
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setPassword(password);
        String result = instance.getPassword();
        assertEquals(password, result);
    }

    /**
     * Test of getResourceType method, of class DataSource.
     */
    @Test
    public void testGetResourceType() {
        System.out.println("getResourceType");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = RES_TYPE_QUERY;
        String result = instance.getResourceType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setResourceType method, of class DataSource.
     */
    @Test
    public void testSetResourceType() {
        System.out.println("setResourceType");
        String resourceType = RES_TYPE_TABLE;
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setResourceType(resourceType);
        String result = instance.getResourceType();
        assertEquals(resourceType, result);
    }

    /**
     * Test of getResource method, of class DataSource.
     */
    @Test
    public void testGetResource() {
        System.out.println("getResource");
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        String expResult = "SELECT * FROM TEST";
        String result = instance.getResource();
        assertEquals(expResult, result);
    }

    /**
     * Test of setResource method, of class DataSource.
     */
    @Test
    public void testSetResource() {
        System.out.println("setResource");
        String resource = "SELECT Id, User FROM TEST WHERE Location = 'Turin'";
        DataSource instance = new DataSource("datasource test", "this is just a test", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, new ObjectId(), "test status", "test subtype",
                "jdbc:mysql://localhost/test", "testUsername", "superSecret", RES_TYPE_QUERY,
                "SELECT * FROM TEST");
        instance.setResource(resource);
        String result = instance.getResource();
        assertEquals(resource, result);
    }

}

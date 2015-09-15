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
public class PermissionTest {

    public PermissionTest() {
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
     * Test of getReference method, of class Permission.
     */
    @Ignore
    public void testGetReference() {
        System.out.println("getReference");
        Permission instance = new Permission();
        String expResult = "";
        String result = instance.getReference();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReferenceId method, of class Permission.
     */
    @Test
    public void testGetReferenceId() {
        System.out.println("getReferenceId");
        ObjectId expResult = new ObjectId();
        Permission instance = new Permission(expResult, "rud");
        ObjectId result = instance.getReferenceId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setReference method, of class Permission.
     */
    @Ignore
    public void testSetReference() {
        System.out.println("setReference");
        String referenceId = "";
        Permission instance = new Permission();
        instance.setReference(referenceId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReferenceId method, of class Permission.
     */
    @Test
    public void testSetReferenceId() {
        System.out.println("setReferenceId");
        ObjectId referenceId = new ObjectId();
        Permission instance = new Permission(new ObjectId(), "r");
        instance.setReferenceId(referenceId);
        ObjectId result = instance.getReferenceId();
        assertEquals(referenceId, result);
    }

    /**
     * Test of getPerm method, of class Permission.
     */
    @Test
    public void testGetPerm() {
        System.out.println("getPerm");
        String expResult = "rud";
        Permission instance = new Permission(new ObjectId(), expResult);
        String result = instance.getPerm();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPerm method, of class Permission.
     */
    @Test
    public void testSetPerm() {
        System.out.println("setPerm");
        String perm = "r";
        Permission instance = new Permission(new ObjectId(), "rud");
        instance.setPerm(perm);
        String result = instance.getPerm();
        assertEquals(perm, result);
    }

}

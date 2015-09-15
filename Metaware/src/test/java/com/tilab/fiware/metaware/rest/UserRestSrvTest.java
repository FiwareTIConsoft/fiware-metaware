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
package com.tilab.fiware.metaware.rest;

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class UserRestSrvTest {

    public UserRestSrvTest() {
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
     * Test of getUsersList method, of class UserRestSrv.
     */
    @Ignore
    public void testGetUsersList() {
        System.out.println("getUsersList");
        String authorization = "";
        UserRestSrv instance = new UserRestSrv();
        Response expResult = null;
        Response result = instance.getUsersList(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class UserRestSrv.
     */
    @Ignore
    public void testGetUser() {
        System.out.println("getUser");
        String authorization = "";
        String id = "";
        UserRestSrv instance = new UserRestSrv();
        Response expResult = null;
        Response result = instance.getUser(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUser method, of class UserRestSrv.
     */
    @Ignore
    public void testCreateUser() {
        System.out.println("createUser");
        String authorization = "";
        User user = null;
        UserRestSrv instance = new UserRestSrv();
        Response expResult = null;
        Response result = instance.createUser(authorization, user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertUser method, of class UserRestSrv.
     */
    @Ignore
    public void testUpsertUser() {
        System.out.println("upsertUser");
        String authorization = "";
        String id = "";
        User user = null;
        UserRestSrv instance = new UserRestSrv();
        Response expResult = null;
        Response result = instance.upsertUser(authorization, id, user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class UserRestSrv.
     */
    @Ignore
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String authorization = "";
        String id = "";
        UserRestSrv instance = new UserRestSrv();
        Response expResult = null;
        Response result = instance.deleteUser(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

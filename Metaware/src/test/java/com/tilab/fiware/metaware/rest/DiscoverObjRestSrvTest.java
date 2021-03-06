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
public class DiscoverObjRestSrvTest {

    public DiscoverObjRestSrvTest() {
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
     * Test of discoverUsable method, of class DiscoverObjRestSrv.
     */
    @Ignore
    public void testDiscoverUsable() {
        System.out.println("discoverUsable");
        String authorization = "";
        String requestedId = "";
        DiscoverObjRestSrv instance = new DiscoverObjRestSrv();
        Response expResult = null;
        Response result = instance.discoverUsable(authorization, requestedId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of discoverOwned method, of class DiscoverObjRestSrv.
     */
    @Ignore
    public void testDiscoverOwned() {
        System.out.println("discoverOwned");
        String authorization = "";
        String ownerId = "";
        DiscoverObjRestSrv instance = new DiscoverObjRestSrv();
        Response expResult = null;
        Response result = instance.discoverOwned(authorization, ownerId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

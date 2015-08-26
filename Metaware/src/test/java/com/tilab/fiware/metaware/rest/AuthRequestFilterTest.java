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
package com.tilab.fiware.metaware.rest;

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class AuthRequestFilterTest {

    public AuthRequestFilterTest() {
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
     * Test of filter method, of class AuthRequestFilter.
     */
    @Ignore
    public void testFilter() {
        System.out.println("filter");
        ContainerRequestContext crc = null;
        AuthRequestFilter instance = new AuthRequestFilter();
        instance.filter(crc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of securityRoleFilter method, of class AuthRequestFilter.
     */
    @Ignore
    public void testSecurityRoleFilter() {
        System.out.println("securityRoleFilter");
        User user = null;
        String verb = "";
        String path = "";
        AuthRequestFilter instance = new AuthRequestFilter();
        boolean expResult = false;
        boolean result = instance.securityRoleFilter(user, verb, path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPerm method, of class AuthRequestFilter.
     */
    @Ignore
    public void testCheckPerm() {
        System.out.println("checkPerm");
        List<Permission> permissionsList = null;
        String userId = "";
        CharSequence perm = null;
        AuthRequestFilter instance = new AuthRequestFilter();
        boolean expResult = false;
        boolean result = instance.checkPerm(permissionsList, userId, perm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

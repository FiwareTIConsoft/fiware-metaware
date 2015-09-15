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

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
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
public class DepartmentRestSrvTest {

    public DepartmentRestSrvTest() {
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
     * Test of getDepartmentsList method, of class DepartmentRestSrv.
     */
    @Ignore
    public void testGetDepartmentsList() {
        System.out.println("getDepartmentsList");
        String authorization = "";
        DepartmentRestSrv instance = new DepartmentRestSrv();
        Response expResult = null;
        Response result = instance.getDepartmentsList(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepartment method, of class DepartmentRestSrv.
     */
    @Ignore
    public void testGetDepartment() {
        System.out.println("getDepartment");
        String authorization = "";
        String id = "";
        DepartmentRestSrv instance = new DepartmentRestSrv();
        Response expResult = null;
        Response result = instance.getDepartment(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDepartment method, of class DepartmentRestSrv.
     */
    @Ignore
    public void testCreateDepartment() {
        System.out.println("createDepartment");
        String authorization = "";
        Department department = null;
        DepartmentRestSrv instance = new DepartmentRestSrv();
        Response expResult = null;
        Response result = instance.createDepartment(authorization, department);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertDepartment method, of class DepartmentRestSrv.
     */
    @Ignore
    public void testUpsertDepartment() {
        System.out.println("upsertDepartment");
        String authorization = "";
        String id = "";
        Department department = null;
        DepartmentRestSrv instance = new DepartmentRestSrv();
        Response expResult = null;
        Response result = instance.upsertDepartment(authorization, id, department);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDepartment method, of class DepartmentRestSrv.
     */
    @Ignore
    public void testDeleteDepartment() {
        System.out.println("deleteDepartment");
        String authorization = "";
        String id = "";
        DepartmentRestSrv instance = new DepartmentRestSrv();
        Response expResult = null;
        Response result = instance.deleteDepartment(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

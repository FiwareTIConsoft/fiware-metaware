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

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
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
public class CompanyRestSrvTest {

    public CompanyRestSrvTest() {
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
     * Test of getCompaniesList method, of class CompanyRestSrv.
     */
    @Ignore
    public void testGetCompaniesList() {
        System.out.println("getCompaniesList");
        String authorization = "";
        CompanyRestSrv instance = new CompanyRestSrv();
        Response expResult = null;
        Response result = instance.getCompaniesList(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompany method, of class CompanyRestSrv.
     */
    @Ignore
    public void testGetCompany() {
        System.out.println("getCompany");
        String authorization = "";
        String id = "";
        CompanyRestSrv instance = new CompanyRestSrv();
        Response expResult = null;
        Response result = instance.getCompany(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCompany method, of class CompanyRestSrv.
     */
    @Ignore
    public void testCreateCompany() {
        System.out.println("createCompany");
        String authorization = "";
        Company company = null;
        CompanyRestSrv instance = new CompanyRestSrv();
        Response expResult = null;
        Response result = instance.createCompany(authorization, company);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertCompany method, of class CompanyRestSrv.
     */
    @Ignore
    public void testUpsertCompany() {
        System.out.println("upsertCompany");
        String authorization = "";
        String id = "";
        Company company = null;
        CompanyRestSrv instance = new CompanyRestSrv();
        Response expResult = null;
        Response result = instance.upsertCompany(authorization, id, company);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCompany method, of class CompanyRestSrv.
     */
    @Ignore
    public void testDeleteCompany() {
        System.out.println("deleteCompany");
        String authorization = "";
        String id = "";
        CompanyRestSrv instance = new CompanyRestSrv();
        Response expResult = null;
        Response result = instance.deleteCompany(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

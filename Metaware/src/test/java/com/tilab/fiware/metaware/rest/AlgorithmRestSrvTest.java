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

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Algorithm;
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
public class AlgorithmRestSrvTest {

    public AlgorithmRestSrvTest() {
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
     * Test of getAlgorithmsList method, of class AlgorithmRestSrv.
     */
    @Ignore
    public void testGetAlgorithmsList() {
        System.out.println("getAlgorithmsList");
        String authorization = "";
        AlgorithmRestSrv instance = new AlgorithmRestSrv();
        Response expResult = null;
        Response result = instance.getAlgorithmsList(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlgorithm method, of class AlgorithmRestSrv.
     */
    @Ignore
    public void testGetAlgorithm() {
        System.out.println("getAlgorithm");
        String authorization = "";
        String id = "";
        AlgorithmRestSrv instance = new AlgorithmRestSrv();
        Response expResult = null;
        Response result = instance.getAlgorithm(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAlgorithm method, of class AlgorithmRestSrv.
     */
    @Ignore
    public void testCreateAlgorithm() {
        System.out.println("createAlgorithm");
        String authorization = "";
        Algorithm algorithm = null;
        AlgorithmRestSrv instance = new AlgorithmRestSrv();
        Response expResult = null;
        Response result = instance.createAlgorithm(authorization, algorithm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertAlgorithm method, of class AlgorithmRestSrv.
     */
    @Ignore
    public void testUpsertAlgorithm() {
        System.out.println("upsertAlgorithm");
        String authorization = "";
        String id = "";
        Algorithm algorithm = null;
        AlgorithmRestSrv instance = new AlgorithmRestSrv();
        Response expResult = null;
        Response result = instance.upsertAlgorithm(authorization, id, algorithm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAlgorithm method, of class AlgorithmRestSrv.
     */
    @Ignore
    public void testDeleteAlgorithm() {
        System.out.println("deleteAlgorithm");
        String authorization = "";
        String id = "";
        AlgorithmRestSrv instance = new AlgorithmRestSrv();
        Response expResult = null;
        Response result = instance.deleteAlgorithm(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

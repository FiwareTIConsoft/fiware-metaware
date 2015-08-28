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

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Process;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class ProcessRestSrvTest {

    public ProcessRestSrvTest() {
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
     * Test of getProcessesList method, of class ProcessRestSrv.
     */
    @Ignore
    public void testGetProcessesList() {
        System.out.println("getProcessesList");
        String authorization = "";
        ProcessRestSrv instance = new ProcessRestSrv();
        Response expResult = null;
        Response result = instance.getProcessesList(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProcess method, of class ProcessRestSrv.
     */
    @Ignore
    public void testGetProcess() {
        System.out.println("getProcess");
        String authorization = "";
        String id = "";
        ProcessRestSrv instance = new ProcessRestSrv();
        Response expResult = null;
        Response result = instance.getProcess(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createProcess method, of class ProcessRestSrv.
     */
    @Ignore
    public void testCreateProcess() {
        System.out.println("createProcess");
        String authorization = "";
        Process process = null;
        ProcessRestSrv instance = new ProcessRestSrv();
        Response expResult = null;
        Response result = instance.createProcess(authorization, process);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertProcess method, of class ProcessRestSrv.
     */
    @Ignore
    public void testUpsertProcess() {
        System.out.println("upsertProcess");
        String authorization = "";
        String id = "";
        Process process = null;
        ProcessRestSrv instance = new ProcessRestSrv();
        Response expResult = null;
        Response result = instance.upsertProcess(authorization, id, process);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProcess method, of class ProcessRestSrv.
     */
    @Ignore
    public void testDeleteProcess() {
        System.out.println("deleteProcess");
        String authorization = "";
        String id = "";
        ProcessRestSrv instance = new ProcessRestSrv();
        Response expResult = null;
        Response result = instance.deleteProcess(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

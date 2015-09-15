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
package com.tilab.fiware.metaware.dao;

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
public class ProcessDaoTest {

    public ProcessDaoTest() {
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
     * Test of getProcessesList method, of class ProcessDao.
     */
    @Ignore
    public void testGetProcessesList() {
        System.out.println("getProcessesList");
        ProcessDao instance = new ProcessDaoImpl();
        List<Process> expResult = null;
        List<Process> result = instance.getProcessesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProcess method, of class ProcessDao.
     */
    @Ignore
    public void testGetProcess() {
        System.out.println("getProcess");
        String id = "";
        ProcessDao instance = new ProcessDaoImpl();
        Process expResult = null;
        Process result = instance.getProcess(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createProcess method, of class ProcessDao.
     */
    @Ignore
    public void testCreateProcess() {
        System.out.println("createProcess");
        Process process = null;
        ProcessDao instance = new ProcessDaoImpl();
        String expResult = "";
        String result = instance.createProcess(process);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertProcess method, of class ProcessDao.
     */
    @Ignore
    public void testUpsertProcess() {
        System.out.println("upsertProcess");
        String id = "";
        Process process = null;
        ProcessDao instance = new ProcessDaoImpl();
        Process expResult = null;
        Process result = instance.upsertProcess(id, process);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProcess method, of class ProcessDao.
     */
    @Ignore
    public void testDeleteProcess() {
        System.out.println("deleteProcess");
        String id = "";
        ProcessDao instance = new ProcessDaoImpl();
        instance.deleteProcess(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ProcessDaoImpl implements ProcessDao {

        public List<Process> getProcessesList() {
            return null;
        }

        public Process getProcess(String id) {
            return null;
        }

        public String createProcess(Process process) {
            return "";
        }

        public Process upsertProcess(String id, Process process) {
            return null;
        }

        public void deleteProcess(String id) {
        }
    }

}

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
package com.tilab.fiware.metaware.service;

import static com.tilab.fiware.metaware.core.SingltProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Process;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.ProcessingBlock;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class ProcessServiceTest {

    private static Properties testProperties;

    // Temporary data
    private static Process proc1, proc2;
    private static Permission perm1, perm2;
    private static User user1, user2;
    private static Company comp;
    private static Department dep;
    private static String compId, depId, userId1, userId2, procId1, procId2;

    public ProcessServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test
        INSTANCE.setManualProperties(testProperties);

        INSTANCE.createCoreObjects();

        // Objects definition
        comp = new Company("company test name", "company test description", "company@test.com",
                "123456", "Via Reiss Romoli, 274 Torino", "company.test.one.com");
        dep = new Department("department test name", "department test description", null, // company id is set after
                "dep@test.com", "123456", "Via Reiss Romoli, 274 Torino", "http://dep.test.com");
        user1 = new User("user test name 1", "user test surname 1", "user1@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", null, null, "usernametestalgo1", "secret", ""); // company id and department id are set after
        user2 = new User("user test name 2", "user test surname 2", "user2@test.com", "654321",
                "Via Reiss Romoli, 274 Torino", null, null, "usernametestalgo2", "secret", ""); // company id and department id are set after
        perm1 = new Permission(null, "rud"); // user id is set after
        perm2 = new Permission(null, "r"); // user id is set after
        proc1 = new Process("process test name 1", "process test description 1", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, null, "private", "daily", Long.MIN_VALUE,
                Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<ProcessingBlock>(),
                "http://process.test.one");
        proc2 = new Process("process test name 2", "process test description 2", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, null, "private", "hourly", Long.MIN_VALUE,
                Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<ProcessingBlock>(),
                "http://process.test.two");
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeCoreObjects();
    }

    @Before
    public void setUp() {
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep.setCompany(compId);
        depId = INSTANCE.getDepartmentService().createDepartment(dep);
        user1.setCompany(compId);
        user1.setDepartment(depId);
        userId1 = INSTANCE.getUserService().createUser(user1);
        user2.setCompany(compId);
        user2.setDepartment(depId);
        userId2 = INSTANCE.getUserService().createUser(user2);
        perm1.setReference(userId1);
        perm2.setReference(userId2);
        proc1.setPermissions(Arrays.asList(perm1));
        proc1.setOwner(userId2);
        procId1 = INSTANCE.getProcessService().createProcess(proc1);
        proc2.setPermissions(Arrays.asList(perm2));
        proc2.setOwner(userId1);
        procId2 = INSTANCE.getProcessService().createProcess(proc2);
    }

    @After
    public void tearDown() {
        INSTANCE.getProcessService().deleteProcess(procId1);
        INSTANCE.getProcessService().deleteProcess(procId2);
        INSTANCE.getUserService().deleteUser(userId1);
        INSTANCE.getUserService().deleteUser(userId2);
        INSTANCE.getDepartmentService().deleteDepartment(depId);
        INSTANCE.getCompanyService().deleteCompany(compId);
    }

    /**
     * Test of getProcessesList method, of class ProcessService.
     */
    @Test
    public void testGetProcessesList() {
        System.out.println("getProcessesList");
        ProcessService instance = INSTANCE.getProcessService();
        List<Process> expResult = new ArrayList<>();
        expResult.add(proc1);
        expResult.add(proc2);
        List<Process> result = instance.getProcessesList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult)); // same elements
    }

    /**
     * Test of getProcess method, of class ProcessService.
     */
    @Test
    public void testGetProcess() {
        System.out.println("getProcess");
        String id = procId1;
        ProcessService instance = INSTANCE.getProcessService();
        Process expResult = proc1;
        Process result = instance.getProcess(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createProcess method, of class ProcessService.
     */
    @Test
    public void testCreateProcess() {
        System.out.println("createProcess");
        Process process = new Process("process test name", "process test description",
                "test type", Long.MIN_VALUE, Long.MIN_VALUE, null, null, "private", "hourly",
                Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<ProcessingBlock>(),
                "http://process.test.two");
        process.setPermissions(Arrays.asList(perm1));
        process.setOwner(userId2);
        Process expResult = process;
        ProcessService instance = INSTANCE.getProcessService();
        String id = instance.createProcess(process);
        Process result = instance.getProcess(id);
        assertEquals(expResult, result);
        instance.deleteProcess(id);
    }

    /**
     * Test of upsertProcess method, of class ProcessService.
     */
    @Test
    public void testUpsertProcess() {
        System.out.println("upsertProcess");
        String id = procId2;
        Process process = new Process();
        process.setName("new process name");
        process.setDescription("new process description");
        process.setCreationDate(Long.MIN_VALUE);
        process.setLastModifiedDate(proc2.getLastModifiedDate());
        process.setPermissions(Arrays.asList(perm1));
        process.setOwner(userId2);
        process.setStatus("public");
        process.setFrequence("daily");
        process.setStartingTime(Long.MAX_VALUE);
        process.setLastRunTime(Long.MIN_VALUE);
        process.setRunNumber(100L);
        process.setProcessingBlocks(new ArrayList<ProcessingBlock>());
        process.setLogUrl("http://another.url.test/");
        ProcessService instance = INSTANCE.getProcessService();
        Process expResult = process;
        Process result = instance.upsertProcess(id, process);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteProcess method, of class ProcessService.
     */
    @Test
    public void testDeleteProcess() {
        System.out.println("deleteProcess");
        ProcessService instance = INSTANCE.getProcessService();
        Process process = new Process("process test name", "process test description",
                "test type", Long.MIN_VALUE, Long.MIN_VALUE, null, null, "private", "hourly",
                Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, new ArrayList<ProcessingBlock>(),
                "http://process.test.two");
        process.setPermissions(Arrays.asList(perm1));
        process.setOwner(userId2);
        String id = instance.createProcess(process);
        instance.deleteProcess(id);
    }

}

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
package com.tilab.fiware.metaware.dao.impls.mongodb;

import com.mongodb.DBCollection;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.PROCESSES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.USERS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Process;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.ProcessingBlock;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class ProcessDaoTest {

    private static Properties testProperties;

    // MongoDB objects
    private static DBCollection processesCollection;
    private static DBCollection usersCollection;

    // Temporary data
    private static Process proc1, proc2;
    private static Permission perm1, perm2, perm3;
    private static User user1, user2;

    public ProcessDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test

        INSTANCE.createDaoObjects(testProperties);

        // Create 3 users
        usersCollection = INSTANCE.getDatasource().getDbCollection(USERS_COLLECTION_NAME);
        usersCollection.setObjectClass(User.class);
        user1 = new User("user test name 1", "user test surname 1", "userr1@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(), new ObjectId(),
                "usernametestdataset1", "secret", "");
        user2 = new User("user test name 2", "user test surname 2", "userr2@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", new ObjectId(), new ObjectId(),
                "usernametestdataset2", "secret", "");

        // Create 2 processes (moved to setUp to obtain ux's Id)
        processesCollection = INSTANCE.getDatasource().getDbCollection(PROCESSES_COLLECTION_NAME);
        processesCollection.setObjectClass(Process.class);
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeDaoObjects();
    }

    @Before
    public void setUp() {
        // Insert 2 users
        List users = new ArrayList();
        users.add(user1);
        users.add(user2);
        usersCollection.insert(users);

        // Create 3 permissions
        perm1 = new Permission(new ObjectId(user1.getId()), "rud"); // user1 can read, update, and delete
        perm2 = new Permission(new ObjectId(user1.getId()), "r"); // user1 can read
        perm3 = new Permission(new ObjectId(user2.getId()), "rud"); // user2 can read, update, and delete
        List<Permission> permissionsData1 = new ArrayList<>();
        permissionsData1.add(perm1);
        permissionsData1.add(perm3);
        List<Permission> permissionsData2 = new ArrayList<>();
        permissionsData2.add(perm2);

        // Create 2 processes
        proc1 = new Process("process test name 1", "process test description 1", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, permissionsData1, new ObjectId(user2.getId()),
                "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE,
                new ArrayList<ProcessingBlock>(), "http://process.one.test");
        proc2 = new Process("process test name 2", "process test description 2", "test",
                Long.MAX_VALUE, Long.MAX_VALUE, permissionsData2, new ObjectId(user1.getId()),
                "test status", "hourly", Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE,
                new ArrayList<ProcessingBlock>(), "http://process.one.test");

        // Insert 2 processes
        List processes = new ArrayList();
        processes.add(proc1);
        processes.add(proc2);
        processesCollection.insert(processes);
    }

    @After
    public void tearDown() {
        processesCollection.drop();
        usersCollection.drop();
    }

    /**
     * Test of getProcessesList method, of class ProcessDao.
     */
    @Test
    public void testGetProcessesList() {
        System.out.println("getProcessesList");
        ProcessDao instance = new ProcessDao();
        List<Process> expResult = new ArrayList<>();
        expResult.add(proc1);
        expResult.add(proc2);
        List<Process> result = instance.getProcessesList();
        assertEquals(expResult, result);
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getProcess method, of class ProcessDao.
     */
    @Test
    public void testGetProcess() {
        System.out.println("getProcess");
        String id = proc1.getId();
        ProcessDao instance = new ProcessDao();
        Process expResult = proc1;
        Process result = instance.getProcess(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createProcess method, of class ProcessDao.
     */
    @Test
    public void testCreateProcess() {
        System.out.println("createProcess");
        List<Permission> permissionsData = new ArrayList<>();
        Process process = new Process("process test name", "process test description",
                "test", Long.MIN_VALUE, Long.MIN_VALUE, permissionsData,
                new ObjectId(user1.getId()), "test status", "daily", Long.MIN_VALUE, Long.MIN_VALUE,
                Long.MIN_VALUE, new ArrayList<ProcessingBlock>(), "http://create.process.test");
        ProcessDao instance = new ProcessDao();
        String result = instance.createProcess(process);
        assertTrue(ObjectId.isValid(result));
    }

    /**
     * Test of upsertProcess method, of class ProcessDao.
     */
    @Ignore
    public void testUpsertProcess() {
        System.out.println("upsertProcess");
        String id = proc1.getId();
        proc1.setName("this is a new name for process");
        proc1.setStatus("new status");
        ProcessDao instance = new ProcessDao();
        Process expResult = proc1;
        Process result = instance.upsertProcess(id, proc1);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteProcess method, of class ProcessDao.
     */
    @Test
    public void testDeleteProcess() {
        System.out.println("deleteProcess");
        String id = proc2.getId();
        ProcessDao instance = new ProcessDao();
        instance.deleteProcess(id);
    }

}

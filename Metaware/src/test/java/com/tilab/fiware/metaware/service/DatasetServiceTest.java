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
package com.tilab.fiware.metaware.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.tilab.fiware.metaware.core.SingltProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Dataset;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DatasetStructure;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
//import org.json.JSONException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DatasetServiceTest {

    private static Properties testProperties;

    // Temporary data
    private static Dataset data1, data2;
    private static Permission perm1, perm2;
    private static User user1, user2;
    private static Company comp;
    private static Department dep;
    private static String compId, depId, userId1, userId2, dataId1, dataId2;

    public DatasetServiceTest() {
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
        data1 = new Dataset("dataset test name 1", "dataset test description 1", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, null, null, "private", true,
                new DatasetStructure());
        data2 = new Dataset("dataset test name 2", "dataset test description 2", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, null, null, "private", true,
                new DatasetStructure());
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
        data1.setPermissions(Arrays.asList(perm1));
        data1.setOwner(userId2);
        dataId1 = INSTANCE.getDatasetService().createDataset(data1);
        data2.setPermissions(Arrays.asList(perm2));
        data2.setOwner(userId1);
        dataId2 = INSTANCE.getDatasetService().createDataset(data2);
    }

    @After
    public void tearDown() {
        INSTANCE.getDatasetService().deleteDataset(dataId1);
        INSTANCE.getDatasetService().deleteDataset(dataId2);
        INSTANCE.getUserService().deleteUser(userId1);
        INSTANCE.getUserService().deleteUser(userId2);
        INSTANCE.getDepartmentService().deleteDepartment(depId);
        INSTANCE.getCompanyService().deleteCompany(compId);
    }

    /**
     * Test of getDatasetsList method, of class DatasetService.
     */
    @Test
    public void testGetDatasetsList() {
        System.out.println("getDatasetsList");
        DatasetService instance = INSTANCE.getDatasetService();
        List<Dataset> expResult = new ArrayList<>();
        expResult.add(data1);
        expResult.add(data2);
        List<Dataset> result = instance.getDatasetsList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getDataset method, of class DatasetService.
     */
    @Test
    public void testGetDataset() {
        System.out.println("getDataset");
        String id = dataId1;
        DatasetService instance = INSTANCE.getDatasetService();
        Dataset expResult = data1;
        Dataset result = instance.getDataset(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createDataset method, of class DatasetService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testCreateDataset() throws JsonProcessingException {
        System.out.println("createDataset");
        Dataset dataset = new Dataset("dataset test name", "dataset test description", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, null, null, "private", true,
                new DatasetStructure());
        dataset.setPermissions(Arrays.asList(perm1));
        dataset.setOwner(userId2);
        Dataset expResult = dataset;
        DatasetService instance = INSTANCE.getDatasetService();
        String id = instance.createDataset(dataset);
        Dataset result = instance.getDataset(id);
        assertEquals(expResult, result);
        instance.deleteDataset(id);
    }

    /**
     * Test of upsertDataset method, of class DatasetService.
     */
    @Test
    public void testUpsertDataset() {
        System.out.println("upsertDataset");
        String id = dataId2;
        Dataset dataset = new Dataset();
        dataset.setName("new dataset name");
        dataset.setDescription("new dataset description");
        dataset.setCreationDate(Long.MAX_VALUE);
        dataset.setLastModifiedDate(data2.getLastModifiedDate());
        dataset.setPermissions(Arrays.asList(perm1));
        dataset.setOwner(userId2);
        dataset.setStatus("public");
        dataset.setReadOnly(true);
        dataset.setStructure(new DatasetStructure());
        DatasetService instance = new DatasetService();
        Dataset expResult = dataset;
        Dataset result = instance.upsertDataset(id, dataset);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteDataset method, of class DatasetService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testDeleteDataset() throws JsonProcessingException {
        System.out.println("deleteDataset");
        DatasetService instance = INSTANCE.getDatasetService();
        Dataset dataset = new Dataset("dataset test name", "dataset test description", "test",
                Long.MIN_VALUE, Long.MIN_VALUE, null, null, "private", true,
                new DatasetStructure());
        dataset.setPermissions(Arrays.asList(perm1));
        dataset.setOwner(userId2);
        String id = instance.createDataset(dataset);
        instance.deleteDataset(id);
    }

}

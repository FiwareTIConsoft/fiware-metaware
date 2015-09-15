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
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DataSource;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
//import org.json.JSONException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DataSourceServiceTest {

    private static Properties testProperties;

    // Temporary data
    private static DataSource datas1, datas2;
    private static Permission perm1, perm2;
    private static User user1, user2;
    private static Company comp;
    private static Department dep;
    private static String compId, depId, userId1, userId2, datasId1, datasId2, datasId3;

    public DataSourceServiceTest() {
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
        datas1 = new DataSource("datasource test 1", "this is just a test 1", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, null, "test status", "test subtype",
                "jdbc:mysql://localhost/test/one", "testUsernameOne", "superSecretOne", "query",
                "SELECT * FROM TEST");
        datas2 = new DataSource("datasource test 2", "this is just a test 2", "test type",
                Long.MIN_VALUE, Long.MIN_VALUE, null, null, "test status", "test subtype",
                "jdbc:mysql://localhost/test/two", "testUsernameTwo", "superSecretTwo", "query",
                "SELECT * FROM TEST");
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeCoreObjects();
    }

    @Before
    public void setUp() throws JsonProcessingException, NoSuchAlgorithmException {
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
        datas1.setPermissions(Arrays.asList(perm1));
        datas1.setOwner(userId2);
        datasId1 = INSTANCE.getDataSourceService().createDataSource(datas1);
        datas2.setPermissions(Arrays.asList(perm2));
        datas2.setOwner(userId1);
        datasId2 = INSTANCE.getDataSourceService().createDataSource(datas2);
    }

    @After
    public void tearDown() {
        INSTANCE.getDataSourceService().deleteDataSource(datasId1);
        INSTANCE.getDataSourceService().deleteDataSource(datasId2);
        INSTANCE.getUserService().deleteUser(userId1);
        INSTANCE.getUserService().deleteUser(userId2);
        INSTANCE.getDepartmentService().deleteDepartment(depId);
        INSTANCE.getCompanyService().deleteCompany(compId);
    }

    /**
     * Test of getDataSourcesList method, of class DataSourceService.
     */
    @Test
    public void testGetDataSourcesList() {
        System.out.println("getDataSourcesList");
        DataSourceService instance = new DataSourceService();
        List<DataSource> expResult = new ArrayList<>();
        expResult.add(datas1);
        expResult.add(datas2);
        List<DataSource> result = instance.getDataSourcesList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getDataSource method, of class DataSourceService.
     */
    @Test
    public void testGetDataSource() {
        System.out.println("getDataSource");
        String id = datasId1;
        DataSourceService instance = new DataSourceService();
        DataSource expResult = datas1;
        DataSource result = instance.getDataSource(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createDataSource method, of class DataSourceService.
     */
    @Test
    public void testCreateDataSource() {
        System.out.println("createDataSource");
        DataSource datasource = new DataSource("datasource test", "this is just a test",
                "test type", Long.MIN_VALUE, Long.MIN_VALUE, null, null, "test status",
                "test subtype", "jdbc:mysql://localhost/test", "testUsername", "superSecret",
                "query", "SELECT * FROM TEST");
        DataSourceService instance = new DataSourceService();
        datasource.setPermissions(Arrays.asList(perm1));
        datasource.setOwner(userId2);
        DataSource expResult = datasource;
        String datasId = instance.createDataSource(datasource);
        DataSource result = instance.getDataSource(datasId);
        assertEquals(expResult, result);
        instance.deleteDataSource(datasId);
    }

    /**
     * Test of upsertDataSource method, of class DataSourceService.
     */
    @Test
    public void testUpsertDataSource() {
        System.out.println("upsertDataSource");
        String id = datasId1;
        DataSource datasource = new DataSource();
        datasource.setName("the new test name");
        datasource.setDescription(datas1.getDescription());
        datasource.setType(datas1.getType());
        datasource.setCreationDate(datas1.getCreationDate());
        datasource.setLastModifiedDate(datas1.getLastModifiedDate());
        datasource.setPermissions(Arrays.asList(perm2));
        datasource.setOwner(userId1);
        datasource.setStatus("public");
        datasource.setSubtype(datas1.getSubtype());
        datasource.setURL(datas1.getURL());
        datasource.setUsername("newUsername");
        datasource.setPassword(datas1.getPassword());
        datasource.setResourceType(datas1.getResourceType());
        datasource.setResource(datas1.getResource());
        DataSourceService instance = new DataSourceService();
        DataSource expResult = datasource;
        DataSource result = instance.upsertDataSource(id, datasource);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteDataSource method, of class DataSourceService.
     */
    @Test
    public void testDeleteDataSource() {
        System.out.println("deleteDataSource");
        DataSource datasource = new DataSource("datasource test", "this is just a test",
                "test type", Long.MIN_VALUE, Long.MIN_VALUE, null, null, "test status",
                "test subtype", "jdbc:mysql://localhost/test", "testUsername", "superSecret",
                "query", "SELECT * FROM TEST");
        DataSourceService instance = new DataSourceService();
        datasource.setPermissions(Arrays.asList(perm1));
        datasource.setOwner(userId2);
        DataSource expResult = datasource;
        String datasId = instance.createDataSource(datasource);
        instance.deleteDataSource(datasId);
    }

}

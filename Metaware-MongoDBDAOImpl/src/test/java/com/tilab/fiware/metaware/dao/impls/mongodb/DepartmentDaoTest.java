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
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.COMPANIES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.DEPARTMENTS_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
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

/**
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class DepartmentDaoTest {

    private static Properties testProperties;

    // MongoDB objects
    private static DBCollection companiesCollection;
    private static DBCollection departmentsCollection;

    // Temporary data
    private static Company comp1;
    private static Department dep1, dep2;

    public DepartmentDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test

        INSTANCE.createDaoObjects(testProperties);

        // Create 1 company
        companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        companiesCollection.setObjectClass(Company.class);
        comp1 = new Company("company test name 1", "company test description 1", "company1@test.com",
                "123456", "Via Reiss Romoli, 274 Torino", "company.one.test");

        // Create 2 departments (moved to setUp to get comp1.Id)
        departmentsCollection = INSTANCE.getDatasource().
                getDbCollection(DEPARTMENTS_COLLECTION_NAME);
        departmentsCollection.setObjectClass(Department.class);
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeDaoObjects();
    }

    @Before
    public void setUp() {
        // Insert 1 company
        companiesCollection.insert(comp1);

        // Create 2 departments
        dep1 = new Department("dep test name 1", "dep test description 1",
                new ObjectId(comp1.getId()), "dep.one@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", "http://www.dep.test.one.com");
        dep2 = new Department("dep test name 2", "dep test description 2",
                new ObjectId(comp1.getId()), "dep.two@test.com", "123456",
                "Via Reiss Romoli, 274 Torino", "http://www.dep.test.two.com");

        // Insert 2 departments
        List departments = new ArrayList();
        departments.add(dep1);
        departments.add(dep2);
        departmentsCollection.insert(departments);
    }

    @After
    public void tearDown() {
        companiesCollection.drop();
        departmentsCollection.drop();
    }

    /**
     * Test of getDepartmentsList method, of class DepartmentDao.
     */
    @Test
    public void testGetDepartmentsList() {
        System.out.println("getDepartmentsList");
        DepartmentDao instance = new DepartmentDao();
        List<Department> expResult = new ArrayList<>();
        expResult.add(dep1);
        expResult.add(dep2);
        List<Department> result = instance.getDepartmentsList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getDepartment method, of class DepartmentDao.
     */
    @Test
    public void testGetDepartment() {
        System.out.println("getDepartment");
        String id = dep1.getId();
        DepartmentDao instance = new DepartmentDao();
        Department expResult = dep1;
        Department result = instance.getDepartment(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createDepartment method, of class DepartmentDao.
     */
    @Test
    public void testCreateDepartment() {
        System.out.println("createDepartment");
        Department department = new Department("dep test name", "dep test description",
                new ObjectId(comp1.getId()), "dep@test.com", "123456", "Via Reiss Romoli, 274 Torino", "http://www.dep.test.com");
        DepartmentDao instance = new DepartmentDao();
        String result = instance.createDepartment(department);
        assertTrue(ObjectId.isValid(result));
    }

    /**
     * Test of upsertDepartment method, of class DepartmentDao.
     */
    @Test
    public void testUpsertDepartment() {
        System.out.println("upsertDepartment");
        String id = dep1.getId();
        dep1.setName("new test name");
        dep1.setEmail("amazing.mail@test.com");
        DepartmentDao instance = new DepartmentDao();
        Department expResult = dep1;
        Department result = instance.upsertDepartment(id, dep1);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteDepartment method, of class DepartmentDao.
     */
    @Test
    public void testDeleteDepartment() {
        System.out.println("deleteDepartment");
        String id = dep1.getId();
        DepartmentDao instance = new DepartmentDao();
        instance.deleteDepartment(id);
    }

}

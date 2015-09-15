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
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
public class DepartmentServiceTest {

    private static Properties testProperties;

    // Temporary data
    private static String depId1, depId2, compId;
    private static Department dep1, dep2;
    private static Company comp;

    public DepartmentServiceTest() {
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
        comp = new Company("company test name 1", "company test description 1",
                "company.one@test.com", "123456", "Via Reiss Romoli, 274 Torino", "company.test.one.com");
        dep1 = new Department("department test name 1", "department test description 1", null, // company id is set after
                "dep.one@test.com", "123456", "Via Reiss Romoli, 274 Torino", "http://dep.one.test.com");
        dep2 = new Department("department test name 2", "department test description 2", null, // company id is set after
                "dep.two@test.com", "654321", "Via Reiss Romoli, 274 Torino", "http://dep.two.test.com");
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeCoreObjects();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDepartmentsList method, of class DepartmentService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testGetDepartmentsList() throws JsonProcessingException {
        System.out.println("getDepartmentsList");
        DepartmentService instance = INSTANCE.getDepartmentService();
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep1.setCompany(compId);
        depId1 = instance.createDepartment(dep1);
        dep2.setCompany(compId);
        depId2 = instance.createDepartment(dep2);

        List<Department> expResult = new ArrayList<>();
        expResult.add(dep1);
        expResult.add(dep2);
        List<Department> result = instance.getDepartmentsList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));

        INSTANCE.getCompanyService().deleteCompany(compId);
        instance.deleteDepartment(depId1);
        instance.deleteDepartment(depId2);
    }

    /**
     * Test of getDepartment method, of class DepartmentService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testGetDepartment() throws JsonProcessingException {
        System.out.println("getDepartment");
        DepartmentService instance = new DepartmentService();
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep1.setCompany(compId);
        depId1 = instance.createDepartment(dep1);

        Department expResult = dep1;
        Department result = instance.getDepartment(depId1);
        assertEquals(expResult, result);

        INSTANCE.getCompanyService().deleteCompany(compId);
        instance.deleteDepartment(depId1);
    }

    /**
     * Test of createDepartment method, of class DepartmentService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testCreateDepartment() throws JsonProcessingException {
        System.out.println("createDepartment");
        DepartmentService instance = new DepartmentService();

        Department department = new Department("dep test name", "dep test description", null, // company id is set after
                "dep.one@test.com", "123456", "Via Reiss Romoli, 274 Torino",
                "http://dep.one.test.com");
        compId = INSTANCE.getCompanyService().createCompany(comp);
        department.setCompany(compId);
        String depId = instance.createDepartment(department);
        Department result = instance.getDepartment(depId);
        assertEquals(department, result);

        INSTANCE.getCompanyService().deleteCompany(compId);
        instance.deleteDepartment(depId);
    }

    /**
     * Test of upsertDepartment method, of class DepartmentService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testUpsertDepartment() throws JsonProcessingException {
        System.out.println("upsertDepartment");
        DepartmentService instance = new DepartmentService();
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep1.setCompany(compId);
        String depId = instance.createDepartment(dep1);

        Department expResult = new Department();
        expResult.setName("new name for department");
        expResult.setDescription(dep1.getDescription());
        expResult.setEmail("new.email@dep.com");
        expResult.setPhone(dep1.getPhone());
        expResult.setUrl(dep1.getUrl());
        expResult.setCompany(compId);
        Department result = instance.upsertDepartment(depId, expResult);
        assertEquals(expResult, result);

        INSTANCE.getCompanyService().deleteCompany(compId);
        instance.deleteDepartment(depId);
    }

    /**
     * Test of deleteDepartment method, of class DepartmentService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testDeleteDepartment() throws JsonProcessingException {
        System.out.println("deleteDepartment");
        DepartmentService instance = new DepartmentService();
        compId = INSTANCE.getCompanyService().createCompany(comp);
        dep1.setCompany(compId);
        String depId = instance.createDepartment(dep1);

        INSTANCE.getCompanyService().deleteCompany(compId);
        instance.deleteDepartment(depId);
    }

}

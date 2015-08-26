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

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.tilab.fiware.metaware.core.SingltProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
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
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class CompanyServiceTest {

    private static Properties testProperties;

    // Temporary data
    private static String compId1, compId2;
    private static Company comp1, comp2;

    public CompanyServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test
        INSTANCE.setManualProperties(testProperties);

        INSTANCE.createCoreObjects();

        comp1 = new Company("company test name 1", "company test description 1",
                "company.one@test.com", "123456", "Via Reiss Romoli, 274 Torino", "company.test.one.com");
        comp2 = new Company("company test name 2", "company test description 2",
                "company.two@test.com", "654321", "Via Reiss Romoli, 274 Torino", "company.test.two.com");
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
     * Test of getCompaniesList method, of class CompanyService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testGetCompaniesList() throws JsonProcessingException {
        compId1 = INSTANCE.getCompanyService().createCompany(comp1);
        compId2 = INSTANCE.getCompanyService().createCompany(comp2);
        System.out.println("getCompaniesList");
        CompanyService instance = INSTANCE.getCompanyService();
        List<Company> expResult = new ArrayList<>();
        expResult.add(comp1);
        expResult.add(comp2);
        List<Company> result = instance.getCompaniesList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
        instance.deleteCompany(compId1);
        instance.deleteCompany(compId2);
    }

    /**
     * Test of getCompany method, of class CompanyService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testGetCompany() throws JsonProcessingException {
        System.out.println("getCompany");
        CompanyService instance = INSTANCE.getCompanyService();
        compId1 = INSTANCE.getCompanyService().createCompany(comp1);
        String id = compId1;
        Company result = instance.getCompany(id);
        Company expResult = comp1;
        assertEquals(expResult, result);
        INSTANCE.getCompanyService().deleteCompany(compId1);
    }

    /**
     * Test of createCompany method, of class CompanyService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testCreateCompany() throws JsonProcessingException {
        System.out.println("createCompany");
        CompanyService instance = INSTANCE.getCompanyService();
        Company expResult = new Company("Company Test", "This is just a Company test",
                "test@company.com", "123456789", "Via Reiss Romoli, 274 Torino", "http://companytest.com");
        String companyId = instance.createCompany(expResult);
        Company result = instance.getCompany(companyId);
        assertEquals(expResult, result);
        instance.deleteCompany(companyId);
    }

    /**
     * Test of upsertCompany method, of class CompanyService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testUpsertCompany() throws JsonProcessingException {
        System.out.println("upsertCompany");
        CompanyService instance = INSTANCE.getCompanyService();
        compId1 = instance.createCompany(comp1);
        Company expResult = new Company();
        expResult.setName(comp1.getName());
        expResult.setDescription("New description for comp1");
        expResult.setEmail(comp1.getEmail());
        expResult.setPhone(comp1.getPhone());
        expResult.setUrl("http://newurl.compant.com");
        Company result = instance.upsertCompany(compId1, expResult);
        assertEquals(expResult, result);
        instance.deleteCompany(compId1);
    }

    /**
     * Test of deleteCompany method, of class CompanyService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testDeleteCompany() throws JsonProcessingException {
        System.out.println("deleteCompany");
        CompanyService instance = INSTANCE.getCompanyService();
        compId1 = instance.createCompany(comp1);
        instance.deleteCompany(compId1);
    }

}

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
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
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
public class CompanyDaoTest {

    private static Properties testProperties;

    // MongoDB objects
    private static DBCollection companiesCollection;

    // Temporary data
    private static Company comp1, comp2;

    public CompanyDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test

        INSTANCE.createDaoObjects(testProperties);

        // Create 2 companies
        companiesCollection = INSTANCE.getDatasource().getDbCollection(COMPANIES_COLLECTION_NAME);
        companiesCollection.setObjectClass(Company.class);
        comp1 = new Company("company test name 1", "company test description 1", "company1@test.com",
                "123456", "Via Reiss Romoli, 274 Torino", "company.one.test");
        comp2 = new Company("company test name 2", "company test description 2", "company2@test.com",
                "123456", "Via Reiss Romoli, 274 Torino", "company.two.test");
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeDaoObjects();
    }

    @Before
    public void setUp() {
        // Insert 2 companies
        List companies = new ArrayList();
        companies.add(comp1);
        companies.add(comp2);
        companiesCollection.insert(companies);
    }

    @After
    public void tearDown() {
        companiesCollection.drop();
    }

    /**
     * Test of getCompaniesList method, of class CompanyDao.
     */
    @Test
    public void testGetCompaniesList() {
        System.out.println("getCompaniesList");
        CompanyDao instance = new CompanyDao();
        List<Company> expResult = new ArrayList<>();
        expResult.add(comp1);
        expResult.add(comp2);
        List<Company> result = instance.getCompaniesList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getCompany method, of class CompanyDao.
     */
    @Test
    public void testGetCompany() {
        System.out.println("getCompany");
        String id = comp1.getId();
        CompanyDao instance = new CompanyDao();
        Company expResult = comp1;
        Company result = instance.getCompany(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createCompany method, of class CompanyDao.
     */
    @Test
    public void testCreateCompany() {
        System.out.println("createCompany");
        Company company = new Company("company test name", "company test description",
                "company@test.com", "123456", "Via Reiss Romoli, 274 Torino", "company.test");
        CompanyDao instance = new CompanyDao();
        String result = instance.createCompany(company);
        assertTrue(ObjectId.isValid(result));
        instance.deleteCompany(result); // walkaround, code from setUpClass and tearDownClass should be put in setUp and tearDown
    }

    /**
     * Test of upsertCompany method, of class CompanyDao.
     */
    @Test
    public void testUpsertCompany() {
        System.out.println("upsertCompany");
        String id = comp1.getId();
        comp1.setEmail("newmale.newtest@test.com");
        comp1.setPhone("5874587458745");
        CompanyDao instance = new CompanyDao();
        Company expResult = comp1;
        Company result = instance.upsertCompany(id, comp1);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteCompany method, of class CompanyDao.
     */
    @Test
    public void testDeleteCompany() {
        System.out.println("deleteCompany");
        String id = comp2.getId();
        CompanyDao instance = new CompanyDao();
        instance.deleteCompany(id);
    }

}

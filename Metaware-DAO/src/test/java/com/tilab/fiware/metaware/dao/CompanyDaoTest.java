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

import com.tilab.fiware.metaware.dao.domain.Company;
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
public class CompanyDaoTest {
    
    public CompanyDaoTest() {
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
     * Test of getCompaniesList method, of class CompanyDao.
     */
    @Ignore
    public void testGetCompaniesList() {
        System.out.println("getCompaniesList");
        CompanyDao instance = new CompanyDaoImpl();
        List<Company> expResult = null;
        List<Company> result = instance.getCompaniesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompany method, of class CompanyDao.
     */
    @Ignore
    public void testGetCompany() {
        System.out.println("getCompany");
        String id = "";
        CompanyDao instance = new CompanyDaoImpl();
        Company expResult = null;
        Company result = instance.getCompany(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCompany method, of class CompanyDao.
     */
    @Ignore
    public void testCreateCompany() {
        System.out.println("createCompany");
        Company c = null;
        CompanyDao instance = new CompanyDaoImpl();
        String expResult = "";
        String result = instance.createCompany(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertCompany method, of class CompanyDao.
     */
    @Ignore
    public void testUpsertCompany() {
        System.out.println("upsertCompany");
        String id = "";
        Company c = null;
        CompanyDao instance = new CompanyDaoImpl();
        Company expResult = null;
        Company result = instance.upsertCompany(id, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCompany method, of class CompanyDao.
     */
    @Ignore
    public void testDeleteCompany() {
        System.out.println("deleteCompany");
        String id = "";
        CompanyDao instance = new CompanyDaoImpl();
        instance.deleteCompany(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CompanyDaoImpl implements CompanyDao {

        public List<Company> getCompaniesList() {
            return null;
        }

        public Company getCompany(String id) {
            return null;
        }

        public String createCompany(Company c) {
            return "";
        }

        public Company upsertCompany(String id, Company c) {
            return null;
        }

        public void deleteCompany(String id) {
        }
    }
    
}

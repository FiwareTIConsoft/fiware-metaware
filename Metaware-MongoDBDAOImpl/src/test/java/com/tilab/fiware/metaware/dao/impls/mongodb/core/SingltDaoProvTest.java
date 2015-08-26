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
package com.tilab.fiware.metaware.dao.impls.mongodb.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tilab.fiware.metaware.dao.impls.mongodb.AlgorithmDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.CompanyDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.DataSourceDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.DatasetDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.DepartmentDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.DiscoverObjDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.TemplateDao;
import com.tilab.fiware.metaware.dao.impls.mongodb.UserDao;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class SingltDaoProvTest {

    public SingltDaoProvTest() {
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
     * Test of values method, of class SingltDaoProv.
     */
    @Ignore
    public void testValues() {
        System.out.println("values");
        SingltDaoProv[] expResult = null;
        SingltDaoProv[] result = SingltDaoProv.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class SingltDaoProv.
     */
    @Ignore
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        SingltDaoProv expResult = null;
        SingltDaoProv result = SingltDaoProv.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDaoObjects method, of class SingltDaoProv.
     */
    @Ignore
    public void testCreateDaoObjects() {
        System.out.println("createDaoObjects");
        Properties props = null;
        SingltDaoProv instance = null;
        instance.createDaoObjects(props);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeDaoObjects method, of class SingltDaoProv.
     */
    @Ignore
    public void testCloseDaoObjects() {
        System.out.println("closeDaoObjects");
        SingltDaoProv instance = null;
        instance.closeDaoObjects();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDatasource method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetDatasource() {
        System.out.println("getDatasource");
        SingltDaoProv instance = null;
        DataSourceProvider expResult = null;
        DataSourceProvider result = instance.getDatasource();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringProperty method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetStringProperty() {
        System.out.println("getStringProperty");
        String propertyName = "";
        SingltDaoProv instance = null;
        String expResult = "";
        String result = instance.getStringProperty(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntProperty method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetIntProperty() {
        System.out.println("getIntProperty");
        String propertyName = "";
        SingltDaoProv instance = null;
        int expResult = 0;
        int result = instance.getIntProperty(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectMapper method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetObjectMapper() {
        System.out.println("getObjectMapper");
        SingltDaoProv instance = null;
        ObjectMapper expResult = null;
        ObjectMapper result = instance.getObjectMapper();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlgorithmDao method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetAlgorithmDao() {
        System.out.println("getAlgorithmDao");
        SingltDaoProv instance = null;
        AlgorithmDao expResult = null;
        AlgorithmDao result = instance.getAlgorithmDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompanyDao method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetCompanyDao() {
        System.out.println("getCompanyDao");
        SingltDaoProv instance = null;
        CompanyDao expResult = null;
        CompanyDao result = instance.getCompanyDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDatasetDao method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetDatasetDao() {
        System.out.println("getDatasetDao");
        SingltDaoProv instance = null;
        DatasetDao expResult = null;
        DatasetDao result = instance.getDatasetDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataSourceDao method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetDataSourceDao() {
        System.out.println("getDataSourceDao");
        SingltDaoProv instance = null;
        DataSourceDao expResult = null;
        DataSourceDao result = instance.getDataSourceDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepartmentDao method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetDepartmentDao() {
        System.out.println("getDepartmentDao");
        SingltDaoProv instance = null;
        DepartmentDao expResult = null;
        DepartmentDao result = instance.getDepartmentDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemplateDao method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetTemplateDao() {
        System.out.println("getTemplateDao");
        SingltDaoProv instance = null;
        TemplateDao expResult = null;
        TemplateDao result = instance.getTemplateDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserDao method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetUserDao() {
        System.out.println("getUserDao");
        SingltDaoProv instance = null;
        UserDao expResult = null;
        UserDao result = instance.getUserDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiscoverObjDao method, of class SingltDaoProv.
     */
    @Ignore
    public void testGetDiscoverObjDao() {
        System.out.println("getDiscoverObjDao");
        SingltDaoProv instance = null;
        DiscoverObjDao expResult = null;
        DiscoverObjDao result = instance.getDiscoverObjDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

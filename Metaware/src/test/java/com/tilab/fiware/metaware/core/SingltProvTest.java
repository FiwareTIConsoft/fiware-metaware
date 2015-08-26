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
package com.tilab.fiware.metaware.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tilab.fiware.metaware.service.AlgorithmService;
import com.tilab.fiware.metaware.service.CompanyService;
import com.tilab.fiware.metaware.service.DataSourceService;
import com.tilab.fiware.metaware.service.DatasetService;
import com.tilab.fiware.metaware.service.DepartmentService;
import com.tilab.fiware.metaware.service.DiscoverObjService;
import com.tilab.fiware.metaware.service.TemplateService;
import com.tilab.fiware.metaware.service.UserService;
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
public class SingltProvTest {

    public SingltProvTest() {
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
     * Test of values method, of class SingltProv.
     */
    @Ignore
    public void testValues() {
        System.out.println("values");
        SingltProv[] expResult = null;
        SingltProv[] result = SingltProv.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class SingltProv.
     */
    @Ignore
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        SingltProv expResult = null;
        SingltProv result = SingltProv.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCoreObjects method, of class SingltProv.
     */
    @Ignore
    public void testCreateCoreObjects() {
        System.out.println("createCoreObjects");
        SingltProv instance = null;
        instance.createCoreObjects();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeCoreObjects method, of class SingltProv.
     */
    @Ignore
    public void testCloseCoreObjects() {
        System.out.println("closeCoreObjects");
        SingltProv instance = null;
        instance.closeCoreObjects();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setManualProperties method, of class SingltProv.
     */
    @Ignore
    public void testSetManualProperties() {
        System.out.println("setManualProperties");
        Properties customProp = null;
        SingltProv instance = null;
        instance.setManualProperties(customProp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringProperty method, of class SingltProv.
     */
    @Ignore
    public void testGetStringProperty() {
        System.out.println("getStringProperty");
        String propertyName = "";
        SingltProv instance = null;
        String expResult = "";
        String result = instance.getStringProperty(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntProperty method, of class SingltProv.
     */
    @Ignore
    public void testGetIntProperty() {
        System.out.println("getIntProperty");
        String propertyName = "";
        SingltProv instance = null;
        int expResult = 0;
        int result = instance.getIntProperty(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectMapper method, of class SingltProv.
     */
    @Ignore
    public void testGetObjectMapper() {
        System.out.println("getObjectMapper");
        SingltProv instance = null;
        ObjectMapper expResult = null;
        ObjectMapper result = instance.getObjectMapper();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlgorithmService method, of class SingltProv.
     */
    @Ignore
    public void testGetAlgorithmService() {
        System.out.println("getAlgorithmService");
        SingltProv instance = null;
        AlgorithmService expResult = null;
        AlgorithmService result = instance.getAlgorithmService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompanyService method, of class SingltProv.
     */
    @Ignore
    public void testGetCompanyService() {
        System.out.println("getCompanyService");
        SingltProv instance = null;
        CompanyService expResult = null;
        CompanyService result = instance.getCompanyService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDatasetService method, of class SingltProv.
     */
    @Ignore
    public void testGetDatasetService() {
        System.out.println("getDatasetService");
        SingltProv instance = null;
        DatasetService expResult = null;
        DatasetService result = instance.getDatasetService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataSourceService method, of class SingltProv.
     */
    @Ignore
    public void testGetDataSourceService() {
        System.out.println("getDataSourceService");
        SingltProv instance = null;
        DataSourceService expResult = null;
        DataSourceService result = instance.getDataSourceService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepartmentService method, of class SingltProv.
     */
    @Ignore
    public void testGetDepartmentService() {
        System.out.println("getDepartmentService");
        SingltProv instance = null;
        DepartmentService expResult = null;
        DepartmentService result = instance.getDepartmentService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDiscoverObjService method, of class SingltProv.
     */
    @Ignore
    public void testGetDiscoverObjService() {
        System.out.println("getDiscoverObjService");
        SingltProv instance = null;
        DiscoverObjService expResult = null;
        DiscoverObjService result = instance.getDiscoverObjService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemplateService method, of class SingltProv.
     */
    @Ignore
    public void testGetTemplateService() {
        System.out.println("getTemplateService");
        SingltProv instance = null;
        TemplateService expResult = null;
        TemplateService result = instance.getTemplateService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserService method, of class SingltProv.
     */
    @Ignore
    public void testGetUserService() {
        System.out.println("getUserService");
        SingltProv instance = null;
        UserService expResult = null;
        UserService result = instance.getUserService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

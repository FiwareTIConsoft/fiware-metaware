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
package com.tilab.fiware.metaware.rest;

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Template;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class TemplateRestSrvTest {

    public TemplateRestSrvTest() {
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
     * Test of getTemplatesList method, of class TemplateRestSrv.
     */
    @Ignore
    public void testGetTemplatesList() {
        System.out.println("getTemplatesList");
        String authorization = "";
        TemplateRestSrv instance = new TemplateRestSrv();
        Response expResult = null;
        Response result = instance.getTemplatesList(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemplate method, of class TemplateRestSrv.
     */
    @Ignore
    public void testGetTemplate() {
        System.out.println("getTemplate");
        String authorization = "";
        String name = "";
        TemplateRestSrv instance = new TemplateRestSrv();
        Response expResult = null;
        Response result = instance.getTemplate(authorization, name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTemplate method, of class TemplateRestSrv.
     */
    @Ignore
    public void testCreateTemplate() {
        System.out.println("createTemplate");
        String authorization = "";
        Template template = null;
        TemplateRestSrv instance = new TemplateRestSrv();
        Response expResult = null;
        Response result = instance.createTemplate(authorization, template);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTemplate method, of class TemplateRestSrv.
     */
    @Ignore
    public void testDeleteTemplate() {
        System.out.println("deleteTemplate");
        String authorization = "";
        String name = "";
        TemplateRestSrv instance = new TemplateRestSrv();
        Response expResult = null;
        Response result = instance.deleteTemplate(authorization, name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

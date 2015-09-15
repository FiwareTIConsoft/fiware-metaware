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
package com.tilab.fiware.metaware.dao.impls.mongodb.domain;

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
public class TemplateTest {

    public TemplateTest() {
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
     * Test of getName method, of class Template.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "template name test";
        Template instance = new Template(expResult, new TemplateDetails());
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Template.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "new template test name";
        TemplateDetails details = new TemplateDetails();
        Template instance = new Template("template name test", details);
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getDetails method, of class Template.
     */
    @Test
    public void testGetDetails() {
        System.out.println("getDetails");
        TemplateDetails details = new TemplateDetails();
        details.append("_id", "id - string");
        details.append("name", "string");
        details.append("surname", "string");
        details.append("email", "string");
        details.append("phone", "string");
        details.append("company_id", "id - string");
        details.append("department_id", "id - string");
        details.append("username", "string");
        details.append("password", "hex - string");
        details.append("role", "string");
        Template instance = new Template("template name test", details);
        TemplateDetails result = instance.getDetails();
        assertEquals(details, result);
    }

    /**
     * Test of setDetails method, of class Template.
     */
    @Test
    public void testSetDetails() {
        System.out.println("setDetails");
        TemplateDetails details = new TemplateDetails();
        details.append("_id", "id - string");
        details.append("name", "string");
        details.append("surname", "string");
        details.append("email", "string");
        details.append("phone", "string");
        details.append("company_id", "id - string");
        details.append("department_id", "id - string");
        details.append("username", "string");
        details.append("password", "hex - string");
        details.append("role", "string");
        Template instance = new Template("template name test", null);
        instance.setDetails(details);
        assertEquals(details, instance.getDetails());
    }

}

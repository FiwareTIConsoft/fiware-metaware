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
package com.tilab.fiware.metaware.dao;

import com.tilab.fiware.metaware.dao.domain.Template;
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
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class TemplateDaoTest {

    public TemplateDaoTest() {
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
     * Test of getTemplatesList method, of class TemplateDao.
     */
    @Ignore
    public void testGetTemplatesList() {
        System.out.println("getTemplatesList");
        TemplateDao instance = new TemplateDaoImpl();
        List<Template> expResult = null;
        List<Template> result = instance.getTemplatesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemplate method, of class TemplateDao.
     */
    @Ignore
    public void testGetTemplate() {
        System.out.println("getTemplate");
        String uid = "";
        TemplateDao instance = new TemplateDaoImpl();
        Template expResult = null;
        Template result = instance.getTemplate(uid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTemplate method, of class TemplateDao.
     */
    @Ignore
    public void testCreateTemplate() {
        System.out.println("createTemplate");
        Template template = null;
        TemplateDao instance = new TemplateDaoImpl();
        String expResult = "";
        String result = instance.createTemplate(template);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TemplateDaoImpl implements TemplateDao {

        public List<Template> getTemplatesList() {
            return null;
        }

        public Template getTemplate(String uid) {
            return null;
        }

        public String createTemplate(Template template) {
            return "";
        }
    }

}

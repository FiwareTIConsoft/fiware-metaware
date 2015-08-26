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
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Template;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.TemplateDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class TemplateServiceTest {

    private static Properties testProperties;

    // Temporary data
    private static Template temp1, temp2;
    private static String tempName1, tempName2;

    public TemplateServiceTest() {
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
        temp1 = new Template("template name test 1", new TemplateDetails());
        temp2 = new Template("template name test 2", new TemplateDetails());

    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeCoreObjects();
    }

    @Before
    public void setUp() throws JsonProcessingException {
        tempName1 = INSTANCE.getTemplateService().createTemplate(temp1);
        tempName2 = INSTANCE.getTemplateService().createTemplate(temp2);
    }

    @After
    public void tearDown() {
        INSTANCE.getTemplateService().deleteTemplate(tempName1);
        INSTANCE.getTemplateService().deleteTemplate(tempName2);
    }

    /**
     * Test of getTemplatesList method, of class TemplateService.
     */
    @Test
    public void testGetTemplatesList() {
        System.out.println("getTemplatesList");
        TemplateService instance = new TemplateService();
        List<Template> expResult = new ArrayList<>();
        expResult.add(temp1);
        expResult.add(temp2);
        List<Template> result = instance.getTemplatesList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getTemplate method, of class TemplateService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testGetTemplate() throws JsonProcessingException {
        System.out.println("getTemplate");
        String name = tempName1;
        TemplateService instance = new TemplateService();
        Template expResult = temp1;
        Template result = instance.getTemplate(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of createTemplate method, of class TemplateService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testCreateTemplate() throws JsonProcessingException {
        System.out.println("createTemplate");
        Template template = new Template("template name test", new TemplateDetails());
        TemplateService instance = new TemplateService();
        String expResult = "template name test";
        String result = instance.createTemplate(template);
        assertEquals(expResult, result);
        Template expResultTemplate = template;
        Template resultTemplate = instance.getTemplate(result);
        assertEquals(expResultTemplate, resultTemplate); // double check on template object
        instance.deleteTemplate(result);
    }

    /**
     * Test of deleteTemplate method, of class TemplateService.
     *
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @Test
    public void testDeleteTemplate() throws JsonProcessingException {
        System.out.println("deleteTemplate");
        String name = "template name test";
        Template template = new Template(name, new TemplateDetails());
        TemplateService instance = new TemplateService();
        instance.createTemplate(template);
        instance.deleteTemplate(name);
    }

}

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
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.TEMPLATES_COLLECTION_NAME;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Template;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.TemplateDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
public class TemplateDaoTest {

    private static Properties testProperties;

    // MongoDB objects
    private static DBCollection templatesCollection;

    // Temporary data
    private static Template templ1, templ2;

    public TemplateDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testProperties = new Properties();
        testProperties.setProperty("db.host", "localhost");
        testProperties.setProperty("db.port", "27017");
        testProperties.setProperty("db.name", "MetadataRepoTest"); // DB test

        INSTANCE.createDaoObjects(testProperties);

        // Create 2 templates
        templatesCollection = INSTANCE.getDatasource().getDbCollection(TEMPLATES_COLLECTION_NAME);
        templatesCollection.setObjectClass(Template.class);
        templ1 = new Template("template name test 1", new TemplateDetails());
        templ2 = new Template("template name test 2", new TemplateDetails());
    }

    @AfterClass
    public static void tearDownClass() {
        INSTANCE.closeDaoObjects();
    }

    @Before
    public void setUp() {
        // Insert 2 templates
        List templates = new ArrayList();
        templates.add(templ1);
        templates.add(templ2);
        templatesCollection.insert(templates);
    }

    @After
    public void tearDown() {
        templatesCollection.drop();
    }

    /**
     * Test of getTemplatesList method, of class TemplateDao.
     */
    @Test
    public void testGetTemplatesList() {
        System.out.println("getTemplatesList");
        TemplateDao instance = new TemplateDao();
        List<Template> expResult = new ArrayList();
        expResult.add(templ1);
        expResult.add(templ2);
        List<Template> result = instance.getTemplatesList();
        assertTrue(expResult.containsAll(result) && result.containsAll(expResult));
    }

    /**
     * Test of getTemplate method, of class TemplateDao.
     */
    @Test
    public void testGetTemplate() {
        System.out.println("getTemplate");
        String name = templ1.getName();
        TemplateDao instance = new TemplateDao();
        Template expResult = templ1;
        Template result = instance.getTemplate(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of createTemplate method, of class TemplateDao.
     */
    @Test
    public void testCreateTemplate() {
        System.out.println("createTemplate");
        String name = "additional template name test";
        Template template = new Template(name, new TemplateDetails());
        TemplateDao instance = new TemplateDao();
        String result = instance.createTemplate(template);
        assertEquals(name, result); // createTemplate returns the name of the template
    }

}

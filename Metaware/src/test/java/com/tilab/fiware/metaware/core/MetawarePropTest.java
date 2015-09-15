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
package com.tilab.fiware.metaware.core;

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
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class MetawarePropTest {

    public MetawarePropTest() {
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
     * Test of getStringProperty method, of class MetawareProp.
     */
    @Ignore
    public void testGetStringProperty() {
        System.out.println("getStringProperty");
        String propertyName = "";
        MetawareProp instance = new MetawareProp();
        String expResult = "";
        String result = instance.getStringProperty(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntProperty method, of class MetawareProp.
     */
    @Ignore
    public void testGetIntProperty() {
        System.out.println("getIntProperty");
        String propertyName = "";
        MetawareProp instance = new MetawareProp();
        int expResult = 0;
        int result = instance.getIntProperty(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDsProperties method, of class MetawareProp.
     */
    @Ignore
    public void testGetDsProperties() {
        System.out.println("getDsProperties");
        MetawareProp instance = new MetawareProp();
        Properties expResult = null;
        Properties result = instance.getDsProperties();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

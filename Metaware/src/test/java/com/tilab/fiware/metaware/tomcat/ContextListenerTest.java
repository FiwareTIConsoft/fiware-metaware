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
package com.tilab.fiware.metaware.tomcat;

import javax.servlet.ServletContextEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class ContextListenerTest {

    public ContextListenerTest() {
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
     * Test of contextInitialized method, of class ContextListener.
     */
    @Ignore
    public void testContextInitialized() {
        System.out.println("contextInitialized");
        ServletContextEvent sce = null;
        ContextListener instance = new ContextListener();
        instance.contextInitialized(sce);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contextDestroyed method, of class ContextListener.
     */
    @Ignore
    public void testContextDestroyed() {
        System.out.println("contextDestroyed");
        ServletContextEvent sce = null;
        ContextListener instance = new ContextListener();
        instance.contextDestroyed(sce);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

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
package com.tilab.fiware.metaware.dao.domain;

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
public class ProcessingBlockTest {

    public ProcessingBlockTest() {
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
     * Test of getOrder method, of class ProcessingBlock.
     */
    @Ignore
    public void testGetOrder() {
        System.out.println("getOrder");
        ProcessingBlock instance = null;
        int expResult = 0;
        int result = instance.getOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrder method, of class ProcessingBlock.
     */
    @Ignore
    public void testSetOrder() {
        System.out.println("setOrder");
        int order = 0;
        ProcessingBlock instance = null;
        instance.setOrder(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBlock method, of class ProcessingBlock.
     */
    @Ignore
    public void testGetBlock() {
        System.out.println("getBlock");
        ProcessingBlock instance = null;
        String expResult = "";
        String result = instance.getBlock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBlock method, of class ProcessingBlock.
     */
    @Ignore
    public void testSetBlock() {
        System.out.println("setBlock");
        String block = "";
        ProcessingBlock instance = null;
        instance.setBlock(block);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

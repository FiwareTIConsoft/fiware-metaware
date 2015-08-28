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
package com.tilab.fiware.metaware.dao.impls.mongodb.domain;

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
    @Test
    public void testGetOrder() {
        System.out.println("getOrder");
        int expResult = 0;
        ProcessingBlock instance = new ProcessingBlock(expResult, "block test");
        int result = instance.getOrder();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrder method, of class ProcessingBlock.
     */
    @Test
    public void testSetOrder() {
        System.out.println("setOrder");
        int order = 1;
        ProcessingBlock instance = new ProcessingBlock(0, "block test");
        instance.setOrder(order);
        int result = instance.getOrder();
        assertEquals(order, result);
    }

    /**
     * Test of getBlock method, of class ProcessingBlock.
     */
    @Test
    public void testGetBlock() {
        System.out.println("getBlock");
        String expResult = "block test";
        ProcessingBlock instance = new ProcessingBlock(0, expResult);
        String result = instance.getBlock();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBlock method, of class ProcessingBlock.
     */
    @Test
    public void testSetBlock() {
        System.out.println("setBlock");
        String block = "updated block test";
        ProcessingBlock instance = new ProcessingBlock(0, "block test");
        instance.setBlock(block);
        String result = instance.getBlock();
        assertEquals(block, result);
    }

}

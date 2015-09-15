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
package com.tilab.fiware.metaware.dao;

import com.tilab.fiware.metaware.dao.domain.Algorithm;
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
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class AlgorithmDaoTest {

    public AlgorithmDaoTest() {
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
     * Test of getAlgorithmsList method, of class AlgorithmDao.
     */
    @Ignore
    public void testGetAlgorithmsList() {
        System.out.println("getAlgorithmsList");
        AlgorithmDao instance = new AlgorithmDaoImpl();
        List<Algorithm> expResult = null;
        List<Algorithm> result = instance.getAlgorithmsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlgorithm method, of class AlgorithmDao.
     */
    @Ignore
    public void testGetAlgorithm() {
        System.out.println("getAlgorithm");
        String id = "";
        AlgorithmDao instance = new AlgorithmDaoImpl();
        Algorithm expResult = null;
        Algorithm result = instance.getAlgorithm(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAlgorithm method, of class AlgorithmDao.
     */
    @Ignore
    public void testCreateAlgorithm() {
        System.out.println("createAlgorithm");
        Algorithm algorithm = null;
        AlgorithmDao instance = new AlgorithmDaoImpl();
        String expResult = "";
        String result = instance.createAlgorithm(algorithm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertAlgorithm method, of class AlgorithmDao.
     */
    @Ignore
    public void testUpsertAlgorithm() {
        System.out.println("upsertAlgorithm");
        String id = "";
        Algorithm algorithm = null;
        AlgorithmDao instance = new AlgorithmDaoImpl();
        Algorithm expResult = null;
        Algorithm result = instance.upsertAlgorithm(id, algorithm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAlgorithm method, of class AlgorithmDao.
     */
    @Ignore
    public void testDeleteAlgorithm() {
        System.out.println("deleteAlgorithm");
        String id = "";
        AlgorithmDao instance = new AlgorithmDaoImpl();
        instance.deleteAlgorithm(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AlgorithmDaoImpl implements AlgorithmDao {

        public List<Algorithm> getAlgorithmsList() {
            return null;
        }

        public Algorithm getAlgorithm(String id) {
            return null;
        }

        public String createAlgorithm(Algorithm algorithm) {
            return "";
        }

        public Algorithm upsertAlgorithm(String id, Algorithm algorithm) {
            return null;
        }

        public void deleteAlgorithm(String id) {
        }
    }

}

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

import com.tilab.fiware.metaware.dao.domain.Dataset;
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
public class DatasetDaoTest {

    public DatasetDaoTest() {
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
     * Test of getDatasetsList method, of class DatasetDao.
     */
    @Ignore
    public void testGetDatasetsList() {
        System.out.println("getDatasetsList");
        DatasetDao instance = new DatasetDaoImpl();
        List<Dataset> expResult = null;
        List<Dataset> result = instance.getDatasetsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataset method, of class DatasetDao.
     */
    @Ignore
    public void testGetDataset() {
        System.out.println("getDataset");
        String id = "";
        DatasetDao instance = new DatasetDaoImpl();
        Dataset expResult = null;
        Dataset result = instance.getDataset(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDataset method, of class DatasetDao.
     */
    @Ignore
    public void testCreateDataset() {
        System.out.println("createDataset");
        Dataset dataset = null;
        DatasetDao instance = new DatasetDaoImpl();
        String expResult = "";
        String result = instance.createDataset(dataset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertDataset method, of class DatasetDao.
     */
    @Ignore
    public void testUpsertDataset() {
        System.out.println("upsertDataset");
        String id = "";
        Dataset dataset = null;
        DatasetDao instance = new DatasetDaoImpl();
        Dataset expResult = null;
        Dataset result = instance.upsertDataset(id, dataset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDataset method, of class DatasetDao.
     */
    @Ignore
    public void testDeleteDataset() {
        System.out.println("deleteDataset");
        String id = "";
        DatasetDao instance = new DatasetDaoImpl();
        instance.deleteDataset(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DatasetDaoImpl implements DatasetDao {

        public List<Dataset> getDatasetsList() {
            return null;
        }

        public Dataset getDataset(String id) {
            return null;
        }

        public String createDataset(Dataset dataset) {
            return "";
        }

        public Dataset upsertDataset(String id, Dataset dataset) {
            return null;
        }

        public void deleteDataset(String id) {
        }
    }

}

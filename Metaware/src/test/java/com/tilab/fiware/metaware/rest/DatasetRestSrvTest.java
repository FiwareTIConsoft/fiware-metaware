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
package com.tilab.fiware.metaware.rest;

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Dataset;
import java.io.InputStream;
import javax.ws.rs.core.Response;
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
public class DatasetRestSrvTest {

    public DatasetRestSrvTest() {
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
     * Test of getDatasetsList method, of class DatasetRestSrv.
     */
    @Ignore
    public void testGetDatasetsList() {
        System.out.println("getDatasetsList");
        String authorization = "";
        DatasetRestSrv instance = new DatasetRestSrv();
        Response expResult = null;
        Response result = instance.getDatasetsList(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataset method, of class DatasetRestSrv.
     */
    @Ignore
    public void testGetDataset() {
        System.out.println("getDataset");
        String authorization = "";
        String id = "";
        DatasetRestSrv instance = new DatasetRestSrv();
        Response expResult = null;
        Response result = instance.getDataset(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDataset method, of class DatasetRestSrv.
     */
    @Ignore
    public void testCreateDataset() {
        System.out.println("createDataset");
        String authorization = "";
        Dataset dataset = null;
        DatasetRestSrv instance = new DatasetRestSrv();
        Response expResult = null;
        Response result = instance.createDataset(authorization, dataset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertDataset method, of class DatasetRestSrv.
     */
    @Ignore
    public void testUpsertDataset() {
        System.out.println("upsertDataset");
        String authorization = "";
        String id = "";
        Dataset dataset = null;
        DatasetRestSrv instance = new DatasetRestSrv();
        Response expResult = null;
        Response result = instance.upsertDataset(authorization, id, dataset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDataset method, of class DatasetRestSrv.
     */
    @Ignore
    public void testDeleteDataset() {
        System.out.println("deleteDataset");
        String authorization = "";
        String id = "";
        DatasetRestSrv instance = new DatasetRestSrv();
        Response expResult = null;
        Response result = instance.deleteDataset(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importOpenData method, of class DatasetRestSrv.
     *
     * @throws java.lang.Exception
     */
    @Ignore
    public void testImportOpenData() throws Exception {
        System.out.println("importOpenData");
        String authorization = "";
        InputStream dcatInputStream = null;
        DatasetRestSrv instance = new DatasetRestSrv();
        Response expResult = null;
        Response result = instance.importOpenData(authorization, dcatInputStream);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportOpenData method, of class DatasetRestSrv.
     */
    @Ignore
    public void testExportOpenData() {
        System.out.println("exportOpenData");
        String authorization = "";
        String id = "";
        DatasetRestSrv instance = new DatasetRestSrv();
        Response expResult = null;
        Response result = instance.exportOpenData(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

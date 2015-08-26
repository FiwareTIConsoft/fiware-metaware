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

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DataSource;
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
public class DataSourceRestSrvTest {

    public DataSourceRestSrvTest() {
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
     * Test of getDataSourcesList method, of class DataSourceRestSrv.
     */
    @Ignore
    public void testGetDataSourcesList() {
        System.out.println("getDataSourcesList");
        String authorization = "";
        DataSourceRestSrv instance = new DataSourceRestSrv();
        Response expResult = null;
        Response result = instance.getDataSourcesList(authorization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataSource method, of class DataSourceRestSrv.
     */
    @Ignore
    public void testGetDataSource() {
        System.out.println("getDataSource");
        String authorization = "";
        String id = "";
        DataSourceRestSrv instance = new DataSourceRestSrv();
        Response expResult = null;
        Response result = instance.getDataSource(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDataSource method, of class DataSourceRestSrv.
     */
    @Ignore
    public void testCreateDataSource() {
        System.out.println("createDataSource");
        String authorization = "";
        DataSource datasource = null;
        DataSourceRestSrv instance = new DataSourceRestSrv();
        Response expResult = null;
        Response result = instance.createDataSource(authorization, datasource);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertDataSource method, of class DataSourceRestSrv.
     */
    @Ignore
    public void testUpsertDataSource() {
        System.out.println("upsertDataSource");
        String authorization = "";
        String id = "";
        DataSource datasource = null;
        DataSourceRestSrv instance = new DataSourceRestSrv();
        Response expResult = null;
        Response result = instance.upsertDataSource(authorization, id, datasource);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDataSource method, of class DataSourceRestSrv.
     */
    @Ignore
    public void testDeleteDataSource() {
        System.out.println("deleteDataSource");
        String authorization = "";
        String id = "";
        DataSourceRestSrv instance = new DataSourceRestSrv();
        Response expResult = null;
        Response result = instance.deleteDataSource(authorization, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

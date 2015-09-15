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

import com.tilab.fiware.metaware.dao.domain.Department;
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
public class DepartmentDaoTest {
    
    public DepartmentDaoTest() {
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
     * Test of getDepartmentsList method, of class DepartmentDao.
     */
    @Ignore
    public void testGetDepartmentsList() {
        System.out.println("getDepartmentsList");
        DepartmentDao instance = new DepartmentDaoImpl();
        List<Department> expResult = null;
        List<Department> result = instance.getDepartmentsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepartment method, of class DepartmentDao.
     */
    @Ignore
    public void testGetDepartment() {
        System.out.println("getDepartment");
        String id = "";
        DepartmentDao instance = new DepartmentDaoImpl();
        Department expResult = null;
        Department result = instance.getDepartment(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDepartment method, of class DepartmentDao.
     */
    @Ignore
    public void testCreateDepartment() {
        System.out.println("createDepartment");
        Department d = null;
        DepartmentDao instance = new DepartmentDaoImpl();
        String expResult = "";
        String result = instance.createDepartment(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertDepartment method, of class DepartmentDao.
     */
    @Ignore
    public void testUpsertDepartment() {
        System.out.println("upsertDepartment");
        String id = "";
        Department d = null;
        DepartmentDao instance = new DepartmentDaoImpl();
        Department expResult = null;
        Department result = instance.upsertDepartment(id, d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDepartment method, of class DepartmentDao.
     */
    @Ignore
    public void testDeleteDepartment() {
        System.out.println("deleteDepartment");
        String id = "";
        DepartmentDao instance = new DepartmentDaoImpl();
        instance.deleteDepartment(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DepartmentDaoImpl implements DepartmentDao {

        public List<Department> getDepartmentsList() {
            return null;
        }

        public Department getDepartment(String id) {
            return null;
        }

        public String createDepartment(Department d) {
            return "";
        }

        public Department upsertDepartment(String id, Department d) {
            return null;
        }

        public void deleteDepartment(String id) {
        }
    }
    
}

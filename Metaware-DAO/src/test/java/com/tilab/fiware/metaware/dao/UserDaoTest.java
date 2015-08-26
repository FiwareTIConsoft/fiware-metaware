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
package com.tilab.fiware.metaware.dao;

import com.tilab.fiware.metaware.dao.domain.User;
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
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class UserDaoTest {
    
    public UserDaoTest() {
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
     * Test of getUsersList method, of class UserDao.
     */
    @Ignore
    public void testGetUsersList() {
        System.out.println("getUsersList");
        UserDao instance = new UserDaoImpl();
        List<User> expResult = null;
        List<User> result = instance.getUsersList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class UserDao.
     */
    @Ignore
    public void testGetUser() {
        System.out.println("getUser");
        String id = "";
        UserDao instance = new UserDaoImpl();
        User expResult = null;
        User result = instance.getUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUser method, of class UserDao.
     */
    @Ignore
    public void testCreateUser() {
        System.out.println("createUser");
        User u = null;
        UserDao instance = new UserDaoImpl();
        String expResult = "";
        String result = instance.createUser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upsertUser method, of class UserDao.
     */
    @Ignore
    public void testUpsertUser() {
        System.out.println("upsertUser");
        String id = "";
        User u = null;
        UserDao instance = new UserDaoImpl();
        User expResult = null;
        User result = instance.upsertUser(id, u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class UserDao.
     */
    @Ignore
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String id = "";
        UserDao instance = new UserDaoImpl();
        instance.deleteUser(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class UserDaoImpl implements UserDao {

        public List<User> getUsersList() {
            return null;
        }

        public User getUser(String id) {
            return null;
        }

        public String createUser(User u) {
            return "";
        }

        public User upsertUser(String id, User u) {
            return null;
        }

        public void deleteUser(String id) {
        }
    }
    
}

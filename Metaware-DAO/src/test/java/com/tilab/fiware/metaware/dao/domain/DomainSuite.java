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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.tilab.fiware.metaware.dao.domain.DataSourceTest.class,
    com.tilab.fiware.metaware.dao.domain.TemplateTest.class,
    com.tilab.fiware.metaware.dao.domain.UserTest.class,
    com.tilab.fiware.metaware.dao.domain.AlgorithmTest.class,
    com.tilab.fiware.metaware.dao.domain.DatasetTest.class,
    com.tilab.fiware.metaware.dao.domain.PermissionTest.class,
    com.tilab.fiware.metaware.dao.domain.DepartmentTest.class,
    com.tilab.fiware.metaware.dao.domain.CompanyTest.class,
    com.tilab.fiware.metaware.dao.domain.DatasetStructureTest.class})
public class DomainSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}

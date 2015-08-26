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

/**
 * Common constants values related to DAO.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public interface DaoCommonConstatns {

    public final static String KEY_DB_HOST = "db.host";
    public final static String KEY_DB_PORT = "db.port";
    public final static String KEY_DB_NAME = "db.name";
    public static final String KEY_DB_USERNAME = "db.username";
    public static final String KEY_DB_PASSWORD = "db.password";

    public static final String COMPANIES_COLLECTION_NAME = "companies";
    public static final String DEPARTMENTS_COLLECTION_NAME = "departments";
    public static final String DATASETS_COLLECTION_NAME = "datasets";
    public static final String DATASOURCES_COLLECTION_NAME = "datasources";
    public static final String ALGORITHMS_COLLECTION_NAME = "algorithms";
    public static final String TEMPLATES_COLLECTION_NAME = "templates";
    public static final String USERS_COLLECTION_NAME = "users";
}

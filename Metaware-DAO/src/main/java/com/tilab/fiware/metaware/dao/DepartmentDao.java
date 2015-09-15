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

/**
 * DAO interface for Department domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public interface DepartmentDao {

    /**
     * Retrieves the list of all the departments. Equivalent to "GET /departments".
     *
     * @return the list of departments.
     */
    public abstract List<Department> getDepartmentsList();

    /**
     * Retrieve the selected department. Equivalent to "GET /departments/{departmentId}".
     *
     * @param id the Id of the selected department.
     * @return the selected department.
     */
    public abstract Department getDepartment(String id);

    /**
     * Create a new department. Equivalent to "POST /departments".
     *
     * @param d the object of the new department.
     * @return the Id of the new department.
     */
    public abstract String createDepartment(Department d);

    /**
     * Update (or insert, if missing) the selected department. Equivalent to "PUT
     * /departments/{departmentId}".
     *
     * @param id the Id of the selected department to be updated.
     * @param d the department to be updated.
     * @return the updated department.
     */
    public abstract Department upsertDepartment(String id, Department d);

    /**
     * Delete the selected department. Equivalent to "DELETE /departments/{departmentId}".
     *
     * @param id the Id of the department to be deleted.
     */
    public abstract void deleteDepartment(String id);
}

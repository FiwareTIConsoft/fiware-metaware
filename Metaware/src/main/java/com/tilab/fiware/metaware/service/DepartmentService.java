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
package com.tilab.fiware.metaware.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Operations related to departments.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class DepartmentService {

    // Logger
    private static final Logger log = Logger.getLogger(DepartmentService.class);

    // Messages
    private static final String MSG_SRV_GET_LIST = "Serving departments list request...";
    private static final String MSG_SRV_GET = "Serving request for department with Id: ";
    private static final String MSG_SRV_CREATE = "Serving new department creation request...";
    private static final String MSG_SRV_UPSERT = "Serving update request for department with Id: ";
    private static final String MSG_SRV_DELETE = "Serving delete request for department with Id: ";

    /**
     * Retrieves the list of departments.
     *
     * @return the list of departments.
     */
    public List<Department> getDepartmentsList() {
        log.debug(MSG_SRV_GET_LIST);
        return INSTANCE.getDepartmentDao().getDepartmentsList();
    }

    /**
     * Retrieves the selected department.
     *
     * @param id the Id of the selected department.
     * @return the selected department object.
     * @throws JsonProcessingException
     */
    public Department getDepartment(String id) {
        log.debug(MSG_SRV_GET + id + " ...");
        return INSTANCE.getDepartmentDao().getDepartment(id);
    }

    /**
     * Create a new department.
     *
     * @param department the new department to be saved
     * @return the Id of the new department.
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public String createDepartment(Department department) throws JsonProcessingException {
        log.debug(MSG_SRV_CREATE);
        return INSTANCE.getDepartmentDao().createDepartment(department);
    }

    /**
     * Update the selected department if exists, otherwise create a new one.
     *
     * @param id the Id of the selected department to be updated.
     * @param department the department object with the modifications (or the department to be
     * saved).
     * @return the updated department object.
     * @throws JsonProcessingException
     */
    public Department upsertDepartment(String id, Department department)
            throws JsonProcessingException {
        log.debug(MSG_SRV_UPSERT + id + " ...");
        return INSTANCE.getDepartmentDao().upsertDepartment(id, department);
    }

    /**
     * Remove the selected department.
     *
     * @param id the Id of the selected department.
     */
    public void deleteDepartment(String id) {
        log.debug(MSG_SRV_DELETE + id + " ...");
        INSTANCE.getDepartmentDao().deleteDepartment(id);
    }
}

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

import java.util.List;

/**
 * DAO interface for Process domain class.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public interface ProcessDao {

    /**
     * Retrieves the list of all the processes' metadata. Equivalento to "GET /processes".
     *
     * @return the list of processes' metadata.
     */
    public List<Process> getProcessesList();

    /**
     * Retrieves the selected process' metadata. Equivalent to "GET /processes/{processId}".
     *
     * @param id the Id of the selected process'metadata.
     * @return the selected process' metadata object.
     */
    public Process getProcess(String id);

    /**
     * Creates a new process' metadata object. Equivalent to "POST /processes".
     *
     * @param process the metadata object of the new process.
     * @return the Id of the new process' metadata object.
     */
    public String createProcess(Process process);

    /**
     * Upserts (update or insert if missing) the selected process' metadata. Equivalent to "PUT
     * /processes/{processId}".
     *
     * @param id the Id of the selected process' metadata object to be updated.
     * @param process the process' metadata to be updated.
     * @return the updated process' metadata object.
     */
    public Process upsertProcess(String id, Process process);

    /**
     * Deletes the selected process' metadata. Equivalent to "DELETE /processes/{processId}".
     *
     * @param id the Id of the selected process' metadata object to be deleted.
     */
    public void deleteProcess(String id);
}

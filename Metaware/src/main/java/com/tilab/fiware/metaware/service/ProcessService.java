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

import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Process;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Operations related to processes' metadata.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class ProcessService {

    // Logger
    private static final Logger log = Logger.getLogger(ProcessService.class);

    // Messages
    private static final String MSG_SRV_GET_LIST = "Serving processes list request...";
    private static final String MSG_SRV_GET = "Serving request for process with Id: ";
    private static final String MSG_SRV_CREATE = "Serving new process creation request...";
    private static final String MSG_SRV_UPSERT = "Serving update request for process with Id: ";
    private static final String MSG_SRV_DELETE = "Serving delete request for process with Id: ";

    /**
     * Retrieves the list of processes' metadata.
     *
     * @return the list of processes' metadata.
     */
    public List<Process> getProcessesList() {
        log.debug(MSG_SRV_GET_LIST);
        return INSTANCE.getProcessDao().getProcessesList();
    }

    /**
     * Retrieves the selected process' metadata.
     *
     * @param id the Id of the selected process' metadata.
     * @return the selected process' metadata.
     */
    public Process getProcess(String id) {
        log.debug(MSG_SRV_GET + id);
        return INSTANCE.getProcessDao().getProcess(id);
    }

    /**
     * Create a new process' metadata object.
     *
     * @param process the new process' metadata to be saved.
     * @return the Id of the new process' metadata.
     */
    public String createProcess(Process process) {
        log.debug(MSG_SRV_CREATE);
        return INSTANCE.getProcessDao().createProcess(process);
    }

    /**
     * Upserts the selected process' metadata if exists, otherwise create a new one.
     *
     * @param id      the Id of the selected process' metadata to be updated.
     * @param process the process' metadata object with the modifications.
     * @return the updated process' metadata object.
     */
    public Process upsertProcess(String id, Process process) {
        log.debug(MSG_SRV_UPSERT + id);
        return INSTANCE.getProcessDao().upsertProcess(id, process);
    }

    /**
     * Remove the selected process' metadata.
     *
     * @param id the Id of the selected process' metadata.
     */
    public void deleteProcess(String id) {
        log.debug(MSG_SRV_DELETE + id);
        INSTANCE.getProcessDao().deleteProcess(id);
    }
}

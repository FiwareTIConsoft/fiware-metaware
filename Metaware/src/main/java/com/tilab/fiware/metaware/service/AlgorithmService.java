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
package com.tilab.fiware.metaware.service;

import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Algorithm;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Operations related to algorithms
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class AlgorithmService {

    // Logger
    private static final Logger log = Logger.getLogger(AlgorithmService.class);

    // Messages
    private static final String MSG_SRV_GET_LIST = "Serving algorithms' metadata list request...";
    private static final String MSG_SRV_GET = "Serving request for algorithm's metadata with Id: ";
    private static final String MSG_SRV_CREATE
            = "Serving new algorithm's metadata creation request...";
    private static final String MSG_SRV_UPSERT
            = "Serving update request for algorithm's metadata with Id: ";
    private static final String MSG_SRV_DELETE
            = "Serving delete request for algorithm's metadata with Id: ";

    /**
     * Retrieves the list of algorithms' metadata.
     *
     * @return the list of algorithms.
     */
    public List<Algorithm> getAlgorithmsList() {
        log.debug(MSG_SRV_GET_LIST);
        return INSTANCE.getAlgorithmDao().getAlgorithmsList();
    }

    /**
     * Retrieves the selected algorithm's metadata.
     *
     * @param id the Id of the selected algorithm.
     * @return the selected algorithm object.
     */
    public Algorithm getAlgorithm(String id) {
        log.debug(MSG_SRV_GET + id);
        return INSTANCE.getAlgorithmDao().getAlgorithm(id);
    }

    /**
     * Create a new algorithm's metadata object.
     *
     * @param algorithm the new metadata object to be saved.
     * @return the Id of the new algorithm's metadata object.
     */
    public String createAlgorithm(Algorithm algorithm) {
        log.debug(MSG_SRV_CREATE);
        return INSTANCE.getAlgorithmDao().createAlgorithm(algorithm);
    }

    /**
     * Update the metadata object of the selected algorithm (if it doesn't exist then create a new
     * one).
     *
     * @param id the Id of the selected algorithm's metadata object to be updated.
     * @param algoritm the updated metadata object of the selected algorithm.
     * @return the updated metadata object.
     */
    public Algorithm upsertAlgorithm(String id, Algorithm algoritm) {
        log.debug(MSG_SRV_UPSERT + id);
        return INSTANCE.getAlgorithmDao().upsertAlgorithm(id, algoritm);
    }

    /**
     * Remove the metadata object of the selected algorithm.
     *
     * @param id the Id of the selected algorithm's metadata object.
     */
    public void deleteAlgorithm(String id) {
        log.debug(MSG_SRV_DELETE + id);
        INSTANCE.getAlgorithmDao().deleteAlgorithm(id);
    }
}

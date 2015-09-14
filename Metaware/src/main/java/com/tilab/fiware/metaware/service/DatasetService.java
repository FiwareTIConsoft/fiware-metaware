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

import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Dataset;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Operations related to dataset's metadata.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class DatasetService {

    // Logger
    private static final Logger log = Logger.getLogger(DatasetService.class);

    // Messages
    private static final String MSG_SRV_GET_LIST = "Serving datasets list request...";
    private static final String MSG_SRV_GET = "Serving request for dataset with Id: ";
    private static final String MSG_SRV_CREATE = "Serving new dataset creation request...";
    private static final String MSG_SRV_UPSERT = "Serving update request for dataset with Id: ";
    private static final String MSG_SRV_DELETE = "Serving delete request for dataset with Id: ";

    /**
     * Retrieves the list of datasets' metadata.
     *
     * @return the list of datasets' metadata.
     */
    public List<Dataset> getDatasetsList() {
        log.debug(MSG_SRV_GET_LIST);
        return INSTANCE.getDatasetDao().getDatasetsList();
    }

    /**
     * Retrieves the selected dataset's metadata.
     *
     * @param id the Id of the selected dataset's metadata.
     * @return the selected dataset's metadata.
     */
    public Dataset getDataset(String id) {
        log.debug(MSG_SRV_GET);
        return INSTANCE.getDatasetDao().getDataset(id);
    }

    /**
     * Create a new dataset's metadata.
     *
     * @param dataset the new dataset's metadata to be saved.
     * @return the Id of the new dataset's metadata.
     */
    public String createDataset(Dataset dataset) {
        log.debug(MSG_SRV_CREATE);
        return INSTANCE.getDatasetDao().createDataset(dataset);
    }

    /**
     * Update the selected dataset's metadata if exists, otherwise create a new one.
     *
     * @param id      the Id of the selected dataset's metadata to be updated.
     * @param dataset the dataset's metadata object with the modifications (or the dataset's
     *                metadata to be saved).
     * @return the updated dataset's metadata object.
     */
    public Dataset upsertDataset(String id, Dataset dataset) {
        log.debug(MSG_SRV_UPSERT + id);
        return INSTANCE.getDatasetDao().upsertDataset(id, dataset);
    }

    /**
     * Remove the selected dataset's metadata.
     *
     * @param id the Id of the selected dataset's metadata.
     */
    public void deleteDataset(String id) {
        log.debug(MSG_SRV_DELETE + id);
        INSTANCE.getDatasetDao().deleteDataset(id);
    }
}

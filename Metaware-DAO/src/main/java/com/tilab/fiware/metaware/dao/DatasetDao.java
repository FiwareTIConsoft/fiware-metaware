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

import com.tilab.fiware.metaware.dao.domain.Dataset;
import java.util.List;

/**
 * DAO interface for Dataset domain class.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public interface DatasetDao {

    /**
     * Retrieves the list of all the datasets' metadata. Equivalent to "GET /datasets".
     *
     * @return the list of datasets' metadata.
     */
    public List<Dataset> getDatasetsList();

    /**
     * Retrieves the selected dataset's metadata. Equivalent to "GET /datasets/{datasetId}".
     *
     * @param id the Id of the selected dataset's metadata.
     * @return the selected dataset's metadata
     */
    public Dataset getDataset(String id);

    /**
     * Creates a new dataset's metadata. Equivalent to "POST /datasets".
     *
     * @param dataset the metadata object of the new dataset.
     * @return the Id of the new dataset's metadata.
     */
    public String createDataset(Dataset dataset);

    /**
     * Upserts (update or insert if missing) the selected dataset's metadata. Equivalent to "PUT
     * /datasets/{datasetId}"
     *
     * @param id the Id of the selected dataset's metadata to be updated.
     * @param dataset the dataset's metadata to be updated.
     * @return the updated dataset's metadata.
     */
    public Dataset upsertDataset(String id, Dataset dataset);

    /**
     * Deletes the selected dataset's metadata. Equivalent to "DELETE /datasets/{datasetId}"
     *
     * @param id the Id of the dataset's metadata to be deleted.
     */
    public void deleteDataset(String id);
}

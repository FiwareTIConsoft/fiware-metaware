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

import com.tilab.fiware.metaware.dao.domain.DataSource;
import java.util.List;

/**
 * DAO interface for Data-Source domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public interface DataSourceDao {

    /**
     * Retrieves the list of all the data-sources' metadata. Equivalent to "GET /datasources".
     *
     * @return the list of data-sources' metadata.
     */
    public List<DataSource> getDataSourcesList();

    /**
     * Retrieves the selected data-source's metadata. Equivalent to "GET
     * /datasources/{datasourceId}".
     *
     * @param id the Id of the selected data-source's metadata.
     * @return the selected data-source's metadata.
     */
    public DataSource getDataSource(String id);

    /**
     * Creates a new data-source's metadata object. Equivalent to "POST /datasources".
     *
     * @param dataSource the metadata object of the new data-source.
     * @return the Id of the new data-source's metadata object.
     */
    public String createDataSource(DataSource dataSource);

    /**
     * Upserts (update or insert if missing) the selected data-source's metadata. Equivalent to "PUT
     * /datasources/{datasourceId}".
     *
     * @param id the Id of the selected data-source's metadata to be updated.
     * @param dataSource the data-source's metadata object to be updated (it contains the
     * modifications).
     * @return the updated data-source's metadata object.
     */
    public DataSource upsertDataSource(String id, DataSource dataSource);

    /**
     * Deletes the selected data-source's metadata. Equivalent to "DELETE
     * /datasources/{datasourceId}".
     *
     * @param id the Id of the data-source's metadata to be deleted.
     */
    public void deleteDataSource(String id);
}

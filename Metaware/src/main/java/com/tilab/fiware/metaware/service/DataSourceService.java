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
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DataSource;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Operations related to data sources.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DataSourceService {

    // Logger
    private static final Logger log = Logger.getLogger(DataSourceService.class);

    // Messages
    private static final String MSG_SRV_GET_LIST = "Serving datasources' metadata list request...";
    private static final String MSG_SRV_GET = "Serving request for datasource's metadata with Id: ";
    private static final String MSG_SRV_CREATE
            = "Serving new datasource's metadata creation request...";
    private static final String MSG_SRV_UPSERT
            = "Serving update request for datasource's metadata with Id: ";
    private static final String MSG_SRV_DELETE
            = "Serving delete request for datasource's metadata with Id: ";

    /**
     *
     * @return
     */
    public List<DataSource> getDataSourcesList() {
        log.debug(MSG_SRV_GET_LIST);
        return INSTANCE.getDataSourceDao().getDataSourcesList();
    }

    /**
     *
     * @param id
     * @return
     */
    public DataSource getDataSource(String id) {
        log.debug(MSG_SRV_GET);
        return INSTANCE.getDataSourceDao().getDataSource(id);
    }

    /**
     *
     * @param datasource
     * @return
     */
    public String createDataSource(DataSource datasource) {
        log.debug(MSG_SRV_CREATE);
        return INSTANCE.getDataSourceDao().createDataSource(datasource);
    }

    /**
     *
     * @param id
     * @param datasource
     * @return
     */
    public DataSource upsertDataSource(String id, DataSource datasource) {
        log.debug(MSG_SRV_UPSERT + id);
        return INSTANCE.getDataSourceDao().upsertDataSource(id, datasource);
    }

    /**
     *
     * @param id
     */
    public void deleteDataSource(String id) {
        log.debug(MSG_SRV_DELETE + id);
        INSTANCE.getDataSourceDao().deleteDataSource(id);
    }
}

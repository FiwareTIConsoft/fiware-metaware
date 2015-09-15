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
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Operations related to discover objects.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DiscoverObjService {

    // Logger
    private static final Logger log = Logger.getLogger(DiscoverObjService.class);

    // Messages
    private static final String MSG_SRV_DISCV_USABLE
            = "Serving metadata objects discovery usable by: ";
    private static final String MSG_SRV_DISCV_OWNED
            = "Serving metadata objects discovery owned by: ";

    /**
     * Discover the objects that can be used.
     *
     * @param id the Id of the user, company, or department that can use the objects.
     * @return the result of the query.
     */
    public List discoverUsable(String id) {
        log.debug(MSG_SRV_DISCV_USABLE + id + " ...");
        return INSTANCE.getDiscoverObjDao().discoverUsable(id);
    }

    /**
     * Discover the objects that are owned.
     *
     * @param id the Id of the owner.
     * @return the result of the query.
     */
    public List discoverOwned(String id) {
        log.debug(MSG_SRV_DISCV_OWNED + id + " ...");
        return INSTANCE.getDiscoverObjDao().discoverOwned(id);
    }
}

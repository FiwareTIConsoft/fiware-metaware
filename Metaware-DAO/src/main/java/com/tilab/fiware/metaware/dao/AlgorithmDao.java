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

import com.tilab.fiware.metaware.dao.domain.Algorithm;
import java.util.List;

/**
 * DAO interface for Algorithm domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public interface AlgorithmDao {

    /**
     *
     * @return
     */
    public List<Algorithm> getAlgorithmsList();

    /**
     *
     * @param id
     * @return
     */
    public Algorithm getAlgorithm(String id);

    /**
     *
     * @param algorithm
     * @return
     */
    public String createAlgorithm(Algorithm algorithm);

    /**
     *
     * @param id
     * @param algorithm
     * @return
     */
    public Algorithm upsertAlgorithm(String id, Algorithm algorithm);

    /**
     *
     * @param id
     */
    public void deleteAlgorithm(String id);
}

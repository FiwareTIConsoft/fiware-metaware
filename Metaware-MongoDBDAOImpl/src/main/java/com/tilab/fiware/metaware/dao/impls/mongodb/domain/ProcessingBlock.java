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
package com.tilab.fiware.metaware.dao.impls.mongodb.domain;

import com.mongodb.BasicDBObject;
import java.util.Map;

/**
 * Domain class for ProcessingBlock, part of Process and extension of a BasicDBObject for MongoDB.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class ProcessingBlock extends BasicDBObject {
//    private int order;
//    private String block;

    /**
     * Empty constructor.
     *
     * Creates a new processing block section for a process.
     */
    public ProcessingBlock() {
    }

    /**
     * Full map constructor.
     *
     * Create a new processing block section for a process from a starting map.
     *
     * @param m the starting map.
     */
    public ProcessingBlock(Map m) {
        super(m);
    }

    /**
     * Full constructor.
     *
     * @param order the order of the processing block.
     * @param block the block of the processing block.
     */
    public ProcessingBlock(int order, String block) {
        put("order", order);
        put("block", block);
    }

    /**
     * Retrieves the order of the associated processing block.
     *
     * @return the order of the processing block.
     */
    public int getOrder() {
        return getInt("order");
    }

    /**
     * Sets the order of the associated processing block.
     *
     * @param order the order of the processing block.
     */
    public void setOrder(int order) {
        put("order", order);
    }

    /**
     * Retrieves the block of the processing block.
     *
     * @return the order of the processing block.
     */
    public String getBlock() {
        return getString("block");
    }

    /**
     * Sets the block of the processing block.
     *
     * @param block the order of the processing block.
     */
    public void setBlock(String block) {
        put("block", block);
    }

}

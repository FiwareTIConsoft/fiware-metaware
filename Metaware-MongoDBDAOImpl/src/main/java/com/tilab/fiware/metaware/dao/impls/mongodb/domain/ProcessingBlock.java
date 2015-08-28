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
package com.tilab.fiware.metaware.dao.impls.mongodb.domain;

import com.mongodb.BasicDBObject;
import java.util.Map;

/**
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class ProcessingBlock extends BasicDBObject {

    private int order;
    private String block;

    public ProcessingBlock() {
    }

    public ProcessingBlock(Map m) {
        super(m);
    }

    public ProcessingBlock(int order, String block) {
        put("order", order);
        put("block", block);
    }

    public int getOrder() {
        return getInt("order");
    }

    public void setOrder(int order) {
        put("order", order);
    }

    public String getBlock() {
        return getString("block");
    }

    public void setBlock(String block) {
        put("block", block);
    }

}
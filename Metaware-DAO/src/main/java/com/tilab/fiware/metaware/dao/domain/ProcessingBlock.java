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
package com.tilab.fiware.metaware.dao.domain;

/**
 * Processing block class for Process domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class ProcessingBlock {

    private int order;
    private String block;

    public ProcessingBlock(int order, String block) {
        this.order = order;
        this.block = block;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

}

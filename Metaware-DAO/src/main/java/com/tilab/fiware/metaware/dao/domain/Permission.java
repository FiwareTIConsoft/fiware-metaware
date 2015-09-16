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
 * Permission domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Permission {

    User referenceId;
    String perm;

    /**
     *
     * @param referenceId
     * @param perm
     */
    public Permission(User referenceId, String perm) {
        this.referenceId = referenceId;
        this.perm = perm;
    }

    /**
     *
     * @return
     */
    public User getReferenceId() {
        return referenceId;
    }

    /**
     *
     * @param referenceId
     */
    public void setReferenceId(User referenceId) {
        this.referenceId = referenceId;
    }

    /**
     *
     * @return
     */
    public String getPerm() {
        return perm;
    }

    /**
     *
     * @param perm
     */
    public void setPerm(String perm) {
        this.perm = perm;
    }

}

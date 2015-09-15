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

import java.util.Date;
import java.util.List;

/**
 * Dataset domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Dataset {

    private String name;
    private String description;
    private String type;
    private Date creationDate;
    private Date lastModifiedDate;
    private List<User> users;
    private User owner;
    private String status;
    private boolean readOnly;
    private DatasetStructure structure;

    public Dataset() {
    }

    public Dataset(String name, String description, String type, Date creationDate,
            Date lastModifiedDate, List<User> users, User owner, String status, boolean readOnly,
            DatasetStructure structure) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.users = users;
        this.owner = owner;
        this.status = status;
        this.readOnly = readOnly;
        this.structure = structure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUser(List<User> users) {
        this.users = users;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public DatasetStructure getStructure() {
        return structure;
    }

    public void setStructure(DatasetStructure structure) {
        this.structure = structure;
    }

}

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

/**
 * Template domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Template {

    private String name;
    private String description;
    private String type;
    private Date creationDate;
    private Date lastModifiedDate;
    private User user;
    private String owner;
    private String userObjectStatus;
    private String ownerObjectStatus;
    // details: ...
    // objects: ...

    /**
     *
     */
    public Template() {
    }

    /**
     *
     * @param name
     * @param description
     * @param type
     * @param user
     * @param owner
     * @param userObjectStatus
     * @param ownerObjectStatus
     */
    public Template(String name, String description, String type, User user, String owner,
            String userObjectStatus, String ownerObjectStatus) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.creationDate = new Date();
        this.lastModifiedDate = new Date();
        this.user = user;
        this.owner = owner;
        this.userObjectStatus = userObjectStatus;
        this.ownerObjectStatus = ownerObjectStatus;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     *
     * @return
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     *
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public String getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     */
    public String getUserObjectStatus() {
        return userObjectStatus;
    }

    /**
     *
     * @param userObjectStatus
     */
    public void setUserObjectStatus(String userObjectStatus) {
        this.userObjectStatus = userObjectStatus;
    }

    /**
     *
     * @return
     */
    public String getOwnerObjectStatus() {
        return ownerObjectStatus;
    }

    /**
     *
     * @param ownerObjectStatus
     */
    public void setOwnerObjectStatus(String ownerObjectStatus) {
        this.ownerObjectStatus = ownerObjectStatus;
    }

}

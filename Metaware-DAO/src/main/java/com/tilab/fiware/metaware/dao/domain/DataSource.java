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

import java.util.List;

/**
 * Data-source domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DataSource {

    private String name;
    private String description;
    private String type;
    private Long creationDate;
    private Long lastModifiedDate;
    private List<Permission> permissions;
    private User owner;
    private String status;
    private String subtype;
    private String URL;
    private String username;
    private String password;
    private String resourceType;
    private String resource;

    /**
     *
     * @param name
     * @param description
     * @param type
     * @param creationDate
     * @param lastModifiedDate
     * @param permissions
     * @param owner
     * @param status
     * @param subtype
     * @param URL
     * @param username
     * @param password
     * @param resourceType
     * @param resource
     */
    public DataSource(String name, String description, String type, Long creationDate,
            Long lastModifiedDate, List<Permission> permissions, User owner, String status,
            String subtype, String URL, String username, String password, String resourceType,
            String resource) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.permissions = permissions;
        this.owner = owner;
        this.status = status;
        this.subtype = subtype;
        this.URL = URL;
        this.username = username;
        this.password = password;
        this.resourceType = resourceType;
        this.resource = resource;
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
    public Long getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate
     */
    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    /**
     *
     * @return
     */
    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     *
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     *
     * @return
     */
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     *
     * @param permissions
     */
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     *
     * @return
     */
    public User getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     *
     * @param subtype
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     *
     * @return
     */
    public String getURL() {
        return URL;
    }

    /**
     *
     * @param URL
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     *
     * @param resourceType
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     *
     * @return
     */
    public String getResource() {
        return resource;
    }

    /**
     *
     * @param resource
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

}

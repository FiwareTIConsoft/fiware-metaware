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
import com.tilab.fiware.metaware.dao.exception.InvalidIdException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Domain class for Data Source, extension of a BasicDBObject for MongoDB.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class DataSource extends BasicDBObject {
//    private String name;
//    private String description;
//    private String type;
//    private Long creationDate;
//    private Long lastModifiedDate;
//    private List<Permission> permissions;
//    private ObjectId owner;
//    private String status;
//    private String subtype;
//    private String URL;
//    private String username;
//    private String password;
//    private String resourceType;
//    private String resource;

    /**
     * Empty constructor.
     */
    public DataSource() {
    }

    /**
     * Full constructor.
     *
     * @param name the name of the datasource.
     * @param description the description of the datasource.
     * @param type the type of the datasource.
     * @param creationDate the creation date of the datasource.
     * @param lastModifiedDate the date of last update of the datasource.
     * @param permissions the permissions list of the datasource.
     * @param owner the owner of the datasource.
     * @param status the status of the datasource.
     * @param subtype the sub-type of the datasource.
     * @param URL the URL from which the datasource is accessible.
     * @param username the username to access the datasource.
     * @param password the password to access the datasource (for this current version of Metaware,
     * this field is stored in plaintext).
     * @param resourceType the resource type in the datasource (can be: "table", "query", or
     * "file").
     * @param resource the resource in the datasource (can be: "table name", "query", "file name").
     */
    public DataSource(String name, String description, String type, Long creationDate,
            Long lastModifiedDate, List<Permission> permissions, ObjectId owner, String status,
            String subtype, String URL, String username, String password, String resourceType,
            String resource) {
        put("name", name);
        put("description", description);
        put("type", type);
        put("creationDate", creationDate);
        put("lastModifiedDate", lastModifiedDate);
        put("permissions", permissions);
        put("owner", owner);
        put("status", status);
        put("subtype", subtype);
        put("url", URL);
        put("username", username);
        put("password", password);
        put("resourceType", resourceType);
        put("resource", resource);
    }

    /**
     * Get the String representation of the ObjectId of the datasource.
     *
     * @return the ObjectId of the datasource as a normal String.
     */
    public String getId() {
        return getString("_id");
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public String getType() {
        return getString("type");
    }

    public void setType(String type) {
        put("type", type);
    }

    public Long getCreationDate() {
        return getLong("creationDate");
    }

    public void setCreationDate(Long creationDate) {
        put("creationDate", creationDate);
    }

    public Long getLastModifiedDate() {
        return getLong("lastModifiedDate");
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        put("lastModifiedDate", lastModifiedDate);
    }

    public List<Object> getPermissions() {
        return (List<Object>) get("permissions");
    }

    public List<Permission> getPermissionsIds() {
        return (List<Permission>) get("permissions");
    }

    public void setPermissions(List<Permission> permissions) {
        put("permissions", permissions);
    }

    public Object getOwner() {
        return get("owner");
    }

    public ObjectId getOwnerId() {
        return getObjectId("owner");
    }

    public void setOwner(String owner) {
        if (!ObjectId.isValid(owner)) {
            // do nothing
            throw new InvalidIdException();
        } else {
            put("owner", new ObjectId(owner));
        }
    }

    public void setOwnerId(ObjectId owner) {
        put("owner", owner);
    }

    public String getStatus() {
        return getString("status");
    }

    public void setStatus(String status) {
        put("status", status);
    }

    public String getSubtype() {
        return getString("subtype");
    }

    public void setSubtype(String subtype) {
        put("subtype", subtype);
    }

    public String getURL() {
        return getString("url");
    }

    public void setURL(String URL) {
        put("url", URL);
    }

    public String getUsername() {
        return getString("username");
    }

    public void setUsername(String username) {
        put("username", username);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        put("password", password);
    }

    public String getResourceType() {
        return getString("resourceType");
    }

    public void setResourceType(String resourceType) {
        put("resourceType", resourceType);
    }

    public String getResource() {
        return getString("resource");
    }

    public void setResource(String resource) {
        put("resource", resource);
    }

}

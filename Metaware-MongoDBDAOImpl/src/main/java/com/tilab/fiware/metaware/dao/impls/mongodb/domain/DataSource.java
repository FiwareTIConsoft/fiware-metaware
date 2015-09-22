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
     * @param name             the name of the datasource.
     * @param description      the description of the datasource.
     * @param type             the type of the datasource.
     * @param creationDate     the creation date of the datasource.
     * @param lastModifiedDate the date of last update of the datasource.
     * @param permissions      the permissions list of the datasource.
     * @param owner            the owner of the datasource.
     * @param status           the status of the datasource.
     * @param subtype          the sub-type of the datasource.
     * @param URL              the URL from which the datasource is accessible.
     * @param username         the username to access the datasource.
     * @param password         the password to access the datasource (for this current version of
     *                         Metaware,
     *                         this field is stored in plaintext).
     * @param resourceType     the resource type in the datasource (can be: "table", "query", or
     *                         "file").
     * @param resource         the resource in the datasource (can be: "table name", "query", "file
     *                         name").
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

    /**
     * Retrieves the name of the current data-source.
     *
     * @return the name of the data-source.
     */
    public String getName() {
        return getString("name");
    }

    /**
     * Specifies the name of the current data-source.
     *
     * @param name the name of the data-source.
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Retrieves the description of the current data-source.
     *
     * @return the description of the data-source.
     */
    public String getDescription() {
        return getString("description");
    }

    /**
     * Specifies the description of the current data-source.
     *
     * @param description the description of the data-source.
     */
    public void setDescription(String description) {
        put("description", description);
    }

    /**
     * Retrieves the type of the current data-source.
     *
     * @return the type of the data-source.
     */
    public String getType() {
        return getString("type");
    }

    /**
     * Specifies the type of the current data-source.
     *
     * @param type the type of the data-source.
     */
    public void setType(String type) {
        put("type", type);
    }

    /**
     * Retrieves the creation date of the data-source, expressed in Unix Epoch time.
     *
     * @return the creation date of the data-source.
     */
    public Long getCreationDate() {
        return getLong("creationDate");
    }

    /**
     * Sets the creation date of the data-source.
     *
     * @param creationDate the creation date of the data-source, expressed in Unix Epoch time.
     */
    public void setCreationDate(Long creationDate) {
        put("creationDate", creationDate);
    }

    /**
     * Retrieves the date of last modifications of the data-source, expressed in Unix Epoch time.
     *
     * @return the date of the last modifications in Unix Epoch time.
     */
    public Long getLastModifiedDate() {
        return getLong("lastModifiedDate");
    }

    /**
     * Sets a last modification date for the data-source.
     *
     * @param lastModifiedDate the last modification date of the data-source, expressed in Unix
     *                         Epoch time.
     */
    public void setLastModifiedDate(Long lastModifiedDate) {
        put("lastModifiedDate", lastModifiedDate);
    }

    /**
     * Retrieves the list of permissions for the current data-source.
     *
     * Note: this method returns a list of Object objects.
     *
     * @return the list of permissions.
     */
    public List<Object> getPermissions() {
        return (List<Object>) get("permissions");
    }

    /**
     * Retrieves the list of permissions for the current data-source.
     * Note: this method returns a list of Permission objects.
     *
     * @return the list of permissions.
     */
    public List<Permission> getPermissionsIds() {
        return (List<Permission>) get("permissions");
    }

    /**
     * Sets the permissions for the current data-source.
     *
     * @param permissions the list of permissions, composed by Permission objects.
     */
    public void setPermissions(List<Permission> permissions) {
        put("permissions", permissions);
    }

    /**
     * Retrieves the Id of the owner of the current data-source.
     *
     * Note: this method returns the Id expressed as Object class.
     *
     * @return the Id of the owner.
     */
    public Object getOwner() {
        return get("owner");
    }

    /**
     * Retrieves the Id of the owner of the current data-source.
     *
     * Note: this method returns the Id expressed as ObjectId class.
     *
     * @return the Id of the owner.
     */
    public ObjectId getOwnerId() {
        return getObjectId("owner");
    }

    /**
     * Sets the owner of the current data-source.
     *
     * @param owner the Id of the owner expressed as string.
     */
    public void setOwner(String owner) {
        if (!ObjectId.isValid(owner)) {
            // do nothing
            throw new InvalidIdException();
        } else {
            put("owner", new ObjectId(owner));
        }
    }

    /**
     * Sets the owner of the current data-source by specifying its Id.
     *
     * Note: the input parameter for this method must be expressed as ObjectId.
     *
     * @param owner the Id of the owner, expressed as ObjectId,
     */
    public void setOwnerId(ObjectId owner) {
        put("owner", owner);
    }

    /**
     * Retrieves the status of the current data-source.
     *
     * @return the status of the data-source.
     */
    public String getStatus() {
        return getString("status");
    }

    /**
     * Sets the status of the data-source.
     *
     * @param status the status of the data-source.
     */
    public void setStatus(String status) {
        put("status", status);
    }

    /**
     * Retrieves the sub-type of the current data-source.
     *
     * @return the sub-type of the data-source.
     */
    public String getSubtype() {
        return getString("subtype");
    }

    /**
     * Specifies the sub-type for the current data-source.
     *
     * @param subtype the sub-type for the data-source.
     */
    public void setSubtype(String subtype) {
        put("subtype", subtype);
    }

    /**
     * Retrieves the URL of the current data-source.
     *
     * @return the URL of the data-source.
     */
    public String getURL() {
        return getString("url");
    }

    /**
     * Specifies the URL for the current data-source.
     *
     * @param URL the URL for the data-source.
     */
    public void setURL(String URL) {
        put("url", URL);
    }

    /**
     * Retrieves the username to connect with the current data-source.
     *
     * @return the username for the data-source.
     */
    public String getUsername() {
        return getString("username");
    }

    /**
     * Sets the username to connect with the current data-source.
     *
     * @param username the username for the data-source.
     */
    public void setUsername(String username) {
        put("username", username);
    }

    /**
     * Retrieves the password to connect with the current data-source.
     *
     * @return the password for the data-source.
     */
    public String getPassword() {
        return getString("password");
    }

    /**
     * Sets the password to connect with the current data-source.
     *
     * @param password the password for the data-source.
     */
    public void setPassword(String password) {
        put("password", password);
    }

    /**
     * Retrieves the type of resource of the current data-source; it can be "table", "query", or
     * "file".
     *
     * @return the resource type of the data-source.
     */
    public String getResourceType() {
        return getString("resourceType");
    }

    /**
     * Sets the type of resource for the current data-source; it can be "table", "query", or "file".
     *
     * @param resourceType the resource type of the data-source.
     */
    public void setResourceType(String resourceType) {
        put("resourceType", resourceType);
    }

    /**
     * Retrieves the resource of the current data-source; it can be "table name", "query", or "file
     * name".
     *
     * @return the resource of the data-source.
     */
    public String getResource() {
        return getString("resource");
    }

    /**
     * Sets the resource for the current data-source; it can be "table name", "query", or "file
     * name".
     *
     * @param resource the resource of the data-source.
     */
    public void setResource(String resource) {
        put("resource", resource);
    }

}

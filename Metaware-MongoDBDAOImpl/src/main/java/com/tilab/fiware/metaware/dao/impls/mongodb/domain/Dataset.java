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
import java.util.Map;
import org.bson.types.ObjectId;

/**
 * Domain class for Dataset, extension of a BasicDBObject for MongoDB.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Dataset extends BasicDBObject {
//    private String name;
//    private String description;
//    private String type;
//    private Long creationDate;
//    private Long lastModifiedDate;
//    private List<Permission> permissions;
//    private ObjectId owner;
//    private String status;
//    private boolean readOnly;
//    private DatasetStructure structure;
    // Related to XML - DCAT
//    private String id;
//    private String title;
//    private String description;
//    private Date issued;
//    private Date modified;

    /**
     * Empty constructor.
     */
    public Dataset() {
    }

    /**
     * Full constructor.
     *
     * @param name             the name of the dataset.
     * @param description      the description of the dataset.
     * @param type             the type of the dataset.
     * @param creationDate     the date of creation of the dataset.
     * @param lastModifiedDate the date of last modification of the dataset.
     * @param permissions      the permissions list associated with the dataset.
     * @param owner            the Id of the owner of the dataset.
     * @param status           the status of the dataset.
     * @param readOnly         specify if the dataset is read-only.
     * @param structure        the structure (or schema) of the associated dataset.
     */
    public Dataset(String name, String description, String type, Long creationDate,
            Long lastModifiedDate, List<Permission> permissions, ObjectId owner, String status,
            boolean readOnly, DatasetStructure structure) {
        put("name", name);
        put("description", description);
        put("type", type);
        put("creationDate", creationDate);
        put("lastModifiedDate", lastModifiedDate);
        put("permissions", permissions);
        put("owner", owner);
        put("status", status);
        put("readOnly", readOnly);
        put("structure", structure);
    }

    /**
     * Main constructor extension for DCAT extension
     *
     * @param name             the name of the dataset.
     * @param description      the description of the dataset.
     * @param type             the type of the dataset.
     * @param creationDate     the date of creation of the dataset.
     * @param lastModifiedDate the date of last modification of the dataset.
     * @param permissions      the permissions list associated with the dataset.
     * @param owner            the Id of the owner of the dataset.
     * @param status           the status of the dataset.
     * @param readOnly         specify if the dataset is read-only.
     * @param keyword          a keyword associated with the dataset.
     * @param theme            the main theme of the dataset.
     * @param accessUrl        the access URL for the dataset.
     * @param distDescription  the description of the distribution for the dataset.
     * @param distFormat       the distribution format for the dataset.
     * @param license          the license associated with the dataset.
     * @param structure        the structure (or schema) of the associated dataset.
     */
    public Dataset(String name, String description, String type, Long creationDate,
            Long lastModifiedDate, List<Permission> permissions, ObjectId owner, String status,
            boolean readOnly, String keyword, String theme, String accessUrl,
            String distDescription, String distFormat, String license, DatasetStructure structure) {
        put("name", name);
        put("description", description);
        put("type", type);
        put("creationDate", creationDate);
        put("lastModifiedDate", lastModifiedDate);
        put("permissions", permissions);
        put("owner", owner);
        put("status", status);
        put("readOnly", readOnly);
        put("keyword", keyword);
        put("theme", theme);
        put("accessUrl", accessUrl);
        put("distDescription", distDescription);
        put("distFormat", distFormat);
        put("license", license);
        put("structure", structure);
    }

    /**
     * Retrieves the Id of the current dataset.
     *
     * @return the Id of the dataset, expressed in a String.
     */
    public String getId() {
        return getString("_id");
    }

    /**
     * Specifies the new Id of the current dataset.
     *
     * Note: the usage of this method can lead to data inconsistency at DB level; use this method
     * only if really necessary.
     *
     * @param id the Id of the dataset, expressed in a String.
     */
    public void setId(String id) {
        if (!ObjectId.isValid(id)) {
            // do nothing
            throw new InvalidIdException();
        } else {
            put("_id", new ObjectId(id));
        }
    }

    /**
     * Retrieves the name of the current dataset.
     *
     * @return the name of the dataset.
     */
    public String getName() {
        return getString("name");
    }

    /**
     * Sets the name for the current dataset.
     *
     * @param name the name of the dataset.
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Retrieves the description of the current dataset.
     *
     * @return the description of the dataset.
     */
    public String getDescription() {
        return getString("description");
    }

    /**
     * Sets the description for the current dataset.
     *
     * @param description the description of the dataset.
     */
    public void setDescription(String description) {
        put("description", description);
    }

    /**
     * Retrieves the type of the current dataset.
     *
     * @return the type of the dataset.
     */
    public String getType() {
        return getString("type");
    }

    /**
     * Sets the type for the current dataset.
     *
     * @param type the type of the dataset.
     */
    public void setType(String type) {
        put("type", type);
    }

    /**
     * Retrieves the creation date of the dataset, expressed in Unix Epoch time.
     *
     * @return the creation date of the dataset.
     */
    public Long getCreationDate() {
        return getLong("creationDate");
    }

    /**
     * Sets the creation date of the dataset.
     *
     * @param creationDate the creation date of the dataset, expressed in Unix Epoch time.
     */
    public void setCreationDate(Long creationDate) {
        put("creationDate", creationDate);
    }

    /**
     * Retrieves the date of last modifications of the dataset, expressed in Unix Epoch time.
     *
     * @return the date of the last modifications in Unix Epoch time.
     */
    public Long getLastModifiedDate() {
        return getLong("lastModifiedDate");
    }

    /**
     * Sets a last modification date for the dataset.
     *
     * @param lastModifiedDate the last modification date of the dataset, expressed in Unix Epoch
     *                         time.
     */
    public void setLastModifiedDate(Long lastModifiedDate) {
        put("lastModifiedDate", lastModifiedDate);
    }

    /**
     * Retrieves the list of permissions for the current dataset.
     *
     * Note: this method returns a list of Object objects.
     *
     * @return the list of permissions.
     */
    public List<Object> getPermissions() {
        return (List<Object>) get("permissions");
    }

    /**
     * Retrieves the list of permissions for the current dataset.
     *
     * Note: this method returns a list of Permission objects.
     *
     * @return the list of permissions.
     */
    public List<Permission> getPermissionsIds() {
        return (List<Permission>) get("permissions");
    }

    /**
     * Sets the permissions for the current dataset.
     *
     * @param permissions the list of permissions, composed by Permission objects.
     */
    public void setPermissions(List<Permission> permissions) {
        put("permissions", permissions);
    }

    /**
     * Retrieves the Id of the owner of the current dataset.
     *
     * Note: this method returns the Id expressed as Object class.
     *
     * @return the Id of the owner.
     */
    public Object getOwner() {
        return get("owner");
    }

    /**
     * Sets the owner of the current dataset.
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
     * Retrieves the Id of the owner of the current dataset.
     *
     * Note: this method returns the Id expressed as ObjectId class.
     *
     * @return the Id of the owner.
     */
    public ObjectId getOwnerId() {
        return (ObjectId) get("owner");
    }

    /**
     * Sets the owner of the current dataset by specifying its Id.
     *
     * Note: the input parameter for this method must be expressed as ObjectId.
     *
     * @param owner the Id of the owner, expressed as ObjectId,
     */
    public void setOwnerId(ObjectId owner) {
        put("owner", owner);
    }

    /**
     * Retrieves the status of the current dataset.
     *
     * @return the status of the dataset.
     */
    public String getStatus() {
        return getString("status");
    }

    /**
     * Sets the status of the dataset.
     *
     * @param status the status of the dataset.
     */
    public void setStatus(String status) {
        put("status", status);
    }

    /**
     * Retrieves the keyword associated to the current dataset.
     *
     * @return the associated keyword.
     */
    public String getKeyword() {
        return getString("keyword");
    }

    /**
     * Associates a keyword to the current dataset.
     *
     * @param keyword the keyword for the dataset.
     */
    public void setKeyword(String keyword) {
        put("keyword", keyword);
    }

    /**
     * Retrieves the theme of the dataset.
     *
     * @return the theme of the dataset.
     */
    public String getTheme() {
        return getString("theme");
    }

    /**
     * Sets the theme for the dataset.
     *
     * @param theme the theme of the dataset.
     */
    public void setTheme(String theme) {
        put("theme", theme);
    }

    /**
     * Retrieves the access URL of the dataset.
     *
     * @return the access URL of the dataset.
     */
    public String getAccessUrl() {
        return getString("accessUrl");
    }

    /**
     * Sets the access URL for the dataset.
     *
     * @param accessUrl the access URL of the dataset.
     */
    public void setAccessUrl(String accessUrl) {
        put("accessUrl", accessUrl);
    }

    /**
     * Retrieves the description of the dataset's distribution.
     *
     * @return the description of the distribution.
     */
    public String getDistDescription() {
        return getString("distDescription");
    }

    /**
     * Sets the description of the dataset's distribution.
     *
     * @param distDescription the description of the distribution.
     */
    public void setDistDescription(String distDescription) {
        put("distDescription", distDescription);
    }

    /**
     * Retrieves the format of the dataset's distribution.
     *
     * @return the format of the distribution.
     */
    public String getDistFormat() {
        return getString("distFormat");
    }

    /**
     * Set the format of the dataset's distribution.
     *
     * @param distFormat the format of the distribution.
     */
    public void setDistFormat(String distFormat) {
        put("distFormat", distFormat);
    }

    /**
     * Retrieves the license of the dataset.
     *
     * @return the license of the dataset.
     */
    public String getLicense() {
        return getString("license");
    }

    /**
     * Sets the license of the dataset.
     *
     * @param license the license of the dataset.
     */
    public void setLicense(String license) {
        put("license", license);
    }

    /**
     * Tells if the current dataset is read-only.
     *
     * @return true if the dataset is read-only, false otherwise.
     */
    public boolean isReadOnly() {
        return getBoolean("readOnly");
    }

    /**
     * Specifies if the dataset is read-only.
     *
     * @param readOnly true if the dataset is read-only, false otherwise.
     */
    public void setReadOnly(boolean readOnly) {
        put("readOnly", readOnly);
    }

    /**
     * Retrieves the structure (or schema) of the dataset.
     *
     * @return the dataset's structure.
     */
    public DatasetStructure getStructure() {
        try {
            return new DatasetStructure((Map) get("structure")); // mongodb java driver doesn't support automatically 
        } catch (NullPointerException e) { // the field doesn't exist
            return null;
        }
    }

    /**
     * Specifies the structure (or schema) of the dataset.
     *
     * @param structure the dataset's structure.
     */
    public void setStructure(DatasetStructure structure) {
        put("structure", structure);
    }

    // Methods related to XML - DCAT
//    @XmlAttribute
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @XmlElement
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    @XmlElement
//    public void setDescriptionDCAT(String description) {
//        this.description = description;
//    }
//
//    @XmlElement
//    public void setIssued(Date issued) {
//        this.issued = issued;
//    }
//
//    @XmlElement
//    public void setModified(Date modified) {
//        this.modified = modified;
//    }
}

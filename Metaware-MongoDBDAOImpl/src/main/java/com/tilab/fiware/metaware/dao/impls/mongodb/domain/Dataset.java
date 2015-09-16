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
     *
     * @param name
     * @param description
     * @param type
     * @param creationDate
     * @param lastModifiedDate
     * @param permissions
     * @param owner
     * @param status
     * @param readOnly
     * @param structure
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
     * @param name
     * @param description
     * @param type
     * @param creationDate
     * @param lastModifiedDate
     * @param permissions
     * @param owner
     * @param status
     * @param readOnly
     * @param keyword
     * @param theme
     * @param accessUrl
     * @param distDescription
     * @param distFormat
     * @param license
     * @param structure
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
     *
     * @return
     */
    public String getId() {
        return getString("_id");
    }

    /**
     *
     * @param id
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
     *
     * @return
     */
    public String getName() {
        return getString("name");
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return getString("description");
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        put("description", description);
    }

    /**
     *
     * @return
     */
    public String getType() {
        return getString("type");
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        put("type", type);
    }

    /**
     *
     * @return
     */
    public Long getCreationDate() {
        return getLong("creationDate");
    }

    /**
     *
     * @param creationDate
     */
    public void setCreationDate(Long creationDate) {
        put("creationDate", creationDate);
    }

    /**
     *
     * @return
     */
    public Long getLastModifiedDate() {
        return getLong("lastModifiedDate");
    }

    /**
     *
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Long lastModifiedDate) {
        put("lastModifiedDate", lastModifiedDate);
    }

    /**
     *
     * @return
     */
    public List<Object> getPermissions() {
        return (List<Object>) get("permissions");
    }

    /**
     *
     * @return
     */
    public List<Permission> getPermissionsIds() {
        return (List<Permission>) get("permissions");
    }

    /**
     *
     * @param permissions
     */
    public void setPermissions(List<Permission> permissions) {
        put("permissions", permissions);
    }

    /**
     *
     * @return
     */
    public Object getOwner() {
        return get("owner");
    }

    /**
     *
     * @param owner
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
     *
     * @return
     */
    public ObjectId getOwnerId() {
        return (ObjectId) get("owner");
    }

    /**
     *
     * @param owner
     */
    public void setOwnerId(ObjectId owner) {
        put("owner", owner);
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return getString("status");
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        put("status", status);
    }

    /**
     *
     * @return
     */
    public String getKeyword() {
        return getString("keyword");
    }

    /**
     *
     * @param keyword
     */
    public void setKeyword(String keyword) {
        put("keyword", keyword);
    }

    /**
     *
     * @return
     */
    public String getTheme() {
        return getString("theme");
    }

    /**
     *
     * @param theme
     */
    public void setTheme(String theme) {
        put("theme", theme);
    }

    /**
     *
     * @return
     */
    public String getAccessUrl() {
        return getString("accessUrl");
    }

    /**
     *
     * @param accessUrl
     */
    public void setAccessUrl(String accessUrl) {
        put("accessUrl", accessUrl);
    }

    /**
     *
     * @return
     */
    public String getDistDescription() {
        return getString("distDescription");
    }

    /**
     *
     * @param distDescription
     */
    public void setDistDescription(String distDescription) {
        put("distDescription", distDescription);
    }

    /**
     *
     * @return
     */
    public String getDistFormat() {
        return getString("distFormat");
    }

    /**
     *
     * @param distFormat
     */
    public void setDistFormat(String distFormat) {
        put("distFormat", distFormat);
    }

    /**
     *
     * @return
     */
    public String getLicense() {
        return getString("license");
    }

    /**
     *
     * @param license
     */
    public void setLicense(String license) {
        put("license", license);
    }

    /**
     *
     * @return
     */
    public boolean isReadOnly() {
        return getBoolean("readOnly");
    }

    /**
     *
     * @param readOnly
     */
    public void setReadOnly(boolean readOnly) {
        put("readOnly", readOnly);
    }

    /**
     *
     * @return
     */
    public DatasetStructure getStructure() {
        try {
            return new DatasetStructure((Map) get("structure")); // mongodb java driver doesn't support automatically 
        } catch (NullPointerException e) { // the field doesn't exist
            return null;
        }
    }

    /**
     *
     * @param structure
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

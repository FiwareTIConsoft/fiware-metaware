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
    public Dataset() {
    }

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

    // Main constructor extension for DCAT extension
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

    public String getId() {
        return getString("_id");
    }

    public void setId(String id) {
        if (!ObjectId.isValid(id)) {
            // do nothing
            throw new InvalidIdException();
        } else {
            put("_id", new ObjectId(id));
        }
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

    public void setOwner(String owner) {
        if (!ObjectId.isValid(owner)) {
            // do nothing
            throw new InvalidIdException();
        } else {
            put("owner", new ObjectId(owner));
        }
    }

    public ObjectId getOwnerId() {
        return (ObjectId) get("owner");
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

    /////
    public String getKeyword() {
        return getString("keyword");
    }

    public void setKeyword(String keyword) {
        put("keyword", keyword);
    }

    public String getTheme() {
        return getString("theme");
    }

    public void setTheme(String theme) {
        put("theme", theme);
    }

    public String getAccessUrl() {
        return getString("accessUrl");
    }

    public void setAccessUrl(String accessUrl) {
        put("accessUrl", accessUrl);
    }

    public String getDistDescription() {
        return getString("distDescription");
    }

    public void setDistDescription(String distDescription) {
        put("distDescription", distDescription);
    }

    public String getDistFormat() {
        return getString("distFormat");
    }

    public void setDistFormat(String distFormat) {
        put("distFormat", distFormat);
    }

    public String getLicense() {
        return getString("license");
    }

    public void setLicense(String license) {
        put("license", license);
    }

    /////
    public boolean isReadOnly() {
        return getBoolean("readOnly");
    }

    public void setReadOnly(boolean readOnly) {
        put("readOnly", readOnly);
    }

    public DatasetStructure getStructure() {
        try {
            return new DatasetStructure((Map) get("structure")); // mongodb java driver doesn't support automatically 
        } catch (NullPointerException e) { // the field doesn't exist
            return null;
        }
    }

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

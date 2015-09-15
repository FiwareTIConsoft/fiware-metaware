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
 * Domain class for Algorithm, extension of a BasicDBObject for MongoDB.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Algorithm extends BasicDBObject {
//    private String name;
//    private String description;
//    private String type;
//    private Long creationDate;
//    private Long lastModifiedDate;
//    private List<Permission> permissions;
//    private ObjectId owner;
//    private String status;
//    private String model;
//    private String subModel;
//    private String hiveQuery;
//    private Long elapsedTime;
//    private int runNumber;
//    private String logUrl;

    public Algorithm() {
    }

    public Algorithm(String name, String description, String type, Long creationDate,
            Long lastModifiedDate, List<Permission> permissions, ObjectId owner, String status,
            String model, String subModel, String hiveQuery, Long elapsedTime, int runNumber,
            String logUrl) {
        put("name", name);
        put("description", description);
        put("type", type);
        put("creationDate", creationDate);
        put("lastModifiedDate", lastModifiedDate);
        put("permissions", permissions);
        put("owner", owner);
        put("status", status);
        put("model", model);
        put("subModel", subModel);
        put("hiveQuery", hiveQuery);
        put("elapsedTime", elapsedTime);
        put("runNumber", runNumber);
        put("logUrl", logUrl);
    }

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
        return (ObjectId) get("owner");
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

    public String getModel() {
        return getString("model");
    }

    public void setModel(String model) {
        put("model", model);
    }

    public String getSubModel() {
        return getString("subModel");
    }

    public void setSubModel(String subModel) {
        put("subModel", subModel);
    }

    public String getHiveQuery() {
        return getString("hiveQuery");
    }

    public void setHiveQuery(String hiveQuery) {
        put("hiveQuery", hiveQuery);
    }

    public Long getElapsedTime() {
        return getLong("elapsedTime");
    }

    public void setElapsedTime(Long elapsedTime) {
        put("elapsedTime", elapsedTime);
    }

    public int getRunNumber() {
        return getInt("runNumber");
    }

    public void setRunNumber(int runNumber) {
        put("runNumber", runNumber);
    }

    public String getLogUrl() {
        return getString("logUrl");
    }

    public void setLogUrl(String logUrl) {
        put("logUrl", logUrl);
    }

}

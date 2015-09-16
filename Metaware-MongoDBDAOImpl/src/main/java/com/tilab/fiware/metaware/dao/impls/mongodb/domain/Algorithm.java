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

    /**
     * Empty constructor.
     */
    public Algorithm() {
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
     * @param model
     * @param subModel
     * @param hiveQuery
     * @param elapsedTime
     * @param runNumber
     * @param logUrl
     */
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

    /**
     *
     * @return
     */
    public String getId() {
        return getString("_id");
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
     * @return
     */
    public ObjectId getOwnerId() {
        return (ObjectId) get("owner");
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
    public String getModel() {
        return getString("model");
    }

    /**
     *
     * @param model
     */
    public void setModel(String model) {
        put("model", model);
    }

    /**
     *
     * @return
     */
    public String getSubModel() {
        return getString("subModel");
    }

    /**
     *
     * @param subModel
     */
    public void setSubModel(String subModel) {
        put("subModel", subModel);
    }

    /**
     *
     * @return
     */
    public String getHiveQuery() {
        return getString("hiveQuery");
    }

    /**
     *
     * @param hiveQuery
     */
    public void setHiveQuery(String hiveQuery) {
        put("hiveQuery", hiveQuery);
    }

    /**
     *
     * @return
     */
    public Long getElapsedTime() {
        return getLong("elapsedTime");
    }

    /**
     *
     * @param elapsedTime
     */
    public void setElapsedTime(Long elapsedTime) {
        put("elapsedTime", elapsedTime);
    }

    /**
     *
     * @return
     */
    public int getRunNumber() {
        return getInt("runNumber");
    }

    /**
     *
     * @param runNumber
     */
    public void setRunNumber(int runNumber) {
        put("runNumber", runNumber);
    }

    /**
     *
     * @return
     */
    public String getLogUrl() {
        return getString("logUrl");
    }

    /**
     *
     * @param logUrl
     */
    public void setLogUrl(String logUrl) {
        put("logUrl", logUrl);
    }

}

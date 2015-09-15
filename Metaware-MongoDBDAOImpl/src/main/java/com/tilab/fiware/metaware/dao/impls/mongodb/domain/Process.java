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
 * Process domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Process extends BasicDBObject {

    private String name;
    private String description;
    private String type;
    private Long creationDate;
    private Long lastModifiedDate;
    private List<Permission> permissions;
    private ObjectId owner;
    private String status;
    private String frequence; // "daily" | "hourly" | ...
    private Long startingTime;
    private Long lastRunTime;
    private Long runNumber;
    private List<ProcessingBlock> processingBlocks;
    private String logUrl;

    public Process() {
    }

    public Process(String name, String description, String type, Long creationDate,
            Long lastModifiedDate, List<Permission> permissions, ObjectId owner, String status,
            String frequence, Long startingTime, Long lastRunTime, Long runNumber,
            List<ProcessingBlock> processingBlocks, String logUrl) {
        put("name", name);
        put("description", description);
        put("type", type);
        put("creationDate", creationDate);
        put("lastModifiedDate", lastModifiedDate);
        put("permissions", permissions);
        put("owner", owner);
        put("status", status);
        put("frequence", frequence);
        put("startingTime", startingTime);
        put("lastRunTime", lastRunTime);
        put("runNumber", runNumber);
        put("processingBlocks", processingBlocks);
        put("logUrl", logUrl);
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

    public String getFrequence() {
        return getString("frequence");
    }

    public void setFrequence(String frequence) {
        put("frequence", frequence);
    }

    public Long getStartingTime() {
        return getLong("startingTime");
    }

    public void setStartingTime(Long startingTime) {
        put("startingTime", startingTime);
    }

    public Long getLastRunTime() {
        return getLong("lastRunTime");
    }

    public void setLastRunTime(Long lastRunTime) {
        put("lastRunTime", lastRunTime);
    }

    public Long getRunNumber() {
        return getLong("runNumber");
    }

    public void setRunNumber(Long runNumber) {
        put("runNumber", runNumber);
    }

    public List<Object> getProcessingBlocksObj() {
        return (List<Object>) get("processingBlocks");
    }

    public List<ProcessingBlock> getProcessingBlocks() {
        return (List<ProcessingBlock>) get("processingBlocks");
    }

    public void setProcessingBlocks(List<ProcessingBlock> processingBlocks) {
        put("processingBlocks", processingBlocks);
    }

    public String getLogUrl() {
        return getString("logUrl");
    }

    public void setLogUrl(String logUrl) {
        put("logUrl", logUrl);
    }

}

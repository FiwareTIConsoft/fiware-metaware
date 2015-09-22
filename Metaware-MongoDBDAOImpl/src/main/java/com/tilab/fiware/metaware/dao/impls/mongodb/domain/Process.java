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
//    private String name;
//    private String description;
//    private String type;
//    private Long creationDate;
//    private Long lastModifiedDate;
//    private List<Permission> permissions;
//    private ObjectId owner;
//    private String status;
//    private String frequence; // "daily" | "hourly" | ...
//    private Long startingTime;
//    private Long lastRunTime;
//    private Long runNumber;
//    private List<ProcessingBlock> processingBlocks;
//    private String logUrl;

    /**
     * Empty constructor.
     */
    public Process() {
    }

    /**
     * Full constructor.
     *
     * @param name             the name of the process.
     * @param description      the description of the process.
     * @param type             the type of the process.
     * @param creationDate     the date when the process was created.
     * @param lastModifiedDate the last date when the process was modified.
     * @param permissions      the permissions list associated with the process.
     * @param owner            the Id of the owner of the process.
     * @param status           the status of the process.
     * @param frequence        the frequence of the process.
     * @param startingTime     the time when the process started.
     * @param lastRunTime      the time when the process run for the last time.
     * @param runNumber        the number of run of the process.
     * @param processingBlocks the processing block.
     * @param logUrl           the URL where the log of the process is stored.
     */
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

    /**
     * Retrieves the Id of the process as a String.
     *
     * @return the string containing the Id of the process.
     */
    public String getId() {
        return getString("_id");
    }

    /**
     * Sets the Id of the process.
     *
     * Note: the usage of this method can lead to data inconsistency at DB level; use this method
     * only if really necessary.
     *
     * @param id the string containing the Id of the process.
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
     * Retrieves the name of the current process.
     *
     * @return the name of the process.
     */
    public String getName() {
        return getString("name");
    }

    /**
     * Sets the name of the current process.
     *
     * @param name the name of the process.
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Retrieves the description of the current process.
     *
     * @return the description of the process.
     */
    public String getDescription() {
        return getString("description");
    }

    /**
     * Sets the description of the current process.
     *
     * @param description the description of the process.
     */
    public void setDescription(String description) {
        put("description", description);
    }

    /**
     * Retrieves the type of the current process.
     *
     * @return the type of the process.
     */
    public String getType() {
        return getString("type");
    }

    /**
     * Sets the type of the current process.
     *
     * @param type the type of the process.
     */
    public void setType(String type) {
        put("type", type);
    }

    /**
     * Retrieves the creation date of the process, expressed in Unix Epoch time.
     *
     * @return the creation date of the process.
     */
    public Long getCreationDate() {
        return getLong("creationDate");
    }

    /**
     * Sets the creation date of the process.
     *
     * @param creationDate the creation date of the process, expressed in Unix Epoch time.
     */
    public void setCreationDate(Long creationDate) {
        put("creationDate", creationDate);
    }

    /**
     * Retrieves the date of last modifications of the process, expressed in Unix Epoch time.
     *
     * @return the date of the last modifications in Unix Epoch time.
     */
    public Long getLastModifiedDate() {
        return getLong("lastModifiedDate");
    }

    /**
     * Sets a last modification date for the process.
     *
     * @param lastModifiedDate the last modification date of the process, expressed in Unix Epoch
     *                         time.
     */
    public void setLastModifiedDate(Long lastModifiedDate) {
        put("lastModifiedDate", lastModifiedDate);
    }

    /**
     * Retrieves the list of permissions for the current process.
     *
     * Note: this method returns a list of Object objects.
     *
     * @return the list of permissions.
     */
    public List<Object> getPermissions() {
        return (List<Object>) get("permissions");
    }

    /**
     * Retrieves the list of permissions for the current process.
     *
     * Note: this method returns a list of Permission objects.
     *
     * @return the list of permissions.
     */
    public List<Permission> getPermissionsIds() {
        return (List<Permission>) get("permissions");
    }

    /**
     * Sets the permissions for the current process.
     *
     * @param permissions the list of permissions, composed by Permission objects.
     */
    public void setPermissions(List<Permission> permissions) {
        put("permissions", permissions);
    }

    /**
     * Retrieves the Id of the owner of the current process.
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
     * Retrieves the frequence of the current dataset.
     *
     * @return the frequence of the dataset.
     */
    public String getFrequence() {
        return getString("frequence");
    }

    /**
     * Sets the frequence of the current dataset.
     *
     * @param frequence the frequence of the dataset.
     */
    public void setFrequence(String frequence) {
        put("frequence", frequence);
    }

    /**
     * Retrieves the starting time of the current dataset, expressed in Unix Epoch time.
     *
     * @return the starting time of the dataset in Unix Epoch time.
     */
    public Long getStartingTime() {
        return getLong("startingTime");
    }

    /**
     * Sets the starting time of the current dataset.
     *
     * @param startingTime the starting time of the dataset in Unix Epoch time.
     */
    public void setStartingTime(Long startingTime) {
        put("startingTime", startingTime);
    }

    /**
     * Retrieves the time when the process executed, expressed in Unix Epoch time.
     *
     * @return the last run time of the dataset in Unix Epoch time.
     */
    public Long getLastRunTime() {
        return getLong("lastRunTime");
    }

    /**
     * Sets the time when the process executed for the last time.
     *
     * @param lastRunTime the last run time of the dataset in Unix Epoch time.
     */
    public void setLastRunTime(Long lastRunTime) {
        put("lastRunTime", lastRunTime);
    }

    /**
     * Retrieves the total number of runs of the process.
     *
     * @return the run number of the process.
     */
    public Long getRunNumber() {
        return getLong("runNumber");
    }

    /**
     * Sets the total number of runs of the process.
     *
     * @param runNumber the run number of the process.
     */
    public void setRunNumber(Long runNumber) {
        put("runNumber", runNumber);
    }

    /**
     * Retrieves the processing blocks of the process.
     *
     * Note: this method returns a list of Object objects.
     *
     * @return the list of processing blocks.
     */
    public List<Object> getProcessingBlocksObj() {
        return (List<Object>) get("processingBlocks");
    }

    /**
     * Retrieves the processing blocks of the process.
     *
     * Note: this method returns a list of ProcessingBlock objects.
     *
     * @return the list of processing blocks
     */
    public List<ProcessingBlock> getProcessingBlocks() {
        return (List<ProcessingBlock>) get("processingBlocks");
    }

    /**
     * Sets the processing blocks of the process.
     *
     * @param processingBlocks the list of processing blocks of the process.
     */
    public void setProcessingBlocks(List<ProcessingBlock> processingBlocks) {
        put("processingBlocks", processingBlocks);
    }

    /**
     * Retrieves the URL where the process stores the log.
     *
     * @return the log URL of the process.
     */
    public String getLogUrl() {
        return getString("logUrl");
    }

    /**
     * Sets the URL where the process stores the log.
     *
     * @param logUrl the log URL of the process.
     */
    public void setLogUrl(String logUrl) {
        put("logUrl", logUrl);
    }

}

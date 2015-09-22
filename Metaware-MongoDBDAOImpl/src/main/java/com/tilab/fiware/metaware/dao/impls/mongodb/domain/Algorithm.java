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
     * Full constructor
     *
     * @param name             the name of the algorithm.
     * @param description      the description of the algorithm.
     * @param type             the type of the algorithm.
     * @param creationDate     the creation date of the algorithm in Unix epoch time.
     * @param lastModifiedDate the date of the last modification in Unix epoch time.
     * @param permissions      the array of permissions associated to the algorithm.
     * @param owner            the Id of the owner.
     * @param status           the status of the algorithm.
     * @param model            the model of the algorithm.
     * @param subModel         the sub-model of the algorithm.
     * @param hiveQuery        the hive query of the algorithm.
     * @param elapsedTime      the elapsed time of the algorithm in Unix epoch time.
     * @param runNumber        the number of runs of the algorithm.
     * @param logUrl           the URL of the algorithm containing the logs.
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
     * Retrieves the Id of the algorithm as a String.
     *
     * @return the Id of the algorithm.
     */
    public String getId() {
        return getString("_id");
    }

    /**
     * Retrieves the name of the algorithm.
     *
     * @return the name of the algorithm.
     */
    public String getName() {
        return getString("name");
    }

    /**
     * Sets the name of the algorithm.
     *
     * @param name the new name of the algorithm.
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Retrieves the description of the algorithm.
     *
     * @return the description of the algorithm.
     */
    public String getDescription() {
        return getString("description");
    }

    /**
     * Sets the description of the algorithm.
     *
     * @param description the new description of the algorithm.
     */
    public void setDescription(String description) {
        put("description", description);
    }

    /**
     * Retrieves the type of the algorithm.
     *
     * @return the type of the algorithm.
     */
    public String getType() {
        return getString("type");
    }

    /**
     * Sets the type of the algorithm.
     *
     * @param type the new type of the algorithm.
     */
    public void setType(String type) {
        put("type", type);
    }

    /**
     * Retrieves the creation date of the algorithm, expressed in Unix Epoch time.
     *
     * @return the creation date of the algorithm.
     */
    public Long getCreationDate() {
        return getLong("creationDate");
    }

    /**
     * Sets the creation date of the algorithm.
     *
     * @param creationDate the creation date of the algorithm, expressed in Unix Epoch time.
     */
    public void setCreationDate(Long creationDate) {
        put("creationDate", creationDate);
    }

    /**
     * Retrieves the date of last modifications of the algorithm, expressed in Unix Epoch time.
     *
     * @return the date of the last modifications in Unix Epoch time.
     */
    public Long getLastModifiedDate() {
        return getLong("lastModifiedDate");
    }

    /**
     * Sets a last modification date for the algorithm.
     *
     * @param lastModifiedDate the last modification date of the algorithm, expressed in Unix Epoch
     *                         time.
     */
    public void setLastModifiedDate(Long lastModifiedDate) {
        put("lastModifiedDate", lastModifiedDate);
    }

    /**
     * Retrieves the list of permissions for the current algorithm.
     *
     * Note: this method returns a list of Object objects.
     *
     * @return the list of permissions.
     */
    public List<Object> getPermissions() {
        return (List<Object>) get("permissions");
    }

    /**
     * Retrieves the list of permissions for the current algorithm.
     *
     * Note: this method returns a list of Permission objects.
     *
     * @return the list of permissions.
     */
    public List<Permission> getPermissionsIds() {
        return (List<Permission>) get("permissions");
    }

    /**
     * Sets the permissions for the current algorithm.
     *
     * @param permissions the list of permissions, composed by Permission objects.
     */
    public void setPermissions(List<Permission> permissions) {
        put("permissions", permissions);
    }

    /**
     * Retrieves the Id of the owner of the current algorithm.
     *
     * Note: this method returns the Id expressed as Object class.
     *
     * @return the Id of the owner.
     */
    public Object getOwner() {
        return get("owner");
    }

    /**
     * Retrieves the Id of the owner of the current algorithm.
     *
     * Note: this method returns the Id expressed as ObjectId class.
     *
     * @return the Id of the owner.
     */
    public ObjectId getOwnerId() {
        return (ObjectId) get("owner");
    }

    /**
     * Sets the owner of the current algorithm.
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
     * Sets the owner of the current algorithm by specifying its Id.
     *
     * Note: the input parameter for this method must be expressed as ObjectId.
     *
     * @param owner the Id of the owner, expressed as ObjectId,
     */
    public void setOwnerId(ObjectId owner) {
        put("owner", owner);
    }

    /**
     * Retrieves the status of the current algorithm.
     *
     * @return the status of the algorithm.
     */
    public String getStatus() {
        return getString("status");
    }

    /**
     * Sets the status of the algorithm.
     *
     * @param status the status of the algorithm.
     */
    public void setStatus(String status) {
        put("status", status);
    }

    /**
     * Retrieves the model of the current algorithm.
     *
     * @return the model of the algorithm.
     */
    public String getModel() {
        return getString("model");
    }

    /**
     * Sets the model for the current algorithm.
     *
     * @param model the model of the algorithm.
     */
    public void setModel(String model) {
        put("model", model);
    }

    /**
     * Retrieves the sub-model for the current algorithm.
     *
     * @return the sub-model for the algorithm.
     */
    public String getSubModel() {
        return getString("subModel");
    }

    /**
     * Sets the sub-model for the current algorithm.
     *
     * @param subModel the sub-model for the algorithm.
     */
    public void setSubModel(String subModel) {
        put("subModel", subModel);
    }

    /**
     * Retrieves the hive-query for the current algorithm.
     *
     * @return the hive-query for the algorithm.
     */
    public String getHiveQuery() {
        return getString("hiveQuery");
    }

    /**
     * Sets the hive-query for the current algorithm.
     *
     * @param hiveQuery the hive-query for the algorithm.
     */
    public void setHiveQuery(String hiveQuery) {
        put("hiveQuery", hiveQuery);
    }

    /**
     * Retrieves the elapsed time for the current algorithm, expressed in Unix Epoch time.
     *
     * @return the elapsed time for the algorithm in Unix Epoch time.
     */
    public Long getElapsedTime() {
        return getLong("elapsedTime");
    }

    /**
     * Sets the elapsed time for the current algorithm, expressed in Unix Epoch time.
     *
     * @param elapsedTime the elapsed time for the algorithm in Unix Epoch time.
     */
    public void setElapsedTime(Long elapsedTime) {
        put("elapsedTime", elapsedTime);
    }

    /**
     * Retrieves how many times the algorithm has been started.
     *
     * @return the number of runs of the algorithm.
     */
    public int getRunNumber() {
        return getInt("runNumber");
    }

    /**
     * Sets how many times the algorithm has been started.
     *
     * @param runNumber the number of runs of the algorithm.
     */
    public void setRunNumber(int runNumber) {
        put("runNumber", runNumber);
    }

    /**
     * Retrieves the URL where the logs of the algorithm are stored.
     *
     * @return the log URL.
     */
    public String getLogUrl() {
        return getString("logUrl");
    }

    /**
     * Sets the URL where the logs of the algorithm are stored.
     *
     * @param logUrl the log URL.
     */
    public void setLogUrl(String logUrl) {
        put("logUrl", logUrl);
    }

}

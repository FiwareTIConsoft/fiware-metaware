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
package com.tilab.fiware.metaware.dao.domain;

import java.util.List;

/**
 * Process domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Process {

    private String name;
    private String description;
    private String type;
    private Long creationDate;
    private Long lastModifiedDate;
    private List<User> users;
    private User owner;
    private String status;
    private String frequence; // "daily" | "hourly" | ...
    private Long startingTime;
    private Long lastRunTime;
    private Long runNumber;
    private List<ProcessingBlock> processingBlocks;
    private String logUrl;

    /**
     *
     */
    public Process() {
    }

    /**
     *
     * @param name
     * @param description
     * @param type
     * @param creationDate
     * @param lastModifiedDate
     * @param users
     * @param owner
     * @param status
     * @param frequence
     * @param startingTime
     * @param lastRunTime
     * @param runNumber
     * @param processingBlocks
     * @param logUrl
     */
    public Process(String name, String description, String type, Long creationDate,
            Long lastModifiedDate, List<User> users, User owner, String status, String frequence,
            Long startingTime, Long lastRunTime, Long runNumber,
            List<ProcessingBlock> processingBlocks, String logUrl) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.users = users;
        this.owner = owner;
        this.status = status;
        this.frequence = frequence;
        this.startingTime = startingTime;
        this.lastRunTime = lastRunTime;
        this.runNumber = runNumber;
        this.processingBlocks = processingBlocks;
        this.logUrl = logUrl;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public Long getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate
     */
    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    /**
     *
     * @return
     */
    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     *
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     *
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     *
     * @return
     */
    public User getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getFrequence() {
        return frequence;
    }

    /**
     *
     * @param frequence
     */
    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    /**
     *
     * @return
     */
    public Long getStartingTime() {
        return startingTime;
    }

    /**
     *
     * @param startingTime
     */
    public void setStartingTime(Long startingTime) {
        this.startingTime = startingTime;
    }

    /**
     *
     * @return
     */
    public Long getLastRunTime() {
        return lastRunTime;
    }

    /**
     *
     * @param lastRunTime
     */
    public void setLastRunTime(Long lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    /**
     *
     * @return
     */
    public Long getRunNumber() {
        return runNumber;
    }

    /**
     *
     * @param runNumber
     */
    public void setRunNumber(Long runNumber) {
        this.runNumber = runNumber;
    }

    /**
     *
     * @return
     */
    public List<ProcessingBlock> getProcessingBlocks() {
        return processingBlocks;
    }

    /**
     *
     * @param processingBlocks
     */
    public void setProcessingBlocks(List<ProcessingBlock> processingBlocks) {
        this.processingBlocks = processingBlocks;
    }

    /**
     *
     * @return
     */
    public String getLogUrl() {
        return logUrl;
    }

    /**
     *
     * @param logUrl
     */
    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

}

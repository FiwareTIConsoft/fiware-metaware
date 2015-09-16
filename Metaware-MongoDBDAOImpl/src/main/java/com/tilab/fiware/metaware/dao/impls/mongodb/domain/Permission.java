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
import java.util.Map;
import org.bson.types.ObjectId;

/**
 * Domain class for Permission, extension of a BasicDBObnject for MongoDB.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Permission extends BasicDBObject {
//    ObjectId referenceId;
//    String perm;

    /**
     * Empty constructor.
     */
    public Permission() {
    }

    /**
     * Create a new permission from a starting map.
     *
     * @param m the starting map.
     */
    public Permission(Map m) {
        super(m);
    }

    /**
     * Full constructor.
     *
     * @param referenceId the Id of the User, Department, or Company that can perform the specified
     *                    actions.
     * @param perm        the string that contains the actions allowed for the related entity.
     */
    public Permission(ObjectId referenceId, String perm) {
        put("referenceId", referenceId);
        put("perm", perm);
    }

    /**
     * Get the Id of the User, Department, or Company associated to the current permission object.
     *
     * @return the String representation of the Id.
     */
    public String getReference() {
        return getString("referenceId");
    }

    /**
     * Get the Id of the User, Department, or Company associated to the current permission object.
     *
     * @return ObjectId
     */
    public ObjectId getReferenceId() {
        if (get("referenceId") instanceof ObjectId) {
            return (ObjectId) get("referenceId");
        }
        return null;
    }

    /**
     *
     * @param referenceId
     * @throws InvalidIdException
     */
    public void setReference(String referenceId) throws InvalidIdException {
        if (!ObjectId.isValid(referenceId)) {
            // do nothing
            throw new InvalidIdException();
        } else {
            put("referenceId", new ObjectId(referenceId));
        }
    }

    /**
     *
     * @param referenceId
     */
    public void setReferenceId(ObjectId referenceId) {
        put("referenceId", referenceId);
    }

    /**
     *
     * @return
     */
    public String getPerm() {
        return getString("perm");
    }

    /**
     *
     * @param perm
     */
    public void setPerm(String perm) {
        put("perm", perm);
    }
}

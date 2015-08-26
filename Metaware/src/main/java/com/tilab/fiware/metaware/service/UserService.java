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
 * Author: Marco Terrinoni <marco.terrinoni at consoft.it>
 */
package com.tilab.fiware.metaware.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;

/**
 * Operations related to users.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class UserService {

    // Logger
    private static final Logger log = Logger.getLogger(UserService.class);

    // Messages
    private static final String MSG_SRV_GET_BY_CREDENTIALS
            = "Finding specific user by credentials...";
    private static final String MSG_SRV_GET_LIST = "Serving users list request...";
    private static final String MSG_SRV_GET = "Serving request for user with Id: ";
    private static final String MSG_SRV_CREATE = "Serving new user creation request...";
    private static final String MSG_SRV_UPSERT = "Serving update request for user with Id: ";
    private static final String MSG_SRV_DELETE = "Serving delete request for user with Id: ";

    /**
     * Checks if a user with the specified credentials exists.
     *
     * @param username the username of the user to be searched.
     * @param password the password of the user to be searched.
     * @return the user with the specified credentials.
     */
    public User getUserByCredentials(String username, String password) {
        log.debug(MSG_SRV_GET_BY_CREDENTIALS);
        return INSTANCE.getUserDao().getUserByCredentials(username, password);
    }

    /**
     * Retrieves the list of users.
     *
     * @return the list of users.
     */
    public List<User> getUsersList() {
        log.debug(MSG_SRV_GET_LIST);
        return INSTANCE.getUserDao().getUsersList();
    }

    /**
     * Retrieves the selected user.
     *
     * @param id the Id of the selected user.
     * @return the selected user object.
     */
    public User getUser(String id) {
        log.debug(MSG_SRV_GET + id + " ...");
        return INSTANCE.getUserDao().getUser(id);
    }

    /**
     * Create a new user
     *
     * @param user the new user to be saved.
     * @return the Id of the new user.
     * @throws JsonProcessingException
     * @throws java.security.NoSuchAlgorithmException
     * @throws JSONException
     */
    public String createUser(User user)
            throws JsonProcessingException, NoSuchAlgorithmException, JSONException {
        log.debug(MSG_SRV_CREATE);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        //user.setSalt(Util.getSalt());
        return INSTANCE.getUserDao().createUser(user);
    }

    /**
     * Update the selected user if exists, otherwise create a new one.
     *
     * @param id the Id of the selected user to be updated.
     * @param user the user object with the modifications (or the new user to be saved).
     * @return the updated user object.
     * @throws JsonProcessingException
     */
    public User upsertUser(String id, User user) throws JsonProcessingException {
        log.debug(MSG_SRV_UPSERT + id + " ...");
        return INSTANCE.getUserDao().upsertUser(id, user);
    }

    /**
     * Remove the selected user.
     *
     * @param id the Id of the selected user.
     */
    public void deleteUser(String id) {
        log.debug(MSG_SRV_DELETE + id + " ...");
        INSTANCE.getUserDao().deleteUser(id);
    }

}

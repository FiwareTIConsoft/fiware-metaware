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
package com.tilab.fiware.metaware.dao;

import com.tilab.fiware.metaware.dao.domain.User;
import java.util.List;

/**
 * DAO interface for User domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public interface UserDao {

    /**
     * Retrieves the list of all the users. Equivalent to "GET /users".
     *
     * @return the list of users.
     */
    public abstract List<User> getUsersList();

    /**
     * Retrieve the selected user. Equivalent to "GET /users/{userId}".
     *
     * @param id the Id of the selected user.
     * @return the selected user.
     */
    public abstract User getUser(String id);

    /**
     * Create a new user. Equivalent to "POST /users".
     *
     * @param u the object of the new user.
     * @return the Id of the new user.
     */
    public abstract String createUser(User u);

    /**
     * Update (or insert, if missing) the selected user. Equivalent to "PUT /users/{userId}".
     *
     * @param id the Id of the selected user to be updated.
     * @param u the user to be updated.
     * @return the updated user.
     */
    public abstract User upsertUser(String id, User u);

    /**
     * Delete the selected user. Equivalent to "DELETE /users/{userId}".
     *
     * @param id the Id of the user to be deleted.
     */
    public abstract void deleteUser(String id);
}

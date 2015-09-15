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
import org.bson.types.ObjectId;

/**
 * Domain class for User, extension of a BasicDBObnject for MongoDB.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class User extends BasicDBObject {
//    private String name;
//    private String surname;
//    private String email;
//    private String phone;
//    private Company company;
//    private Department departments;
//    private String username;
//    private String password;
//    private Role role;

    /**
     * Empty constructor.
     */
    public User() {
    }

    /**
     * Full constructor.
     *
     * @param name the namer of the user.
     * @param surname the surname of the user.
     * @param email the email address of the user.
     * @param phone the phone number of the user.
     * @param address
     * @param company_id the company which the user is associated with.
     * @param department_id the department which the used is associated with.
     * @param username the username of the user.
     * @param password the password of the user (hashed).
     * @param role the role of the user.
     */
    public User(String name, String surname, String email, String phone, String address,
            ObjectId company_id, ObjectId department_id, String username, String password,
            String role) {
        put("name", name);
        put("surname", surname);
        put("email", email);
        put("phone", phone);
        put("address", address);
        put("company_id", company_id); // could be useless since the department already contains the id of the company
        put("department_id", department_id);
        put("username", username);
        put("password", password);
        put("role", role);
    }

    /**
     * Get the String representation of the ObjectId of the user.
     *
     * @return the ObjectId of the user as a normal String.
     */
    public String getId() {
        return getString("_id");
    }

    /**
     * Get the name of the user.
     *
     * @return the name of the user.
     */
    public String getName() {
        return getString("name");
    }

    /**
     * Set the name of the user.
     *
     * @param name the name of the user.
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Get the surname of the user.
     *
     * @return the surname of the user.
     */
    public String getSurname() {
        return getString("surname");
    }

    /**
     * Set the surname of the user.
     *
     * @param surname the surname of the user
     */
    public void setSurname(String surname) {
        put("surname", surname);
    }

    /**
     * Get the email address of the user.
     *
     * @return the email address of the user.
     */
    public String getEmail() {
        return getString("email");
    }

    /**
     * Set the email address of the user.
     *
     * @param email the email address of the user.
     */
    public void setEmail(String email) {
        put("email", email);
    }

    /**
     * Get the phone number of the user.
     *
     * @return the phone number of the user.
     */
    public String getPhone() {
        return getString("phone");
    }

    /**
     * Set the phone number of the user.
     *
     * @param phone the phone number of the user.
     */
    public void setPhone(String phone) {
        put("phone", phone);
    }

    public String getAddress() {
        return getString("address");
    }

    public void setAddress(String address) {
        put("address", address);
    }

    /**
     * Get the company associated to the user.
     *
     * @return the company associated to the user.
     */
    public Object getCompany() {
        return get("company_id");
    }

    /**
     * Assign a company to the user. Note: actually at this version this method is for testing
     * purpose only.
     *
     * @param company_id the company Id of the user (as String).
     */
    public void setCompany(String company_id) {
        put("company_id", company_id);
    }

    /**
     * Get the company Id of the user.
     *
     * @return the company Id of the user.
     */
    public ObjectId getCompanyId() {
        if (get("company_id") instanceof ObjectId) {
            return (ObjectId) get("company_id");
        }
        return null;
    }

    /**
     * Assign a company to the user.
     *
     * @param company_id the company Id to be associated.
     */
    public void setCompanyId(ObjectId company_id) {
        put("company_id", company_id);
    }

    /**
     * Get the department associated to the user.
     *
     * @return the department associated to the user.
     */
    public Object getDepartment() {
        return get("department_id");
    }

    /**
     * Assign a department to the user. Note: actually at this version this method is for testing
     * purpose only.
     *
     * @param department_id the department Id of the user (as String).
     */
    public void setDepartment(String department_id) {
        put("department_id", department_id);
    }

    /**
     * Get the department Id of the user.
     *
     * @return the department Id of the user.
     */
    public ObjectId getDepartmentId() {
        if (get("department_id") instanceof ObjectId) {
            return (ObjectId) get("department_id");
        }
        return null;
    }

    /**
     * Assign a department to the user.
     *
     * @param department_id the department to be assigned
     */
    public void setDepartmentId(ObjectId department_id) {
        put("department_id", department_id);
    }

    /**
     * Get the username of the user.
     *
     * @return the username of the user.
     */
    public String getUsername() {
        return getString("username");
    }

    /**
     * Set the username of the user.
     *
     * @param username the username of the user.
     */
    public void setUsername(String username) {
        put("username", username);
    }

    /**
     * Get the hashed password of the user.
     *
     * @return the hashed password of the user.
     */
    public String getPassword() {
        return getString("password");
    }

    /**
     * Assign a new password to the user.
     *
     * @param password the new hashed password to be assigned.
     */
    public void setPassword(String password) {
        put("password", password);
    }

    /**
     * Get the role of the user, rendered as a String.
     *
     * @return the string of the role of the user.
     */
    public String getRole() {
        return getString("role");
    }

    /**
     * Set the role of the user.
     *
     * @param role the string of the role to be assigned.
     */
    public void setRole(String role) {
        put("role", role);
    }

    /**
     * Check if the user has administrator rights.
     *
     * @return true whenever the user is administrator.
     */
    public boolean isAdmin() {
        return getString("role").equals("admin");
    }

}

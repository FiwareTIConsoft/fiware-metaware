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

/**
 * Domain class for Company, extension of a BasicDBObject for MongoDB.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Company extends BasicDBObject {
//    private String name;
//    private String description;
//    private String email;
//    private String phone;
//    private String address;
//    private String url;

    /**
     * Empty constructor.
     */
    public Company() {
    }

    /**
     * Full constructor.
     *
     * @param name        the name of the company.
     * @param description the description of the company.
     * @param email       the email address of the company.
     * @param phone       the phone number of the company (stored as a String).
     * @param address     the address of the company (stored as a String).
     * @param url         the web page of the company.
     */
    public Company(String name, String description, String email, String phone, String address,
            String url) {
        put("name", name);
        put("description", description);
        put("email", email);
        put("phone", phone);
        put("address", address);
        put("url", url);
    }

    /**
     * Get the String representation of the ObjectId of the company.
     *
     * @return the ObjectId of the company as a normal String.
     */
    public String getId() {
        return getString("_id");
    }

    /**
     * Get the name of the company.
     *
     * @return the name of the company.
     */
    public String getName() {
        return getString("name");
    }

    /**
     * Set the name of the company.
     *
     * @param name the name of the company.
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Get the description of the company.
     *
     * @return the description of the company.
     */
    public String getDescription() {
        return getString("description");
    }

    /**
     * Set the description of the company.
     *
     * @param description the description of the company.
     */
    public void setDescription(String description) {
        put("description", description);
    }

    /**
     * Get the email address of the company as a normal String.
     *
     * @return the email address of the company as a normal String.
     */
    public String getEmail() {
        return getString("email");
    }

    /**
     * Set the email address of the company.
     *
     * @param email the email address of the company as a normal String.
     */
    public void setEmail(String email) {
        put("email", email);
    }

    /**
     * Get the phone number of the company as a normal String.
     *
     * @return the phone number of the company as a normal String.
     */
    public String getPhone() {
        return getString("phone");
    }

    /**
     * Set the phone number of the company.
     *
     * @param phone the phone number of the company.
     */
    public void setPhone(String phone) {
        put("phone", phone);
    }

    /**
     * Retrieve the physical address of the company in a unique String.
     *
     * @return the address of the company.
     */
    public String getAddress() {
        return getString("address");
    }

    /**
     * Set the physical address of the company.
     *
     * @param address the address of the company in a unique String.
     */
    public void setAddress(String address) {
        put("address", address);
    }

    /**
     * Get the web page address of the company as a normal String.
     *
     * @return the web page address of the company as a normal String.
     */
    public String getUrl() {
        return getString("url");
    }

    /**
     * Set the web page address of the company.
     *
     * @param url the web page address of the company as a normal String.
     */
    public void setUrl(String url) {
        put("url", url);
    }

}

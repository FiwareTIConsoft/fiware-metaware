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
 * Domain class for Department, extension of a BasicDBObnject for MongoDB.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Department extends BasicDBObject {
//    private String name;
//    private String description;
//    private Company company;
//    private String email;
//    private String phone;
//    private String url;

    /**
     * Empty constructor.
     */
    public Department() {
    }

    /**
     * Full constructor.
     *
     * @param name the name of the department.
     * @param description the description of the department.
     * @param company_id the Id of the company at which the department is associated.
     * @param email the email address of the department.
     * @param phone the phone number of the department.
     * @param address
     * @param url the web page of the department.
     */
    public Department(String name, String description, ObjectId company_id, String email,
            String phone, String address, String url) {
        put("name", name);
        put("description", description);
        put("company_id", company_id);
        put("email", email);
        put("phone", phone);
        put("address", address);
        put("url", url);
    }

    /**
     * Get the String representation of the ObjectId of the department.
     *
     * @return the ObjectId of the department as a normal String.
     */
    public String getId() {
        return getString("_id");
    }

    /**
     * Get the name of the department.
     *
     * @return the name of the department.
     */
    public String getName() {
        return getString("name");
    }

    /**
     * Sets the new name of the department.
     *
     * @param name the name of the department.
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Get the description of the department.
     *
     * @return the description of the department.
     */
    public String getDescription() {
        return getString("description");
    }

    /**
     * Sets the new description of the department.
     *
     * @param description the description of the department.
     */
    public void setDescription(String description) {
        put("description", description);
    }

    /**
     * Get the company of the department. This method can return either a String or a LinkedHashMap.
     *
     * @return the company of the department.
     */
    public Object getCompany() {
        return get("company_id");
    }

    /**
     * Assign the department to a specific company. Note: actually at this version this method is
     * for testing purpose only.
     *
     * @param company_id the Id of the company that will include the department (as String).
     */
    public void setCompany(String company_id) {
        put("company_id", company_id);
    }

    /**
     * Get the company Id of the department.
     *
     * @return the company Id of the department.
     */
    public ObjectId getCompanyId() {
        return (ObjectId) get("company_id");
    }

    /**
     * Assign the department to a specific company.
     *
     * @param company_id the Id company that will include the department.
     */
    public void setCompanyId(ObjectId company_id) {
        put("company_id", company_id);
    }

    /**
     * Get the email address of the department.
     *
     * @return the email address of the department.
     */
    public String getEmail() {
        return getString("email");
    }

    /**
     * Sets the new email address of the department.
     *
     * @param email the email address of the department.
     */
    public void setEmail(String email) {
        put("email", email);
    }

    /**
     * Get the phone number of the department as a normal String.
     *
     * @return the phone number of the department.
     */
    public String getPhone() {
        return getString("phone");
    }

    /**
     * Sets the new phone number of the department (as a normal String).
     *
     * @param phone the phone number of the department.
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
     * Get the web page of the department as a normal String.
     *
     * @return the web page of the department.
     */
    public String getUrl() {
        return getString("url");
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        put("url", url);
    }

}

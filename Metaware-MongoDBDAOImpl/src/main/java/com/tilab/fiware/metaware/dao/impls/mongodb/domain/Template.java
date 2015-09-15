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
import java.util.Map;

/**
 * Domain class for Template, extension of a BasicDBObject for MongoDB.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Template extends BasicDBObject {
//    private String name;
//    private TemplateDetails details;

    /**
     * Creates a new template.
     */
    public Template() {
    }

    public Template(String name, TemplateDetails details) {
        put("name", name);
        put("details", details);
    }

    /**
     * Gets the name of the dataset at which the template is associated.
     *
     * @return the name of the dataset.
     */
    public String getName() {
        return getString("name");
    }

    /**
     * Set the name of the dataset at which the template is associated.
     *
     * @param name the name of the dataset.
     */
    public void setName(String name) {
        put("name", name);
    }

    /**
     * Gets the details of the template.
     *
     * @return the object containing the details of the template.
     */
    public TemplateDetails getDetails() {
        try {
            return new TemplateDetails((Map) get("details")); // mongodb java driver doesn't support it automatically
        } catch (NullPointerException e) { // the field doesn't exist
            return null;
        }
    }

    /**
     * Set the details of the template
     *
     * @param details
     */
    public void setDetails(TemplateDetails details) {
        put("details", details);
    }

}

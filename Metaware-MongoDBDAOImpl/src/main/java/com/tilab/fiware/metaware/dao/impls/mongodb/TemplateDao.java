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
package com.tilab.fiware.metaware.dao.impls.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import static com.tilab.fiware.metaware.dao.DaoCommonConstants.TEMPLATES_COLLECTION_NAME;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Template;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * DAO implementation for Template domain class.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class TemplateDao {

    // Logger
    private static final Logger log = Logger.getLogger(TemplateDao.class);

    // Messages
    private static final String MSG_DAO_GET_LIST = "Retrieve templates list.";
    private static final String MSG_DAO_GET = "Retrieve template with name: ";
    private static final String MSG_DAO_CREATE = "Create new template.";
    private static final String MSG_DAO_DELETE = "Delete template with name: ";
    private static final String MSG_ERR_NOT_FOUND = "Template not found.";
    private static final String MSG_ERR_NOT_VALID_OBJ = "Template object not specified.";

    // MongoDB objects
    private DBCollection templatesCollection;
    private DBCursor cursor;

    /**
     * Gets the list of metadata structure templates of all objects.
     *
     * @return templatesList the list of metadata structure templates.
     */
    public List<Template> getTemplatesList() {
        log.debug(MSG_DAO_GET_LIST);

        List<Template> templatesList = new ArrayList<>();

        templatesCollection = INSTANCE.getDatasource().getDbCollection(TEMPLATES_COLLECTION_NAME);
        templatesCollection.setObjectClass(Template.class);
        cursor = templatesCollection.find();

        try {
            while (cursor.hasNext()) {
                Template t = (Template) cursor.next();
                templatesList.add(t);
            }
        } finally {
            cursor.close();
        }

        return templatesList;
    }

    /**
     * Gets the metadata structure template from the specified object name.
     *
     * @param name the name of the specified object.
     * @return template the template of the metadata structure of the object.
     */
    public Template getTemplate(String name) {
        log.debug(MSG_DAO_GET + name + " .");

        templatesCollection = INSTANCE.getDatasource().getDbCollection(TEMPLATES_COLLECTION_NAME);
        templatesCollection.setObjectClass(Template.class);
        BasicDBObject query = new BasicDBObject();
        query.put("name", name);
        Template template = (Template) templatesCollection.findOne(query);

        if (template == null) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(template);
            log.debug("Requested user: " + jsonMsg); // print res in json format
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return template;
    }

    /**
     * Create the metadata structure template of the specified object.
     *
     * @param template the metadata structure template.
     * @return the name of the object.
     */
    public String createTemplate(Template template) {
        log.debug(MSG_DAO_CREATE);

        if (template == null) {
            log.error(MSG_ERR_NOT_VALID_OBJ);
            throw new BadRequestException(MSG_ERR_NOT_VALID_OBJ);
        }

        templatesCollection = INSTANCE.getDatasource().getDbCollection(TEMPLATES_COLLECTION_NAME);
        templatesCollection.setObjectClass(Template.class);
        templatesCollection.insert(template);

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(template);
            log.debug("New template: " + jsonMsg);
        } catch (JsonProcessingException e) {
            log.error(e, e);
        }

        return template.getName();
    }

    /**
     * Remove the metadata structure template of the specified object.
     *
     * @param name the name of the object.
     */
    public void deleteTemplate(String name) {
        log.debug(MSG_DAO_DELETE + name);

        templatesCollection = INSTANCE.getDatasource().getDbCollection(TEMPLATES_COLLECTION_NAME);
        BasicDBObject query = new BasicDBObject();
        query.put("name", name);
        WriteResult wRes = templatesCollection.remove(query);

        if (wRes.getN() == 0) {
            log.error(MSG_ERR_NOT_FOUND);
            throw new ResourceNotFoundException();
        }
    }

}

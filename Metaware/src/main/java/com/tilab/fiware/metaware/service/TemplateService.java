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
package com.tilab.fiware.metaware.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Template;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Operations related to templates.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class TemplateService {

    // Logger
    private static final Logger log = Logger.getLogger(TemplateService.class);

    // Messages
    private static final String MSG_SRV_GET_LIST = "Serving templates list request...";
    private static final String MSG_SRV_GET = "Serving request for template with name: ";
    private static final String MSG_SRV_CREATE = "Serving new template creation request...";
    private static final String MSG_SRV_DELETE = "Serving request for template removal with name: ";

    /**
     * Retrieves the list of templates.
     *
     * @return The list of templates
     */
    public List<Template> getTemplatesList() {
        log.debug(MSG_SRV_GET_LIST);
        return INSTANCE.getTemplateDao().getTemplatesList();
    }

    /**
     * Retrieves the template of a specific object.
     *
     * @param name The name of the template to be found
     * @return The selected template
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public Template getTemplate(String name) throws JsonProcessingException {
        log.debug(MSG_SRV_GET + name + " ...");
        return INSTANCE.getTemplateDao().getTemplate(name);
    }

    /**
     * Creates a new template.
     *
     * @param template the template to be stored.
     * @return the name of the new template.
     */
    public String createTemplate(Template template) {
        log.debug(MSG_SRV_CREATE);
        return INSTANCE.getTemplateDao().createTemplate(template);
    }

    /**
     * Deletes the selected template.
     *
     * @param name the name of the template.
     */
    public void deleteTemplate(String name) {
        log.debug(MSG_SRV_DELETE + name);
        INSTANCE.getTemplateDao().deleteTemplate(name);
    }
}

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
package com.tilab.fiware.metaware.dao;

import com.tilab.fiware.metaware.dao.domain.Template;
import java.util.List;

/**
 * DAO interface for Template domain class.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public interface TemplateDao {

    /**
     * Retrieves a list of available templates.
     *
     * @return the list of templates.
     */
    public abstract List<Template> getTemplatesList();

    /**
     * Retrieves the selected template.
     *
     * @param uid
     * @return the object of the selected template.
     */
    public abstract Template getTemplate(String uid);

    /**
     * Creates a new template.
     *
     * @param template
     * @return the id of the new template.
     */
    public abstract String createTemplate(Template template);
}

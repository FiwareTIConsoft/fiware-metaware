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

import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Operations related to companies.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class CompanyService {

    // Logger
    private static final Logger log = Logger.getLogger(CompanyService.class);

    // Messages
    private static final String MSG_SRV_GET_LIST = "Serving companies list request...";
    private static final String MSG_SRV_GET = "Serving request for company with Id: ";
    private static final String MSG_SRV_CREATE = "Serving new company creation request...";
    private static final String MSG_SRV_UPSERT = "Serving update request for company with Id: ";
    private static final String MSG_SRV_DELETE = "Serving delete request for company with Id: ";

    /**
     * Retrieves the list of companies.
     *
     * @return the list of companies.
     */
    public List<Company> getCompaniesList() {
        log.debug(MSG_SRV_GET_LIST);
        return INSTANCE.getCompanyDao().getCompaniesList();
    }

    /**
     * Retrieves the selected company.
     *
     * @param id the Id of the selected company.
     * @return the selected company object.
     */
    public Company getCompany(String id) {
        log.debug(MSG_SRV_GET + id + "...");
        return INSTANCE.getCompanyDao().getCompany(id);
    }

    /**
     * Create a new company.
     *
     * @param company the new company to be saved.
     * @return the Id of the new company.
     */
    public String createCompany(Company company) {
        log.debug(MSG_SRV_CREATE);
        return INSTANCE.getCompanyDao().createCompany(company);
    }

    /**
     * Update the selected company if exists, otherwise create a new one.
     *
     * @param id      the Id of the selected company to be updated.
     * @param company the company object with the modifications (or the company to be saved).
     * @return the updated company object.
     */
    public Company upsertCompany(String id, Company company) {
        log.debug(MSG_SRV_UPSERT + id + "...");
        return INSTANCE.getCompanyDao().upsertCompany(id, company);
    }

    /**
     * Remove the selected company.
     *
     * @param id the Id of the selected company.
     */
    public void deleteCompany(String id) {
        log.debug(MSG_SRV_DELETE + id + "...");
        INSTANCE.getCompanyDao().deleteCompany(id);
    }
}

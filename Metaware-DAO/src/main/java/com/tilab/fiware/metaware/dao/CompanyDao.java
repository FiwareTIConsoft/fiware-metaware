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

import com.tilab.fiware.metaware.dao.domain.Company;
import java.util.List;

/**
 * DAO interface for Company domain class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public interface CompanyDao {

    /**
     * Retrieves the list of all the companies. Equivalent to "GET /companies".
     *
     * @return the list of companies.
     */
    public abstract List<Company> getCompaniesList();

    /**
     * Retrieve the selected company. Equivalent to "GET /companies/{companyId}".
     *
     * @param id the Id of the selected company.
     * @return the selected company.
     */
    public abstract Company getCompany(String id);

    /**
     * Create a new company. Equivalent to "POST /companies".
     *
     * @param c the object of the new company.
     * @return the Id of the new company.
     */
    public abstract String createCompany(Company c);

    /**
     * Update (or insert, if missing) the selected company. Equivalent to "PUT
     * /companies/{companyId}".
     *
     * @param id the Id of the selected company to be updated.
     * @param c the company to be updated.
     * @return the updated company.
     */
    public abstract Company upsertCompany(String id, Company c);

    /**
     * Delete the selected company. Equivalent to "DELETE /companies/{companyId}".
     *
     * @param id the Id of the company to be deleted.
     */
    public abstract void deleteCompany(String id);
}

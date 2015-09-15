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
package com.tilab.fiware.metaware.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.tilab.fiware.metaware.core.SingltProv.INSTANCE;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 * API about companies.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
@Path("/v1/companies")
@Api(
        value = "/v1/companies",
        description = "Operations about companies"
)
public class CompanyRestSrv {

    // Logger
    private static final Logger log = Logger.getLogger(CompanyRestSrv.class);

    // Messages
    private static final String MSG_GET_TEST = "Company test requested.";
    private static final String MSG_GET_TEST_OK
            = "Result: this service for Company is up and running!";
    private static final String MSG_GET_LIST = "Companies list requested.";
    private static final String MSG_GET = "Company requested with Id: ";
    private static final String MSG_CREATE = "New company creation requested.";
    private static final String MSG_UPSERT = "Company update requested.";
    private static final String MSG_DELETE = "Company removal requested.";

    /**
     * Retrieves the list of companies.
     *
     * @param authorization basic authorization string.
     * @return The list of companies.
     */
    @GET
    @ApiOperation(
            value = "Retrieves the list of companies",
            notes = "This resource is accessible only by an admin.",
            response = Company.class,
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompaniesList(
            @ApiParam(value = "Basic authentication string",
                    defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization) {
        log.info(MSG_GET_LIST);

        List<Company> companiesList;
        String jsonMsg;

        try {
            companiesList = INSTANCE.getCompanyService().getCompaniesList();
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(companiesList);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Retrieves the selected company by Id.
     *
     * @param authorization basic authorization string.
     * @param id the Id of the selected company.
     * @return the selected company.
     */
    @GET
    @Path("/{companyId}")
    @ApiOperation(
            value = "Retrieves the selected company by Id",
            notes = "Only the admin can retrieve all the companies",
            response = Company.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid Id supplied"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Company not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompany(
            @ApiParam(value = "Basic authentication string",
                    defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the company to fetch", required = true)
            @PathParam("companyId") String id) {
        log.info(MSG_GET + id + ".");

        Company company;
        String jsonMsg;

        try {
            company = INSTANCE.getCompanyService().getCompany(id);
        } catch (BadRequestException e) {
            log.error(e, e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ResourceNotFoundException e) {
            log.error(e, e);
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(company);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Create a new company.
     *
     * @param authorization basic authorization string.
     * @param company
     * @return
     */
    @POST
    @ApiOperation(
            value = "Create a new company",
            notes = "Only an admin can create a new company."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompany(
            @ApiParam(value = "Basic authentication string",
                    defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The company to be created", required = true) Company company) {
        log.info(MSG_CREATE);

        String id;

        try {
            id = INSTANCE.getCompanyService().createCompany(company);
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(id, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Update an existing company or insert a new one.
     *
     * @param authorization basic authorization string.
     * @param id
     * @param company
     * @return
     */
    @PUT
    @Path(value = "/{companyId}")
    @ApiOperation(
            value = "Updates an existing company on insert a new one",
            notes = "If the selected company to be update does not exist, then a new company is "
            + "created with the details specified in the sent Company object.",
            response = Company.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upsertCompany(
            @ApiParam(value = "Basic authentication string",
                    defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected company to be updated", required = true)
            @PathParam("companyId") String id,
            @ApiParam(value = "The company object with the modifications (or the company to be"
                    + "saved)") Company company) {
        log.info(MSG_UPSERT);

        Company res;
        String jsonMsg;

        try {
            res = INSTANCE.getCompanyService().upsertCompany(id, company);
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(res);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Delete the selected company.
     *
     * @param authorization basic authorization string.
     * @param id
     * @return
     */
    @DELETE
    @Path("/{companyId}")
    @ApiOperation(
            value = "Deletes the selected company by Id",
            notes = "Only an admin can delete a company."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Company not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCompany(@HeaderParam("Authorization") String authorization,
            @PathParam("companyId") String id) {
        log.info(MSG_DELETE);

        try {
            INSTANCE.getCompanyService().deleteCompany(id);
        } catch (BadRequestException e) {
            log.error(e, e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ResourceNotFoundException e) {
            log.error(e, e);
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(id, MediaType.APPLICATION_JSON).build();
    }
}

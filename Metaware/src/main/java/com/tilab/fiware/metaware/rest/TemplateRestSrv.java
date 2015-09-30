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
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Template;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 * Operations about templates.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
@Path("/v1/templates")
@Api(
        value = "/v1/templates",
        description = "Operations about templates"
)
public class TemplateRestSrv {

    // Logger
    private static final Logger log = Logger.getLogger(TemplateRestSrv.class);

    // Messages
    private static final String MSG_GET_TEST = "Template test requested.";
    private static final String MSG_GET_TEST_OK
            = "Result: this service for Template is up and running!";
    private static final String MSG_GET_LIST = "Templates list requested.";
    private static final String MSG_GET = "Template requested with name: ";
    private static final String MSG_CREATE = "New template creation requested.";
    private static final String MSG_DELETE = "Request for template removal with name: ";

    /**
     * Discovery the list of the available templates.
     *
     * @param authorization basic authorization string.
     * @return The list of templates.
     */
    @GET
    @ApiOperation(
            value = "Discovery the list of the available templates.",
            response = Template.class,
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemplatesList(
            @ApiParam(value = "Basic authentication string",
                      defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization) {
        log.info(MSG_GET_LIST);

        List<Template> templatesList;
        String jsonMsg;

        try {
            templatesList = INSTANCE.getTemplateService().getTemplatesList();
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(templatesList);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Discovery the selected template by name.
     *
     * @param authorization basic authorization string.
     * @param name          The name of the selected template
     * @return The selected template
     */
    @GET
    @Path("/{templateName}")
    @ApiOperation(
            value = "Retrieves the selected template by name",
            response = Template.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid name supplied"),
        @ApiResponse(code = 404, message = "Template not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemplate(
            @ApiParam(value = "Basic authentication string",
                      defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The name of the template to fetch", required = true)
            @PathParam("templateName") String name) {
        log.info(MSG_GET);

        Template template;
        String jsonMsg;

        try {
            template = INSTANCE.getTemplateService().getTemplate(name);
        } catch (ResourceNotFoundException e) {
            log.error(e, e);
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(template);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Creates a new template.
     *
     * @param authorization basic authorization string.
     * @param template      the details about the new template.
     * @return the name of the template stored in Metaware.
     */
    @POST
    @ApiOperation(
            value = "Creates a new template",
            notes = "Only an admin can create a new template."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTemplate(
            @ApiParam(value = "Basic authentication string",
                      defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The template to be created", required = true) Template template) {
        log.info(MSG_CREATE);

        String name;

        name = INSTANCE.getTemplateService().createTemplate(template);

        return Response.ok(name, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Deletes the selected template from Metaware.
     *
     * @param authorization basic authorization string.
     * @param name          the name of the selected template.
     * @return the name of the selected template.
     */
    @DELETE
    @Path("/{templateName}")
    @ApiOperation(
            value = "Deletes the selected template by name",
            notes = "Only an admin can delete a template."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Template not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTemplate(@HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The name of the template to fetch", required = true)
            @PathParam("templateName") String name) {
        log.info(MSG_DELETE);

        try {
            INSTANCE.getTemplateService().deleteTemplate(name);
        } catch (ResourceNotFoundException e) {
            log.error(e, e);
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(name, MediaType.APPLICATION_JSON).build();
    }
}

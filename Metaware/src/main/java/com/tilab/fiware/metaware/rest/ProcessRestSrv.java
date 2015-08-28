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
package com.tilab.fiware.metaware.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.tilab.fiware.metaware.core.SingltProv.INSTANCE;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Process;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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
 * API about processes' metadata.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
@Path("/v1/processes")
@Api(
        value = "/v1/processes",
        description = "Operations about processes' metadata"
)
public class ProcessRestSrv {

    // Logger
    private static final Logger log = Logger.getLogger(ProcessRestSrv.class);

    // Messages
    private static final String MSG_GET_LIST = "Processes' metadata list requested.";
    private static final String MSG_GET = "Process' metadata requested with Id: ";
    private static final String MSG_CREATE = "New Process' metadata creation requested.";
    private static final String MSG_UPSERT = "Process' metadata update requested with Id: ";
    private static final String MSG_DELETE = "Process' metadata removal requested with Id: ";
    private static final String MSG_ERR_NOT_IMPL = "This feature is not implemented yet.";

    /**
     * Retrieves the list of processes' metadata.
     *
     * @param authorization basic authorization string.
     * @return the list of processes' metadata.
     */
    @GET
    @ApiOperation(
            value = "Retrieves the list of processes' metadata",
            response = Process.class,
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 501, message = "Not Implemented")
    })
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getProcessesList(@HeaderParam("Authorization") String authorization) {
        log.info(MSG_GET_LIST);

        log.error(MSG_ERR_NOT_IMPL);

        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    /**
     * Retrieves the selected process' metadata by its Id.
     *
     * @param authorization basic authorization string.
     * @param id            the Id of the selected process' metadata.
     * @return the selected process' metadata.
     */
    @GET
    @Path("/{processId}")
    @ApiOperation(
            value = "Retrieves the selected process' metadata by its Id",
            response = Process.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 501, message = "Not Implemented")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProcess(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the process' metadata to fetch", required = true)
            @PathParam("processId") String id) {
        log.info(MSG_GET);

        Process process;

        // Retrieve requesting process
        try {
            process = INSTANCE.getProcessService().getProcess(id);
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

        String jsonMsg;

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(process);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Store a new process' metadata object.
     *
     * @param authorization basic authentication string.
     * @param process       the process' metadata object to be stored.
     * @return the Id of the new process' metadata object.
     */
    @POST
    @ApiOperation(
            value = "Create a new process' metadata object"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProcess(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The process' metadata to be created", required = true) Process process) {
        log.info(MSG_CREATE);

        String id;

        try {
            id = INSTANCE.getProcessService().createProcess(process);
        } catch (BadRequestException e) {
            log.error(e, e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(id, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Updates an existing process' metadata object if exists, otherwise inserts a new one.
     *
     * @param authorization basic authorization string.
     * @param id            the Id of the existing process' metadata object.
     * @param process       the process' metadata object with the modifications.
     * @return the updated process' metadata object.
     */
    @PUT
    @Path("/{processId}")
    @ApiOperation(
            value = "Updates an existing process' metadata object or insert a new one",
            notes = "If the selected metadata object to be updated does not exist, then a new "
            + "process' metadata object is created with the details in the sent Process "
            + "object."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 501, message = "Not Implemented")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    public Response upsertProcess(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the process' metadata to update", required = true)
            @PathParam("processId") String id,
            @ApiParam(value = "The process' metadata object with the modifications") Process process) {
        log.info(MSG_UPSERT + id);

        log.error(MSG_ERR_NOT_IMPL);

        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    /**
     * Deletes the selected process' metadata.
     *
     * @param authorization basic authorization string.
     * @param id            the Id of the process' metadata object to be removed.
     * @return the deleted process' metadata object.
     */
    @DELETE
    @Path("/{processId}")
    @ApiOperation(
            value = "Deletes the selected process' metadata by its Id"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 501, message = "Not Implemented")
    })
    //@Produces(MediaType.APPLICATION_JSON)
    public Response deleteProcess(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the process' metadata to remove", required = true)
            @PathParam("processId") String id) {
        log.info(MSG_DELETE + id);

        log.error(MSG_ERR_NOT_IMPL);

        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

}

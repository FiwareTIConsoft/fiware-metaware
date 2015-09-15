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
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Algorithm;
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
 * API about algorithms' metadata.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
@Path("/v1/algorithms")
@Api(
        value = "/v1/algorithms",
        description = "Operations about algorithms' metadata"
)
public class AlgorithmRestSrv {

    // Logger
    private static final Logger log = Logger.getLogger(AlgorithmRestSrv.class);

    // Messages
    private static final String MSG_GET_LIST = "Algorithms' metadata list requested.";
    private static final String MSG_GET = "Algorithm's metadata requested with Id: ";
    private static final String MSG_CREATE = "New algorithm's metadata creation requested.";
    private static final String MSG_UPSERT = "Algorithm's metadata update requested.";
    private static final String MSG_DELETE = "Algorithm's metadata removal requested.";

    /**
     * Retrieves the list of algorithms' metadata.
     *
     * @param authorization basic authorization string.
     * @return The list of algorithms' metadata.
     */
    @GET
    @ApiOperation(
            value = "Retrieves the list of algorithms' metadata",
            response = Algorithm.class,
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlgorithmsList(@HeaderParam("Authorization") String authorization) {
        log.info(MSG_GET_LIST);

        List<Algorithm> algorithmsList;
        String jsonMsg;

        try {
            algorithmsList = INSTANCE.getAlgorithmService().getAlgorithmsList();
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(algorithmsList);
            log.debug(jsonMsg);
        } catch (Exception e) { // this catches also JsonProcessingException
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Retrieves the selected algorithm's metadata by Id.
     *
     * @param authorization basic authorization string.
     * @param id the Id of the selected algorithm's metadata.
     * @return the selected algorithm's metadata.
     */
    @GET
    @Path("/{algorithmId}")
    @ApiOperation(
            value = "Retrieves the selected algorithm's metadata by Id",
            response = Algorithm.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid Id supplied"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Algorithm's metadata not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlgorithm(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the algorithm's metadata to fetch", required = true)
            @PathParam("algorithmId") String id) {
        log.info(MSG_GET + id + ".");

        Algorithm algorithm;
        String jsonMsg;

        try {
            algorithm = INSTANCE.getAlgorithmService().getAlgorithm(id);
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
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(algorithm);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Create a new algorithm's metadata object.
     *
     * @param authorization basic authorization string.
     * @param algorithm the algorithm's metadata object to be stored.
     * @return the Id of the new algorithm's metadata object.
     */
    @POST
    @ApiOperation(
            value = "Creates a new algorithm's metadata object"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAlgorithm(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The algotithm's metadata to be created",
                    required = true) Algorithm algorithm) {
        log.info(MSG_CREATE);

        String id;

        try {
            id = INSTANCE.getAlgorithmService().createAlgorithm(algorithm);
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
     * Update an existing algorithm's metadata object, or insert a new one.
     *
     * @param authorization basic authorization string.
     * @param id the Id of the existing algorithm's metadata.
     * @param algorithm the algorithm's metadata object with the changes.
     * @return the updated dataset's metadata object.
     */
    @PUT
    @Path("/{algorithmId}")
    @ApiOperation(
            value = "Updates an existing algorithm's metadata object or insert a new one",
            notes = "If the selected metadata object to be updated does not exist, then a new"
            + "algorithm's metadata object is created with the datails specified in the"
            + "sent Algorithm object",
            response = Algorithm.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upsertAlgorithm(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected algorithm's metadata to be updated",
                    required = true)
            @PathParam("algorithmId") String id,
            @ApiParam(value = "The algorithm's metadata object with the modifications (or the new"
                    + "object to be saved)", required = true) Algorithm algorithm) {
        log.info(MSG_UPSERT);

        Algorithm res;
        String jsonMsg;

        try {
            res = INSTANCE.getAlgorithmService().upsertAlgorithm(id, algorithm);
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(res);
        } catch (BadRequestException e) {
            log.error(e, e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) { // catch also JsonProcessingException
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Deletes the selected algorithm's metadata.
     *
     * @param authorization basic authorization string.
     * @param id the Id of the algorithm's metadata object to be deleted.
     * @return the deleted algorithm's metadata.
     */
    @DELETE
    @Path("/{algorithmId}")
    @ApiOperation(
            value = "Deletes the selected algorithm's metadata by Id"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Algorithm's metadata not found"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAlgorithm(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected algorithm's metadata to be removed",
                    required = true)
            @PathParam("algorithmId") String id) {
        log.info(MSG_DELETE);

        try {
            INSTANCE.getAlgorithmService().deleteAlgorithm(id);
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

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
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.security.NoSuchAlgorithmException;
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
//import org.json.JSONException;

/**
 * API about users.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
@Path("/v1/users")
@Api(
        value = "/v1/users",
        description = "Operations about users"
)
public class UserRestSrv {

    // Logger
    private static final Logger log = Logger.getLogger(UserRestSrv.class);

    // Messages
    private static final String MSG_GET_TEST = "User test requested.";
    private static final String MSG_GET_TEST_OK
            = "Result: this service for User is up and running!";
    private static final String MSG_GET_LIST = "Users list requested.";
    private static final String MSG_GET = "User requested with Id: ";
    private static final String MSG_CREATE = "New user creation requested.";
    private static final String MSG_UPSERT = "User update requested.";
    private static final String MSG_DELETE = "User removal requested.";

    /**
     * Retrieves the list of users.
     *
     * @param authorization basic authorization string.
     * @return The list of users.
     */
    @GET
    @ApiOperation(
            value = "Retrieves the list of users",
            notes = "This resource is accesible only by an admin.",
            response = User.class,
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersList(
            @ApiParam(value = "Basic authentication string",
                      defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization) {
        log.info(MSG_GET_LIST);

        List<User> usersList;
        String jsonMsg;

        try {
            usersList = INSTANCE.getUserService().getUsersList();
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(usersList);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Retrieves the selected user by Id.
     *
     * @param authorization basic authorization string.
     * @param id            the id of the selected user.
     * @return the selected user.
     */
    @GET
    @Path("/{userId}")
    @ApiOperation(
            value = "Retrieves the selected user by Id",
            notes = "Only the admin can retrieve all the users",
            response = User.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid Id supplied"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "User not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(
            @ApiParam(value = "Basic authentication string",
                      defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the user to fetch", required = true)
            @PathParam("userId") String id) {
        log.info(MSG_GET + id + ".");

        User user;
        String jsonMsg;

        try {
            user = INSTANCE.getUserService().getUser(id);
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
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Creates a new user.
     *
     * @param authorization basic authorization string.
     * @param user          the new user to be created.
     * @return the id of the new user.
     */
    @POST
    @ApiOperation(
            value = "Creates a new user",
            notes = "Only an admin can create a new user."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(
            @ApiParam(value = "Basic authentication string",
                      defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The user to be created", required = true) User user) {
        log.info(MSG_CREATE);

        String id;

        try {
            id = INSTANCE.getUserService().createUser(user);
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
     * Updates an existing user on insert a new one.
     *
     * @param authorization basic authorization string.
     * @param id            the Id of the selected user to be updated.
     * @param user          the user object with the modifications (or the user to be saved).
     * @return
     */
    @PUT
    @Path(value = "/{userId}")
    @ApiOperation(
            value = "Updates an existing user on insert a new one",
            notes = "If the selected user to be update does not exist, then a new user is created"
            + " with the details specified in the sent User object.",
            response = User.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response upsertUser(
            @ApiParam(value = "Basic authentication string",
                      defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected user to be updated", required = true)
            @PathParam("userId") String id,
            @ApiParam(value = "The user object with the modifications (or the user to be saved)",
                      required = true) User user) {
        log.info(MSG_UPSERT);

        User res;
        String jsonMsg;

        try {
            res = INSTANCE.getUserService().upsertUser(id, user);
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
     * Deletes the selected user by Id.
     *
     * @param authorization basic authorization string.
     * @param id            the id of the selected user to be removed.
     * @return
     */
    @DELETE
    @Path("/{userId}")
    @ApiOperation(
            value = "Deletes the selected user by Id",
            notes = "Only an admin can delete a user."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "User not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(
            @ApiParam(value = "Basic authentication string",
                      defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected user to be removed", required = true)
            @PathParam("userId") String id) {
        log.info(MSG_DELETE);

        try {
            INSTANCE.getUserService().deleteUser(id);
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

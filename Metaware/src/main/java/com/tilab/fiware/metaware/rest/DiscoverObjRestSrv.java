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

import static com.tilab.fiware.metaware.core.SingltProv.INSTANCE;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 * API about discovery objects.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
@Path("/v1/discoverObjects")
@Api(
        value = "/v1/discoverObjects",
        description = "Discover all the objects usable/owned by a specific authorized user"
)
public class DiscoverObjRestSrv {

    // Logger
    private static final Logger log = Logger.getLogger(DiscoverObjRestSrv.class);

    // Messages
    private static final String MSG_DISCV_USABLE
            = "Request for metadata objects discovery usable by ";
    private static final String MSG_DISCV_OWNED
            = "Request for metadata objects discovery owned by ";

    /**
     * Discovers the objects that can be used by the specified user.
     *
     * @param authorization basic authorization string.
     * @param requestedId the Id of the specified user, department, or company that can use the
     * objects.
     * @return the result of the query.
     */
    @GET
    @Path("/usable/{requestedId}")
    @ApiOperation(
            value = "Discover all the objects usable by a specific authorized user",
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response discoverUsable(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the authorized user, department, or company that can use the "
                    + "objects", required = true)
            @PathParam("requestedId") String requestedId) {
        log.info(MSG_DISCV_USABLE + requestedId);

        List objectsList;
        String jsonMsg;

        try {
            objectsList = INSTANCE.getDiscoverObjService().discoverUsable(requestedId);
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(objectsList);
        } catch (BadRequestException e) {
            log.error(e, e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) { // this catches also JsonProcessingException
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Discovers the objects that are owned by the specified owner.
     *
     * @param authorization basic authorization string.
     * @param ownerId the Id of the specified owner.
     * @return the result of the query.
     */
    @GET
    @Path("/owned/{ownerId}")
    @ApiOperation(
            value = "Discover all the objects owned by a specific authorized user",
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response discoverOwned(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the authorized user", required = true)
            @PathParam("ownerId") String ownerId) {
        log.info(MSG_DISCV_OWNED + ownerId);

        List objectsList;
        String jsonMsg;

        try {
            objectsList = INSTANCE.getDiscoverObjService().discoverOwned(ownerId);
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(objectsList);
        } catch (BadRequestException e) {
            log.error(e, e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) { // this catches also JsonProcessingException
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }
}

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
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DataSource;
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
 * APIs about data sources' metadata
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
@Path("/v1/datasources")
@Api(
        value = "/v1/datasources",
        description = "Operations about data sources' metadata"
)
public class DataSourceRestSrv {

    // Logger
    private static final Logger log = Logger.getLogger(DataSourceRestSrv.class);

    // Messages
    private static final String MSG_GET_LIST = "Data sources' metadata list requested.";
    private static final String MSG_GET = "Data source's metadata requested with Id: ";
    private static final String MSG_CREATE = "New Data source's metadata creation requested.";
    private static final String MSG_UPSERT = "Data source's metadata update requested with Id: ";
    private static final String MSG_DELETE = "Data source's metadata removal requested with Id: ";

    /**
     * Retrieves the list of data sources' metadata.
     *
     * @param authorization basic authorization string.
     * @return the list of data sources' metadata.
     */
    @GET
    @ApiOperation(
            value = "Retrieves the list of data sources' metadata",
            response = DataSource.class,
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataSourcesList(String authorization) {
        log.info(MSG_GET_LIST);

        List<DataSource> datasourcesList;
        String jsonMsg;

        try {
            datasourcesList = INSTANCE.getDataSourceService().getDataSourcesList();
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(datasourcesList);
            log.debug(jsonMsg);
        } catch (Exception e) { // this catches also JsonProcessingException
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Retrieves the selected data source's metadata by Id.
     *
     * @param authorization basic authorization string.
     * @param id the Id of the selected data source's metadata.
     * @return the selected data source's metadata.
     */
    @GET
    @Path("/{datasourceId}")
    @ApiOperation(
            value = "Retrieves the selected data source's metadata by Id",
            response = DataSource.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid Id supplied"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Data source's metadata not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataSource(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the data source's metadata to fetch", required = true)
            @PathParam("datasourceId") String id) {
        log.info(MSG_GET + id + ".");

        DataSource datasource;
        String jsonMsg;

        try {
            datasource = INSTANCE.getDataSourceService().getDataSource(id);
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
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(datasource);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Creates a new data source's metadata.
     *
     * @param authorization basic authorization string.
     * @param datasource the data source's metadata object to be stored.
     * @return the Id of the new data source's metadata object.
     */
    @POST
    @ApiOperation(
            value = "Creates a new data source's metadata object"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDataSource(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The data source's metadata to be created", required = true) DataSource datasource) {
        log.info(MSG_CREATE);

        String id;

        try {
            id = INSTANCE.getDataSourceService().createDataSource(datasource);
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
     * Update an existing data source's metadata object, or insert a new one (if doesn't exist).
     *
     * @param authorization basic authorization string.
     * @param id the Id of the existing data source's metadata.
     * @param datasource the data source's metadata object with the changes.
     * @return the updated data source's metadata object.
     */
    @PUT
    @Path("/{datasourceId}")
    @ApiOperation(
            value = "Updates an existing data source's metadata object or inster a new one",
            notes = "If the selected metadata object to be updated does not exist, then a new"
            + "data source's metadata object is created with the datails specified in the"
            + "sent DataSource object",
            response = DataSource.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upsertDataSource(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected data source's metadata to be updated",
                    required = true) @PathParam("datasourceId") String id,
            @ApiParam(value = "The data source's metadata object with the modifications (or the new"
                    + "object to be saved)", required = true) DataSource datasource) {
        log.info(MSG_UPSERT + id);

        DataSource res;
        String jsonMsg;

        try {
            res = INSTANCE.getDataSourceService().upsertDataSource(id, datasource);
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
     * Deletes the selected data source's metadata.
     *
     * @param authorization basic authorization string.
     * @param id the Id of the data source's metadata object to be deleted.
     * @return the deleted data source's metadata.
     */
    @DELETE
    @Path("/{datasourceId}")
    @ApiOperation(
            value = "Deletes the selected data source's metadata by Id"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Data source's metadata not found"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDataSource(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected algorithm's metadata to be removed",
                    required = true) @PathParam("datasourceId") String id) {
        log.info(MSG_DELETE + id);

        try {
            INSTANCE.getDataSourceService().deleteDataSource(id);
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

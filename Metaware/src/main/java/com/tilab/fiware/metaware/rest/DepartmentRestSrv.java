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
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
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
 * API about departments.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
@Path("/v1/departments")
@Api(
        value = "/v1/departments",
        description = "Operations about departments"
)
public class DepartmentRestSrv {

    // Logger
    private static final Logger log = Logger.getLogger(DepartmentRestSrv.class);

    // Messages
    private static final String MSG_GET_TEST = "Department test requested.";
    private static final String MSG_GET_TEST_OK
            = "Result: this service for Department is up and running!";
    private static final String MSG_GET_LIST = "Departments list requested.";
    private static final String MSG_GET = "Department requested with Id: ";
    private static final String MSG_CREATE = "New department creation requested.";
    private static final String MSG_UPSERT = "Department update requested.";
    private static final String MSG_DELETE = "Department removal requested.";

    /**
     * Retrieves the list of departments.
     *
     * @param authorization basic authorization string.
     * @return The list of departments.
     */
    @GET
    @ApiOperation(
            value = "Retrieves the list of departments",
            notes = "This resource is accesible only by an admin.",
            response = Department.class,
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentsList(
            @ApiParam(value = "Basic authentication string",
                    defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization) {
        log.info(MSG_GET_LIST);

        List<Department> departmentsList;
        String jsonMsg;

        try {
            departmentsList = INSTANCE.getDepartmentService().getDepartmentsList();
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        try {
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(departmentsList);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Retrieves the selected department by Id.
     *
     * @param authorization basic authorization string.
     * @param id the Id of the selected department.
     * @return the selected department.
     */
    @GET
    @Path(value = "/{departmentId}")
    @ApiOperation(
            value = "Retrieves the selected department by Id",
            notes = "Only the admin can retrieve all the users",
            response = Department.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid Id supplied"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Department not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartment(
            @ApiParam(value = "Basic authentication string",
                    defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected department to fetch", required = true)
            @PathParam("departmentId") String id) {
        log.info(MSG_GET + id + ".");

        Department department;
        String jsonMsg;

        try {
            department = INSTANCE.getDepartmentService().getDepartment(id);
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
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(department);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Create a new department.
     *
     * @param authorization basic authorization string.
     * @param department the new department to be created.
     * @return the Id of the new department.
     */
    @POST
    @ApiOperation(
            value = "Creates a new department",
            notes = "Only an admin can create a new department."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDepartment(
            @ApiParam(value = "Basic authentication string",
                    defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The department to be created.") Department department) {
        log.info(MSG_CREATE);

        String id;

        try {
            id = INSTANCE.getDepartmentService().createDepartment(department);
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(id, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Update an existing department or insert a new one.
     *
     * @param authorization basic authorization string.
     * @param id the Id of the selected department to be updated.
     * @param department the department object with the modifications (or the department to be
     * saved).
     * @return
     */
    @PUT
    @Path(value = "/{departmentId}")
    @ApiOperation(
            value = "Updates an existing department on insert a new one",
            notes = "If the selected department to be update does not exist, then a new department"
            + "is created with the details specified in the sent Department object.",
            response = Department.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upsertDepartment(
            @ApiParam(value = "Basic authentication string",
                    defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected department to be updated", required = true)
            @PathParam("departmentId") String id,
            @ApiParam(value = "The department object with the modifications (or the department to"
                    + "be saved)") Department department) {
        log.info(MSG_UPSERT);

        Department res;
        String jsonMsg;

        try {
            res = INSTANCE.getDepartmentService().upsertDepartment(id, department);
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
     * Delete the selected department by Id.
     *
     * @param authorization basic authorization string.
     * @param id the id of the selected department to be removed.
     * @return
     */
    @DELETE
    @Path(value = "/{departmentId}")
    @ApiOperation(
            value = "Deletes the selected department by Id",
            notes = "Only an admin can delete a department."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Department not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDepartment(
            @ApiParam(value = "Basic authentication string",
                    defaultValue = "Basic cm9zc2k6cm9zc2k=", required = true)
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected department to be removed", required = true)
            @PathParam("departmentId") String id) {
        log.info(MSG_DELETE);

        try {
            INSTANCE.getDepartmentService().deleteDepartment(id);
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

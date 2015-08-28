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
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
//import com.hp.hpl.jena.vocabulary.VCARD;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.ResIterator;
import static com.tilab.fiware.metaware.core.SingltProv.INSTANCE;
import com.tilab.fiware.metaware.dao.exception.BadRequestException;
import com.tilab.fiware.metaware.dao.exception.ResourceNotFoundException;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Company;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Dataset;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.DatasetStructure;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Department;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.Permission;
import com.tilab.fiware.metaware.dao.impls.mongodb.domain.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * API about datasets' metadata.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
@Path("/v1/datasets")
@Api(
        value = "/v1/datasets",
        description = "Operations about datasets' metadata"
)
public class DatasetRestSrv {

    // Logger
    private static final Logger log = Logger.getLogger(DatasetRestSrv.class);

    // Messages
    private static final String MSG_GET_LIST = "Datasets' metadata list requested.";
    private static final String MSG_GET = "Dataset's metadata requested with Id: ";
    private static final String MSG_CREATE = "New dataset's metadata creation requested.";
    private static final String MSG_UPSERT = "Dataset's metadata update requested.";
    private static final String MSG_DELETE = "Dataset's metadata removal requested.";
    private static final String MGS_IMPORT_OPENDATA = "New dataset import.";
    private static final String MGS_EXPORT_OPENDATA = "Dataset export.";

    // Additional namespaces
    private static final String DCAT = "http://www.w3.org/ns/dcat#";
    private static final String DCT = "http://purl.org/dc/terms/";
    private static final String ADMS = "http://www.w3.org/ns/adms#";
    private static final String VCARD = "http://www.w3.org/2006/vcard/ns#";
    private static final String METAWARE = "http://metaware.org/";

    // URIs
    private static final String URI_CATALOG = METAWARE + "catalog#";
    private static final String URI_DATASET = METAWARE + "dataset#";
    private static final String URI_ORGANIZATION = METAWARE + "organization#";
    private static final String URI_CONTACTPOINT = METAWARE + "contactpoint#";
    private static final String URI_TELEPHONE = METAWARE + "telephone#";
    private static final String URI_ADDRESS = METAWARE + "address#";
    private static final String URI_DISTRIBUTION = METAWARE + "distribution#";
    private static final String URI_PUBLISHER = METAWARE + "publisher#";
    private static final String URI_LICENSE_CCZERO
            = "http://creativecommons.org/publicdomain/zero/1.0/";
    private static final String URI_VCARD_ORGANIZATION = VCARD + "Organization";
    private static final String URI_VCARD_INDIVIDUAL = VCARD + "Individual";
    private static final String URI_VCARD_TELEPHONE = VCARD + "Voice";
    private static final String URI_VCARD_WORK = VCARD + "Work";
    private static final String URI_VCARD_ADDRESS = VCARD + "Address";
    private static final String URI_LANGUAGE_EN = "http://id.loc.gov/vocabulary/iso639-1/en";

    // RDF Properties
    Property admsContactPoint;
    Property dcatAccessURL;
    Property dcatDataset;
    Property dcatDistribution;
    Property dcatKeyword;
//        Property dcatTheme;
//        Property dcatThemeTaxonomy;
    Property dctDescription;
    Property dctFormat;
    Property dctIdentifier;
    Property dctIssued;
    Property dctLanguage;
    Property dctLicense;
    Property dctModified;
    Property dctPublisher;
    Property dctTitle;
    Property dctType;
    Property vcardHasValue;
    Property vardHasAddress;
    Property vcardHasTelephone;
    Property vcardHasEmail;
    Property vcardStreetAddress;
    Property vcardPostalCode;
    Property vcardLocality;
    Property vcardCountryName;
    Property vcardFn;

    // Jena Model
    Model model;

    // Date format declaration
    DateFormat format;

    /**
     * Class constructor used to instantiate the Jena Model and define the various properties for
     * the model itself.
     */
    public DatasetRestSrv() {
        // Jena model instantiation - empty model
        model = ModelFactory.createDefaultModel();

        // Prefixes definition
        model.setNsPrefix("adms", ADMS);
        model.setNsPrefix("dcat", DCAT);
        model.setNsPrefix("dct", DCT);
        model.setNsPrefix("foaf", FOAF.NS);
        model.setNsPrefix("vcard", VCARD);

        // Properties instantiation
        admsContactPoint = model.createProperty(ADMS, "contactPoint");
        dcatAccessURL = model.createProperty(DCAT, "accessURL");
        dcatDataset = model.createProperty(DCAT, "dataset");
        dcatDistribution = model.createProperty(DCAT, "distribution");
        dcatKeyword = model.createProperty(DCAT, "keyword");
        //        dcatTheme = model.createProperty(DCAT, "theme");
        //        dcatThemeTaxonomy = model.createProperty(DCAT, "themeTaxonomy");
        dctDescription = model.createProperty(DCT, "description");
        dctFormat = model.createProperty(DCT, "format");
        dctIdentifier = model.createProperty(DCT, "identifier");
        dctIssued = model.createProperty(DCT, "issued");
        dctLanguage = model.createProperty(DCT, "language");
        dctLicense = model.createProperty(DCT, "license");
        dctModified = model.createProperty(DCT, "modified");
        dctPublisher = model.createProperty(DCT, "publisher");
        dctTitle = model.createProperty(DCT, "title");
        dctType = model.createProperty(DCT, "type");
        vcardHasValue = model.createProperty(VCARD, "hasValue");
        vardHasAddress = model.createProperty(VCARD, "hasAddress");
        vcardHasTelephone = model.createProperty(VCARD, "hasTelephone");
        vcardHasEmail = model.createProperty(VCARD, "hasEmail");
        vcardStreetAddress = model.createProperty(VCARD, "street-address");
        vcardPostalCode = model.createProperty(VCARD, "postal-code");
        vcardLocality = model.createProperty(VCARD, "locality");
        vcardCountryName = model.createProperty(VCARD, "country-name");
        vcardFn = model.createProperty(VCARD, "fn");

        // Date Format specification
        format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }

    /**
     * Retrieves the list of datasets' metadata.
     *
     * @param authorization basic authorization string.
     * @return the list of datasets' metadata.
     */
    @GET
    @ApiOperation(
            value = "Retrieves the list of datasets' metadata",
            response = Dataset.class,
            responseContainer = "Array"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDatasetsList(@HeaderParam("Authorization") String authorization) {
        log.info(MSG_GET_LIST);

        List<Dataset> datasetsList;
        String jsonMsg;

        try {
            datasetsList = INSTANCE.getDatasetService().getDatasetsList();
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(datasetsList);
            log.debug(jsonMsg);
        } catch (Exception e) { // this catches also JsonProcessingException
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Retrieves the selected dataset's metadata by Id.
     *
     * @param authorization basic authorization string.
     * @param id            the Id of the selected dataset's metadata.
     * @return the selected dataset's metadata.
     */
    @GET
    @Path("/{datasetId}")
    @ApiOperation(
            value = "Retrieves the selected dataset's metadata by Id",
            response = Dataset.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid Id supplied"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Dataset's metadata not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataset(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "Id of the dataset's metadata to fetch", required = true)
            @PathParam("datasetId") String id) {
        log.info(MSG_GET + id + ".");

        Dataset dataset;
        String jsonMsg;

        // Retrieve requesting dataset
        try {
            dataset = INSTANCE.getDatasetService().getDataset(id);
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
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(dataset);
        } catch (JsonProcessingException e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Create a new dataset's metadata.
     *
     * @param authorization basic authorization string.
     * @param dataset       the dataset's metadata object to be stored.
     * @return the Id of the new dataset's metadata object.
     */
    @POST
    @ApiOperation(
            value = "Create a new dataset's metadata object"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDataset(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The dataset's metadata to be created", required = true) Dataset dataset) {
        log.info(MSG_CREATE);

        String id;

        try {
            id = INSTANCE.getDatasetService().createDataset(dataset);
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
     * Update an existing dataset's metadata object, or insert a new one.
     *
     * @param authorization basic authorization string.
     * @param id            the Id of the existing dataset's metadata object.
     * @param dataset       the dataset's metadata object with the changes.
     * @return the updated dataset's metadata object.
     */
    @PUT
    @Path("/{datasetId}")
    @ApiOperation(
            value = "Updates an existing dataset's metadata object or insert a new one",
            notes = "If the selected metadata object to be updated does not exist, then a new "
            + "dataset's metadata object is created with the details specified in the sent "
            + "Dataset object.",
            response = Dataset.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upsertDataset(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected dataset's metadata to be updated",
                      required = true)
            @PathParam("datasetId") String id,
            @ApiParam(value = "The dataset's metadata object with the modifications (or the object "
                    + "to be saved)") Dataset dataset) {
        log.info(MSG_UPSERT);

        Dataset res;
        String jsonMsg;

        try {
            res = INSTANCE.getDatasetService().upsertDataset(id, dataset);
            jsonMsg = INSTANCE.getObjectMapper().writeValueAsString(res);
        } catch (BadRequestException e) {
            log.error(e, e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error(e, e);
            return Response.serverError().build();
        }

        return Response.ok(jsonMsg, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Deletes the selected dataset's metadata.
     *
     * @param authorization basic authorization string.
     * @param id            the Id of the dataset's metadata object to be deleted.
     * @return the deleted dataset's metadata.
     */
    @DELETE
    @Path("/{datasetId}")
    @ApiOperation(
            value = "Deletes the selected dataset's metadata by Id"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Dataset's metadata not found"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDataset(
            @HeaderParam("Authorization") String authorization,
            @ApiParam(value = "The Id of the selected dataset's metadata to be removed",
                      required = true)
            @PathParam("datasetId") String id) {
        log.info(MSG_DELETE);

        try {
            INSTANCE.getDatasetService().deleteDataset(id);
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

    /**
     * Imports a private dataset in Open Data format (DCAT).
     *
     * @param authorization   authorization string.
     * @param dcatInputStream
     * @return
     * @throws java.text.ParseException
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @POST
    @Path("/importOpenData")
    @ApiOperation(
            value = "Import Open Data."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importOpenData(@HeaderParam("Authorization") String authorization,
            InputStream dcatInputStream) throws ParseException, JsonProcessingException {
        log.info(MGS_IMPORT_OPENDATA);
        model = ModelFactory.createDefaultModel(); // new allocation of the model, not sure if really needed
        model.read(dcatInputStream, null);

        StringWriter res = new StringWriter();
        RDFWriter writer = model.getWriter("RDF/XML");
        writer.setProperty("showXmlDeclaration", true);
        writer.write(model, res, null);

        log.debug("Serialized RDF result:\n" + res);

        ResIterator iterator = model.listSubjects();

        List<Resource> resourcesList = iterator.toList();

        String datasetId = "";

        for (Resource resource : resourcesList) {
            if (resource.getURI() != null && resource.getURI().startsWith(URI_DATASET)) {
                Dataset dataset = new Dataset();
                dataset.setId(resource.getProperty(dctIdentifier).getString());
                dataset.setName(resource.getProperty(dctTitle).getString());
                dataset.setDescription(resource.getProperty(dctDescription).getString());
                dataset.setType("Default type");
                Date dt = format.parse(resource.getProperty(dctIssued).getString());
                dataset.setCreationDate(dt.getTime() / 1000);
                dt = format.parse(resource.getProperty(dctModified).getString());
                dataset.setLastModifiedDate(dt.getTime() / 1000);
                dataset.setPermissions(new ArrayList<Permission>());
                // get owner
                String str = resource.getProperty(dctPublisher).getObject().toString();
                String id[] = str.split("#");
                dataset.setOwner(id[id.length - 1]);
                // get status
                dataset.setStatus("public");
                // get readonly
                dataset.setReadOnly(false);
                // get structure
                dataset.setStructure(new DatasetStructure());
                log.debug("here's the dataset: " + dataset.toString());
                datasetId = INSTANCE.getDatasetService().createDataset(dataset);
                break; // exit while the dataset is completed
            }
        }

        return Response.ok(datasetId, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Exports a dataset in Open Data format (DCAT).
     *
     * @param authorization authorization string.
     * @param id            the Id of the dataset.
     * @return the RDF serialization of the dataset.
     */
    @GET
    @Path("/exportOpenData/{datasetId}")
    @ApiOperation(
            value = "Export Open Data."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Dataset's metadata not found"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @Produces(MediaType.APPLICATION_XML)
    public Response exportOpenData(@HeaderParam("Authorization") String authorization,
            @PathParam("datasetId") String id) {
        log.info(MGS_EXPORT_OPENDATA);

        // Retrieve the dataset
        Dataset dataset;
        try {
            dataset = INSTANCE.getDatasetService().getDataset(id);
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

        String ownerId = dataset.getOwner().toString();
        String ownerEmail = "";
        String ownerPhone = "";
        String ownerName = "";
        String ownerHomepage = "";
        String ownerAddress = "";
        String ownerContactPointUri = "";
        try {
            log.debug("Check if the owner is a user...");
            User userOwner = INSTANCE.getUserService().getUser(ownerId);
            log.debug("The owner is a user.");
            ownerEmail = userOwner.getEmail();
            ownerPhone = userOwner.getPhone();
            ownerAddress = userOwner.getAddress();
            ownerName = userOwner.getName() + " " + userOwner.getSurname();
            ownerContactPointUri = URI_VCARD_INDIVIDUAL;
        } catch (ResourceNotFoundException exUsr) {
            log.debug("The owner is not a user.");
            try {
                log.debug("Check if the owner is a department...");
                Department departmentOwner = INSTANCE.getDepartmentService().getDepartment(ownerId);
                log.debug("The owner is a department.");
                ownerEmail = departmentOwner.getEmail();
                ownerPhone = departmentOwner.getPhone();
                ownerAddress = departmentOwner.getAddress();
                ownerName = departmentOwner.getName();
                ownerHomepage = departmentOwner.getUrl();
                ownerContactPointUri = URI_VCARD_ORGANIZATION;
            } catch (ResourceNotFoundException exDep) {
                log.debug("The owner is not a department.");
                try {
                    log.debug("Check if the owner is a company...");
                    Company companyOwner = INSTANCE.getCompanyService().getCompany(ownerId);
                    log.debug("The owner is a company.");
                    ownerEmail = companyOwner.getEmail();
                    ownerPhone = companyOwner.getPhone();
                    ownerAddress = companyOwner.getAddress();
                    ownerName = companyOwner.getName();
                    ownerHomepage = companyOwner.getUrl();
                    ownerContactPointUri = URI_VCARD_ORGANIZATION;
                } catch (ResourceNotFoundException exComp) {
                    log.debug("The owner is not a company.");
                    log.error("error");
                }
            }
        }

        // Resources definition
        Resource resourcePublisher = model.createResource(URI_ORGANIZATION + ownerId, FOAF.Agent);
        resourcePublisher.addProperty(RDF.type, FOAF.Agent);
        resourcePublisher.addProperty(dctType, "http://purl.org/adms/publishertype/Company");
        resourcePublisher.addProperty(FOAF.name, ownerName);
        resourcePublisher.addProperty(FOAF.homepage, ownerHomepage);
        resourcePublisher.addProperty(FOAF.mbox, ownerEmail);

        // VCARD resources definition
        Resource contactPointTelephone = model.createResource(URI_TELEPHONE);
        contactPointTelephone.addProperty(vcardHasValue, ownerPhone);
        contactPointTelephone.addProperty(RDF.type, URI_VCARD_TELEPHONE);
        contactPointTelephone.addProperty(RDF.type, URI_VCARD_WORK);
        Resource contactPointAddress = model.createResource(URI_ADDRESS);
        contactPointAddress.addProperty(RDF.type, URI_VCARD_ADDRESS);
        contactPointAddress.addProperty(vcardStreetAddress, ownerAddress);
        contactPointAddress.addProperty(vcardPostalCode, "tmp");
        contactPointAddress.addProperty(vcardLocality, "tmp");
        contactPointAddress.addProperty(vcardCountryName, "tmp");

        // Contact Point definition
        Resource resourceContactPoint = model.createResource(URI_CONTACTPOINT + ownerId);
        resourceContactPoint.addProperty(RDF.type, ownerContactPointUri);
        resourceContactPoint.addProperty(vcardFn, ownerName);
        resourceContactPoint.
                addProperty(vcardHasEmail, model.createResource("mailto:" + ownerEmail));
        resourceContactPoint.addProperty(vcardHasTelephone, contactPointTelephone);
        resourceContactPoint.addProperty(vardHasAddress, contactPointAddress);

        // Distribution definition
        Resource resourceDistribution = model.createResource(URI_DISTRIBUTION
                + "558d759f8192682c10631285");
        resourceDistribution.addProperty(RDF.type, DCAT + "Distribution");
        if (dataset.getAccessUrl() != null) {
            resourceDistribution.addProperty(dcatAccessURL, dataset.getAccessUrl());
        }
        if (dataset.getDistDescription() != null) {
            resourceDistribution.addProperty(dctDescription, dataset.getDistDescription());
        }
        if (dataset.getDistFormat() != null) {
            resourceDistribution.addProperty(dctFormat, dataset.getDistFormat());
        }
        if (dataset.getLicense() != null) {
            resourceDistribution.addProperty(dctLicense, dataset.getLicense());
        }

        // Dataset definition
        Resource resource = model.createResource(URI_DATASET + dataset.getId());
        resource.addProperty(RDF.type, DCAT + "Dataset");
        if (dataset.getName() != null) {
            resource.addProperty(dctTitle, dataset.getName());
        }
        if (dataset.getDescription() != null) {
            resource.addProperty(dctDescription, dataset.getDescription());
        }
        resource.addProperty(dctPublisher, resourcePublisher);
        resource.addProperty(admsContactPoint, resourceContactPoint);
        resource.addProperty(dcatDistribution, resourceDistribution);
        if (dataset.getKeyword() != null) {
            resource.addProperty(dcatKeyword, dataset.getKeyword());
        }
//        if (dataset.getTheme() != null) {
//            resource.addProperty(dcatTheme, dataset.getTheme());
//        }
        resource.addProperty(dctIdentifier, dataset.getId());
        if (dataset.getLastModifiedDate() != null) {
            // Date conversion to XML DateTime
            Date dt = new Date(dataset.getLastModifiedDate() * 1000);
            resource.addProperty(dctModified, format.format(dt), XSDDatatype.XSDdateTime);
        }
        if (dataset.getCreationDate() != null) {
            // Date conversion to XML DateTime
            Date dt = new Date(dataset.getCreationDate() * 1000);
            resource.addProperty(dctIssued, format.format(dt), XSDDatatype.XSDdateTime);
        }

        // Catalog publisher definition
        Resource catalogPublisher = model.createResource(URI_PUBLISHER + "558d761b8192682c10631286", // TODO: insert proper ID
                FOAF.Agent);
        catalogPublisher.addProperty(RDF.type, FOAF.Agent);
        catalogPublisher.addProperty(dctType, "http://purl.org/adms/publishertype/Company");
        catalogPublisher.addProperty(FOAF.name, "Telecom Italia");

        // Catalog definition
        Resource catalog = model.createResource(URI_CATALOG);
        catalog.addProperty(RDF.type, DCAT + "Catalog");
//        catalog.addProperty(dcatThemeTaxonomy, "http://eurovoc.europa.eu/"); // TODO: define this property
        catalog.addProperty(dctTitle, "Default Catalog Title");
        catalog.addProperty(dctDescription, "This is the default catalog for MetaWare.");
        catalog.addProperty(dctPublisher, catalogPublisher);
        catalog.addProperty(dcatDataset, resource);
        catalog.addProperty(dctLanguage, URI_LANGUAGE_EN); // fixed to english for now - TODO: implement other languages
        catalog.addProperty(FOAF.homepage, METAWARE);
        catalog.addProperty(dctIssued, format.format(new Date()), XSDDatatype.XSDdateTime);
        catalog.addProperty(dctModified, format.format(new Date()), XSDDatatype.XSDdateTime);
        if (dataset.getLicense() != null) {
            catalog.addProperty(dctLicense, model.createResource(dataset.getLicense())); // the license of the dataset is assigned also to the catalog
        }

        // Generate the output string
        StringWriter res = new StringWriter();
        // Possible serializations:
        // RDF/XML
        // RDF/XML-ABBREV
        // N-TRIPLES
        // TURTLE
        // TTL
        // N3
        // JSON-LD
        RDFWriter writer = model.getWriter("RDF/XML");
        writer.setProperty("showXmlDeclaration", true);
//        writer.setProperty("showDoctypeDeclaration", true);
        writer.write(model, res, null);

        log.debug("Serialized RDF result:\n" + res);

        return Response.ok(res.toString(), MediaType.APPLICATION_XML).build();
    }
}

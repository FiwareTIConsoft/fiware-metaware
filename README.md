# Metadata Store Management Platform
This is the code repository for the Metaware, the reference implementation of the Metadata Store Management Platform GE.

This project is part of [FIWARE](https://www.fiware.org/).

##Description
Metadata is data that describes other data.
_Meta_ is a prefix that in most IT usages means "an underlying definition or description".
In our context metadata is used to describe objects in FIWARE Big Data environment.

Metadata Store GE (codename _Metaware_) will allow a smooth approach to a general Big Data platform decoupling from technical usage issues, maintaining platform objects descriptors enabling complex processing flows and controlling the access to the data and data APIs by the authorized users, keep accounting of the data consumption and supporting its publication.
More in general Metaware enables the ease of use.
Everything that a user can use, publish, and buy in the FIWARE Big Data environment, can be managed as an object, and can be described into the Metadata Store.
An object can be private and owned by a user, or can be public, owned by a user owner and bought by one or more users.
The Metaware maintains the relation between the user and her objects.
The object types described by Metaware are:

- Company
- Department
- User
- Algorithm
- Dataset
- Data-Source
- Process
- API\*
- Application\*
- Dashboard\*

<sub>\* future release</sub>

Big Data deals with large volumes of data characterized by volume, variety, and velocity.
Any enterprise that is in the process of considering a Big Data application deployment has to address the metadata management problem.
Big Data introduces large volumes of unstructured data for analysis.
This data could be in the various form.
To bring this data into the fold of information management solution, its metadata should be defined correctly.
A good metadata management solution must provide visibility across multiple solutions and bring business users into the fold for a collaborative and active metadata management process.
The purpose of Metaware is to address the issues described before offering a metadata repository accessible through RESTful Web Services.

###How it works
Metadata is defined by a JSON with a basic structure that describes the characteristics of each object; inside

Metadata is a JSON-based structure that describes the details of the associated object.
JSON structure allows the possibility to include fields and sub-fields in the main structure, in order to enhance the overall description.

Each object in FIWARE Big Data environment is described by its own corresponding metadata object in Metaware, which is provided through RESTful Web Services implementing CRUD operations (and other complex actions).

##Build & Install
Metaware GE is a Java-based Web application that runs on a Web Server like Apache Tomcat, and connects to a MongoDB instance for persistent storage.
Thus, you will need these additional software to run Metaware properly.
The guide [Installation and Administration Manual](https://github.com/FiwareTIConsoft/fiware-metaware/blob/master/docs/installation_and_administration_manual.md) will drive you through the various installation steps.

Once the environment is correctly set up, Metaware has to be built by using Maven and eventually deployed on Apache Tomcat.

##API Overview
Metaware exposes a rich set of RESTful API as Web Services, so all of them are accessible through HTTP calls.
Most of them requires a basic authentication while calling, this means that the header of the HTTP request must contain the authorization parameter with the hash of the user's credentials; in addition, when requested, another header parameter specifying the Content-Type must be inserted (`application/json` is valid in most of the cases).

The following is an example of a header parameters:

```
Authorization: Basic YWRtaW46YWRtaW4=
Content-Type: application/json
```
Where the `YWRtaW46YWRtaW4=` string is the hash of the user credentials.

The payload of the HTTP requests, when requested, must respect the schema of the related resource you are going to call; this is important in order to maintain the functionalities of Metaware.
Unspecified schema in resource's call is accepted by Metaware, but then some functionalities will not work properly.

###API examples
The following are just some examples of how you can interact with Metaware (here by using `curl`).
To have a complete overview about Metaware APIs we strongly suggest to have a look to the dedicated section in the __User and Programming manual__ (you can find the link at the end of this page).

__Get the datasets list:__
```
curl -X GET -H "Authorization: Basic YWRtaW46YWRtaW4=" -H "Cache-Control: no-cache" -H "Postman-Token: ea8c1ca0-4d47-a662-5f5e-dc2a3048ab82" 'http://localhost:8080/metaware/api/v1/datasets/'
```
__Insert a new dataset:__
```
curl -X POST -H "Content-Type: application/json" -H "Authorization: Basic YWRtaW46YWRtaW4=" -H "Cache-Control: no-cache" -H "Postman-Token: b07209cc-2e33-ce9c-7bbd-120d71a1de25" -d '{
  "name": "Car Sharing",
  "description": "Car Sharing of Turin from AperTO",
  "type": "test",
  "creationDate": 1411002061,
  "lastModifiedDate": 1474160461,
  "permissions": [
    {
      "referenceId": "5508151eb2d1f89cb41208c6",
      "perm": "rud"
    }
  ],
  "owner": "550812fab2d1f89cb41208c2",
  "status": "public",
  "readOnly": false,
  "keyword": "car",
  "theme": "viability",
  "accessUrl": "http://aperto.comune.torino.it/sites/default/files/car_sharing.csv",
  "distDescription": "Comma separated value file",
  "distFormat": "cvs",
  "license": "http://www.dati.gov.it/iodl/2.0/",
  "structure": {
    "ID_CAR_SHARING": "String",
    "NOME_PARCHEGGIO" : "String",
    "UBICAZIONE": "String",
    "URL": "String",
    "COORD_X": "Float",
    "COORD_Y": "Float"
  }
}' 'http://localhost:8080/metaware/api/v1/datasets'
```
__Create a new company:__
```
curl -X POST -H "Content-Type: application/json" -H "Authorization: Basic YWRtaW46YWRtaW4=" -H "Cache-Control: no-cache" -H "Postman-Token: 106e4876-6b39-bc6d-e255-b25c09cc2eba" -d '{
    "name": "FIWARE",
    "description": "Open APIs for Open Minds",
    "email": "fiware-general-help@lists.fi-ware.org",
    "phone": "",
    "url": "https://www.fiware.org/"
  }' 'http://localhost:8080/metaware/api/v1/companies'
```
###API reference documentation
The Open Spec API is available at [Apiary](http://docs.metaware.apiary.io/#).

##Tests
Some unit tests are already available in Metaware and you can decide to execute them at compile time.
These tests are located in `src/test` folders for each layer of Metaware.

Additional to unit tests, some "Sanity Checks" can also be performed before start using Metaware, including Apache Tomcat status check and MongoDB.
The section "Sanity check procedures" in __Installation and Administration manual__ explains how to proceed.

##Documentations
[User and Programming Manual](https://github.com/FiwareTIConsoft/fiware-metaware/blob/master/docs/user_and_programming_manual.md)

[Installation and Administration Manual](https://github.com/FiwareTIConsoft/fiware-metaware/blob/master/docs/installation_and_administration_manual.md)

###Other documents
The full Javadoc of Metaware is available in [this folder](https://github.com/FiwareTIConsoft/fiware-metaware/tree/master/docs/javadocs) of the repository.
Each folder refers to a specific "layer" of Metaware.

##Docker
A Dockerfile of Metaware is available in this repository (folder `docker`), this means that

### Docker instructions
The following are instructions to run the "Dockerized" Metaware; please refer to the related section of the [Installation and Administration Manual](installation_and_administration_manual.md#Docker)
1. `docker pull mongo`
2. `docker run -d -p 27017:27017 --name mongodb mongo`
3. `cd fiware-metaware/docker`
4. `docker build -t metaware/tomcat .`
5. `docker run --name tomcat --link mongodb:mongodb -p 8080:8080 metaware/tomcat`

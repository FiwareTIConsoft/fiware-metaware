# Metadata Store Management Platform
This is the code repository for the Metaware, the reference implementation of the Metadata Store Management Platform GE.

This project is part of [FIWARE](https://www.fiware.org/).

##Description
To-Do

##Build & Install
To-Do

##API Overview
To-Do

###API Examples
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
###API Reference Documentation
Link to the Apiary Blueprints.

##Tests
End to End tests.
Link to the Sanity Checks part included in the "Installation & Administration Guide".

##Documentations
###User & Programmers Manual
link here
###Installation & Administration Manual
link here
###Other documents
links here

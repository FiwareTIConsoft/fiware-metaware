#Metaware
##Introduction
Welcome to the Metaware Installation and Administration Guide!
Metaware is a Java-based web service that deals with metadata information, and it can run on Tomcat.

It is an implementation of the FIWARE Metadata Store Management Platform Generic Enabler, from Telecom Italia and Consoft.

Any feedback on this document is highly welcome, including bug reports, typos or stuff you think should be included but is not.
Please send the feedback through [GitHub](https://github.com/FiwareTIConsoft/fiware-metaware).
Thanks in advance.

##User guide
The interaction with Metaware is designed to work only through RESTful APIs, and no user interface is currently implemented; for these reasons we assume that the audience is mainly composed by developers and engineers, so please refer to the following section of this manual.

##Programming guide
The following is the list of available RESTful APIs from Metaware.

Please assume the root of the Metaware as the following: http://localhost:8080/metaware
###Users
| Name           | Verb   | URL                |
| :------------- | :----- | :----------------- |
| Get User       | GET    | /v1/users/{userId} |
| Get Users List | GET    | /v1/users/         |
| Create User    | POST   | /v1/users          |
| Update User    | PUT    | /v1/users/{userId} |
| Delete User    | DELETE | /v1/users/{userId} |

###Departments
| Name                 | Verb   | URL                            |
| :------------------- | :----- | :----------------------------- |
| Get Department       | GET    | /v1/departments/{departmentId} |
| Get Departments List | GET    | /v1/departments/               |
| Create Department    | POST   | /v1/departments                |
| Update Department    | PUT    | /v1/departments/{departmentId} |
| Delete Department    | DELETE | /v1/departments/{departmentId} |

###companies
| Name               | Verb   | URL                       |
| :----------------- | :----- | :------------------------ |
| Get Company        | GET    | /v1/companies/{companyId} |
| Get Companies List | GET    | /v1/companies/            |
| Create Company     | POST   | /v1/companies             |
| Update Company     | PUT    | /v1/companies/{companyId} |
| Delete Company     | DELETE | /v1/companies/{companyId} |

###DiscoverObjects
| Name                    | Verb   | URL                                      |
| :---------------------- | :----- | :--------------------------------------- |
| Discover usable objects | GET    | /v1/discoverObjects/usable/{requestedId} |
| Discover owned objects  | GET    | /v1/discoverObjects/owner/{userId}       |

###Algorithms
| Name                | Verb   | URL                          |
| :------------------ | :----- | :--------------------------- |
| Get Algorithm       | GET    | /v1/algorithms/{algorithmId} |
| Get Algorithms List | GET    | /v1/algorithms/              |
| Create Algorithm    | POST   | /v1/algorithms               |
| Update Algorithm    | PUT    | /v1/algorithms/{algorithmId} |
| Delete Algorithm    | DELETE | /v1/algorithms/{algorithmId} |

###Datasets
| Name              | Verb   | URL                      |
| :---------------- | :----- | :----------------------- |
| Get Dataset       | GET    | /v1/datasets/{datasetId} |
| Get Datasets List | GET    | /v1/datasets/            |
| Create Dataset    | POST   | /v1/datasets             |
| Update Dataset    | PUT    | /v1/datasets/{datasetId} |
| Delete Dataset    | DELETE | /v1/datasets/{datasetId} |

###Datasources
| Name                  | Verb   | URL                            |
| :-------------------- | :----- | :----------------------------- |
| Get Data-Source       | GET    | /v1/datasources/{datasourceId} |
| Get Data-Sources List | GET    | /v1/datasources/               |
| Create Data-Source    | POST   | /v1/datasources                |
| Update Data-Source    | PUT    | /v1/datasources/{datasourceId} |
| Delete Data-Source    | DELETE | /v1/datasources/{datasourceId} |

###Templates
| Name               | Verb   | URL                          |
| :----------------- | :----- | :--------------------------- |
| Get Template       | GET    | /v1/templates/{templateName} |
| Get Templates List | GET    | /v1/templates/               |

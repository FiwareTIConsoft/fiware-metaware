#Metaware
##Introduction
Welcome to the Metaware Installation and Administration Guide!
Metaware is a Java-based web service that deals with metadata information, and it can run on Tomcat.

It is an implementation of the FIWARE Metadata Store Management Platform Generic Enabler, from Telecom Italia and Consoft.

Any feedback on this document is highly welcome, including bug reports, typos or stuff you think should be included but is not.
Please send the feedback through [GitHub](https://github.com/FiwareTIConsoft/fiware-metaware).
Thanks in advance.

## System requirements
This section describes the basic requirements of Metaware and how to install them

###Hardware requirements
The hardware requirements depend on the application to be deployed.
Any hardware running Java JRE/JDK 7 or later is supported.
Please refer to the main [Oracle Java web-page](http://www.oracle.com/technetwork/java/javase/downloads/index.html) for minimal hardware requirements and further details.

###Software requirements
Runtime System:
* Any Java SE JRE 7 or later distribution;
* Apache Tomcat 7 or later;
* MongoDB 2.6 or later.

Development system:
* Any Java SE JDK 7 or later distribution;
* Apache Tomcat 7 or later (if you are working on Windows, you can also use XAMPP);
* MongoDB 2.6 or later.

[Apache Maven](https://maven.apache.org/index.html), or other building tools are necessary to create the final WAR package that will run on Tomcat.

###Operating system supported
Any OS running Java JRE/JDK 7 or later is supported.

Metaware is tested on Windows (7).

##Installation
###Installation of the Java JDK
Please follow the installation instructions of the respective Java distribution:
* [OpenJDK](http://openjdk.java.net/install/)
* [Oracle Java SE JDK](http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)

To verify that the installation is correct, please open a terminal, a shell, or a command line interface (cmd.exe), and check that the `java` command is executable:
```
$ java -version
java version "1.8.0_31"
Java(TM) SE Runtime Environment (build 1.8.0_31-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.31-b07, mixed mode)
```

If the `java` command is not found, please make sure that the `<java_home>/bin` directory is in your `PATH` environment variable and the `java_HOME` environment variable is set (see troubleshooting instructions on the [Oracle website](http://docs.oracle.com/javase/8/docs/technotes/guides/install/windows_jdk_install.html#BABGDJFH)).

###Installation of build tools
Please follow the instructions about how to [download](https://maven.apache.org/download.cgi) and [install](https://maven.apache.org/install.html) the Apache Maven tool.

####Integrated development environments
If you prefer working with IDEs to develop or just build the final package, you are free to do it.

The following are just two of the most common IDEs, and of course you can choose the one you prefer.
* [Eclipse](https://eclipse.org/)
* [NetBeans](https://netbeans.org/)

###Setting up the development environment
To have a functional working environment for Metaware, a MongoDB instance is needed.
MongoDB is a well-known document-oriented NoSQL database, and the instructions to download it and install it are available at this [web-page](http://docs.mongodb.org/manual/installation/).

###Build Metaware
Once you have a working environment, you can download the source code of Metaware.

It is very important to mention that Metaware is actually composed by three pieces of software, in order to distinguish the various levels of the application.
The first part is `metaware`, and it is the tier that actually exposes the RESTful APIs web-services.
Then there is the `metaware-dao`, which basically defines the various entities inside the Metaware, together with the custom exceptions.
Finally, the `metaware-mongodbdaoimpl` defines the implementation of the entities specified in `metaware-dao`; actually, at this level, a new definition of the entities is done, but this one is more related to MongoDB implementation.
If one decides to change MongoDB in favor of other databases, only this last piece of Metaware has to be re-implemented (plus some other minor changes in `metaware`, but nothing crucial).

The GitHub repositories for the Metaware's sections are the following:

1. https://github.com/FiwareTIConsoft/fiware-metaware.git
2. https://github.com/FiwareTIConsoft/fiware-metaware-dao.git
3. https://github.com/FiwareTIConsoft/fiware-metaware-mongodbdaoimpl.git

In a command line interface, these are some simple instructions to get all the code you need to start working with Metaware:
```
cd ~
mkdir metaware
cd metaware
git clone https://github.com/FiwareTIConsoft/fiware-metaware.git
git clone https://github.com/FiwareTIConsoft/fiware-metaware-dao.git
git clone https://github.com/FiwareTIConsoft/fiware-metaware-mongodbdaoimpl.git
```

To check that everything is fine, you can use the `tree` command; you should see something like this:
```
$ tree
.
└── metaware
    ├── fiware-metaware
    │   ├── docs
    │   │   ├── installation_and_administration_manual.md
    │   │   └── user_and_programming_manual.md
    │   ├── pom.xml
    │   ├── README.md
    │   └── src
    │       ├── main
    │       │   ├── java
    │       │   │   └── com
    │       │   │       ...
    │       │   ├── resources
    │       │   │   ...
    │       │   └── webapp
    │       │       ...
    │       └── test
    │           └── java
    │               └── com
    │                   ...
    ├── fiware-metaware-dao
    │   ├── pom.xml
    │   ├── README.md
    │   └── src
    │       ├── main
    │       │   └── java
    │       │       ...
    │       └── test
    │           └── java
    │               └── com
    │                   ...
    └── fiware-metaware-mongodbdaoimpl
        ├── pom.xml
        ├── README.md
        └── src
            ├── main
            │   ├── java
            │   │   ...
            │   └── resources
            │       └── log4j.properties
            └── test
                └── java
                    └── com
                        ...

73 directories, 152 files
```
(The number of directories and files may be different due to new features inclusion).

####Deploy
In order to deploy the Metaware, you first need to create a WAR package of the application.
The final package shall contain all the three parts of Metaware, due to some dependencies between the various parts.
To produce the WAR package we suggest to use your favorite IDE, which should include build tools (like Apache Maven), but you are free to use the tool you prefer.

The most important fact to keep in mind is the order of build, which is the following:

1. `metaware-dao`
2. `metaware-mongodbdaoimpl`
3. `metaware`

In other words you first have to generate the WAR package of `metaware-dao`, which contains some of the very basic definitions (mainly entities and exceptions); once this step is done, you can proceed with `metaware-mongodbdaoimpl`. Finally, the same step has to be performed for `metaware`.
It is absolutely important the "dependency hierarchy" between the three parts of Metaware is respected.

Another important note is about testing.
For some IDE, while building the WAR package, also the test suite is executed, in order to keep the code working properly; this means that while building, your MongoDB instance has to run properly.

In Metaware, we use the default port for MongoDB, which we assume it is running on localhost.
If this is not your case, you can modify the "Database access information" in the file `fiware-metaware/src/main/resources/metaware.properties` before the creation of the WAR package.

Once you get the WAR package from `metaware`, you can deploy it on Tomcat.
If the deploy goes fine, the Metaware will be available at `http://localhost:8080/metaware/api/v1/`.

##Sanity check procedures
TO-DO

###List of running processes
1. java.exe (for Tomcat - Metaware)
2. mongod.exe (for MongoDB)

###Network interfaces up and down
If everything is set in a default way, the only opened ports are:
* 8080 for Tomcat;
* 27017 for MongoDB.

##Diagnosis procedures
Metaware includes [Swagger-UI](http://swagger.io/swagger-ui/) framework to check and discover the various available RESTful APIs.
You can go to `http://localhost:8080/swagger-ui/` and use the APIs that are available for the various entities and actions (i.e., `datasources`, `discoverObjects`, `companies`, `departments`, `users`, `templates`, `datasets`).

###Resource availability
TO-DO

###Remote service access
No remote service access is planned for Metaware (excluding Tomcat management page, but this is not up to Metaware and only depends on your system administrator).

###Resource consumption
TO-DO

###I/O flows
TO-DO

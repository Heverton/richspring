[![Build Status](https://travis-ci.org/thiaguten/richspring.svg)](https://travis-ci.org/thiaguten/richspring)

Maven multi-module JavaEE 7 CRUD project of integration between JSF and Spring using:

    JSF 2.2.x (RichFaces 4.5.x) + Spring 4.3.x + JPA 2.1 (Hibernate 4.3.x) + HikariCP 2.3.x

This sample project contains embedded Jetty container, so to run it execute the below commands at terminal:

1 - `mvn clean install` at root folder (richspring)

2 - `mvn jetty:run` at subfolder (richspring-web)

The deployed URL is: http://localhost:8080/richspring-web

To stop Jetty instance hit `CTRL + C` at Terminal.

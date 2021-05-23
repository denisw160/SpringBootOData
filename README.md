# SpringBootOData

[![Maven Build](https://github.com/denisw160/SpringBootOData/actions/workflows/maven.yml/badge.svg)](https://github.com/denisw160/SpringBootOData/actions/workflows/maven.yml)
[![CodeQL](https://github.com/denisw160/SpringBootOData/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/denisw160/SpringBootOData/actions/workflows/codeql-analysis.yml)

This demo project shows you the implementation of ODATA in Spring Boot with Spring Data (JPA) and Spring Security.

## Build

The project is configured for Maven and can build with `mvn package`. If maven is not installed, you can use
`mvnw package` to get maven online.

The build works for Java 11+

## Run

The result is a standalone jar for executing the application server. Just run `java -jar /target/spring-odata.jar`.

## Program

After starting the application you can open the UI / testing scenarios in your browser: `http://localhost:8080`

## Database

For query the database you can open the url `http://localhost:8080/h2` in your browser. For file based database please
use this jdbc url: `jdbc:h2:file:./db/h2`

## Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.0/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-security)
* [Bootstrap 5](https://getbootstrap.com/docs/5.0/getting-started/introduction/)
* [Fontawesome](https://fontawesome.com/)
* [Thymeleaf 3](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#introducing-thymeleaf)

## Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)

# Spring Dubbo Demo

Basic web app built with spring and dubbo RPC protocol to communicate with separate modules and zookeeper to host services to simulate cloud computing.
Postgresql is used as the database but with postgREST web service to deliver data in JSON format or RESTful API.

## Requirements

- [Docker](https://docs.docker.com/get-docker/)
- [Postgresql (v15.0)](https://www.postgresql.org/download/)
- [PostgREST (v10.1.1)](https://github.com/PostgREST/postgrest/releases/download/v10.1.1/postgrest-v10.1.1-windows-x64.zip)
- [Intellij (EAP) *preferred*](https://www.jetbrains.com/toolbox-app/)
- [Spring Boot (v2.7.6)](https://spring.io/quickstart)
- [Maven](https://maven.apache.org/index.html)
- [Java SDK (v17.0.5)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Github](https://git-scm.com/downloads)

## Features

* Login page
* Registration page
* Will add more in the future

## Getting Started

1. Download and install Docker, Postgresql, Java with their appropriate versions.
2. After installing PostgREST, run on terminal/shell
    ```sh
        setx /m PATH "%PATH%;C:\Program Files\PostgreSQL\15\bin"
    ```
   replace 15 with the version of postgresql installed. After running command, restart PC to apply changes.
3. Open the application then enter the command at terminal/shell
   ```sh
       docker-compose up
   ```
4. check application.properties in the `/src/main/resources` if the port on `dubbo.registry.address`, `dubbo.config-center.address`, `dubbo.metadata-report.address` is the same as the port assigned on the docker container zookeeper
5. run on terminal/shell
   ```sh
       postgrest postgrest.conf
   ```
6. Build then run the application (start with the provider module then consumer module)

## Issues

- None as of the moment

## References
- https://www.baeldung.com/thymeleaf-in-spring-mvc
- https://www.baeldung.com/security-none-filters-none-access-permitAll
- https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
- https://www.baeldung.com/spring-security-login
- https://scalegrid.io/blog/using-jsonb-in-postgresql-how-to-effectively-store-index-json-data-in-postgresql/
- https://www.tabnine.com/code/java/classes/okhttp3.Request$Builder
- https://www.amazingcto.com/postgres-for-everything/
- https://dubbo.apache.org/docs/v2.7/user/quick-start/
- https://postgrest.org/en/stable/tutorials/tut0.html
# Spring Dubbo Demo

Basic web app built with spring and dubbo RPC protocol to communicate with separate modules and zookeeper to host services to simulate cloud computing.
Postgresql is used as the database but with postgREST web service to deliver data in JSON format or RESTful API. The app also has metrics and logging.

# Requirements

- [Docker](https://docs.docker.com/get-docker/)
- [Postgresql (v15.0)](https://www.postgresql.org/download/)
- [PostgREST (v10.1.1)](https://github.com/PostgREST/postgrest/releases/tag/v10.1.1)
- [Intellij (EAP) *preferred*](https://www.jetbrains.com/toolbox-app/)
- [Spring Boot (v2.7.6)](https://spring.io/quickstart)
- [Maven](https://maven.apache.org/index.html)
- [Java SDK (v17.0.5)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Github](https://git-scm.com/downloads)

# Features

* Login page
* Registration page
* Will add more in the future

# Tools
   * Grafana (metrics data monitoring and presenting)
   * Prometheus (metrics scraper and storage)
   * PostgREST
   * Postgresql
   * Zookeeper
   * Dubbo
   * Spring Boot

# Getting Started

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
       postgrest ./src/main/config/postgrest.conf
   ```
6. Run the application (start with the provider module then consumer module)

# Issues

- Register page is not working yet

# References
- [Thymeleaf](https://www.baeldung.com/thymeleaf-in-spring-mvc): learn how to use thymeleaf in spring mvc
- [Spring Security Filters](https://www.baeldung.com/security-none-filters-none-access-permitAll): learn how to use permitAll in spring security
- [Spring Security Registration](https://www.baeldung.com/registration-with-spring-mvc-and-spring-security): learn how to use spring security with registration
- [Spring Security Login](https://www.baeldung.com/spring-security-login): learn how to use spring security with login
- [Postgresql and JsonB](https://scalegrid.io/blog/using-jsonb-in-postgresql-how-to-effectively-store-index-json-data-in-postgresql/): learn how to use jsonb in postgresql
- [OkHttp3](https://www.tabnine.com/code/java/classes/okhttp3.Request$Builder): learn how to use okhttp3
- [Postgres](https://www.amazingcto.com/postgres-for-everything/): learn how to use postgresql for any project
- [Dubbo](https://dubbo.apache.org/docs/v2.7/user/quick-start/): learn how to use dubbo
- [PostgREST](https://postgrest.org/en/stable/tutorials/tut0.html): learn how to use postgrest
- [Prometheus](https://prometheus.io/docs/prometheus/latest/querying/api/): learn how to use prometheus
- [Grafana](https://grafana.com/): learn how to use grafana
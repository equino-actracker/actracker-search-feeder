# Purpose of actracker-search-feeder
This application has been created with learning the following topics in mind:
- DDD methodology
- Messaging (event consumption)
- Elastic Search (data ingestion)
- Spring Boot (using as many framework features as possible)
- Kubernetes
- Maven
- Observability and debugging (the application is intentionally not bullet-prove, performance tests for sure will point a need for metrics, debugging, tweaking)

# Role of actracker-search-feeder in Actracker project
The role of this application is replicating Actracker bounded context into the Actracker search engine
to run search and analytics query on them.

# Architecture
The application runs as a Spring Boot container. 
The Actracker domain events are consumed from Equino RabbitMQ cluster.

# Running locally

## Setting up infrastructure
Locally setup infrastructure is required to run the application. The maven goal `docker-compose:up` should be run prior 
to application startup to ensure that all the required infrastructure components have been set up locally 
(docker and docker-compose needs to be installed):

`./mvnw docker-compose:up`

`./mvnw docker-compose:down`

## Running application
To run the application locally `ActrackerSearchFeederApplication` must be run. It's a class starting the Spring Boot container.
Alternatively, Maven Spring Boot plugin can be used:

`./mvnw spring-boot:run`

## Postgres schema migration
Postgres schema is maintained by Flyway. Whenever the application starts, Flyway migration is running.
After migration of Postgres schema, JOOQ code needs to be generated (see next bullet).
Schema migration may also by run with Maven (for locally run database):
`./mvnw flyway:migrate`

## JOOQ code generation
JOOQ code should be generated after migrating to new Postgres schema.
To generate JOOQ code, run: `./mvnw jooq-codegen:generate`.

# Build pipelines
There are build pipelines defined in jenkins_files directory.

After the pull request is created, the [PR pipeline](jenkins_files/Jenkinsfile_pr) is started in Equino Jenkins,
which tests the PR and deploys the application to development environment. 

When the build passes, the change may be deployed and merged by adding the 'ready for release' comment to the pull request.
The comment triggers the [Release pipeline](jenkins_files/Jenkinsfile_release). 
It deploys the application to production environment, merges the PR and deletes branch.

Merge of the PR triggers the [Master pipeline](jenkins_files/Jenkinsfile_master).
The pipeline updates version of the application using the [equino-version](https://github.com/marcinciapa/equino-gradle-plugins/blob/master/equino-version/README.md) 
Gradle plugin and deploys the application to integration environment.

# Versioning and Gradle
The application version is maintained automatically with the [equino-version](https://github.com/marcinciapa/equino-gradle-plugins/blob/master/equino-version/README.md)
Gradle plugin.
The current version is passed to the POM using the [versions-maven-plugin](https://www.mojohaus.org/versions/versions-maven-plugin/).
Maintaining the version is the only purpose of Gradle being present in actracker-search-feeder repository. The build lifecycle is maintained using Maven.

# Deployment
The application is deployed to [Equino Kubernetes cluster](https://github.com/marcinciapa/equino-kubernetes) from Jenkins pipelines.
The deployment is handled by Maven [jkube](https://www.eclipse.org/jkube/docs/kubernetes-maven-plugin/) plugin.

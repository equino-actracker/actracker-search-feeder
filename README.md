# Purpose of actracker-search-feeder
The purpose of this project is replicating Actracker bounded context into the Actracker search engine.

# Architecture
The application runs as a Spring Boot container. 
The Actracker domain events are consumed from Equino RabbitMQ cluster.

# Running locally

## Setting up infrastructure
Locally setup infrastructure is required to run the application. The maven goal `docker-compose:up` should be run prior 
to application startup to ensure, that all the required infrastructure has been set up locally 
(docker and docker-compose needs to be installed):

`./mvnw docker-compose:up`

`./mvnw docker-compose:down`

## Running application
To run the application locally `ActrackerSearchFeederApplication` must be run. It's a class starting the Spring Boot container.
Alternatively, Maven Spring Boot plugin can be used:

`./mvnw spring-boot:run`

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
This is the only purpose of Gradle being present in actracker-search-feeder project. The build lifecycle is maintained using Maven.

# Deployment
actracker-search-feeder is deployed to [Equino Kubernetes cluster](https://github.com/marcinciapa/equino-kubernetes) from Jenkins pipelines.
The deployment is handled by Maven [jkube](https://www.eclipse.org/jkube/docs/kubernetes-maven-plugin/) plugin.

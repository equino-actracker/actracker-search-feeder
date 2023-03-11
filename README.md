# Running locally
Locally setup infrastructure is required to run the application. The maven goal `docker-compose:up` should be run prior to application startup to ensure, that all the required infrastructure has been set up locally (docker and docker-compose needs to be installed):
`./mvnw docker-compose:up`
`./mvnw docker-compose:down`

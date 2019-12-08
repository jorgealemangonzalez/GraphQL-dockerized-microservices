# GraphQL microservices architecture using kubernetes

## How to run
Run BookDetailsServer.main() and execute the following curl in order 
to check if the server has been started correctly

`curl 'http://localhost:8080/graphql' -H 'Accept-Encoding: gzip, deflate, br' -H 'Content-Type: application/json' -H 'Accept: application/json' -H 'Connection: keep-alive' -H 'DNT: 1' -H 'Origin: file://' --data-binary '{"query":"{\n  bookById(id: \"book-1\"){\n    id\n    name\n    \n    author {\n      firstName\n      lastName\n    }\n  }\n}\n"}' --compressed`

## How to run on docker

`docker-compose up --build`

or

`./gradlew composeUp`

## This was created thanks to
* [Getting started with GraphQL Java and Spring Boot](https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/)
* [Dockerizing a Spring Boot Application](https://www.baeldung.com/dockerizing-spring-boot-application#Dockerize)
* [Apollo GraphQL java client library generator](https://github.com/apollographql/apollo-android)

## Troubleshooting intellij:
**docker.errors.TLSParameterError: Path to a certificate and key files .........**
- Go to Settings >> Build, Execution, Deployment >> Docker

- Select "TCP socket"

- Enter 'unix:///var/run/docker.sock' under "Engine API URL"

## Checking that works
Open the GraphQL Playground and write `http://localhost:8080/graphql` as host and the following request:
```
{
  bookById(id: "book-1"){
    id
    name
    pageCount
    author {
      firstName
      lastName
    }
  }
}
```

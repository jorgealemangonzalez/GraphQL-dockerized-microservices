FROM alpine:edge
MAINTAINER jorge.alemangonzalez@gmail.com
RUN apk add --no-cache openjdk8
ENTRYPOINT ["/usr/bin/java"]
COPY build/libs/books-server-0.0.1-SNAPSHOT.jar /opt/spring-cloud/lib/app.jar
CMD ["-jar", "/opt/spring-cloud/lib/app.jar"]
EXPOSE 8080
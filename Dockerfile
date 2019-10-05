FROM alpine:edge
MAINTAINER jorge.alemangonzalez@gmail.com
RUN apk add --no-cache openjdk8
ENTRYPOINT ["/usr/bin/java"]
ARG JAR_FILE
COPY ${JAR_FILE} /opt/spring-cloud/lib/app.jar
CMD ["-jar", "/opt/spring-cloud/lib/app.jar"]
EXPOSE 8080
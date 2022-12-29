# syntax=docker/dockerfile:1
FROM maven:3.8-eclipse-temurin-11-alpine as base
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:resolve
COPY src ./src

# FROM base as development
# CMD ["mvn", "spring-boot:run","-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]

FROM base as build
RUN mvn package

FROM maven:3.8-eclipse-temurin-11-alpine as production
EXPOSE 8080
COPY --from=build /app/target/client-*.jar /communityknowledge.jar
CMD ["java", "-jar", "/communityknowledge.jar"]
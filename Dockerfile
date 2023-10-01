#
# Build stage
#
FROM maven:3.8.5-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
#
# Run stage
#
FROM openjdk:17
COPY --from=build  /home/app/target/quarkus-app/ /opt/maven/deployments/
EXPOSE 8080
CMD ["java","-jar","/opt/maven/deployments/quarkus-run.jar"]

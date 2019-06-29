FROM openjdk:8
MAINTAINER Miljan Cabrilo <miljancabrilo@yahoo.com>
ADD target/db_service-0.0.1-SNAPSHOT.jar db_service.jar
ENTRYPOINT ["java", "-jar", "./dbservice.jar"]
EXPOSE 8500
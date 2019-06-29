FROM openjdk:8
MAINTAINER Miljan Cabrilo <miljancabrilo@yahoo.com>
ADD target/messages_service-0.0.1-SNAPSHOT.jar messages_service.jar
ENTRYPOINT ["java", "-jar", "./messages_service.jar"]
EXPOSE 8900
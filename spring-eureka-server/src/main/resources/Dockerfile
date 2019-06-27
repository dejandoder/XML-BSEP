FROM anapsix/alpine-java
MAINTAINER Miljan Cabrilo <miljancabrilo@yahoo.com>
ADD target/spring-eureka-server-0.0.1-SNAPSHOT.jar spring-eureka-server.jar
ENTRYPOINT ["java", "-jar", "./spring-eureka-server.jar"]
EXPOSE 8761
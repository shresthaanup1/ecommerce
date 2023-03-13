FROM maven:3.9-amazoncorretto-11

#copy source code from current directory to container
COPY  ./ /source/
WORKDIR /source
#build jar
RUN mvn package


EXPOSE 9090
#run the eureka application in cmd
ENTRYPOINT ["java" ,"-jar", "/source/target/eureka-server-0.0.1-SNAPSHOT.jar"]
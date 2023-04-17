FROM maven:3.9-amazoncorretto-11

#copy source code from current directory to container
COPY  ./ /source/
WORKDIR /source
#build jar
RUN mvn package





EXPOSE 9897
#run the api-gateway application in cmd
ENTRYPOINT ["java" ,"-jar", "/source/target/product-services-0.0.1-SNAPSHOT.jar"]

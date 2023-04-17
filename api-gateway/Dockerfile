FROM maven:3.9-amazoncorretto-11

#copy source code from current directory to container
COPY  ./ /source/
WORKDIR /source
#build jar
RUN mvn package


EXPOSE 9999
#run the api-gateway application in cmd
ENTRYPOINT ["java" ,"-jar", "/source/target/api-gateway-0.0.1-SNAPSHOT.jar"]

# STEP 1: docker build -t gateway .
# STEP 2: docker run -p 9999:9999 gateway
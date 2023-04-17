FROM maven:3.9-amazoncorretto-11

#copy source code from current directory to container
COPY  ./ /source/
WORKDIR /source
#build jar
RUN mvn package





EXPOSE 9897
#run the api-gateway application in cmd
ENTRYPOINT ["java" ,"-jar", "/source/target/user-services-0.0.1-SNAPSHOT.jar"]

#docker run --name some-mysql -e MYSQL_DATABASE=ecommerce -e MYSQL_USER=nirmala -e MYSQL_PASSWORD=root1234% -e MYSQL_ROOT_PASSWORD=root1234% -d -p 3307:3306 mysql:latest
# STEP 1: docker build -t user .
# STEP 2: docker run -p 9899:9899 user
#
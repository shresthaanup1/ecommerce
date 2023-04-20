FROM maven:3.9-amazoncorretto-11 AS builder
#copy source code from current directory to container
COPY  ./ /source/
WORKDIR /source
#build jar
RUN mvn package




FROM eclipse-temurin:11-jre-focal AS deploy
EXPOSE 9897
WORKDIR /app/
COPY --from=builder /source/target/auth-services-0.0.1-SNAPSHOT.jar ./
#run the api-gateway application in cmd
ENTRYPOINT ["java" ,"-jar", "/app/auth-services-0.0.1-SNAPSHOT.jar"]

#docker run --name some-mysql -e MYSQL_DATABASE=ecommerce -e MYSQL_USER=nirmala -e MYSQL_PASSWORD=root1234% -e MYSQL_ROOT_PASSWORD=root1234% -d -p 3307:3306 mysql:latest
# STEP 1: docker build -t auth .
# STEP 2: docker run -p 9897:9897 auth
#
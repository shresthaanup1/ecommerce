FROM maven:3.9-amazoncorretto-11 AS builder
#copy source code from current directory to container
COPY  ./ /source/
WORKDIR /source
#build jar
RUN mvn package

FROM eclipse-temurin:11-jre-focal AS deploy
EXPOSE 9999
WORKDIR /app/
COPY --from=builder /source/target/api-gateway-0.0.1-SNAPSHOT.jar ./
#run the api-gateway application in cmd
ENTRYPOINT ["java" ,"-jar", "/app/api-gateway-0.0.1-SNAPSHOT.jar"]

# STEP 1: docker build -t gateway .
# STEP 2: docker run -p 9999:9999 gateway
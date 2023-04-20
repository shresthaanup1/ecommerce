FROM maven:3.9-amazoncorretto-11 AS builder
#copy source code from current directory to container
COPY  ./ /source/
WORKDIR /source
#build jar
RUN mvn package



FROM eclipse-temurin:11-jre-focal AS deploy
EXPOSE 9897
WORKDIR /app/
COPY --from=builder /source/target/product-services-0.0.1-SNAPSHOT.jar ./
#run the api-gateway application in cmd
ENTRYPOINT ["java" ,"-jar", "/app/product-services-0.0.1-SNAPSHOT.jar"]

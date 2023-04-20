FROM maven:3.9-amazoncorretto-11 AS builder
#copy source code from current directory to container
COPY  ./ /source/
WORKDIR /source
#build jar
RUN mvn package

FROM eclipse-temurin:11-jre-focal AS deploy
EXPOSE 9090
WORKDIR /app/
COPY --from=builder /source/target/eureka-server-0.0.1-SNAPSHOT.jar ./
#run the eureka application in cmd
ENTRYPOINT ["java" ,"-jar", "/app/eureka-server-0.0.1-SNAPSHOT.jar"]

## docker run -d -p 9090:9090 eureka
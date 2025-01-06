FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/BikeRideProject-0.0.1-SNAPSHOT.jar ride.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ride.jar"]
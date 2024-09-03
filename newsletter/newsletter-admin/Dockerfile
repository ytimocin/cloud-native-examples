FROM maven:3.9-eclipse-temurin-22-jammy AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
COPY components ./components
RUN mvn clean install

FROM eclipse-temurin:22-jre-jammy

WORKDIR /app

COPY --from=build /app/target/newsletter-admin-0.0.1-SNAPSHOT.jar /app/newsletter-admin.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "/app/newsletter-admin.jar"]
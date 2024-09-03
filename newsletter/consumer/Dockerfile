FROM maven:3.9-eclipse-temurin-22-jammy AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
# Check if components directory exists and copy it if it does, otherwise create an empty directory
RUN if [ -d "components" ]; then \
  cp -r components ./components; \
  else \
  mkdir -p ./components; \
  fi

RUN mvn clean install

FROM eclipse-temurin:22-jre-jammy

WORKDIR /app

COPY --from=build /app/target/consumer-0.0.1-SNAPSHOT.jar /app/consumer.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "/app/consumer.jar"]
FROM eclipse-temurin:21.0.6_7-jdk AS build
WORKDIR /app

COPY ./pom.xml .
COPY ./.mvn ./.mvn
COPY ./mvnw /app

RUN ./mvnw dependency:go-offline

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21.0.6_7-jre AS runtime
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "-webAllowOthers"]

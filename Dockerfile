FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/To_Do_Application_Project-0.0.1-SNAPSHOT.jar"]
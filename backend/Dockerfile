FROM openjdk:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/vslab-1.0.jar"]

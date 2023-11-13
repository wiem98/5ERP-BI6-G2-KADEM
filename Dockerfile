FROM eclipse-temurin:17-alpine
COPY target/kaddem-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]
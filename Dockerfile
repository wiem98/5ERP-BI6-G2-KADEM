FROM eclipse-temurin:17-alpine
EXPOSE 8089
COPY target/kaddem-1.0.0.jar .
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]

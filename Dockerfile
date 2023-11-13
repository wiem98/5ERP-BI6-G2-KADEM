FROM openjdk:11-jdk
EXPOSE 8089
ADD out/artifacts/kaddem_jar/kaddem.jar /app/kaddem.jar
ENTRYPOINT ["java","-jar","/KaddemProject-1.0.jar"]

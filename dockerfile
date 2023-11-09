# Utilisez l'image OpenJDK 17 Alpine
FROM openjdk:17-alpine

# Exposez le port sur lequel votre application Spring Boot écoute
EXPOSE 8089

# Copiez le fichier JAR de votre application dans le conteneur
#ADD /target/KaddemApplication-1.0.0.jar KaddemApplication.jar
#ADD tn/esprit/spring/kaddem/1.0.0/kaddem-1.0.0.jar app.jar
#ADD target/KaddemApplication-1.0.0.jar KaddemApplication.jar
ADD http://192.168.101.4:8081/repository/maven-releases/tn/esprit/spring/kaddem/1.0.0/kaddem-1.0.0.jar app.jar
# Commande pour démarrer l'application lorsque le conteneur est exécuté
ENTRYPOINT ["java", "-jar", "app.jar"]




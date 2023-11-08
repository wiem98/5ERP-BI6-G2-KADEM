FROM openjdk:8
EXPOSE 8088

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ONBUILD ADD . /usr/src/app
ONBUILD RUN mvn install -DskipTests
ONBUILD ADD /usr/src/app/target/kaddem-1.0.jar app.jar

CMD ["java","-jar","/app.jar"]

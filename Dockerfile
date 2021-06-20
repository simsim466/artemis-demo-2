FROM openjdk:15
LABEL maintainer="Anton Simonov"
ADD target/artemis-demo-app.jar artemis-demo-app.jar
ENTRYPOINT ["java", "-jar", "artemis-demo-app.jar"]
FROM openjdk:11.0.6-jre

ADD target/grpc-1.0-SNAPSHOT-jar-with-dependencies.jar grpc-1.0-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar","grpc-1.0-SNAPSHOT-jar-with-dependencies.jar"]
EXPOSE 8080

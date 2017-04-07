FROM openjdk:8

COPY ./tcf-client-1.0-SNAPSHOT.jar ./tcf-client.jar

ENTRYPOINT ["java", "-jar", "./tcf-client.jar"]

CMD ["localhost", "8080"]

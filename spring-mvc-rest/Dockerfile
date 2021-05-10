FROM openjdk:14
ADD target/spring-mvc-rest-0.0.1-SNAPSHOT.jar user-mysql.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "user-mysql.jar"]
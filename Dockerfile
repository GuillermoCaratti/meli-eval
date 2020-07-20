FROM openjdk:11
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./gradlew build
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","build/libs/eval-0.0.1-SNAPSHOT.jar"]

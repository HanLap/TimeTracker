# build
FROM openjdk:11-jdk as build

COPY . /opt
WORKDIR /opt

RUN apt-get update && apt-get install -y sudo && rm -rf /var/lib/apt/lists/*
RUN sudo -E bash ./gradlew build

FROM openjdk:11-jdk
COPY --from=build /opt/build/libs/timetracker-0.0.1.jar /opt/app/app.jar
WORKDIR /opt/app
ENTRYPOINT ["java","-jar","app.jar"]

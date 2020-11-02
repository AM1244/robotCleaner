FROM openjdk:8-jre-alpine

ENV PORT 8080

WORKDIR /var/app/robot-cleaner-api

ADD target/robot-cleaner-api.jar ./

EXPOSE $PORT

CMD java -jar ./robot-cleaner-api.jar


FROM openjdk:jdk-oracle

WORKDIR /api-listen
ADD target/api-listen.jar app.jar

CMD java $JAVA_OPTS -jar app.jar

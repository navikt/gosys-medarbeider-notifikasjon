FROM europe-north1-docker.pkg.dev/cgr-nav/pull-through/nav.no/jdk:openjdk-25

WORKDIR /app

COPY build/quarkus-app/lib/ /app/lib/
COPY build/quarkus-app/*.jar /app/app.jar
COPY build/quarkus-app/app/ /app/app/
COPY build/quarkus-app/quarkus/ /app/quarkus/

ENV LANG="C.UTF-8" LC_ALL="C.UTF-8" TZ="Europe/Oslo"
ENV JDK_JAVA_OPTIONS="-XX:MaxRAMPercentage=75 -XX:ActiveProcessorCount=2"

CMD ["java", "-jar", "app.jar"]
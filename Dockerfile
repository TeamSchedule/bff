FROM evrik/teamschedule_gradle_dependency_cache:1.0 as build
WORKDIR /app
COPY ./ ./
RUN gradle clean build --no-daemon -i --stacktrace -x test

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/bff-0.0.1-SNAPSHOT.jar ./spring-boot-application.jar

ARG SCHEDULE_URL
ARG USER_URL

ENV SCHEDULE_URL = ${SCHEDULE_URL}
ENV USER_URL = ${USER_URL}

ENTRYPOINT ["java","-jar","./spring-boot-application.jar"]

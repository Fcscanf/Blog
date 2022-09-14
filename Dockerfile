FROM openjdk:11

COPY ./target/Blog-0.0.1-SNAPSHOT-exec.jar /usr/app/

ENV TZ=Asia/Shanghai

WORKDIR /usr/app

RUN sh -c 'touch Blog-0.0.1-SNAPSHOT-exec.jar'

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Blog-0.0.1-SNAPSHOT-exec.jar"]
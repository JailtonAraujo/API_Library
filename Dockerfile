FROM openjdk:11

WORKDIR /usr/src/myapp

COPY ./target/api-library.jar .

EXPOSE 8083

CMD [ "java", "-jar", "api-library.jar" ]

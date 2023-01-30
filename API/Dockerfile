FROM openjdk:11

WORKDIR /usr/src/myapp

COPY ./target/customer.jar .

EXPOSE 8083

CMD [ "java", "-jar", "customer.jar" ]

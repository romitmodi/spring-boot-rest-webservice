FROM alpine
RUN echo Update OS
RUN apk update
RUN echo Installing JAVA
RUN apk --update add openjdk8-jre
RUN echo JAVA Installed
ENV JAVA_HOME=/usr/lib/jvm/default-jvm
RUN echo $JAVA_HOME
RUN /usr/bin/java -version
COPY ./target/spring-boot-rest-webservice-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app/
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java","-jar","spring-boot-rest-webservice-0.0.1-SNAPSHOT.jar"]
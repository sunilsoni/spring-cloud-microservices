#FROM java:8
#EXPOSE 8888
#ADD /target/config-server-0.1.0-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java",".jar","app.jar"]

FROM java:8
VOLUME /tmp
ADD config-server-0.1.0-SNAPSHOT.jar app.jar
EXPOSE 8888
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Denvironment=$HOST_IP","-jar","/app.jar"]
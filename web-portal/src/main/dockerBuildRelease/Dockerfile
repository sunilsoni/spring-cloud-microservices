FROM java:8
VOLUME /tmp
COPY web-portal/target/web-portal-1.0-RELEASED.jar app.jar
EXPOSE 9300
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
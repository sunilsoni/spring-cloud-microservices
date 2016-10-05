FROM java:8
VOLUME /tmp
COPY web-portal-service-0.1.0-SNAPSHOT.jar webportalservice.jar
EXPOSE 8090
RUN bash -c 'touch /webportalservice.jar'
ENTRYPOINT ["java","-jar","/webportalservice.jar","8090"]
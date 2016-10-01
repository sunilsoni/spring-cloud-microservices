#!/bin/sh

cd config-server; ./gradlew clean build; cd ..
cd eureka-server; ./gradlew clean build; cd ..
cd api-gateway; ./gradlew clean build; cd ..
cd web-portal; ./gradlew clean build; cd ..
cd job-service; ./gradlew clean build; cd ..
cd po-service; ./gradlew clean build; cd ..
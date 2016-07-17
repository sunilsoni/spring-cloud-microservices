#!/bin/sh

cd api-gateway; ./gradlew clean build; cd ..
cd auth-server; ./gradlew clean build; cd ..
cd config-server; ./gradlew clean build; cd ..
cd asn-service; ./gradlew clean build; cd ..
cd item-service; ./gradlew clean build; cd ..
cd web-portal; ./gradlew clean build; cd ..
cd eureka-server; ./gradlew clean build; cd ..
cd po-service; ./gradlew clean build; cd ..

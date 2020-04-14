#!/bin/sh


# mvn package

mkdir -p tmp
cp -rf foodie-api/target/*.jar tmp/
cp -rf foodie-common/target/*.jar tmp/
cp -rf foodie-mapper/target/*.jar tmp/
cp -rf foodie-pojo/target/*.jar tmp/
cp -rf foodie-service/target/*.jar tmp/

java -jar tmp/*.jar



# docker build -t foodie:1.0 ./


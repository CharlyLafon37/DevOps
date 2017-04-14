#!/bin/bash

#Preparing environment
cd ../../../
echo "Compiling the TCF system"
mvn -q -DskipTests clean package
echo "Done"
cp ./CookieFactory/j2e/WebApp/target/webapp-1.0-SNAPSHOT.war ./CookieFactory/docker/j2e/.

# building the docker image
cd ./CookieFactory/docker/j2e/
docker build -t docker-j2e .

# cleaning up the environment
rm -rf webapp-1.0-SNAPSHOT.war

#!/bin/bash

cd ../../..

mvn clean package

cp ./CookieFactory/j2e/WebApp/target/webapp-1.0-SNAPSHOT.war ./CookieFactory/docker/j2e/.

cd ./CookieFactory/docker/j2e

docker build -t docker-j2e .

rm -rf webapp-1.0-SNAPSHOT.war

echo ""
read -p "Press any key to exit ..."
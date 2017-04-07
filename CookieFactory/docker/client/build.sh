#!/bin/bash

cd ../../client

mvn clean package assembly:single

cp ./target/tcf-client-1.0-SNAPSHOT-jar-with-dependencies.jar ../docker/client/.

cd ../docker/client/

docker build -t docker-client .

rm -rf tcf-client-1.0-SNAPSHOT-jar-with-dependencies.jar

echo ""
read -p "Press any key to exit ..."
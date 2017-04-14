#!/bin/bash

#Preparing environment
cd ../../dotNet
./compile.sh
cp ./server.exe ../docker/dotNet/.

# building the docker image
cd ../docker/dotNet/
docker build -t docker-dotnet .

# cleaning up the environment
rm -rf server.exe

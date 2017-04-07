#!/bin/bash

cd ../../dotNet

./compile.sh

cp ./server.exe ../docker/dotNet/.

cd ../docker/dotNet/

docker build -t docker-dotNet .

rm -rf server.exe

echo ""
read -p "Press any key to exit ..."
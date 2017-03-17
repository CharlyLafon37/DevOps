#!/bin/bash

mkdir -p logs

echo "Building kcc"
cd kcc
mvn clean package 2> ../logs/log[kcc].txt >> ../logs/log[kcc].txt
cd ..

echo "Building Catalogue"
cd Catalogue
mvn clean package 2> ../logs/log[Catalogue].txt >> ../logs/log[Catalogue].txt
cd ..

echo "Building CustomerRegistry"
cd CustomerRegistry
mvn clean package 2> ../logs/log[CustomerRegistry].txt >> ../logs/log[CustomerRegistry].txt
cd ..

echo "Building CartWebService"
cd CartWebService
mvn clean package 2> ../logs/log[CartWebService].txt >> ../logs/log[CartWebService].txt
cd ..

echo "Building CustomerCareService"
cd CustomerCareService
mvn clean package 2> ../logs/log[CustomerCareService].txt >> ../logs/log[CustomerCareService].txt
cd ..

read -p "Press any key to exit ..."
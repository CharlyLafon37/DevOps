#!/bin/sh

path="Island/pom.xml"

processors=(
mutation.AtoBProcessor
mutation.LTToGTProcessor
) 


for process in ${processors[@]}
do
	echo "compiling processor : $process"
	
	sed -i.bak "s@<processor>.*@<processor>$process</processor>@g" $path
	sed -i.bak "s@<outFolder>.*@<outFolder>$process</outFolder>@g" $path

	mvn compile > "log[$process].txt" 
done


read -p "Appuyer sur une touche pour continuer ..."
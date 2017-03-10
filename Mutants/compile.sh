#!/bin/sh

path="Island/pom.xml"

processors=($(ls ./Mutator/src/main/java/mutation | sed -e 's/\.[^.]*$//'))

mkdir -p logs

for process in ${processors[@]}
do
	process="mutation.$process"

	echo "compiling processor : $process"
	
	sed -i.bak "s@<processor>.*@<processor>$process</processor>@g" $path
	sed -i.bak "s@<outFolder>.*@<outFolder>$process</outFolder>@g" $path
	sed -i.bak "s@<outputDirectory>.*@<outputDirectory>$process/report</outputDirectory>@g" $path

	mvn surefire-report:report 2> "logs/log[$process].txt" > "logs/log[$process].txt"
done

echo " "

./report.sh
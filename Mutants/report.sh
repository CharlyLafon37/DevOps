#!/bin/sh

path="report.txt"

processors=($(ls ./Mutator/src/main/java/mutation | sed -e 's/\.[^.]*$//'))

rm -f $path
touch $path

for process in ${processors[@]}
do
	process="mutation.$process"

	echo "reporting processor : $process"

	value=`cat ./Island/$process/report/surefire-report.html`

	value=`echo ${value#*<td>}`

	value=`echo ${value%%</tr>*}`

	value=`echo ${value#*<td>}` # Tests
	value=`echo ${value#*<td>}` # Errors
	value=`echo ${value#*<td>}` # Failures
	value=`echo ${value#*<td>}` # Skipped

	rate=`echo ${value%%</td>*}` # Rate

	value=`echo ${value#*<td>}`

	time=`echo ${value%%</td>*}` # Time

	echo "$process =)" >> $path
	echo "Rate : $rate" >> $path
	echo "Time : $time" >> $path
	echo "" >> $path
done

echo " "

read -p "Press a key to exit ..."
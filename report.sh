#!/bin/sh

path="report.txt"

processors=(
mutation.AtoBProcessor
mutation.LTToGTProcessor
)

rm -f $path
touch $path

for process in ${processors[@]}
do
	value=`cat ./Island/$process/report/surefire-report.html`

	value=`echo ${value#*<td>}`

	value=`echo ${value%%</tr>*}`

	value=`echo ${value#*<td>}`
	value=`echo ${value#*<td>}`
	value=`echo ${value#*<td>}`
	value=`echo ${value#*<td>}`

	rate=`echo ${value%%</td>*}`

	value=`echo ${value#*<td>}`

	time=`echo ${value%%</td>*}`

	echo "$process =)" >> $path
	echo "Rate : $rate" >> $path
	echo "Time : $time" >> $path
	echo "" >> $path
done



read -p "Rapport cree : $path ..."
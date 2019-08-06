#!/bin/bash

wget -r -np -P -q --convert-links $2 $1

tree -J $2 > urlReport.json

md5sum urlReport.json

var=$( grep -o '{' urlReport.json | wc -l )

echo $var

ps -p $var -o comm=

if [ $? -eq 1 ]
then 
	echo "No such process"
fi

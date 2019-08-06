#!/bin/bash

if [ -z $1 ]
then  
	echo 'Usage: ./recursiveSearch.sh <words-to-search>'
	exit 1
else 

	ans1=$( grep -i -r -w -n $1 Data)
	for i in $@
	do 
		ans1=$( echo "$ans1" | grep -i -w $i)
	done
fi
echo "$ans1"
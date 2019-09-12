#!/bin/bash

#grep 'Department' ../D/*.txt | 


for FILE in $1/*.txt 
do	
	#echo ${FILE}
	var=$( sed -n '/Department/ p' ${FILE} | cut -d':' -f 2 | sed -e 's/\s//g' )
	#echo $var
	if [ ! -d $var ]
	then 
		mkdir $var
	fi
	cp ${FILE} $var
done    


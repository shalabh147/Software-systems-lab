#!/bin/bash

num=$( /bin/bash randomNumber.sh 2 )
counter=0
> howFarFromTruth.txt
while [ $num -ne 42 ]
do
	num=$( /bin/bash randomNumber.sh 2 )
	echo $num | cat >> howFarFromTruth.txt
	((counter++))
done



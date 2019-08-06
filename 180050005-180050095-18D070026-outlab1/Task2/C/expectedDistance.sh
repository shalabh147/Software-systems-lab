#!/bin/bash

k=$1
n=$k
sum=0
while [ $k -gt 0 ]
do
	 /bin/bash findTheAnswer.sh
	x=$( < howFarFromTruth.txt wc -l ) 
	((sum=sum+x))
	((k--))
done

((sum = sum/n))
echo $sum



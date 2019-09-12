#!/bin/bash

read input

#echo $input

#dual=[A-Z]
dual=abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz
dual=$( echo $dual | tr a-z A-Z )
key=A
rotat=26
rotat_key=0
while [ $rotat -ge 1 ]
do
	cur_key=$(echo $key | tr "${dual:0:26}" "${dual:${rotat_key}:26}") 
	newphrase=$(echo $input | tr "${dual:0:26}" "${dual:${rotat}:26}")
	echo $cur_key ${newphrase}
	(( rotat -- ))
	(( rotat_key ++ ))
done



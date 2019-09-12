#!/bin/bash

key=$1
read msg 

rot=A
rotat=0
while [ "$key" != "$rot" ]
do
	rot=$( echo $rot | tr '[A-Z]' '[B-ZA-A]')   
	(( rotat++ ))
done
echo $rotat

dual=abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz
dual=$( echo $dual | tr a-z A-Z )
ans=$(echo $msg | tr "${dual:0:26}" "${dual:${rotat}:26}")
echo $ans 

#!/bin/bash
n=$1

#calculating 10**n
ans=1
 
res=$( od -vAn -N4 -tu4 < /dev/urandom )
(( num = res%9 + 1 ))
while [ $n -gt 1 ]
do
	res=$( od -vAn -N4 -tu4 < /dev/urandom )
	(( extra = $res%10 ))
	num=$( echo $num$extra )
	((n--))
	#echo $res $extra $num
done
echo $num  

#res=$( od -vAn -N8 -tu8 < /dev/urandom ) 
#((res=res%ans))
#echo $res
#num=$(echo "123""456")
#echo $(($num+1))

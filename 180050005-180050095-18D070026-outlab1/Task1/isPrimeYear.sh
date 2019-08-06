#!/bin/bash

#for i in range(2,sqrt(n)) :
#	if n%i==0 :  prime=false
#if prime echo "Prime"
#else echo "Not prime"

re='^[0-9]+$'

yournumber=$1

if ! [[ $yournumber =~ $re ]] ; then
	echo "error: Invalid argument!" >&2; exit 1
fi

var=1
i=2
prod=4

while [ $prod -le $yournumber ]
do
	if (( $yournumber%i == 0))
	then
		var=0
	fi
	((i++))
	((prod = i*i))
done

if [ $var -eq 1 ]
then
	echo "Prime Year!"
else
	echo "Not a prime year."
fi	

counter=0
num=2
while [ $num -le 2019 ]
do
	var2=1
	j=2
	product=4

	while [ $product -le $num ]
	do
		if (( $num%j == 0))
		then
			var2=0
		fi
		((j++))
		((product = j*j))
	done
	
	if [ $var2 -eq 1 ]
	then
		((counter++))
	fi
	
	((num++))
done

echo $counter | cat > numberOfPrimeYears.txt


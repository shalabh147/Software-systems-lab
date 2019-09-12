#!/bin/bash

sed -n -e "/$4/ p" $1 | sed -n -e "/$3/ p" > tmp.txt


awk 'BEGIN{FS=",";RS="\r\n"; marks=0}
	FNR==NR && FNR>1{FS="," ; a[$1]=$2} 
    FNR!=NR {FS="," ; tot_credits+=$5 ;
     			credits=$5 ;
     			grade=$7 ;
     			marks+=a[grade]*credits ;
     			 
     			 }
    END{printf "%0.4f\n",marks/tot_credits ;
 		}' $2 tmp.txt

rm tmp.txt
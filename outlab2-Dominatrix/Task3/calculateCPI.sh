#!/bin/bash

awk 'BEGIN{FS=",";RS="\r\n"; marks=0}
	FNR==NR && FNR>1{FS="," ; a[$1]=$2} 
     FNR!=NR && FNR>1{FS="," ; tot_credits+=$5 ;
     			credits=$5 ;
     			grade=$7 ;
     			marks+=a[grade]*credits ;

     			 }
     END{printf "%0.4f\n",marks/tot_credits ;
 		}' $2 $1


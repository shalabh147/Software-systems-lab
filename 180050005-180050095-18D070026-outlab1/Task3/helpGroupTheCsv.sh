#!/bin/bash

out="$(cut -d ',' -f 1,2,3 $1 ; cut -d ',' -f 1,4,5 $1 ; cut -d ',' -f 1,6,7 $1)"

sorted="$(echo "$out" | sort --field-separator=',' -k3,3 -k1,1f)"
echo "$sorted" > $2 

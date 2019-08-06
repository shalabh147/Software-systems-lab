#!/bin/bash

wget -q $1
l=$( echo $1 | rev | cut -d'/' -f 1 | rev )
temp=$(pdftotext $l -)
ans=$( echo "$temp" | grep -w -o -i $2 | wc -l )
echo $ans
rm $l

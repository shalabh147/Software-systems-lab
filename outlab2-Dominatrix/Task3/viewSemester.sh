#!/bin/bash
head -3 $1
sed -n -e "/$3/ p" $1 | sed -n -e "/$2/ p" | awk -F '[[:space:]][[:space:]]+' '{print | "sort -k 3,3"}'  

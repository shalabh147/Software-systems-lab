#!/bin/bash
head -3 $1
 sed -n -e "/$2/ p" $1 | awk -F '[[:space:]][[:space:]]+' '{print | "sort -k 3,3"}'

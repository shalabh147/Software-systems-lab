#!/bin/bash
awk -F"|" 'BEGIN{OFS="|"}NR==1{print $1,$2,$3,$4,$5}' $1 > sortedStudentData.csv
awk -F"|" 'NR!=1{print $1":"$2":"$3":"$4":"$5| "sort -t: -rk3,3 -V"}' $1 | awk -F":" 'BEGIN{OFS="|"}{print $1,$2,$3,$4,$5}' >> sortedStudentData.csv
awk -F"|" 'NR==2,NR==6{print $1}' sortedStudentData.csv > top5Students.txt

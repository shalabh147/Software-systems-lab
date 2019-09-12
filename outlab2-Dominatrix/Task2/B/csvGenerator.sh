#!/bin/bash
ans="$(awk 'NF' $1 |
awk 'BEGIN{ORS="\n"
	OFS="|"
	RS="--------------------------------------------------------------------"
	FS="\n"
	print "Student Name","Roll Number","CPI","Department","Courses Undertaken"
}
NR!=1{
	split($2,names,":"); split($3,roll,":"); split($4,cpi,":"); split($5,dept,":"); split($6,courses,":")
	printf("%s|%s|%s|%s|%s",names[2],roll[2],cpi[2],dept[2],courses[2])
	for(i=7;i<NF;i++)
		printf("%s ",$i)
	print ""
	}' |
sed 's/\f//g'
	)"

echo "$ans" > studentData.csv

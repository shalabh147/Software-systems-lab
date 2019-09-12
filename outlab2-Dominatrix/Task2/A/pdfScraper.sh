#! /bin/bash
wget -q $1
var=$( echo $1 | rev | cut -d'/' -f 1 | rev )
pdftotext $var studentData.txt
sed -i 's/\f//g' studentData.txt 
> and.txt
echo "india" | cat >> and.txt
#cat and.txt
awk 'BEGIN {FS="\n" ; RS="--------------------------------------------------------------------";
		OFS="\n"; ORS="\n--------------------------------------------------------------------"}
	NR>1{
	for(i=8;i<=NF;i++){
		$7=$7" "$i;
	}print $2,$3,$4,$5,$6,$7 }' studentData.txt | tr -dc '\0-\177' | cat >> and.txt 
	
sed -i '1d;$d' and.txt
sed -i 's/^$/--------------------------------------------------------------------/g' and.txt
#cat and.txt
cat and.txt > studentData.txt
rm and.txt

  
rm $var

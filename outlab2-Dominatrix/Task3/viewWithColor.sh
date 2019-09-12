#!/bin/bash
source ./resources/defineColors.sh
#awk -v RED_FONT="$(echo $RED_FONT)" '{arr["RED_FONT"]=RED_FONT; printf "%s %s\n", arr["RED_FONT"], $3}' $2 $1
sed -e 's/\([0-9]\) /\1@/g' $1 | sed -e 's/@ \{1,\}/@/g' | sed -e 's/ \{2,\}/@/g' | sed -e 's/@\{1,\}/@/g' |
awk -v BLACK_FONT="$(echo $BLACK_FONT)" -v RED_FONT="$(echo $RED_FONT)" -v GREEN_FONT="$(echo $GREEN_FONT)" -v YELLOW_FONT="$(echo $YELLOW_FONT)" -v BLUE_FONT="$(echo $BLUE_FONT)" -v MAGENTA_FONT="$(echo $MAGENTA_FONT)" -v CYAN_FONT="$(echo $CYAN_FONT)" -v WHITE_FONT="$(echo $WHITE_FONT)" -v BLACK_BACKGROUND="$(echo $BLACK_BACKGROUND)" -v RED_BACKGROUND="$(echo $RED_BACKGROUND)" -v GREEN_BACKGROUND="$(echo $GREEN_BACKGROUND)" -v YELLOW_BACKGROUND="$(echo $YELLOW_BACKGROUND)" -v BLUE_BACKGROUND="$(echo $BLUE_BACKGROUND)" -v MAGENTA_BACKGROUND="$(echo $MAGENTA_BACKGROUND)" -v CYAN_BACKGROUND="$(echo $CYAN_BACKGROUND)" -v WHITE_BACKGROUND="$(echo $WHITE_BACKGROUND)" -v RESET_ALL="$(echo $RESET_ALL)" 'BEGIN{
		FS=","
		col["BLACK_FONT"]=BLACK_FONT
		col["RED_FONT"]=RED_FONT
		col["GREEN_FONT"]=GREEN_FONT
		col["YELLOW_FONT"]=YELLOW_FONT
		col["BLUE_FONT"]=BLUE_FONT
		col["MAGENTA_FONT"]=MAGENTA_FONT
		col["CYAN_FONT"]=CYAN_FONT
		col["WHITE_FONT"]=WHITE_FONT

		col["BLACK_BACKGROUND"]=BLACK_BACKGROUND
		col["RED_BACKGROUND"]=RED_BACKGROUND
		col["GREEN_BACKGROUND"]=GREEN_BACKGROUND
		col["YELLOW_BACKGROUND"]=YELLOW_BACKGROUND
		col["BLUE_BACKGROUND"]=BLUE_BACKGROUND
		col["MAGENTA_BACKGROUND"]=MAGENTA_BACKGROUND
		col["CYAN_BACKGROUND"]=CYAN_BACKGROUND
		col["WHITE_BACKGROUND"]=WHITE_BACKGROUND

	}
NR==FNR{
fg[$1]=$3."_FONT"
bg[$1]=$4."_BACKGROUND"
}
NR!=FNR&&FNR==1{FS="@"}
NR!=FNR&&FNR==2{
	for(i=1;i<NF;i++)
	{
		for(j=1;j<=20;j++)
		{
			printf "-"
		}
	}
	printf "\n%20s%20s%20s%20s%20s%20s\n",$2,$3,$4,$5,$6,$7,$8
	for(i=1;i<NF;i++)
	{
		for(j=1;j<=20;j++)
		{
			printf "-"
		}
	}
	printf "\n"
}
NR!=FNR&&FNR>3{printf "%s%s%20s%20s%20s%20s%20s%20s%s\n", col[bg[$6]], col[fg[$6]], $2,$3,$4,$5,$6,$7, RESET_ALL}' $2 -

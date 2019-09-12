#!/bin/bash

#awk -F"--------------------------------------------------------------------" '{print > $2".txt"}' $1

#awk '/--------------------------------------------------------------------/{x="F"++i;}{print > $2".txt"}' $1 

#sep="--------------------------------------------------------------------"

#var=$( sed 's/\n\{2,\}//g' $1 )
#echo $var
#echo $var | awk -F"\n" '/sep/{x="F"++i;next}{print > $2".txt";}'

sed -e 's/-.*/\&/g' $1 |
sed -e 's/\f//g' |
sed -e 's/^$/\&/g' |
sed -e '1d' | 
tr -dc '\0-\177' |
awk 'BEGIN{RS="\n&\n";FS="\n|:"} {print > $4".txt"}'

#sed -n -e '1 s/^$//g' *
#tr '\n' '@' | 
#cat > 1

#var2=$( echo $var | sed -e 's/\n\{2,\}/\@/g' )
#echo $var2 | cat > 1

#echo var2 | awk 'RS="@";FS=":"|"\n"; {print > $4".txt"}'  
 

 


# remove all ----...
# replace \n\{2,\} with @ 
# @ to be used as RS
# : or \n to be taken as FS 
# $4 will be roll_no

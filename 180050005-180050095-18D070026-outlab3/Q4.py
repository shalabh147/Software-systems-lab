n=int(input())
main_dict={}

for i in range(n):
	s=input()
	l1=s.split(":") # [ match, other_stuff]
	match=l1[0]
	l2=l1[1].split(",") # ['p12-13', 'p123'-2132, ... ]

	small_dict={}
	for p in l2:
		name=p.split("-")[0]
		runs=p.split("-")[1]
		small_dict[name]=int(runs)

	main_dict[match]=small_dict

print(main_dict)

actual_data=list(main_dict.values())
#print(actual_data)

player_dict={}
for dic in actual_data:
	for p in dic.keys():
		player_dict[p]=player_dict.get(p,0)+dic[p]
#print(player_dict)
print(list(sorted(player_dict.items(), key=lambda x : (x[1],x[0]) , reverse=True )))

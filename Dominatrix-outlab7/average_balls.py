import sqlite3
connection = sqlite3.connect('ipl.db')
cursor = connection.cursor()

cursor.execute('''SELECT player_id, player_name, T.avg
				FROM player
				INNER JOIN (SELECT striker, count(*)*1.0/count(DISTINCT match_id) as avg from BALL_BY_BALL GROUP BY striker) T
					ON player_id=T.striker
				ORDER BY T.avg DESC''')

rank=0
sp_rank=0
prev=0
for x in cursor.fetchall():
	if str(x[2])!=prev:
		prev=str(x[2])
		rank+=1
		sp_rank=rank
	else:
		rank+=1
	if(sp_rank<=10):
		print(str(x[0])+','+x[1]+','+str(x[2]))
	else:
		break
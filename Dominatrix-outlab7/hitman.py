import sqlite3
connection = sqlite3.connect('ipl.db')
cursor = connection.cursor()

 
cursor.execute('''SELECT player_id, player_name, f.sixes, f.balls, (f.sixes*1.0)/f.balls as frequency
					FROM (SELECT striker, COUNT(*) as balls, SUM(CASE runs_scored WHEN 6 THEN 1 ELSE 0 END) as sixes
							FROM BALL_BY_BALL
							GROUP BY striker) f
					INNER JOIN PLAYER ON f.striker = PLAYER.player_id
					ORDER BY frequency DESC
					''')

for x in cursor.fetchall():
	print(str(x[0])+","+str(x[1])+","+str(x[2])+","+str(x[3])+","+str(x[4]))

connection.close()
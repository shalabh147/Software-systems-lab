import sqlite3
connection = sqlite3.connect('ipl.db')
cursor = connection.cursor()


cursor.execute('''SELECT venue_name,AVG(f.runs) AS Y 
				FROM MATCH
				INNER JOIN (SELECT match_id , SUM(runs_scored) AS runs FROM BALL_BY_BALL GROUP BY match_id) f
				 ON MATCH.match_id = f.match_id
				GROUP BY venue_name ORDER BY Y DESC''')

for x in cursor.fetchall():
	print(str(x[0])+","+str(x[1]))
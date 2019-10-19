import sqlite3
import csv
import pandas

connection = sqlite3.connect('ipl.db')
cursor = connection.cursor()

creader = csv.reader(open('team.csv'),delimiter=',',quotechar=',')
next(creader)
for t in creader:
	cursor.execute('INSERT INTO TEAM VALUES (?,?)', t)




creader3 = csv.reader(open('player.csv'),delimiter=',',quotechar=',')
next(creader3)
for n in creader3:
	cursor.execute('INSERT INTO PLAYER VALUES (?,?,?,?,?,?)', n)

creader = csv.reader(open('player_match.csv'),delimiter=',',quotechar=',')
next(creader)
for t in creader:
	cursor.execute('INSERT INTO PLAYER_MATCH VALUES (?,?,?,?,?,?,?)' , t)

creader = csv.reader(open('ball_by_ball.csv'),delimiter=',',quotechar=',')
next(creader)
for t in creader:
	cursor.execute('INSERT INTO BALL_BY_BALL VALUES (?,?,?,?,?,?,?,?,?,?,?)' , t)

x = pandas.read_csv('match.csv');
x.to_sql('MATCH',connection,if_exists='append',index=False);	

connection.commit()
connection.close()
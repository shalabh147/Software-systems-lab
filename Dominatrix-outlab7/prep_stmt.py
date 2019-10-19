import sqlite3
import sys


connection = sqlite3.connect('ipl.db')

cursor = connection.cursor()

i = int(input())

l = []
if(i == 1):
	for j in range(2):
		x = input()
		l.append(x)
	cursor.execute('INSERT INTO TEAM VALUES (?,?)',l)
elif(i == 2):
	for j in range(6):
		x = input()
		l.append(x)
	cursor.execute('INSERT INTO PLAYER VALUES (?,?,?,?,?,?)', l)
elif(i == 3):
	for j in range(15):
		x = input()
		l.append(x)
	cursor.execute('INSERT INTO MATCH VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)', l)
elif(i == 4):
	for j in range(7):
		x = input()
		l.append(x)
	cursor.execute('INSERT INTO PLAYER_MATCH VALUES (?,?,?,?,?,?,?)' , l)
elif(i == 5):
	for j in range(11):
		x = input()
		l.append(x)
	cursor.execute('INSERT INTO BALL_BY_BALL VALUES (?,?,?,?,?,?,?,?,?,?,?)' , l)

connection.commit()
connection.close()
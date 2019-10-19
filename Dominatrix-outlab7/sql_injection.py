import sqlite3

connection = sqlite3.connect('ipl.db')

cursor = connection.cursor()

arg1 = int(input())
arg2 = int(input())
arg3 = input()

if arg2 == 0:
	if arg1 == 1:
		query = '''DELETE FROM TEAM WHERE team_name ="''' + arg3 + '''"'''
		cursor.execute(query)
	elif arg1 == 2:
		query = '''DELETE FROM PLAYER WHERE player_name ="''' + arg3 + '''"'''
		cursor.execute(query)
	elif arg1 == 3:
		query = '''DELETE FROM MATCH WHERE match_id ="''' + arg3 + '''"'''
		cursor.execute(query)
elif arg2 == 1:
	if arg1 == 1:
		cursor.execute('''DELETE FROM TEAM WHERE team_name=(?)''', [arg3])
	elif arg1 == 2:
		cursor.execute('''DELETE FROM PLAYER} WHERE player_name=(?)''', [arg3])
	elif arg1 == 3:
		cursor.execute('''DELETE FROM MATCH WHERE match_id=(?)''', [arg3])

connection.commit()
connection.close()
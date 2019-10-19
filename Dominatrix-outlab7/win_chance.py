import sqlite3
connection = sqlite3.connect('ipl.db')

cursor = connection.cursor()

cursor.execute('''SELECT match_id FROM MATCH WHERE NOT win_type = "NA";''')

count = 0;
for x in cursor:
	count = count + 1;

length_toty = count

cursor2 = connection.cursor()

cursor2.execute('''SELECT match_id FROM MATCH WHERE battedfirst = match_winner AND NOT win_type="NA";''')

count = 0
for x in cursor2.fetchall():
	count = count + 1

length_bat1win = count


cursor3 = connection.cursor()

cursor3.execute('''SELECT match_id FROM MATCH WHERE battedsecond = match_winner AND NOT win_type="NA";''')

count = 0

for x in cursor3.fetchall():
	count = count + 1

length_bat2win = count

print(round((length_bat1win/length_toty),3))
print(round((length_bat2win/length_toty),3))

connection.close()
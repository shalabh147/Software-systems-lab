import sqlite3
mydb = sqlite3.connect('ipl.db')

cursor = mydb.cursor()

cursor.execute('''CREATE TABLE IF NOT EXISTS TEAM 
				(team_id INT NOT NULL,
				 team_name TEXT,
				 PRIMARY KEY (team_id))''')

cursor.execute('''CREATE TABLE IF NOT EXISTS PLAYER
				(player_id INT NOT NULL,
				player_name TEXT,
				dob TIMESTAMP ,
				batting_hand TEXT,
				bowling_skill TEXT,
				country_name TEXT,
				PRIMARY KEY (player_id) )''')

cursor.execute('''CREATE TABLE IF NOT EXISTS MATCH
				(match_id INT ,
				season_year INT ,
				team1 INT,
				team2 INT,
				battedfirst INT,
				battedsecond INT,
				venue_name TEXT,
				city_name TEXT,
				country_name TEXT,
				toss_winner TEXT,
				match_winner TEXT,
				toss_name TEXT,
				win_type TEXT,
				man_of_match INT,
				win_margin INT,
				PRIMARY KEY (match_id),
				FOREIGN KEY (team1) REFERENCES TEAM(team_id)
				FOREIGN KEY (team2) REFERENCES TEAM(team_id)
				FOREIGN KEY (battedfirst) REFERENCES PLAYER(player_id)
				FOREIGN KEY (battedsecond) REFERENCES PLAYER(player_id) )''')


cursor.execute('''CREATE TABLE IF NOT EXISTS PLAYER_MATCH
				(playermatch_key INT,
				match_id INT,
				player_id INT,
				batting_hand TEXT,
				bowling_skill TEXT,
				role_desc TEXT,
				team_id INT,
				PRIMARY KEY (playermatch_key),
				FOREIGN KEY (match_id) REFERENCES MATCH(match_id)
				FOREIGN KEY (player_id) REFERENCES PLAYER(player_id)
				FOREIGN KEY (team_id) REFERENCES TEAM(team_id) )''')

cursor.execute('''CREATE TABLE IF NOT EXISTS BALL_BY_BALL
				(match_id INT,
				innings_no INT,
				over_id INT,
				ball_id INT,
				striker_batting_position INT,
				runs_scored INT,
				extra_runs INT,
				out_type TEXT,
				striker INT,
				non_striker INT,
				bowler INT,
				PRIMARY KEY (match_id,innings_no,over_id,ball_id)
				FOREIGN KEY (match_id) REFERENCES MATCH(match_id)
				FOREIGN KEY (striker) REFERENCES PLAYER(player_id)
				FOREIGN KEY (non_striker) REFERENCES PLAYER(player_id)
				FOREIGN KEY (bowler) REFERENCES PLAYER(player_id)  )''')

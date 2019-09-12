import csv
import os
import argparse

parser=argparse.ArgumentParser()

def print_help(errmsg):
	print(errmsg)
	exit()

if(os.path.exists('student_database.csv')):
	parser.add_argument('--first_name',required=True)
	parser.add_argument('--last_name',required=True)
	parser.add_argument('--gender',required=True)
	parser.add_argument('--mobile',required=True)
	parser.add_argument('--dept',required=True)
	parser.add_argument('--roll_no',required=True)
	parser.add_argument('--CGPA',required=True)

	parser.error = print_help
	args = parser.parse_args()
	#write to csv args.parameters
	#fields = [args.first_name,args.last_name,args.roll_no,args.gender,args.mobile,args.dept,args.CGPA]
	with open('student_database.csv','a') as fd:
		#writer = csv.writer(fd)
		#writer.writerow(fields);
		fd.write(args.first_name+", "+args.last_name+", "+args.roll_no+", "+args.gender+", "+args.mobile+", "+args.dept+", "+args.CGPA+"\n")
		print("Successfully Added!!")
		exit()

else:
	parser.add_argument('--first_name',required=True)
	parser.add_argument('--last_name',required=True)
	parser.add_argument('--gender',required=True)
	parser.add_argument('--mobile',required=True)
	parser.add_argument('--dept',required=True)
	parser.add_argument('--roll_no',required=True)
	parser.add_argument('--CGPA',required=True)

	parser.error = print_help
	args = parser.parse_args()
	#write to csv args.parameters
	fields = [args.first_name, args.last_name, args.roll_no,args.gender,args.mobile,args.dept,args.CGPA]
	fields1 = ['First Name',' Last Name',' Roll Number',' Gender',' Mobile',' Dept',' CGPA']
	with open('student_database.csv','a+') as fd:
		#writer=csv.writer(fd)
		#writer.writerow(fields1);
		fd.write("First Name, Last Name, Roll Number, Gender, Mobile, Dept, CGPA\n")
		fd.write(args.first_name+", "+args.last_name+", "+args.roll_no+", "+args.gender+", "+args.mobile+", "+args.dept+", "+args.CGPA+"\n")
		print("Successfully Added!!")



	#make a new csv file

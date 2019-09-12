from math import sqrt
from math import inf
class Dimension:
	def __init__(self,l,date):
		self.x=l[0];
		self.y=l[1];
		self.z=l[2];
		self.date=date;
	def get_param(self):
		l=list(self.x,self.y,self.z)
		return list(l,self.date);

	def distance(self,D2):
		return sqrt((self.x-D2.x)**2 + (self.y-D2.y)**2 + (self.z-D2.z)**2);

	def less(self,D2):
		if(self.date[2] < D2.date[2]):
			return True;
		elif(self.date[2] > D2.date[2]):
			return False;
		else:
			if(self.date[1] < D2.date[1]):
				return True;
			elif(self.date[1] > D2.date[1]):
				return False;
			else:
				return self.date[0] < D2.date[0]


	def __sub__(self,D2):
		if(self.less(D2)):
			return self.distance(D2);
		else:
			return -1

	def getCoordinates(self):
		return list((self.x,self.y,self.z))

	def __str__(self):
		return 'Coordinates: (' + str(self.x) + ', ' + str(self.y) + ', ' + str(self.z) + ') Time: (' + str(self.date[2]) + ', ' + str(self.date[1]) + ', ' + str(self.date[0]) + ')'


x=input()
l=x.split(" - ");

#print(l[0])

l1 = l[0].split(",");
l2=list(map(int,l1));

l3=l[1].split("/");
l4=list(map(int,l3));

Main_object = Dimension(l2,l4);

n = int(input());

objectList = []
for i in range(n):
	x=input()
	l=x.split(" - ");

	l1 = l[0].split(",");
	l2=list(map(int,l1));

	l3=l[1].split("/");
	l4=list(map(int,l3));

	objectList.append(Dimension(l2,l4));

closest_object = 0;

found=False;
min_distance = inf;

#print(len(objectList))
for obj in objectList:
	print(obj.__str__());
	dist = Main_object-obj;
	#print(dist)
	if(dist == -1):
		continue;
	else:
		if dist <= min_distance:
			found=True;
			min_distance = dist;
			closest_object = obj;




if(found):
	print("Closest Point is:")
	print(closest_object.__str__());
else:
	print("Can't move to any point")


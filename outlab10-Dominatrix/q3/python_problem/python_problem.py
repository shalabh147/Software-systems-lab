## @file python_problem.py
# @author Team Dominatrix
# @details Illustrates doxygen-style comments for documenting a Python
# program file and the functions in that file.
from sys import argv
from copy import deepcopy

## Reads a file containing newline seperated integers and returns a list of those integers 
# @param filename name of the input file
# @return list of integers 
def make(filename):
	file = open (filename)
	l=[]
	for line in file:
		a=int(line)
		l.append(a)
	return l

## Sorts an input list in ascending order 
# @param l the input list
# @return the sorted list
def fun1(l):	
	size=len(l)
	for i in range(0,size-1):
		for j in range(0,size-i-1):
			if l[j] > l[j+1]:
				temp=l[j]
				l[j]=l[j+1]
				l[j+1]=temp
			
	return l

## Performs binary search for a specified input value on a given input list
# which is assumed to be sorted in ascending order. Also prints the mid and low values in each cut.  
# @param l the input list 
# @param x the value to be searched
# @return 1 + the number of times the list had to be cut in half during the binary search 
# to find the input value if it was present in the input list, -1 otherwise
def fun2(l,x):
	n=int(x)
	probes=0
	s=0
	e=len(l)-1
	flag=0
	while(s<=e):
		probes=probes+1
		m=(s+e)//2
		print ("Probe "+str(probes)+":",m,l[m])
		if(l[m]==n):
			flag=1
			break
		elif(l[m]>n):
			e=m-1
		elif(l[m]<n):
			s=m+1
	if(flag==1):
		return probes
	else:	
		return -1

## Calculates the determinant of an input matrix given in the format of a list whose elements are lists
# @param L the input matrix
# @return the determinant 
def fun3(L):
	d=0
	n=len(L)
	if(n==1):
		return L[0][0]
	for i in range(0,n):
		A=deepcopy(L)
		for j in range(0,len(A)):
			A[j].pop(i)
		A.pop(0)
		# print(A)
		d=d+L[0][i]*((-1)**i)*fun3(A)
		# print(d)
	return d
  
L = make("data")
print (L)
L = fun1(L)
print ("\nAfter Fun1:")
print (L)
print ("\nFun2 Starts:")
ans = fun2(L, 48)
print ("Result of Fun2:", ans)

print ("\n")
# Change the elements and observe the result  
# L = [[1, 2, 3], [100, 54, 23], [9, 27, 11]]
L = [[1, 2, 3], [4, 5, 6], [7, 8, 10]]
print(L)
ans = fun3(L)
print ("Result of Fun3:", ans)

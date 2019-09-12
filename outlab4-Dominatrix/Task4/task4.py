import numpy as np
import matplotlib.pyplot as plt
from math import sqrt 


def mean_filter(arr,k):
	t = arr.copy();
	t.astype(float)
		
	z = np.zeros(len(arr)+2*k);
	z[k:len(arr)+k] = arr;
	y = np.cumsum(z);
	l1 = y[:len(arr)-1];
	l1 = np.append(np.array([0]),l1)
	l2 = y[2*k:];
	y = (l2-l1)/(2*k+1)	
	#y = np.convolve(z,np.ones((2*k+1,))/(2*k+1), mode='valid')
	y = y.astype(float);
	return y

def generate_sin_wave(period,range_,num):
	#print(range_)
	xmin = range_[0];
	xmax = range_[1];
	l = (xmax-xmin)/(num-1);
	x = np.arange(xmin,xmax+l,l);
	#print(x)
	y = np.sin(2*3.14*x/period);
	return y

def noisify(array,var):
	length = len(array)
	noise = np.random.normal(0,sqrt(var),length);
	new_array = array + noise;
	return new_array;

#print(mean_filter(np.array([1,2,3,4,5,6,7,8,9,10,11]),3))



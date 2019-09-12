# Write your code here!
import sys
import scipy as sc
import numpy as np
import argparse
from scipy import misc
from scipy.cluster.vq import kmeans2
import imageio
parser = argparse.ArgumentParser()
parser.add_argument('--input')
parser.add_argument('--k',type=int)
parser.add_argument('--output')
input_file = parser.parse_args().input
k = parser.parse_args().k
output_file = parser.parse_args().output   
x = imageio.imread(input_file);
#print(np.shape(x));
#x will be a 3d array of size 768.1024,3

x=x.astype(float)

(a,b,c) = np.shape(x);

y = x.reshape(a*b,c);
y = y[:,0:3];


(centroid,label) = kmeans2(y,k,minit='++',iter=100);

for i in range(len(y)):
	y[i] = centroid[label[i]]

y = y.reshape(a,b,c);
y = y.astype(np.uint8);
imageio.imwrite(output_file,y);



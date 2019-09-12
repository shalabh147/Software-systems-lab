import task4
import matplotlib.pyplot as plt
import numpy as np
def driver():
	clean_sin = task4.generate_sin_wave(2,[-2,8],1000);
	plt.figure();

	xmin = -2;
	xmax = 8;
	num=1000;
	l = (xmax-xmin)/(num-1);
	x = np.arange(xmin,xmax+l,l);

	plt.plot(x,clean_sin)
	plt.xlabel('x')
	plt.ylabel('clean_sin')
	plt.title('clean_sin vs x')
	#plt.show();
	plt.savefig('clean_sin.png')
	plt.close()

	dirty_sin = task4.noisify(clean_sin,0.05*0.05);
	plt.figure();
	plt.plot(x,dirty_sin)
	plt.xlabel('x')
	plt.ylabel('dirty_sin')
	plt.title('dirty_sin vs x')

	#plt.show();
	plt.savefig('dirty_sin.png')
	plt.close()

	cleaned_sin = task4.mean_filter(dirty_sin,1);
	plt.figure();
	plt.plot(x,cleaned_sin)
	plt.xlabel('x')
	plt.ylabel('cleaned_sin')
	plt.title('cleaned_sin vs x')
	#plt.show();
	plt.savefig('cleaned_sin.png')
	plt.close()

driver()



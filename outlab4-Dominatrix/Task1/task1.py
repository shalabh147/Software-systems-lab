import numpy as np 
from scipy.misc import derivative 
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
# import here

def fn_plot1d(fn, x_min, x_max, filename):
    # write your code here
	x=np.linspace(x_min,x_max,200)
	f=np.vectorize(fn)
	plt.plot(x,f(x))
	#plt.show()
	plt.savefig(filename)
	plt.close()
	pass


def nth_derivative_plotter(fn, nth, x_min, x_max, filename):
	# write your code here
	x=np.linspace(x_min,x_max,200)
	f=np.vectorize(fn)
	y=derivative(f,x,dx=0.001,n=nth,order=11)
	plt.plot(x,y)
	if(n==1) : plt.title('%s\'st derivative of input function' % nth)
	elif (n==2) : plt.title('%s\'nd derivative of input function' % nth)
	else : plt.title('%s\'th derivative of input function' % nth)
	#plt.show()
	plt.savefig(filename)
	plt.close()

	pass

def fn_plot2d(fn, x_min, x_max, y_min, y_max, filename):
	# write your code here
	f=np.vectorize(fn)
	ax=plt.axes(projection='3d')
	x=np.linspace(x_min,x_max,100)
	y=np.linspace(y_min,y_max,100)
	(X,Y)=np.meshgrid(x,y)
	Z=f(X,Y)
	ax.plot_surface(X,Y,Z)
	#plt.show()
	plt.savefig(filename)
	plt.close()
	pass

def b(x):
    # write your code here
	return g(abs(x))
	pass

def g(x):
    # write your code here
	return h(2-x)/(h(2-x)+h(x-1))
	pass

def h(x):
	if(x>0):
		return np.exp(-1/(x**2))
	else:
		return 0

def twodsinc(x,y):
    # write your code here
    mag=(x**2+y**2)**(0.5)
    return np.sin(mag)/mag	 
    pass


fn_plot1d(b,-2,2,'fn1plot.png')
bound=1.5*(np.pi)
fn_plot2d(twodsinc,-bound,bound,-bound,bound,'fn2plot.png')
for n in np.arange(10)+1:
	nth_derivative_plotter(b,n,-2,2,'bd_%s' % n )

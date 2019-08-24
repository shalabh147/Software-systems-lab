import tarfile
import os
import sys

if(len(sys.argv) < 3):
    print("Too few arguments")
    exit()

exists = []
notexists = []
for arg in sys.argv[2:]:
    if(os.path.exists(arg)):
        exists.append(arg)
    else:
        notexists.append(arg)

if(len(exists) != 0):
    tar = tarfile.open(sys.argv[1], 'w')
else:
    print("All files are missing")
    exit()

for arg in exists:
    if(os.path.isdir(arg)):
        for root, dirs, files in os.walk(arg, topdown=True):
            for file in files:
                path = os.path.join(os.path.abspath(root), file)
                path = path.replace('/', '-')
                if(path not in tar.getnames()):
                    tar.add(os.path.join(root, file), path)
    elif(os.path.isfile(arg)):
        path = os.path.abspath(arg).replace('/', '-')
        if(path not in tar.getnames()):
            tar.add(arg, os.path.abspath(arg).replace('/', '-'))

print("Successfully compressed")
for file in notexists:
    print(file)

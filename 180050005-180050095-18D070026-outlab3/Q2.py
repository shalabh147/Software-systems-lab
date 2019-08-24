import re
import sys

file = open(sys.argv[1])

freq = {}

for each in file:
    emails = re.findall("\\b(?:[a-zA-Z0-9]+[\._]{0,1})+[a-zA-Z0-9]+@(?:[a-zA-Z0-9]+\.{0,1})+[a-zA-Z]+\\b", each)
    numbers = re.findall("\\b[1-9][0-9]{9}\\b", each)
    for match in emails:
        if match not in freq:
            freq[match] = 1
        else:
            freq[match] = freq[match] + 1
    for match in numbers:
        if match not in freq:
            freq[match] = 1
        else:
            freq[match] = freq[match] + 1

print('my frequency: ', end='')
if sys.argv[2] not in freq:
    myfreq = 0
else:
    myfreq = freq[sys.argv[2]]
print(myfreq)
cheat = False
for i in freq:
    if(freq[i] > myfreq):
        print('Cheater alert!', i, freq[i])
        cheat = True
if not cheat:
    print('It\'s all good yo!')

f = open('04.txt', 'r')

count = 0
for line in f:
    line = line.strip()
    first, second = line.split(',')
    a, b = [int(i) for i in first.split('-')]
    c, d = [int(i) for i in second.split('-')]
    if not (b < c or d < a):
        count += 1

print(count)

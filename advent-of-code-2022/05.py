import numpy as np

f = open('05.txt', 'r')

n = 9
h = 8
crates = []
for i in range(h):
    row = []
    line = f.readline()
    for j in range(n):
        try:
            row.append(line[4*j+1])
        except:
            row.append(' ')

    crates.append(row)

print(crates)
crates = np.transpose(crates)
crates = [list(row)[::-1] for row in crates]
crates = [[i for i in row if not i == ' '] for row in crates]
print(crates)

f.readline()
f.readline()

for line in f:
    line = line.strip()
    split = line.split(' ')
    amount = int(split[1])
    source = int(split[3]) - 1
    target = int(split[5]) - 1

    # print(amount, source, target)
    crates[target] = crates[target] + (crates[source][-amount:])
    crates[source] = crates[source][:-amount]

    # print()
    # print(line)
    # print(crates)

print(crates)
print(''.join([row[-1] for row in crates]))
# print(result)

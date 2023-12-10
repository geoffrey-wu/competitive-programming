f = open('10.txt', 'r')

result = 0
i = 1
reg = 1
indices = [20, 60, 100, 140, 180, 220]
strengths = []
grid = [['.' for _ in range(40)] for _ in range(6)]

for line in f:
    line = line.strip()
    if line == 'noop':
        if i in indices:
            strengths.append(i * reg)
        if (i-1) % 40 + 1 in [reg, reg + 1, reg + 2]:
            grid[(i-1)//40][(i-1) % 40] = '#'
        i += 1

    else:
        _, v = line.split(' ')
        v = int(v)
        if i in indices:
            strengths.append(i * reg)
        if (i-1) % 40 + 1 in [reg, reg + 1, reg + 2]:
            grid[(i-1)//40][(i-1) % 40] = '#'
        i += 1
        if i in indices:
            strengths.append(i * reg)
        if (i-1) % 40 + 1 in [reg, reg + 1, reg + 2]:
            grid[(i-1)//40][(i-1) % 40] = '#'
        i += 1
        reg += v

print(strengths)
print(sum(strengths))
for row in grid:
    print(''.join(row))

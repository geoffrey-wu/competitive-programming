f = open("test.txt", "r")
# f = open('input.txt', 'r')

lines = []
for line in f:
    lines.append([int(s) for s in line.strip().split() if len(s) > 0])
    # lines.append([s for s in line.strip().split() if len(s) > 0])

ans = 0

f = open('02.txt', 'r')

score = 0
for line in f:
    a = line[0]
    b = line[2]

    if (a == 'A' and b == 'Y') or (a == 'B' and b == 'X') or (a == 'C' and b == 'Z'):
        score += 1

    if (a == 'A' and b == 'Z') or (a == 'B' and b == 'Y') or (a == 'C' and b == 'X'):
        score += 2

    if (a == 'A' and b == 'X') or (a == 'B' and b == 'Z') or (a == 'C' and b == 'Y'):
        score += 3

    if b == 'X':
        score += 0
    if b == 'Y':
        score += 3
    if b == 'Z':
        score += 6

print(score)

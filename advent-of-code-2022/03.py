f = open('03.txt', 'r')


def helper(a, b, c):
    for s in a:
        if s in b and s in c:
            return s

    return None


total = 0
for line in f:
    line = line.strip()
    x = f.readline().strip()
    y = f.readline().strip()

    ch = helper(line, x, y)
    ch = ord(ch)
    if ord('a') <= ch <= ord('z'):
        ch = ch - ord('a') + 1
    else:
        ch = ch - ord('A') + 27

    total += ch

print(total)

from collections import Counter


# f = open("test.txt", "r")
f = open("input.txt", "r")

a = []
b = []
result = 0
for line in f:
    line = line.strip().split("   ")
    a.append(int(line[0]))
    b.append(int(line[1]))

c = dict(Counter(b))

for i in a:
    result += i * c.get(i, 0)

# print(sum([abs(int(a[i]) - int(b[i])) for i in range(len(a))]))

print(result)

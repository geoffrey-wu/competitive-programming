from tqdm import tqdm
f = open('11.txt', 'r')

monkeys = [
    [lambda a: a * 13, 19, 5, 6],
    [lambda a: a * a, 7, 5, 0],
    [lambda a: a + 6, 17, 1, 0],
    [lambda a: a + 2, 13, 1, 2],
    [lambda a: a + 3, 11, 3, 7],
    [lambda a: a + 4, 2, 4, 6],
    [lambda a: a + 8, 5, 4, 7],
    [lambda a: a * 7, 3, 2, 3],
]

items = [
    [72, 97],
    [55, 70, 90, 74, 95],
    [74, 97, 66, 57],
    [86, 54, 53],
    [50, 65, 78, 50, 62, 99],
    [90],
    [88, 92, 63, 94, 96, 82, 53, 53],
    [70, 60, 71, 69, 77, 70, 98],
]

counts = [0 for _ in monkeys]

mod = 1
for row in monkeys:
    mod *= row[1]

for _ in tqdm(range(10000)):
    for i in range(len(monkeys)):
        counts[i] += len(items[i])
        for j in items[i]:
            j = monkeys[i][0](j)
            j = j % mod
            # j = j // 3
            if j % monkeys[i][1] == 0:
                items[monkeys[i][2]].append(j)
            else:
                items[monkeys[i][3]].append(j)

        items[i] = []

print(counts)

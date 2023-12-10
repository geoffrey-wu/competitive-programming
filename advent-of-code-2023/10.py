import numpy as np

# f = open("test.txt", "r")
f = open("input.txt", "r")


def pprint(arr):
    for i in range(len(arr)):
        for j in range(len(arr[0])):
            print(f"{arr[i][j]}", end="")
        print()


def transpose(arr):
    temp = [[0 for _ in range(len(arr))] for _ in range(len(arr[0]))]
    for i in range(len(arr)):
        for j in range(len(arr[0])):
            temp[j][i] = arr[i][j]

    return temp


grid = []

result = 0
for line in f:
    line = line.strip()
    grid.append([_ for _ in line])


# transpose grid
grid = list(map(list, zip(*grid)))

distances = []
for i in range(len(grid)):
    distances.append([-1 for _ in range(len(grid[0]))])

x = 0
y = 0

for i in range(len(grid)):
    for j in range(len(grid[0])):
        if grid[i][j] == "S":
            x = i
            y = j


print(x, y)


queue = [(x, y, 0)]
while len(queue) > 0:
    i, j, d = queue.pop(0)

    distances[i][j] = d
    d = d + 1

    if grid[i][j] == "S":
        counter = 0
        if i > 0 and grid[i - 1][j] in ["-", "L", "F"] and distances[i - 1][j] == -1:
            queue.append((i - 1, j, d))
            counter += 1
        if (
            i < len(grid) - 1
            and grid[i + 1][j] in ["-", "J", "7"]
            and distances[i + 1][j] == -1
        ):
            queue.append((i + 1, j, d))
            counter += 1
        if j > 0 and grid[i][j - 1] in ["|", "7", "F"] and distances[i][j - 1] == -1:
            queue.append((i, j - 1, d))
            counter += 1
        if (
            j < len(grid[0]) - 1
            and grid[i][j + 1] in ["|", "L", "J"]
            and distances[i][j + 1] == -1
        ):
            queue.append((i, j + 1, d))
            counter += 1

    if grid[i][j] == "-":
        if i > 0 and grid[i - 1][j] != "." and distances[i - 1][j] == -1:
            queue.append((i - 1, j, d))
        if i < len(grid) - 1 and grid[i + 1][j] != "." and distances[i + 1][j] == -1:
            queue.append((i + 1, j, d))

    if grid[i][j] == "|":
        if j > 0 and grid[i][j - 1] != "." and distances[i][j - 1] == -1:
            queue.append((i, j - 1, d))
        if j < len(grid[0]) - 1 and grid[i][j + 1] != "." and distances[i][j + 1] == -1:
            queue.append((i, j + 1, d))

    if grid[i][j] == "7":
        if i > 0 and grid[i - 1][j] != "." and distances[i - 1][j] == -1:
            queue.append((i - 1, j, d))
        if j < len(grid[0]) - 1 and grid[i][j + 1] != "." and distances[i][j + 1] == -1:
            queue.append((i, j + 1, d))

    # north, east
    if grid[i][j] == "L":
        if i < len(grid) - 1 and grid[i + 1][j] != "." and distances[i + 1][j] == -1:
            queue.append((i + 1, j, d))
        if j > 0 and grid[i][j - 1] != "." and distances[i][j - 1] == -1:
            queue.append((i, j - 1, d))

    if grid[i][j] == "J":
        if i > 0 and grid[i - 1][j] != "." and distances[i - 1][j] == -1:
            queue.append((i - 1, j, d))
        if j > 0 and grid[i][j - 1] != "." and distances[i][j - 1] == -1:
            queue.append((i, j - 1, d))

    if grid[i][j] == "F":
        if i < len(grid) - 1 and grid[i + 1][j] != "." and distances[i + 1][j] == -1:
            queue.append((i + 1, j, d))
        if j < len(grid[0]) - 1 and grid[i][j + 1] != "." and distances[i][j + 1] == -1:
            queue.append((i, j + 1, d))


dumb = [
    ["." if distances[i][j] == -1 else "#" for j in range(len(grid[i]))]
    for i in range(len(grid))
]

print(len(grid), "m", len(dumb[0]))

result = 0


def flood_dumb(x, y):
    queue = [(x, y)]
    while len(queue) > 0:
        i, j = queue.pop(0)

        if i > 0 and dumb[i - 1][j] == ".":
            dumb[i - 1][j] = "X"
            queue.append((i - 1, j))

        if i < len(grid) - 1 and dumb[i + 1][j] == ".":
            dumb[i + 1][j] = "X"
            queue.append((i + 1, j))

        if j > 0 and dumb[i][j - 1] == ".":
            dumb[i][j - 1] = "X"
            queue.append((i, j - 1))

        if j < len(grid[0]) - 1 and dumb[i][j + 1] == ".":
            dumb[i][j + 1] = "X"
            queue.append((i, j + 1))

        if i > 0 and j > 0 and dumb[i - 1][j - 1] == ".":
            dumb[i - 1][j - 1] = "X"
            queue.append((i - 1, j - 1))

        if i < len(grid) - 1 and j < len(grid[0]) - 1 and dumb[i + 1][j + 1] == ".":
            dumb[i + 1][j + 1] = "X"
            queue.append((i + 1, j + 1))

        if i > 0 and j < len(grid[0]) - 1 and dumb[i - 1][j + 1] == ".":
            dumb[i - 1][j + 1] = "X"
            queue.append((i - 1, j + 1))

        if i < len(grid) - 1 and j > 0 and dumb[i + 1][j - 1] == ".":
            dumb[i + 1][j - 1] = "X"
            queue.append((i + 1, j - 1))


for i in range(len(grid)):
    flood_dumb(i, 0)
    flood_dumb(i, len(grid[0]) - 1)

for j in range(len(grid[0])):
    flood_dumb(0, j)
    flood_dumb(len(grid) - 1, j)


count = 0
for i in range(len(grid)):
    for j in range(len(grid[0])):
        if dumb[i][j] != "#":
            continue

        if i > 0 and dumb[i - 1][j] == ".":
            count += 1
        elif i < len(grid) - 1 and dumb[i + 1][j] == ".":
            count += 1
        elif j > 0 and dumb[i][j - 1] == ".":
            count += 1
        elif j < len(grid[0]) - 1 and dumb[i][j + 1] == ".":
            count += 1
        elif i > 0 and j > 0 and dumb[i - 1][j - 1] == ".":
            count += 1
        elif i < len(grid) - 1 and j < len(grid[0]) - 1 and dumb[i + 1][j + 1] == ".":
            count += 1
        elif i > 0 and j < len(grid[0]) - 1 and dumb[i - 1][j + 1] == ".":
            count += 1
        elif i < len(grid) - 1 and j > 0 and dumb[i + 1][j - 1] == ".":
            count += 1

print(count)

grid = list(map(list, zip(*grid)))
distances = list(map(list, zip(*distances)))
dumb = list(map(list, zip(*dumb)))

other = 0
for i in range(len(dumb)):
    for j in range(len(dumb[0])):
        if dumb[i][j] == "X":
            other += 1
        if dumb[i][j] == ".":
            result += 1

# pprint(grid)
# pprint(distances)
pprint(dumb)
print(max([max(_) for _ in distances]))
print(len(grid) * len(grid[0]) - other - count)
print(result)

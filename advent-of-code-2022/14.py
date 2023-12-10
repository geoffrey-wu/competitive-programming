import numpy as np

PART_2 = True


def simulate(x, y):
    if y+1 >= len(grid):
        return False

    if grid[y+1][x] == '.':
        grid[y+1][x] = 'o'
        grid[y][x] = '.'

    elif grid[y+1][x] in ['#', 'o']:
        if x == 0:
            return False
        elif grid[y+1][x-1] in ['#', 'o']:
            if x == len(grid[0]) - 1:
                return False
            elif grid[y+1][x+1] in ['#', 'o']:
                return (x, y)
            else:
                grid[y+1][x+1] = 'o'
                grid[y][x] = '.'
                return simulate(x+1, y+1)
        else:
            grid[y+1][x-1] = 'o'
            grid[y][x] = '.'
            return simulate(x-1, y+1)

    return simulate(x, y+1)


# f = open('test.txt', 'r')
f = open('input.txt', 'r')

result = 0
min_x = 10000000
min_y = 10000000
max_x = 0
max_y = 0
paths = []
for line in f:
    line = line.strip()
    arr = line.split(' -> ')
    arr = [[int(a) for a in i.split(',')] for i in arr]
    min_x = min(min_x, min([i[0] for i in arr]))
    min_y = min(min_y, min([i[1] for i in arr]))
    max_x = max(max_x, max([i[0] for i in arr]))
    max_y = max(max_y, max([i[1] for i in arr]))
    paths.append(arr)

if PART_2:
    min_x = 500 - (max_y + 2)
    max_x = 500 + (max_y + 2)

print(min_x)
print(min_y)
print(max_x)
print(max_y)

grid = [['.' for i in range(max_x - min_x + 1)] for _ in range(max_y + 1 + 2)]
grid[0][500 - min_x] = '+'
grid[-1] = ['#' for i in range(len(grid[-1]))]

for row in paths:
    for i in range(len(row) - 1):
        start_x, start_y = row[i]
        end_x, end_y = row[i+1]
        if start_x == end_x:
            if start_y < end_y:
                for y in range(start_y, end_y + 1):
                    grid[y][start_x - min_x] = '#'
            else:
                for y in range(end_y, start_y + 1):
                    grid[y][start_x - min_x] = '#'
        elif start_x < end_x:
            for x in range(start_x, end_x + 1):
                grid[start_y][x - min_x] = '#'
        else:
            for x in range(end_x, start_x + 1):
                grid[start_y][x - min_x] = '#'

# for row in grid:
#     print(' '.join(row))

if PART_2:
    while not simulate(500 - min_x, 0) == (500 - min_x, 0):
        result += 1
else:
    while simulate(500 - min_x, 0):
        result += 1

for row in grid:
    print(' '.join(row))

if PART_2:
    result += 1

print(result)

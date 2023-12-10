f = open('12.txt', 'r')

grid = []
sx, sy = 0, 0
ex, ey = 0, 0
for line in f:
    line = line.strip()
    if 'S' in line:
        sy = line.index('S')
        sx = len(grid)
        line = line.replace('S', 'a')

    if 'E' in line:
        ey = line.index('E')
        ex = len(grid)
        line = line.replace('E', 'z')

    grid.append([[ord(c) - ord('a'), 999999999, False] for c in line])

print(sx, sy)
print(ex, ey)

# grid[sx][sy][1] = 0
grid[ex][ey][1] = 0
# stack = [(sx, sy)]
stack = [(ex, ey)]
while len(stack):
    x, y = min(stack, key=lambda a: grid[a[0]][a[1]][1])
    stack.remove((x, y))
    # print(x, y)
    grid[x][y][2] = True
    for dx, dy in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
        nx, ny = x + dx, y + dy
        if nx < 0 or ny < 0 or nx >= len(grid) or ny >= len(grid[0]):
            continue

        if grid[nx][ny][2] == True:
            continue

        # if grid[nx][ny][0] - grid[x][y][0] > 1:
        if grid[x][y][0] - grid[nx][ny][0] > 1:
            continue

        if grid[nx][ny][1] > grid[x][y][1] + 1:
            grid[nx][ny][1] = grid[x][y][1] + 1
            stack.append((nx, ny))

# print(grid[ex][ey][1])

m = 999999999
for i in range(len(grid)):
    for j in range(len(grid[i])):
        if grid[i][j][0] == 0 and grid[i][j][2] == True:
            print(i, j, grid[i][j][1])
            m = min(m, grid[i][j][1])

print(m)

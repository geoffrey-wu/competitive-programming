f = open('08.txt', 'r')

grid = []
for line in f:
    grid.append([int(i) for i in line.strip()])

n = len(grid)

total = 0
m = 0
for i in range(1, n - 1):
    for j in range(1, n - 1):
        val = grid[i][j]

        left = 1
        while grid[i][j - left] < val:
            left += 1
            if j - left < 0:
                left -= 1
                break

        right = 1
        while grid[i][j + right] < val:
            right += 1
            if j + right > n - 1:
                right -= 1
                break

        up = 1
        while grid[i - up][j] < val:
            up += 1
            if i - up < 0:
                up -= 1
                break

        down = 1
        while grid[i + down][j] < val:
            down += 1
            if i + down > n - 1:
                down -= 1
                break

        # print(i, j, left, right, up, down, left * right * up * down)
        m = max([m, left * right * up * down])

print(m)

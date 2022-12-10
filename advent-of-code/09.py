f = open('09.txt', 'r')
visited = set([(0, 0)])

pos = [[0, 0] for _ in range(10)]
# pos = [[5, -2], [4, -1], [3, -1], [2, 0], [2, 1], [1, 1], [0, 0], [0, 0], [0, 0], [0, 0]]


def update_tail(x, y, tx, ty):
    sx = 1 if x > tx else -1
    sy = 1 if y > ty else -1

    if abs(x - tx) == 2 and abs(y - ty) == 2:
        tx = x - sx
        ty = y - sy
    elif abs(x - tx) == 2:
        tx = x - sx
        ty = y
    elif abs(y - ty) == 2:
        ty = y - sy
        tx = x

    # if x - tx >= 2:
    #     tx = x - 1
    #     ty = y
    # elif x - tx <= -2:
    #     tx = x + 1
    #     ty = y
    # elif y - ty >= 2:
    #     ty = y - 1
    #     tx = x
    # elif y - ty <= -2:
    #     ty = y + 1
    #     tx = x

    return x, y, tx, ty


print(update_tail(4, -2, 2, 0))


def update_pos():
    for i in range(len(pos) - 1):
        a, b, c, d = update_tail(pos[i][0], pos[i][1], pos[i+1][0], pos[i+1][1])
        pos[i][0] = a
        pos[i][1] = b
        pos[i+1][0] = c
        pos[i+1][1] = d

    # print(pos)
    dist = abs(pos[-2][0] - pos[-1][0]) + abs(pos[-2][1] - pos[-1][1])
    if dist > 2:
        # pass
        print(pos)
        # print(len(pos), a, b, c, d)
    visited.add((pos[-1][0], pos[-1][1]))


for line in f:
    line = line.strip()
    dir, amount = line.split(' ')
    amount = int(amount)
    for i in range(amount):
        if dir == 'R':
            pos[0][0] += 1
            update_pos()

        if dir == 'L':
            pos[0][0] -= 1
            update_pos()

        if dir == 'U':
            pos[0][1] += 1
            update_pos()

        if dir == 'D':
            pos[0][1] -= 1
            update_pos()

# print(visited)
print(len(visited))

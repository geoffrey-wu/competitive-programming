def calculate_size(dir):
    # print(dir)
    total = 0
    for file in tree[dir]:
        if file[0] == 'dir':
            a = dir + '/' + file[1]
            # print(file, tree[dir])
            if a in sizes:
                total += sizes[a]
            elif a in tree:
                total += calculate_size(a)
        else:
            total += int(file[0])

    # print(total)
    sizes[dir] = total
    return total


f = open('07.txt', 'r')

tree = {}
sizes = {}
pwd_stack = []
for line in f:
    line = line.strip()
    if line[:4] == '$ cd':
        pwd = line[5:]
        if pwd == '..':
            pwd_stack.pop()
        else:
            pwd_stack = pwd_stack + [pwd]
        actual_pwd = '/'.join(pwd_stack)
    elif not line[:4] == '$ ls':
        size, name = line.split()
        tree[actual_pwd] = tree.get(actual_pwd, []) + [[size, name]]


# print(tree)
# print()
# print(tree['vbzr'])
# print()
# print(tree['rgdp'])
# print()
# print(tree['blhstw'])
# print()
total2 = 0
for dir in tree:
    # print(dir, tree[dir])
    size = calculate_size(dir)
    if size < 100000:
        total2 += size

print(sizes['/'])
min_dir = ''
min = 10000000000
for dir in sizes:
    if sizes[dir] >= 528671 and sizes[dir] < min:
        min = sizes[dir]
        min_dir = dir

print(min_dir, min)
print(total2)

import re

f = open('test.txt', 'r')
# f = open('input.txt', 'r')


def closest(start):
    visited_copy = []
    for n in valves:
        valves[n]['distance'] = None
        valves[n]['previous'] = None

    stack = [start]
    valves[start]['distance'] = 0
    while len(stack) > 0:
        current = stack[0]
        stack = stack[1:]
        visited_copy.append(current)
        for n in valves[current]['neighbors']:
            if n in visited_copy:
                continue

            if not valves[n]['distance'] or valves[n]['distance'] > valves[current]['distance'] + 1:
                valves[n]['distance'] = valves[current]['distance'] + 1
                valves[n]['previous'] = current
                stack.append(n)


def iterate(current, t, result, visited, opened):
    visited_copy = [_ for _ in visited]
    opened_copy = [_ for _ in opened]
    if t == 30 or set(opened) == set([n for n in valves if not valves[n]['rate'] == 0]):
        if result > 1600:
            print(result)
        return result

    visited_copy.append(current)
    if not current in opened_copy and valves[current]['rate'] > 0:
        opened_copy.append(current)
        result += valves[current]['rate'] * (30 - t)
        print(f'time {t} opened', current, valves[current]['rate'], result)
        return iterate(current, t + 1, result, visited_copy, opened_copy)
    else:
        # find best unopened
        closest(current)
        target = max([n for n in valves if not n in opened and not n == current],
                     key=lambda n: valves[n]['rate'] * (30 - t - valves[n]['distance']))

        target2 = max([n for n in valves if not n in opened and not n == current and not n == target],
                      key=lambda n: valves[n]['rate'] * (30 - t - valves[n]['distance']))

        # print(target, target2, current)
        while not valves[target]['previous'] == current:
            target = valves[target]['previous']

        if not target2 == None:
            while not valves[target2]['previous'] == current:
                target2 = valves[target2]['previous']

            if not target == target2:
                return max(iterate(target, t + 1, result, visited_copy, opened_copy), iterate(target2, t + 1, result, visited_copy, opened_copy))
            else:
                return iterate(target, t + 1, result, visited_copy, opened_copy)
        else:
            return iterate(target, t + 1, result, visited_copy, opened_copy)


result = 0
valves = {}
for line in f:
    line = line.strip()
    valve = line[6:8]
    rate = int(re.findall(r'\d+', line)[0])
    neighbors = re.findall(r'[A-Z]{2}', line[10:])
    valves[valve] = {'rate': rate, 'neighbors': neighbors, 'opened': False, 'visited': False}

# current = 'AA'
# for t in range(1, 30 + 1):
#     valves[current]['visited'] = True
#     if not valves[current]['opened'] and valves[current]['rate'] > 0:
#         valves[current]['opened'] = True
#         result += valves[current]['rate'] * (30 - t)
#         print(f'time {t} opened', current, valves[current]['rate'], result)
#     else:
#         # find best unopened
#         closest(current)
#         target = max([n for n in valves if not valves[n]['opened'] and not n == current],
#                      key=lambda n: valves[n]['rate'] * (30 - t - valves[n]['distance']))

#         while not valves[target]['previous'] == current:
#             target = valves[target]['previous']

#         current = target

# print(result)

print(iterate('AA', 1, 0, [], []))

print(valves)
# closest('AA')
# print([[n, valves[n]['distance']] for n in valves])

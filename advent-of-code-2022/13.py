def compare(a, b):
    if len(a) == 0 and len(b) == 0:
        return 0
    if len(a) == 0:
        return 1
    if len(b) == 0:
        return -1

    if type(a[0]) == type(1):
        if type(b[0]) == type([1]):
            a[0] = [a[0]]
            return compare(a, b)

        else:
            if a[0] < b[0]:
                return 1
            elif a[0] > b[0]:
                return -1
            else:
                return compare(a[1:], b[1:])
    else:
        if type(b[0]) == type([1]):
            x = compare(a[0], b[0])
            if not x == 0:
                return x
            else:
                return compare(a[1:], b[1:])
        else:
            b[0] = [b[0]]
            return compare(a, b)


def bubbleSort(arr):
    n = len(arr)
    # optimize code, so if the array is already sorted, it doesn't need
    # to go through the entire process
    swapped = False
    # Traverse through all array elements
    for i in range(n-1):
        # range(n) also work but outer loop will
        # repeat one time more than needed.
        # Last i elements are already in place
        for j in range(0, n-i-1):

            # traverse the array from 0 to n-i-1
            # Swap if the element found is greater
            # than the next element
            c, d = eval(str(arr[j])), eval(str(arr[j+1]))
            if compare(c, d) == -1:
                swapped = True
                arr[j], arr[j + 1] = arr[j + 1], arr[j]

        if not swapped:
            # if we haven't needed to make a single swap, we
            # can just exit the main loop.
            return


f = open('13.txt', 'r')

result = 0
s = 0
lines = f.readlines()
# for i in range(len(lines) // 3):
#     x = eval(lines[i * 3])
#     y = eval(lines[i * 3 + 1])

#     if compare(x, y) == 1:
#         s += i + 1
#         print(i + 1)

lines = [line.strip() for line in lines]
lines = [eval(line) for line in lines if len(line) > 0]
print(lines.index([[2]]))
print(lines)

bubbleSort(lines)
# lines = [str(line) for line in lines]
print(lines)

print(lines.index([[2]]) + 1)
print(lines.index([[6]]) + 1)
# print(s)

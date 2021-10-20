import time

start = time.time()


def checkPalindrome(a):
    a = str(a)
    return a == a[::-1]


maximum = 100000000
n = 2
answers = []

while n*(n+1)*(2*n+1) <= 6*maximum:
    total = 0
    i = 0
    while total <= maximum:
        i += 1
        total = sum([j*j for j in range(i, i + n)])
        if total > maximum:
            break
        if checkPalindrome(total):
            answers.append(total)

    n += 1

answers = set(answers)
print(answers)
print(sum(answers))

print(time.time() - start)

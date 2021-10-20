import time

start = time.time()

def search(arr, n):
    l = 0
    r = len(arr)

    while (l < r):
        midpoint = (l + r)//2
        if arr[midpoint] == n: return True
        if arr[midpoint] < n: r = midpoint - 1
        else: l = midpoint + 1
    
    return False


def isPrime(n):
    if n == 1: return False
    if n == 2: return True
    if n % 2 == 0: return False
    for i in range(3, 1+int(n**0.5), 2):
        if n % i == 0:
            return False

    return True

maximum = 9999
primes = []
for i in range(maximum):
    if isPrime(i):
        primes.append(i)

print(time.time() - start)

primes2 = [True]*(10000*maximum)
for i in range(len(primes2)):
    if i == 0 or i == 1: continue
    if not primes2[i]: continue
    for j in range(2*i, len(primes2), i):
        primes2[j] = False

primes2[0] = False
primes2[1] = False

print(time.time() - start)

def works(arr):
    a = str(arr[0])
    for i in range(1, len(arr)):
        b = str(arr[i])
        if int(a + b) < 10000*maximum:
            if not primes2[int(a+b)]:
                return False
        else:
            if not isPrime(int(a + b)):
                return False
        
        if int(b + a) < 10000*maximum:
            if not primes2[int(b+a)]:
                return False
        else:
            if not isPrime(int(b + a)):
                return False
        
        # if not search(primes2, int(a + b)) or not search(primes2, int(b + a)):
            # return False
    return True

for i in range(len(primes)):
    for j in range(i):
        if not works([primes[j], primes[i]]): continue
        for k in range(j):
            if not works([primes[k], primes[j], primes[i]]): continue
            for l in range(k):
                if not works([primes[l], primes[k], primes[j], primes[i]]): continue
                for m in range(l):
                    if not works([primes[m], primes[l], primes[k], primes[j], primes[i]]): continue
                    print(primes[i], primes[j], primes[k], primes[l], primes[m])

print(time.time() - start)
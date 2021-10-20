import time

start = time.time()


def works(arr):
    if min(arr[:5]) != arr[0]:
        return False
    s = arr[0] + arr[5] + arr[6]
    return s == arr[1] + arr[6] + arr[7] and s == arr[2] + arr[7] + arr[8] and s == arr[3] + arr[8] + arr[9] and s == arr[4] + arr[9] + arr[5]


def permute(arr, sofar):
    if len(arr) == 0:
        if works(sofar):
            print(sofar)
            for i in range(len(sofar)):
                sofar[i] = str(sofar[i])
            print(sofar[0] + sofar[5] + sofar[6] + sofar[1] + sofar[6] + sofar[7] + sofar[2] +
                  sofar[7] + sofar[8] + sofar[3] + sofar[8] + sofar[9] + sofar[4] + sofar[9] + sofar[5])
    for i in range(len(arr)):
        a = arr[0]
        arr[0] = arr[i]
        arr[i] = a
        permute(arr[1:], sofar + [arr[0]])


permute([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [])

print(time.time() - start)

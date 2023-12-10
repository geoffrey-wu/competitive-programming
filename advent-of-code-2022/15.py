from tqdm import tqdm
f = open('test.txt', 'r')
# f = open('input.txt', 'r')


def mdist(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)


# sensors = [
#     [2, 18],
#     [9, 16],
#     [13, 2],
#     [12, 14],
#     [10, 20],
#     [14, 17],
#     [8, 7],
#     [2, 0],
#     [0, 11],
#     [20, 14],
#     [17, 20],
#     [16, 7],
#     [14, 3],
#     [20, 1],
# ]

# beacons = [
#     [-2, 15],
#     [10, 16],
#     [15, 3],
#     [10, 16],
#     [10, 16],
#     [10, 16],
#     [2, 10],
#     [2, 10],
#     [2, 10],
#     [25, 17],
#     [21, 22],
#     [15, 3],
#     [15, 3],
#     [15, 3],
# ]

sensors = [
    [325337, 2568863],
    [3988825, 837820],
    [1611311, 2053174],
    [101890, 3940049],
    [3962702, 2558425],
    [2957890, 2160813],
    [3907456, 3325610],
    [3354177, 3435919],
    [3997379, 3071868],
    [145143, 1714962],
    [611563, 3148864],
    [3080405, 3904777],
    [644383, 10732],
    [3229566, 1694167],
    [1600637, 3984884],
    [2959765, 2820860],
    [2235330, 3427797],
    [2428996, 210881],
    [369661, 687805],
    [3558476, 2123614],
    [3551529, 2825104],
    [64895, 3577],
    [3079531, 1538659],
]

beacons = [
    [-518661, 2000000],
    [4305648, 2127118],
    [2827226, 1579510],
    [955472, 3457514],
    [4226981, 2604726],
    [2827226, 1579510],
    [3696221, 3226373],
    [3696221, 3226373],
    [3696221, 3226373],
    [-518661, 2000000],
    [955472, 3457514],
    [3696221, 3226373],
    [364635, -294577],
    [2827226, 1579510],
    [955472, 3457514],
    [2491502, 2897876],
    [2491502, 2897876],
    [2827226, 1579510],
    [364635, -294577],
    [4305648, 2127118],
    [3696221, 3226373],
    [364635, -294577],
    [2827226, 1579510],
]

dists = [
    mdist(sensors[i][0], sensors[i][1], beacons[i][0], beacons[i][1]) for i in range(len(sensors))
]

# y = 2000000
for y in tqdm(range(4000000 + 1)):
    # for y in [2000000]:
    disallowed = []
    for i in range(len(sensors)):
        # dx = mdist(sensors[i][0], sensors[i][1], beacons[i][0], beacons[i][1]) - abs(sensors[i][1] - y)
        dx = dists[i] - abs(sensors[i][1] - y)
        if dx > 0:
            disallowed.append([sensors[i][0] - dx, sensors[i][0] + dx])

    # print(disallowed)
    disallowed = sorted(disallowed, key=lambda x: x[0])
    for i in range(len(disallowed) - 1):
        if disallowed[i][1] >= disallowed[i + 1][0]:
            disallowed[i + 1][0] = disallowed[i][0]
            disallowed[i + 1][1] = max(disallowed[i][1], disallowed[i + 1][1])
            disallowed[i] = None

    disallowed = [x for x in disallowed if x is not None]
    if len(disallowed) > 1 or disallowed[0][0] > 0 or disallowed[0][1] < 4000000:
        print(disallowed, y)

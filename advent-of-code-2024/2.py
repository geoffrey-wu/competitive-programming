# f = open("test.txt", "r")
f = open("input.txt", "r")

result = 0
reports = []
for line in f:
    reports.append([int(i) for i in line.strip().split(" ")])


def is_safe(report):
    works = True
    for i in range(len(report) - 1):
        if abs(report[i] - report[i + 1]) not in [1, 2, 3]:
            works = False
            break

    increasing = True
    decreasing = True
    for i in range(len(report) - 1):
        if report[i] > report[i + 1]:
            increasing = False
        if report[i] < report[i + 1]:
            decreasing = False

    return works and (increasing or decreasing)


for report in reports:
    for i in range(len(report)):
        if is_safe(report[:i] + report[i + 1 :]):
            result += 1
            break

print(result)

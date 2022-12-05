f = open('01.txt', 'r')

current_inventory = 0
max_inventory = []
for line in f:
    if line == '\n':
        if len(max_inventory) < 3:
            max_inventory.append(current_inventory)
        else:
            max_inventory[0] = max([current_inventory, max_inventory[0]])
            max_inventory.sort()

        current_inventory = 0
    else:
        current_inventory += int(line)

print(max_inventory)
print(sum(max_inventory))

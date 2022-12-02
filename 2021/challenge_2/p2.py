import re

regex = re.compile(r'(.*)-(.*) (.): (.*)')
valid_pws = 0

with open('input.txt') as input:
    for line in input:
        groups = regex.match(line)
        pos_1 = int(groups[1])
        pos_2 = int(groups[2])
        letter = groups[3]
        password = groups[4]
        if (password[pos_1-1] == letter) != (password[pos_2-1] == letter):
            valid_pws += 1
    print(valid_pws)

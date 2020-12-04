import re

regex = re.compile(r'(.*)-(.*) (.): (.*)')
valid_pws = 0

with open('input.txt') as input:
    for line in input:
        groups = regex.match(line)
        min, max, letter, password = groups[1], groups[2], groups[3], groups[4]
        n_letters = password.count(letter)
        if n_letters >= int(min) and n_letters <= int(max):
            valid_pws += 1
    print(valid_pws)

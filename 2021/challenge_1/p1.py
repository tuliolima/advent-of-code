numbers = {}
with open('input.txt') as input:
    for line in input:
        number = int(line)
        if number in numbers:
            print(number * numbers[number])
        else:
            complement = 2020 - number
            numbers[complement] = number

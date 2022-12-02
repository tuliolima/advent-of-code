numbers = {}
with open('input.txt') as input:
    for line in input:
        input_number = int(line)
        for value in numbers.values():
            sum_2 = input_number + value
            if sum_2 in numbers:
                print(input_number * value * numbers[sum_2])
                break
        complement = 2020 - input_number
        numbers[complement] = input_number

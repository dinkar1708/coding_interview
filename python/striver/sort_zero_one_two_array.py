def sort(numbers):
    """
    Sort zero one and two array 
    Time - o(2n)= o(n) but two pass
    Space - o(1)
    """
    print("Count input in ", numbers)
    zero_count = 0
    one_count = 0
    two_count = 0
    for num in numbers:
        if num == 0:
            zero_count += 1
        elif num == 1:
            one_count += 1
        else:
            two_count += 1
    print("Zero count ", zero_count, "One count ",
          one_count, "Two count", two_count)
    for i in range(zero_count):
        numbers[i] = 0
    for i in range(zero_count, zero_count+one_count):
        numbers[i] = 1
    for i in range(zero_count+one_count, zero_count+one_count+two_count):
        numbers[i] = 2
    return numbers


print(sort([2, 0, 2, 1, 1, 0]))

"""
Count input in  [2, 0, 2, 1, 1, 0]
Zero count  2 One count  2 Two count 2
[0, 0, 1, 1, 2, 2]
"""

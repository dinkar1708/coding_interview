def solution_(N):
    """
    https://app.codility.com/demo/results/trainingWH96Z8-RSB/

    The Idea is, until number is event increase the count
    until number is odd keep state of longest number and reset the current longest
    :param n =
    :return:

    Test - 9: below is the steps
    1001  - 9
    0001
    0001 == 1

    100 - 4
    001
    000 == 0

    1 - 1
    1
    1 == 1

    Test -
    162
    0b10100010
    81
    0b1010001
    val & 1 = 1
    current_binary_gap set to zero
    40
    0b101000
    current_binary_gap
    1
    20
    0b10100
    current_binary_gap
    2
    10
    0b1010
    current_binary_gap
    3
    5
    0b101
    val & 1 = 1
    current_binary_gap set to zero
    2
    0b10
    current_binary_gap
    1
    1
    0b1
    val & 1 = 1
    current_binary_gap set to zero
    Sol 3

    Process finished with exit code 0


    """
    longest_binary_gap = 0
    current_binary_gap = -1
    val = N
    while val != 0:
        print(val)
        print(bin(val))
        if (val & 1) == 1:
            # this means number is odd
            print("val & 1 = 1 ")
            if longest_binary_gap < current_binary_gap:
                longest_binary_gap = current_binary_gap
            current_binary_gap = 0
            print("current_binary_gap set to zero ")
        elif current_binary_gap != -1:
            # else here means number is even
            # print("found....")
            current_binary_gap = current_binary_gap + 1
            print("current_binary_gap ")
            print(current_binary_gap)
        val = val >> 1

    return longest_binary_gap


# https://app.codility.com/demo/results/trainingYFYXH8-JYY/
def solution(N):
    """
    DINAKAR
    convert to binary and find gap by just knowing the entry of 1 and remember the last index of 1
    also remember the longest in each iteration when 1 is encountered
    """
    longest = -1
    last_longest_index = 0
    ar = bin(N).replace("0b", "")
    print(ar)
    for i in range(len(ar)):
        if ar[i] == "1":
            print("1 found...at index " + str(i))
            longest = max(longest, i - last_longest_index - 1)
            last_longest_index = i
            print("current longest " + str(longest))
    return 0 if longest == -1 else longest


result = solution(1041)
print("Sol " + str(result))

"""
10000010001
1 found...at index 0
current longest -1
1 found...at index 6
current longest 5
1 found...at index 10
current longest 5
Sol 5
"""

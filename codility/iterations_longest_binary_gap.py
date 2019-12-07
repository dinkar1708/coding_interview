def solution(N):
    """
    https://app.codility.com/demo/results/trainingWH96Z8-RSB/

    Idea is until number is event increase the count
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


result = solution(162)
print("Sol " + str(result))

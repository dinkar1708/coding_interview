# https://app.codility.com/demo/results/trainingXCFCZW-6HZ/
def solution(A):
    """
    DINAKAR
    Pre process array and create max peeks
    Idea is to check for all flags from start and for each number check by jumping to the max peeks
    When can not place that is the last dead line where can not place
    """
    len_ar = len(A)
    peeks = [0] * len_ar
    next_peek = len_ar
    peeks[len_ar - 1] = next_peek
    # mark index of array in peeks array to indicate that as a next peek
    print("peeks making...")
    for i in range(len_ar - 2, 0, -1):
        if A[i - 1] < A[i] and A[i + 1] < A[i]:
            next_peek = i
        peeks[i] = next_peek
        print(peeks)
    peeks[0] = next_peek

    current_guess = 0
    next_guess = 0
    # square root n time(√n)
    # check for all starting from 0
    while can_place_flag(peeks, next_guess):
        print("Flags to place " + str(next_guess))
        current_guess = next_guess
        next_guess += 1
        print()
    # total √n*√n = n time
    return current_guess


def can_place_flag(peeks, flags_to_place):
    """

    :param peeks:
    :param flags_to_place:
    :return:
    """
    current_pos = 1 - flags_to_place
    # square root n time(√n)
    # 1->1    2->2+2 ,  3-> 3+3+3
    for i in range(flags_to_place):
        if current_pos + flags_to_place > len(peeks) - 1:
            print("can not place..." + str(current_pos + flags_to_place))
            return False
        current_pos = peeks[current_pos + flags_to_place]
        print("can_place_flag() current_pos " + str(current_pos))
    return current_pos < len(peeks)


if __name__ == '__main__':
    result = solution([1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2])
    print("")
    print("Solution " + str(result))
"""
peeks making...
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 12]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 12]
[0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10, 12]
[0, 0, 0, 0, 0, 0, 0, 10, 10, 10, 10, 12]
[0, 0, 0, 0, 0, 0, 10, 10, 10, 10, 10, 12]
[0, 0, 0, 0, 0, 5, 10, 10, 10, 10, 10, 12]
[0, 0, 0, 0, 5, 5, 10, 10, 10, 10, 10, 12]
[0, 0, 0, 3, 5, 5, 10, 10, 10, 10, 10, 12]
[0, 0, 3, 3, 5, 5, 10, 10, 10, 10, 10, 12]
[0, 1, 3, 3, 5, 5, 10, 10, 10, 10, 10, 12]
Flags to place 0

can_place_flag() current_pos 1
Flags to place 1

can_place_flag() current_pos 1
can_place_flag() current_pos 3
Flags to place 2

can_place_flag() current_pos 1
can_place_flag() current_pos 5
can_place_flag() current_pos 10
Flags to place 3

can_place_flag() current_pos 1
can_place_flag() current_pos 5
can_place_flag() current_pos 10
can not place...14

Solution 3
"""
import math
import sys


def solution1(N):
    """
    Problem Statement at -
    https://app.codility.com/demo/results/trainingESED4A-BFV/
    Codility 100%

    Idea is check for all combination of a and b, and minimize the perimeter(find the minimal perimeter of any rectangle whose area equals N)
    We already know that a*b = N so we have to just check up to square root of number

    For N = 30
    (1, 30), with a perimeter of 62,
    (2, 15), with a perimeter of 34,
    (3, 10), with a perimeter of 26
    (5, 6), with a perimeter of 22

    :param N:
    :return:
    """
    if N <= 0:
        return 0
    perimeter_minimum = sys.maxsize

    for a in range(1, int(math.sqrt(N) + 1)):
        if N % a == 0:
            b = int(N / a)
            print("A = " + str(a) + ", B = " + str(b))
            # minimize the perimeter
            perimeter_minimum = int(min(a + b, perimeter_minimum))
            print("perimeter_minimum = " + str(perimeter_minimum))

    # return the area of rectangle
    return perimeter_minimum * 2


if __name__ == '__main__':
    result = solution1(30)
    # result = solution1(35)
    print("")
    print("Solution " + str(result))

"""
Solution1

Example1
A = 1, B = 30
perimeter_minimum = 31
A = 2, B = 15
perimeter_minimum = 17
A = 3, B = 10
perimeter_minimum = 13
A = 5, B = 6
perimeter_minimum = 11

Solution 22

Example2- 
A = 1, B = 35
perimeter_minimum = 36
A = 5, B = 7
perimeter_minimum = 12

Solution 24

"""


def solution(N):
    """
    Problem Statement can be found here-
    https://app.codility.com/demo/results/trainingAZVCGD-3EA/
    Codility 100%

    Idea - As we saw in solution 1, for minimum area a and b will always be the closest to each other so we should start checking from the square root
    and as soon as first divisor a is found we are done. Find the next side calculate area and return

    Note-
    Finding a - if we find the largest width of rectangle, second side should be minimum as b = N/a
    So to find the largest width we should start from square root of rectangle
    """
    if N <= 0:
        return 0

    # for each number in squ1re root
    for a in range(int(math.sqrt(N)), 0, -1):
        # find the factor
        if N % a == 0:
            # largest side a is found
            # second factor is just divide by the number with factor we know
            # minimum side of b
            b = int(N / a)
            print("A = " + str(a) + ", B = " + str(b))
            # find the area of rectangle
            return 2 * (a + b)


if __name__ == '__main__':
    result = solution(30)
    # result = solution(35)
    print("")
    print("Solution " + str(result))

"""
Example1-
A = 5, B = 6

Solution 22

Example2-

A = 5, B = 7

Solution 24
"""

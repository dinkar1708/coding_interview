# https://app.codility.com/demo/results/training9D6VJ8-3BG/
import math


def solution(A, B, K):
    """
    DINAKAR

    A<=i<=B
    Take floor of B/K
    11/2 = 5.5 floor->6
    Take ceil of A/K
    6/2 = 3 ceil->3
    return 6-3 + 1
    """
    # write your code in Python 3.6
    return math.floor(B / K) - math.ceil(A / K) + 1


result = solution(6, 11, 2)
print("Sol " + str(result))

"""
Sol 3
"""

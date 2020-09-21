import math


def solution(A, B, K):
    """
    Dinakar
    https://app.codility.com/demo/results/trainingA9ZXXC-3U9/
    Find start range and end range
    start = 6/2 = 3.0 ceil = 3
    end = 11/2 = 5.5 floor = 6
    """
    # ceil get next start point
    # floor get last end point
    return int(math.floor(B / K) - math.ceil(A / K)) + 1


result = solution(6, 11, 2)
print("Sol " + str(result))

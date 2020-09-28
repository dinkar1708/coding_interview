def solution_(A):
    """
    https://app.codility.com/demo/results/trainingGX7VPJ-NCN/
    :return:
    """
    # sum for one more element ie, one is missing...
    n = len(A) + 1
    s = n * (n + 1) / 2
    # print(s)
    # remove all element one by one, the nth item will be left at end
    for item in A:
        s = s - item
    return int(s)


# https://app.codility.com/demo/results/trainingFQXEUW-FU6/
def solution(A):
    """
    DINAKAR
    Idea is xor of two same numbers produces 0
    x = 3 (011) and y = 3 (011) is 000
    at the end single numbers left in xor variable.
    :return:
    """
    xor = 0
    for item in A:
        xor ^= item
    return xor


result = solution([2, 3, 1, 5])
print("Sol " + str(result))

"""
Sol 5
"""

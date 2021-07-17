def solution(ar):
    """
    Time - o(len(ar))
    Space - o(1)
    Return exactly once

    # xor cancels the same elements ie. 2 xor 2 = 0, et the end only unique element will be left.
    :return:
    """
    xor_sum = 0
    for item in ar:
        xor_sum = xor_sum ^ item
    return xor_sum


arr = [9, 3, 9, 3, 5]
result = solution(arr)
print("Sol " + str(result))
"""
Output
Sol 5
"""
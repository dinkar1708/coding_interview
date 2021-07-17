def solution(ar):
    """
    Return exactly once
    :param ar:
    :return:
    """
    xor_sum = 0
    # xor cancels the same elements ie. 2 xor 2 = 0
    for item in ar:
        xor_sum = xor_sum ^ item
    return xor_sum


arr = [9, 3, 9, 3, 5]
result = solution(arr)
print("Sol " + str(result))

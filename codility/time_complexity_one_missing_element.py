def solution(A):
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


result = solution([2, 3, 1, 5])
print("Sol " + str(result))

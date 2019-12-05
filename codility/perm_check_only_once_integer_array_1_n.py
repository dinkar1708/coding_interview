def solution(ar):
    """
    https://app.codility.com/demo/results/trainingEBQW3A-6N8/
    100%
    idea is use xor - xor of two same number cancel the same bits and make that to zero
    do xor of all elements of array
    do xor of 1 to length plus 1
    at last xor of all should be zero

    A non-empty array A consisting of N integers is given.
    A permutation is a sequence containing each element from 1 to N once, and only once.
    :param ar:
    :return:
    """
    xor_sum = 0
    # xor of 1 to len ar
    # xor of all elements of existing array
    for index in range(1, len(ar) + 1):
        xor_sum = xor_sum ^ index ^ ar[index - 1]
    # at the end xor sum should be zero
    if xor_sum == 0:
        return 1
    else:
        return 0


arr = [1, 2]
result = solution(arr)
print("Sol " + str(result))

def solution(A):
    """
    Complexity - n long n
    Codility -
    https://app.codility.com/demo/results/trainingUV284M-WFD/
    100%

    Idea is to sort the array and check for triplet condition

    :param A:
    :return: total counts
    """
    # sort the array
    A.sort()
    print(A)
    # check for all the pairs
    for index in range(len(A) - 2):
        if A[index] + A[index + 1] > A[index + 2]:
            return 1
    return 0


result = solution([10, 2, 5, 1, 8, 20])
print("")
print("Solution " + str(result))

"""
    [1, 2, 5, 8, 10, 20]

    Solution 1
"""

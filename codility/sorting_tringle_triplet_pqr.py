# https://app.codility.com/demo/results/training2YYU6Z-2PP/
def solution(A):
    """
    Complexity - n long n
    Codility -
    https://app.codility.com/demo/results/trainingUV284M-WFD/
    100%

    Idea is to sort the array and check for triplet condition

    P<=Q<=R
    5   8   10
    i   i+1 i+2

    i plus, i+1 > i+2   ie. P+Q > R
    5 plus 8 > 10

    8+10 > 5 - always true - due to sorted
    10+5 > 8 - always true - due to sorted

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

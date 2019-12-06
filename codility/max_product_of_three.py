def solution(A):
    """
    https://app.codility.com/demo/results/training2UMZQJ-4F5/
    100%
    Problem-
    contains the following example triplets:

        (0, 1, 2), product is −3 * 1 * 2 = −6
        (1, 2, 4), product is 1 * 2 * 5 = 10
        (2, 4, 5), product is 2 * 5 * 6 = 60

    Your goal is to find the maximal product of any triplet
    the function should return 60, as the product of triplet (2, 4, 5) is maximal.

    """
    # sort using n log n
    A.sort()
    print("Input      " + str(A))
    len_ar = len(A)
    # max would be either last three item multiplication or
    # first two
    print("First two and last multiplication - ")
    print(A[0] * A[1] * A[len_ar - 1])
    print("Last 3 multiplication - ")
    print(A[len_ar - 1] * A[len_ar - 2] * A[len_ar - 3])
    return max(A[0] * A[1] * A[len_ar - 1],
               A[len_ar - 1] * A[len_ar - 2] * A[len_ar - 3])


result = solution([-3, 1, 2, -2, 5, 6])
print("")
print("Solution " + str(result))
print("")
result = solution([-1, -5, 1, -2, 2, 3])
print("Solution " + str(result))

"""
   Input      [-3, -2, 1, 2, 5, 6]
    First two and last multiplication - 
    36
    Last 3 multiplication - 
    60
    
    Solution 60
    
    Input      [-5, -2, -1, 1, 2, 3]
    First two and last multiplication - 
    30
    Last 3 multiplication - 
    6
    Solution 30
"""

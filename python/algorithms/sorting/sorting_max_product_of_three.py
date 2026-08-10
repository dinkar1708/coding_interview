def solution(A):
    """
    https://app.codility.com/demo/results/training2UMZQJ-4F5/
    100%
    Idea is sort the array, in sorted array we can check for maximum product including negative numbers and maximum
    product of positive numbers
    max negative case - two negative numbers produces positive result so for maximum we should take biggest negative
    number and take one biggest positive number
    max positive case - three biggest positive numbers
    """
    # sort using n log n
    A.sort()
    print("Input      " + str(A))
    len_ar = len(A)
    # first two and last item multiplication or
    # max would be last three item multiplication
    print("First two and last multiplication - ")
    # max negative case
    print(A[0] * A[1] * A[len_ar - 1])
    print("Last 3 multiplication - ")
    # max positive case
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

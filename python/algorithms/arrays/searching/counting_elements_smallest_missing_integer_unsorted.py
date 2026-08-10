def solution(A):
    """
    solution at https://app.codility.com/demo/results/trainingV4KX2W-3KS/
    100%
    idea is to take temp array of max length of array items
    for all positive use item of array as index and mark in tem array as 1 ie. present item
    traverse again temp array if first found value in tem array is zero that index is the smallest positive integer
    :param A:
    :return:
    """
    max_value = max(A)
    if max_value < 1:
        # max is less than 1 ie. 1 is the smallest positive integer
        return 1
    if len(A) == 1:
        # one element with 1 value
        if A[0] == 1:
            return 2
        # one element other than 1 value
        return 1
    # take array of length max value
    # this will work as set ie. using original array value as index for this array
    temp_max_len_array = [0] * max_value
    for i in range(len(A)):
        # do only for positive items
        if A[i] > 0:
            # check at index for the value in A
            if temp_max_len_array[A[i] - 1] != 1:
                # set that as 1
                temp_max_len_array[A[i] - 1] = 1
    print(temp_max_len_array)
    for i in range(len(temp_max_len_array)):
        # first zero ie. this index is the smallest positive integer
        if temp_max_len_array[i] == 0:
            return i + 1
    # if no value found between 1 to max then last value should be smallest one
    return i + 2


arr = [2, 3, 6, 4, 1, 2]
# arr = [-1, -3]
result = solution(arr)
print("Sol " + str(result))

"""
Task description
This is a demo task.

Write a function:

def solution(A)

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
"""

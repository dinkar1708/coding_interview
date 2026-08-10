def solution(A):
    """
    https://app.codility.com/demo/results/training8MX4YC-E95/
    100%
    Idea-
    Sort the array using sort - n log n
    https://stackoverflow.com/questions/14434490/what-is-the-complexity-of-this-python-sort-method
    Start from first item and check for different item and keep increasing counter

    Problem-
     A[0] = 2    A[1] = 1    A[2] = 1
    A[3] = 2    A[4] = 3    A[5] = 1

    the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.

    """

    print("Input      " + str(A))
    A.sort()
    print("Input      " + str(A))
    prev_item = None
    count = 0
    for item in A:
        # count this item as it is different than previous item
        if item != prev_item:
            count += 1
            # update previous item as current
            prev_item = item
    return count


result = solution([2, 1, 1, 2, 3, 1])
print("")
print("Solution " + str(result))

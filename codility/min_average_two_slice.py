import sys


def solution(A):
    """
    https://app.codility.com/demo/results/trainingB955CC-PHS/
    100%

    idea is to calculate the prefix some and find out the average as per given formula in this problem.
    take care when average will be decreasing as described in solution
    find min average from P to Q in array and store the minimum index

    A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N,
     is called a slice of array A (notice that the slice contains at least two elements).
      The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice.
       To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8

    slice (1, 2), whose average is (2 + 2) / 2 = 2;
    slice (3, 4), whose average is (5 + 1) / 2 = 3;
    slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.

    The goal is to find the starting position of a slice whose average is minimal.

    :return:  return  1 - starting position of min average
       """

    min_index = 0

    # why prefix some? - just to calculate the some between two places without using two loops
    prefix_sum = [0] * len(A)
    prefix_sum[0] = A[0]
    for i in range(1, len(A)):
        prefix_sum[i] = prefix_sum[i - 1] + A[i]
    print(prefix_sum)

    min_avg = sys.float_info.max
    # start from slice p ie. from start, this is start and act as P in problem statement and Q would be the i as shown below
    slice_p = 0
    # print(min_avg)
    for i in range(1, len(A)):
        # for i = 1 - at this time calculating average from 0 to 1
        # length of slice -
        # as per problem statement (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1) - here P is the slice_p and Q is the prefix sum
        print("From P:slice_p= " + str(slice_p) + "  To Q: i= " + str(i))
        print("slice length ")
        print(i - slice_p + 1)
        # get the some for position P,Q - ie. from slice_p to i positions and davide by the slice length
        average = (prefix_sum[i] - prefix_sum[slice_p] + A[slice_p]) / (i - slice_p + 1)
        print("average " + str(average))
        # check for minimum average so far
        if average < min_avg:
            min_avg = average
            # hold the index for minimum average value
            min_index = slice_p

        # A[i] - this is always the next index for calculation so we have to check its value for next average calculation
        # this is to find out the next slice_p(P) index
        # when array item is less than minimum average - reason to check is the minimum average is only affected when array item is less then average
        if A[i] < min_avg:
            print("this item index is less than min average ")
            print(i)
            slice_p = i
    return min_index


result = solution([4, 2, 2, 5, 1, 5, 8], )
print("Sol " + str(result))

"""
    Run - 
    [4, 6, 8, 13, 14, 19, 27]
    From P:slice_p= 0  To Q: i= 1
    slice length 
    2
    average 3.0
    this item index is less than min average 
    1
    From P:slice_p= 1  To Q: i= 2
    slice length 
    2
    average 2.0
    From P:slice_p= 1  To Q: i= 3
    slice length 
    3
    average 3.0
    From P:slice_p= 1  To Q: i= 4
    slice length 
    4
    average 2.5
    this item index is less than min average 
    4
    From P:slice_p= 4  To Q: i= 5
    slice length 
    2
    average 3.0
    From P:slice_p= 4  To Q: i= 6
    slice length 
    3
    average 4.666666666666667
    Sol 1
"""

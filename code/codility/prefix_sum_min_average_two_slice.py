import sys


def solution(A):
    """
    https://app.codility.com/demo/results/training3K95ND-KGH/
    100%
    Idea-
    Idea is to calculate the prefix some and find out the average as per given formula in this problem and determine
    minimum slice starting index
    # why prefix some? - just to calculate the some between two places without using two loops

    Find min average from P to Q in array and store the minimum index and say it as average of slice from p to q
    Objective is to find minimum average of other slice, the next slice can be [next q+1 and either include  p,q,q+1 p
    or exclude q,q+1]
    We can exclude the slice calculated so far if the current item is less than the average

    :return:  return  1 - starting position of min average
    """

    min_index = 0
    print(A)
    prefix_sum = [0] * len(A)
    prefix_sum[0] = A[0]
    for i in range(1, len(A)):
        prefix_sum[i] = prefix_sum[i - 1] + A[i]
    print(prefix_sum)

    min_avg = sys.float_info.max
    # start from slice p ie. from start, this is start and act as P in problem statement and Q would be the q_end_index
    # as shown below
    slice_p_from_index = 0
    for q_end_index in range(1, len(A)):
        print()
        # for q_end_index = 1 - at this time calculating average from 0 to 1
        # as per problem statement (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1)
        print("From P:slice_p_from_index= " + str(slice_p_from_index) + "  To Q= " + str(q_end_index) + " slice " + str(
            A[slice_p_from_index:q_end_index + 1]))
        print("Slice length " + str(q_end_index - slice_p_from_index + 1))
        # get the some for position P,Q - ie. from slice_p_from_index to i positions and davide by the slice length
        average = (prefix_sum[q_end_index] - prefix_sum[slice_p_from_index] + A[slice_p_from_index]) / \
                  (q_end_index - slice_p_from_index + 1)
        # check for minimum average so far
        if average < min_avg:
            min_avg = average
            # hold the index for minimum average value
            min_index = slice_p_from_index

        print("min_avg " + str(min_avg))
        # this is to find out the next starting position for slice_p_from_index
        # idea is to skip all the items found so far up to q_end_index-1, to skip and start from q_end_index we have to
        # proof that the next slice min average
        # can be less than the current minimum average
        # that can only happen if and only if current Item A[q_end_index] is less than the minimum average
        # if so we start taking array slice from current Item A[q_end_index] and make slice with next Item
        print("Index = " + str(q_end_index) + " Item " + str(A[q_end_index]))
        if A[q_end_index] < min_avg:
            print("min index found....Item < min_avg, slice_p_from_index changed to Index")
            slice_p_from_index = q_end_index
    return min_index


result = solution([4, 2, 2, 5, 1, 5, 8], )
print("Solution " + str(result))

"""
[4, 2, 2, 5, 1, 5, 8]
[4, 6, 8, 13, 14, 19, 27]

From P:slice_p_from_index= 0  To Q= 1 slice [4, 2]
Slice length 2
min_avg 3.0
Index = 1 Item 2
min index found....Item < min_avg, slice_p_from_index changed to Index

From P:slice_p_from_index= 1  To Q= 2 slice [2, 2]
Slice length 2
min_avg 2.0
Index = 2 Item 2

From P:slice_p_from_index= 1  To Q= 3 slice [2, 2, 5]
Slice length 3
min_avg 2.0
Index = 3 Item 5

From P:slice_p_from_index= 1  To Q= 4 slice [2, 2, 5, 1]
Slice length 4
min_avg 2.0
Index = 4 Item 1
min index found....Item < min_avg, slice_p_from_index changed to Index

From P:slice_p_from_index= 4  To Q= 5 slice [1, 5]
Slice length 2
min_avg 2.0
Index = 5 Item 5

From P:slice_p_from_index= 4  To Q= 6 slice [1, 5, 8]
Slice length 3
min_avg 2.0
Index = 6 Item 8
Solution 1
"""

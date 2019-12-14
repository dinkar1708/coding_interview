def solution(A):
    """
    Codility 100%
    https://app.codility.com/demo/results/training3BK4RM-FXA/
    
    Idea is use two temporary array and store sum using Kadane’s algorithm
    ending_here_sum[i] -  the maximum sum contiguous sub sequence ending at index i
    starting_here_sum[i] - the maximum sum contiguous sub sequence starting with index i

    Double slice sum should be the maximum sum of ending_here_sum[i-1]+starting_here_sum[i+1]

    Reference -
    https://rafal.io/posts/codility-max-double-slice-sum.html

    The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
    contains the following example double slices:
    double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
    double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
    double slice (3, 4, 5), sum is 0.
    """
    print(A)
    ar_len = len(A)
    ending_here_sum = [0] * ar_len
    starting_here_sum = [0] * ar_len

    # the maximum sum contiguous sub sequence ending at index i
    print("The maximum sum contiguous sub sequence ending at index i ")
    for index in range(1, ar_len - 2):  # A[X + 1] + A[X + 2] + ... + A[Y - 1]
        print(index)
        ending_here_sum[index] = max(ending_here_sum[index - 1] + A[index], 0)
        print(ending_here_sum)

    # the maximum sum contiguous sub sequence starting with index i
    print("The maximum sum contiguous sub sequence starting with index i ")
    for index in range(ar_len - 2, 1, -1):  # A[Y + 1] + A[Y + 2] + ... + A[Z - 1]
        print(index)
        starting_here_sum[index] = max(starting_here_sum[index + 1] + A[index], 0)
        print(starting_here_sum)

    # Double slice sum should be the maximum sum of ending_here_sum[i-1]+starting_here_sum[i+1]
    print("Double slice sum should be the maximum sum ")
    max_slice_sum = ending_here_sum[0] + starting_here_sum[2]
    for index in range(1, ar_len - 1):
        print(index)
        max_slice_sum = max(max_slice_sum, ending_here_sum[index - 1] + starting_here_sum[index + 1])
        print(max_slice_sum)

    return max_slice_sum


if __name__ == '__main__':
    result = solution([3, 2, 6, -1, 4, 5, -1, 2])
    print("")
    print("Solution " + str(result))

"""
Example1-
[3, 2, 6, -1, 4, 5, -1, 2]
The maximum sum contiguous sub sequence ending at index i 
1
[0, 2, 0, 0, 0, 0, 0, 0]
2
[0, 2, 8, 0, 0, 0, 0, 0]
3
[0, 2, 8, 7, 0, 0, 0, 0]
4
[0, 2, 8, 7, 11, 0, 0, 0]
5
[0, 2, 8, 7, 11, 16, 0, 0]
The maximum sum contiguous sub sequence starting with index i 
6
[0, 0, 0, 0, 0, 0, 0, 0]
5
[0, 0, 0, 0, 0, 5, 0, 0]
4
[0, 0, 0, 0, 9, 5, 0, 0]
3
[0, 0, 0, 8, 9, 5, 0, 0]
2
[0, 0, 14, 8, 9, 5, 0, 0]
Double slice sum should be the maximum sum 
1
14
2
14
3
17
4
17
5
17
6
17

Solution 17
"""

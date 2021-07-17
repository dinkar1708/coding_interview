def solution(A):
    """
    Codility 100%
    https://app.codility.com/demo/results/trainingKPWR6B-YWJ/

    Idea is if compute largest sum at each position of array item.
    If maximum sum of slice ending at position i is max_ending_here then maximum slice ending in position
    i+1 = max(0, max_ending here + a'i+1')
    Keep track of max so far and update it if max value found.

    A[0] = 3  A[1] = 2  A[2] = -6
    A[3] = 4  A[4] = 0
    the function should return 5 because:

    (3, 4) is a slice of A that has sum 4,
    (2, 2) is a slice of A that has sum −6,
    (0, 1) is a slice of A that has sum 5,
    no other slice of A has sum greater than (0, 1).
    """
    print(A)
    max_ending_here = A[0]
    max_so_far = A[0]

    for i in range(1, len(A)):
        print(i)
        # max with adding current item
        max_ending_here = max(A[i], max_ending_here + A[i])
        print("max_ending_here " + str(max_ending_here))
        # take the maximum from max so far and max ending here
        max_so_far = max(max_ending_here, max_so_far)
        print("max_so_far " + str(max_so_far))

    return max_so_far


if __name__ == '__main__':
    result = solution([3, 2, -6, 4, 0])
    # result = solution([-2, -3, 4, -1, -2, 1, 5, -3])
    print("")
    print("Solution " + str(result))

"""
Example1-
[3, 2, -6, 4, 0]
1
max_ending_here 5
max_so_far 5
2
max_ending_here -1
max_so_far 5
3
max_ending_here 4
max_so_far 5
4
max_ending_here 4
max_so_far 5

Solution 5


Example2-

[-2, -3, 4, -1, -2, 1, 5, -3]
1
max_ending_here -3
max_so_far -2
2
max_ending_here 4
max_so_far 4
3
max_ending_here 3
max_so_far 4
4
max_ending_here 1
max_so_far 4
5
max_ending_here 2
max_so_far 4
6
max_ending_here 7
max_so_far 7
7
max_ending_here 4
max_so_far 7

Solution 7
"""

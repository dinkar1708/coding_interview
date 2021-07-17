# https://app.codility.com/demo/results/trainingH9YDA2-XPP/    - bad 45% only
import itertools


def solution(A):
    """
    DINAKAR

     A# [1, 5, 2, -2]
     s = [-1, 1, -1, 1]
     len = 4
     sum A = 1+5+2-2 = 6

     possible sequences-
     1,1,1,1
     1,1,1,-1
     1,1,-1,1
     .....
     for 1,1,1,1 - one seq
     i = 0,1,2,3
     val (A, S) at i=0 = 1*1 +  1*5 + 1*2 + 1*-2 = 6

     val (A, S) at i=1 = 1*1 +  1*5 + 1*2 + -1*-2 = 10

     min value = 6
     so return 6 can be answer


    """
    min_value = sum(A)
    # find permutations
    for sequence in list(itertools.product([1, -1], repeat=len(A))):
        print(sequence)
        val = 0
        # do for each combinations
        for i in range(len(A)):
            val += A[i] * sequence[i]
        print(val)
        min_value = min(min_value, abs(val))
        print(min_value)
    return min_value


result = solution([1, 5, 2, -2])
print("Sol " + str(result))

"""
(1, 1, 1, 1)
6
6
(1, 1, 1, -1)
10
6
(1, 1, -1, 1)
2
2
(1, 1, -1, -1)
6
2
(1, -1, 1, 1)
-4
2
(1, -1, 1, -1)
0
0
(1, -1, -1, 1)
-8
0
(1, -1, -1, -1)
-4
0
(-1, 1, 1, 1)
4
0
(-1, 1, 1, -1)
8
0
(-1, 1, -1, 1)
0
0
(-1, 1, -1, -1)
4
0
(-1, -1, 1, 1)
-6
0
(-1, -1, 1, -1)
-2
0
(-1, -1, -1, 1)
-10
0
(-1, -1, -1, -1)
-6
0
Sol 0
"""

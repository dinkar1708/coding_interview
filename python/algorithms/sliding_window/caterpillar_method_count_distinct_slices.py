def solution(M, A):
    """
    # https://app.codility.com/demo/results/training2AX89J-FPF/
    A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A.
    The slice consists of the elements A[P], A[P + 1], ..., A[Q]. 

    3 steps -
    1. count the value of distinct in current window
    2. check for duplicates
    3. count distinct
    method-
    0,0
    tail ------ head
    for tail - 0 -------head 0,1,2,3...length

    :param M:
    :param A:
    :return:
    """
    in_current_slice = [False] * (M + 1)
    total_slices = 0
    head = 0
    # for each tail...
    for tail in range(0, len(A)):
        print()
        print("For this tail..", tail)
        # start with each head...
        # check if not duplicate
        while head < len(A) and (not in_current_slice[A[head]]):
            print("For this head.." + str(head))
            # mark item at head as visited
            in_current_slice[A[head]] = True
            # find total slices
            total_slices += (head - tail) + 1
            head += 1
            total_slices = 1000000000 if total_slices > 1000000000 else total_slices
            print("total up to here ", total_slices)
        # one iteration is finished, now mark tail pointer location as not visited
        print(in_current_slice)
        in_current_slice[A[tail]] = False
    return total_slices


if __name__ == '__main__':
    # There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
    result = solution(6, [3, 4, 5, 5, 2])
    print("Solution " + str(result))

"""
Input  - [3, 4, 5, 5, 2]

For this tail.. 0
For this head..0
total up to here  1
For this head..1
total up to here  3
For this head..2
total up to here  6
[False, False, False, True, True, True, False]

For this tail.. 1
[False, False, False, False, True, True, False]

For this tail.. 2
[False, False, False, False, False, True, False]

For this tail.. 3
For this head..3
total up to here  7
For this head..4
total up to here  9
[False, False, True, False, False, True, False]

For this tail.. 4
[False, False, True, False, False, False, False]
Solution 9

"""

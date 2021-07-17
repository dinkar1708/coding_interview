# https://app.codility.com/demo/results/training2AX89J-FPF/
def solution(M, A):
    """
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
        print("For this tail.."+str(tail))
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
            print("total up to here "+str(total_slices))
        # one iteration is finished, now mark tail pointer location as not visited
        in_current_slice[A[tail]] = False
    return total_slices


if __name__ == '__main__':
    result = solution(6, [3, 4, 5, 5, 2])
    print("Solution " + str(result))

"""
Input  - [3, 4, 5, 5, 2]

For this tail..0
For this head..0
total up to here 1
For this head..1
total up to here 3
For this head..2
total up to here 6

For this tail..1

For this tail..2

For this tail..3
For this head..3
total up to here 7
For this head..4
total up to here 9

For this tail..4
Solution 9

"""

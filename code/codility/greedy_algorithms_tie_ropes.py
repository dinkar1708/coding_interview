# https://app.codility.com/demo/results/trainingB8SRCR-83F/
def solution(K, A):
    """
    DINAKAR
    Idea is to keep the sum of all items until it reach to given K
    After that reset window sum and increment found count
    """
    window_sum = 0
    count = 0
    for i in range(0, len(A)):
        print("process " + str(A[i]))
        print("after window_sum with current " + str(window_sum + A[i]))
        if window_sum + A[i] >= K:
            count += 1
            window_sum = 0
            print("found...count " + str(count))
        else:
            window_sum += A[i]
    return count


if __name__ == '__main__':
    result = solution(4, [1, 2, 3, 4, 1, 1, 3])
    print("Solution " + str(result))
"""
Input-
A 4
B [1,2,3,4,1,1,3]

process 1
after window_sum with current 1
process 2
after window_sum with current 3
process 3
after window_sum with current 6
found...count 1
process 4
after window_sum with current 4
found...count 2
process 1
after window_sum with current 1
process 1
after window_sum with current 2
process 3
after window_sum with current 5
found...count 3
Solution 3

"""

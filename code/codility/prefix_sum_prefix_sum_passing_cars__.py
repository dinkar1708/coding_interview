def solution(A):
    """
    Dinakar
    https://app.codility.com/demo/results/training6SEGRM-7Q2/
    Use suffix some - from right side each 1 must be passing zero cars if found..
    [0, 1, 0, 1, 1]
    [3, 3, 2-->must pass previous cars right side of it, 2, 1] ----
    """
    print(A)
    len_ar = len(A)
    suffix_sum = [0] * (len_ar + 1)
    total = 0
    # loop start from end, up to -1 and decrement by 1
    for i in range(len_ar - 1, -1, -1):
        # print(i)
        suffix_sum[i] = A[i] + suffix_sum[i + 1]
        print("suffix sum here...")
        print(suffix_sum)
        if A[i] == 0:
            total += suffix_sum[i]
            print(total)
            if total > 1000000000:
                return -1
    return total


result = solution([0, 1, 0, 1, 1])
print("")
print("Solution " + str(result))

"""
[0, 1, 0, 1, 1]
suffix sum here...
[0, 0, 0, 0, 1, 0]
suffix sum here...
[0, 0, 0, 2, 1, 0]
suffix sum here...
[0, 0, 2, 2, 1, 0]
2
suffix sum here...
[0, 3, 2, 2, 1, 0]
suffix sum here...
[3, 3, 2, 2, 1, 0]
5

Solution 5
"""

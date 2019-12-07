def solution(A):
    """
    Solution at -
    https://app.codility.com/demo/results/trainingMJWNZV-GPH/

    left sum
    right sum
    do for all values and keep checking current diff in left and right sum
    if current temp diff is less then store in global diff
    :param A:
    :return:
    """
    sum_left = A[0]
    sum_right = sum(A[1:])
    diff = abs(sum_left - sum_right)

    for i in range(1, len(A) - 1):
        # left sum adding....
        sum_left += A[i]
        # right sum decreasing....
        sum_right -= A[i]
        #  check diff
        temp_diff = abs(sum_left - sum_right)

        if temp_diff < diff:
            diff = temp_diff

    return diff


result = solution([2, 3, 1, 5])
print("Sol " + str(result))

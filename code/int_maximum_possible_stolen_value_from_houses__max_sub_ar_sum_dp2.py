def max_profit(A):
    """
    Problem statement-
    https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
    https://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/

    Idea is to just remember last two profit for current house
    update the current house profit using dp condition

    dp[0] = dp_last_to_last
    dp[1] = dp_last
    """
    n = len(A)
    if n == 0:
        return 0
    if n == 1:
        return A[0]
    if n == 2:
        return max(A[0], A[1])

    # find dp[0]
    dp_last_to_last = A[0]  # last to last is first item in the beginning
    # find dp[1]
    dp_last = max(A[1], dp_last_to_last)  # max of current and last to last
    # find other dp starting from second index 2 ie from third element of array
    for i in range(2, n):
        print()
        print("For each i: " + str(i))
        print("A[i]: " + str(A[i]))
        # temporary store the last value
        temp = dp_last
        print("DP dp_last: " + str(dp_last_to_last))
        print("DP dp_last_to_last: " + str(dp_last))
        # dp condition
        dp_last = max(A[i] + dp_last_to_last, dp_last)
        # store last to last with the last value
        dp_last_to_last = temp

    # last dp value should be the max profit
    return dp_last


A = [6, 7, 1, 3, 8, 2, 4]
print(A)
result = max_profit(A)
print("Max Profit " + str(result))

"""
Dry run  -
[6, 7, 1, 3, 8, 2, 4]
For each i: 0
[6, 0, 0, 0, 0, 0, 0]
For each i: 1
[6, 7, 0, 0, 0, 0, 0]

For each i: 2
A[i]: 1
DP dp_last: 6
DP dp_last_to_last: 7

For each i: 3
A[i]: 3
DP dp_last: 7
DP dp_last_to_last: 7

For each i: 4
A[i]: 8
DP dp_last: 7
DP dp_last_to_last: 10

For each i: 5
A[i]: 2
DP dp_last: 10
DP dp_last_to_last: 15

For each i: 6
A[i]: 4
DP dp_last: 15
DP dp_last_to_last: 15
Max Profit 19

"""

def max_profit(A):
    """
    Problem statement-
    https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
    https://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/

    For one return the same value
    For two value return the max of two
    For more than two use dp and check with current house value
    From the out put log -
    dp is the value calculated for current house - ie. max profit can be up to this index
    [6, 7, 0, 0, 0, 0, 0]

    For each i: 2 - check for max(A[i] + dp[i - 2], dp[i - 1])
    A[i]: 1
    dp[i - 2]: 6
    dp[i - 1]: 7
    [6, 7, 7, 0, 0, 0, 0]
    DP value: 7

    and so on...

    """
    n = len(A)
    if n == 0:
        return 0
    if n == 1:
        return A[0]
    if n == 2:
        return max(A[0], A[1])
        # dp[i] represent the maximum value stolen so
        # for after reaching house i.
    dp = [0] * n

    # find dp[0]
    dp[0] = A[0]
    print("For each i: " + str(0))
    print(dp)

    # find dp[1]
    dp[1] = max(A[0], A[1])
    print("For each i: " + str(1))
    print(dp)

    # find other dp
    for i in range(2, n):
        print()
        print("For each i: " + str(i))
        print("A[i]: " + str(A[i]))
        print("dp[i - 2]: " + str(dp[i - 2]))
        print("dp[i - 1]: " + str(dp[i - 1]))
        # dp condition
        dp[i] = max(A[i] + dp[i - 2], dp[i - 1])
        print(dp)
        print("DP value: " + str(dp[i]))

    # return the last value of array
    return dp[-1]


A = [6, 7, 1, 3, 8, 2, 4]
# steal 6, 1, 8 and 4 from the house.
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
dp[i - 2]: 6
dp[i - 1]: 7
[6, 7, 7, 0, 0, 0, 0]
DP value: 7

For each i: 3
A[i]: 3
dp[i - 2]: 7
dp[i - 1]: 7
[6, 7, 7, 10, 0, 0, 0]
DP value: 10

For each i: 4
A[i]: 8
dp[i - 2]: 7
dp[i - 1]: 10
[6, 7, 7, 10, 15, 0, 0]
DP value: 15

For each i: 5
A[i]: 2
dp[i - 2]: 10
dp[i - 1]: 15
[6, 7, 7, 10, 15, 15, 0]
DP value: 15

For each i: 6
A[i]: 4
dp[i - 2]: 15
dp[i - 1]: 15
[6, 7, 7, 10, 15, 15, 19]
DP value: 19
Max Profit 19

"""

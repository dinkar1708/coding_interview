def max_profit(A, i, n, dp, memo_table):
    """
    Problem statement and some more details-
    https://medium.com/outco/how-to-solve-the-house-robber-problem-f3535ebaef1b
    https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent-set-2/

    memo_table - keep track of if problem is solved or not
    dp - calculate the max profit value, ie. keep the profit propagating from right to left

    A = [4, 1, 5, 1, 8, 1]
    Output : 17
    Steal 8,5,4

    Idea is calculate value from the last, for base cases return 0
    Follow two cases - first is skip that house
    second case is still that house
    dp[i] = max(max_profit(A, i + 1, n, dp, memo_table),
                A[i] + max_profit(A, i + 2, n, dp, memo_table))
    These are the base cases
    For i = 7
     return 0
    For i = 6
     return 0

    Start the calculation-
    For each i: 5
    [0, 0, 0, 0, 0, 1]
    return DP value: 1 -  max(max_profit(A, 5 + 1, n, dp, memo_table),
                A[5] + max_profit(A, 5 + 2, n, dp, memo_table)) - TRUE FOR MAX - 1+0 > 0
    For each i: 4
    [0, 0, 0, 0, 8, 1]
    return DP value: 8 -  max(max_profit(A, 4 + 1, n, dp, memo_table),
                A[4] + max_profit(A, 4 + 2, n, dp, memo_table)) TRUE FOR MAX - 8+0 > 1
    For each i: 3
    [0, 0, 0, 8, 8, 1]
    return DP value: 8 -  max(max_profit(A, 3 + 1, n, dp, memo_table), TRUE FOR MAX - 1+1 < 8
                A[3] + max_profit(A, 3 + 2, n, dp, memo_table))

    For each i: 2
    [0, 0, 13, 8, 8, 1]
    return DP value: 13 -  max(max_profit(A, 2 + 1, n, dp, memo_table),
                A[2] + max_profit(A, 2 + 2, n, dp, memo_table)) TRUE FOR MAX - 5+8 > 8
    For each i: 1
    [0, 13, 13, 8, 8, 1]
    return DP value: 13 -  max(max_profit(A, 1 + 1, n, dp, memo_table), TRUE FOR MAX - 1+8 < 13
                A[1] + max_profit(A, 1 + 2, n, dp, memo_table))
    For each i: 0
    [17, 13, 13, 8, 8, 1]
    return DP value: 17 -  max(max_profit(A, 0 + 1, n, dp, memo_table),
                A[0] + max_profit(A, 0 + 2, n, dp, memo_table))  TRUE FOR MAX - 4+13 > 13

    """
    # base case
    if i >= n:
        print("Base case...i:" + str(i))
        return 0

    # memoization, check if solution already solved
    if memo_table[i]:
        return dp[i]
    memo_table[i] = 1
    print("Memoization table i: " + str(i))
    print(memo_table)

    # Required recurrence relation
    # skip the current house -> max_profit(A, i + 1, n, dp, memo_table)
    # choose the current house -> A[i] + max_profit(A, i + 2, n, dp, memo_table)
    dp[i] = max(max_profit(A, i + 1, n, dp, memo_table),
                A[i] + max_profit(A, i + 2, n, dp, memo_table))

    # Returning the value
    print("For each i: " + str(i))
    print(dp)
    print("DP value: " + str(dp[i]))

    return dp[i]


# A = [5, 5, 10, 100, 10, 5]

# A = [6, 7, 1, 3, 8, 2, 4]
A = [4, 1, 5, 1, 8, 1]
print(A)
len_ar = len(A)

memo_table = [0 for i in range(len_ar)]
dp = [0 for j in range(len_ar)]

result = max_profit(A, 0, len_ar, dp, memo_table)
print("Max Profit " + str(result))

"""
Dry run - 
Input -
[4, 1, 5, 1, 8, 1]
Steps-

Memoization table i: 0
[1, 0, 0, 0, 0, 0]
Memoization table i: 1
[1, 1, 0, 0, 0, 0]
Memoization table i: 2
[1, 1, 1, 0, 0, 0]
Memoization table i: 3
[1, 1, 1, 1, 0, 0]
Memoization table i: 4
[1, 1, 1, 1, 1, 0]
Memoization table i: 5
[1, 1, 1, 1, 1, 1]
Base case...i:6
Base case...i:7
For each i: 5
[0, 0, 0, 0, 0, 1]
DP value: 1
Base case...i:6
For each i: 4
[0, 0, 0, 0, 8, 1]
DP value: 8
For each i: 3
[0, 0, 0, 8, 8, 1]
DP value: 8
For each i: 2
[0, 0, 13, 8, 8, 1]
DP value: 13
For each i: 1
[0, 13, 13, 8, 8, 1]
DP value: 13
For each i: 0
[17, 13, 13, 8, 8, 1]
DP value: 17
Max Profit 17
"""

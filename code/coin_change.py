def coin_change(ar1, n, sum1):
    # example n=4 ar[4] - so one output
    if sum1 == 0:
        return 1
    # can not take that solution, no solution
    if sum1 < 0:
        return 0
    # no coins but some is greater than 1 no solution
    if n <= 0 and sum1 >= 1:
        return 0
    print(ar1)
    print(n)
    print(sum1)
    # including sum1[n-1]  2) excluding sum1[n-1]
    return coin_change(ar1, n - 1, sum1) + coin_change(ar1, n, sum1 - ar[n - 1])


def coin_change_top_down(memo, ar1, n, sum1):
    """
    memoization top down
    :param ar1:
    :param n:
    :param sum1:
    :return:
    """
    # example n=4 ar[4] - so one output
    if sum1 == 0:
        return 1
    # can not take that solution, no solution
    if sum1 < 0:
        return 0
    # no coins but some is greater than 1 no solution
    if n <= 0 and sum1 >= 1:
        return 0

    if memo[sum1 - 1][n - 1] != 0:
        return memo[sum1 - 1][n - 1]
    print(ar)

    print(n)
    print(sum1)

    # including sum1[n-1]  2) excluding sum1[n-1]
    memo[sum1 - 1][n - 1] = coin_change_top_down(memo, ar1, n - 1, sum1) + coin_change_top_down(memo, ar1, n, sum1 - ar[n - 1])
    print("node value")
    print(memo[sum1 - 1][n - 1])
    return memo[sum1 - 1][n - 1]


def count(S, n, sum1):
    # We need n+1 rows as the table is constructed
    # in bottom up manner using the base case 0 value
    # case (n = 0)
    table = [[0 for x in range(n)] for x in range(sum1 + 1)]

    # Fill the entries for 0 value case (n = 0)
    for i in range(n):
        table[0][i] = 1

    # Fill rest of the table entries in bottom up manner
    for i in range(1, sum1 + 1):
        for j in range(n):
            print("[" + str(i) + " " + str(j) + "]")
            # Count of solutions including S[j]
            x = table[i - S[j]][j] if i - S[j] >= 0 else 0

            # Count of solutions excluding S[j]
            y = table[i][j - 1] if j >= 1 else 0

            # total count
            table[i][j] = x + y
            print(table)
    return table[sum1][n - 1]


# Driver program to test above function
# arr = [1, 2, 3]
# m = len(arr)
# n = 4
# print(count(arr, m, n))

# contains coin
# do not contains coin
"""
For example, for sum1 = 4 and ar = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
 So output should be 4.
  
   Optimal Substructure
To count the total number of solutions, we can divide all set solutions into two sets.
1) Solutions that do not contain nth coin (or Sn).
2) Solutions that contain at least one Sn.
"""
#

ar = [2, 5, 3, 6]
sum = 10
ar = [1, 2, 3]
sum = 4
# print(coin_change(ar, len(ar), sum))

# """
# [[1, 1, 1, 1], [1, 2, 0, 3], [1, 0, 0, 4], [0, 0, 0, 0]]
memo = [[0 for i in range(len(ar))] for j in range(sum + 1)]
# print(memo)
print("output")
print(coin_change_top_down(memo, ar, len(ar), sum))
print("final memoization table")
print(memo)
# """

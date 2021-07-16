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


arr = [1, 2, 5]
m = len(arr)
n = 5
print(count(arr, m, n))

"""
Output: 4
"""
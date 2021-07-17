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

ar = [1, 2, 5]
sum = 5
print(coin_change(ar, len(ar), sum))
"""
Output: 4
"""
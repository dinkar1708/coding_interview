def solution(A):
    """
    Codility 100%
    https://app.codility.com/demo/results/trainingZ3A6TH-XF3/

    A[P] − A[Q], A[Q] ≥ A[P] considering this as profit.
    Idea is start from end, in each iteration subtract the current price with the previous max_price_from_here.
    Keep track for the maximum value(max_price_from_here) of profit at with current price.
    Whenever max_profit_so_far is less than current profit gained, take its new value ie. take the maximum profit

    A[5] − A[4] = 21367 − 21013 = 354
    A[5] - A[1] = 21367 - 21011 = 356
    """
    print(A)
    ar_len = len(A)
    # no profit
    if ar_len < 2:
        return 0
    # max profit from at this point so for each previous prices subtract from it
    max_price_from_here = A[ar_len - 1]
    print(max_price_from_here)
    # total profit can be
    max_profit_so_far = 0
    for index in range(ar_len - 2, -1, -1):
        print(index)
        print(A[index])
        print("max_price_from_here: " + str(max_price_from_here))
        print("max_price_from_here-A[i]: " + str(max_price_from_here - A[index]))
        # last two prices subtraction and maximum of these
        max_profit_so_far = max(max_profit_so_far, max_price_from_here - A[index])
        print("max_profit_so_far: " + str(max_profit_so_far))
        # max profit here can be the maximum of current price or the transaction difference
        max_price_from_here = max(A[index], max_price_from_here)
        print("max_price_from_here: " + str(max_price_from_here))
    return max_profit_so_far


if __name__ == '__main__':
    result = solution([23171, 21011, 21123, 21366, 21013, 21367])
    # result = solution([1, 18, 10, 12, 14, 16])
    print("")
    print("Solution " + str(result))

"""
Example1-
[23171, 21011, 21123, 21366, 21013, 21367]
21367
4
21013
max_price_from_here: 21367
max_price_from_here-A[i]: 354
max_profit_so_far: 354
max_price_from_here: 21367
3
21366
max_price_from_here: 21367
max_price_from_here-A[i]: 1
max_profit_so_far: 354
max_price_from_here: 21367
2
21123
max_price_from_here: 21367
max_price_from_here-A[i]: 244
max_profit_so_far: 354
max_price_from_here: 21367
1
21011
max_price_from_here: 21367
max_price_from_here-A[i]: 356
max_profit_so_far: 356
max_price_from_here: 21367
0
23171
max_price_from_here: 21367
max_price_from_here-A[i]: -1804
max_profit_so_far: 356
max_price_from_here: 23171

Solution 356


Example2-

[1, 18, 10, 12, 14, 16]
16
4
14
max_price_from_here: 16
max_price_from_here-A[i]: 2
max_profit_so_far: 2
max_price_from_here: 16
3
12
max_price_from_here: 16
max_price_from_here-A[i]: 4
max_profit_so_far: 4
max_price_from_here: 16
2
10
max_price_from_here: 16
max_price_from_here-A[i]: 6
max_profit_so_far: 6
max_price_from_here: 16
1
18
max_price_from_here: 16
max_price_from_here-A[i]: -2
max_profit_so_far: 6
max_price_from_here: 18
0
1
max_price_from_here: 18
max_price_from_here-A[i]: 17
max_profit_so_far: 17
max_price_from_here: 18

Solution 17
"""

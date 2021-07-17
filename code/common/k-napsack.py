def k_napsack(var, wt, w, n):
    """
    using recursion
    :param var:
    :param wt:
    :param w:
    :param n:
    :return:
    """
    # base case
    if w <= 0:
        return 0

    if n == 0:
        return 0
    print("tree")
    print(n)
    print(w)
    # If weight of the nth item is more than Knapsack of capacity
    # W, then this item cannot be included in the optimal solution
    if wt[n - 1] > w:
        return k_napsack(var, wt, w, n - 1)
    # return the maximum of two cases:
    # (1) nth item included
    # (2) not included
    else:
        print("max now")
        print(n)
        print(w)
        return max(val[n - 1] + k_napsack(var, wt, w - wt[n - 1], n - 1),
                   k_napsack(var, wt, w, n - 1))


val = [60, 100, 120]
wt = [10, 20, 30]
W = 50

val = [10, 20, 30]
wt = [1, 1, 1]
W = 1
# // out 220 - 30,20

# dynamic property
"""
either take wt or not 
wt-w, n     or wt, n-1
base case w<= 0 return 0

        10,20,30
10, 20, 30 - 40     10, 20, 50


"""
result = k_napsack(val, wt, W, len(wt))
print("Largest sum " + str(result))

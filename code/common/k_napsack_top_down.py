def k_napsack(value, weight, W, n):
    """
    Problem here - https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
    Given weights and values of n items, put these items in a knapsack of capacity
    W to get the maximum total value in the knapsack.
    
    Using recursion
    Time o(2 power n)
    Space - o(1)
    """
    
    # base case
    if W <= 0:
        return 0
    if n == 0:
        return 0
    print("Each function call.....")
    print(">>>Items , W ", n, W)
    # If weight of the nth item is more than Knapsack of capacity
    # W, then this item cannot be included in the optimal solution
    if weight[n - 1] > W:
        return k_napsack(value, weight, W, n - 1)
    # return the maximum of two cases:
    # (1) nth item included
    # (2) not included
    else:
        print("Items , W ", n, W)
        return max(value[n - 1] + k_napsack(value, weight, W - weight[n - 1], n - 1),
                   k_napsack(value, weight, W, n - 1))


val = [60, 100, 120]
wt = [10, 20, 30]
W = 50

result = k_napsack(val, wt, W, len(wt))
print("Max value " + str(result))

"""
Each function call.....
>>>Items , W  3 50
Items , W  3 50
Each function call.....
>>>Items , W  2 20
Items , W  2 20
Each function call.....
>>>Items , W  1 20
Items , W  1 20
Each function call.....
>>>Items , W  2 50
Items , W  2 50
Each function call.....
>>>Items , W  1 30
Items , W  1 30
Each function call.....
>>>Items , W  1 50
Items , W  1 50
Max value 220
"""

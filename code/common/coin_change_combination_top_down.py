
def coin_change_top_down(amounts_param, amount):
    """
    Problem https://leetcode.com/problems/coin-change-2
    how many types of change can be made for given change
    memoization top down
    """
    dp = [0] * (amount + 1)
    dp[0] = 1
    
    for coin in amounts_param:
        for x in range(coin, amount + 1):
            dp[x] += dp[x - coin]
        print("for coin ", coin, dp)
    return dp[amount]

amounts = [1,2,5]
lenA = len(amounts)
amount = 5
combination = coin_change_top_down(amounts, amount)
print(combination)

"""
Output:
4
"""
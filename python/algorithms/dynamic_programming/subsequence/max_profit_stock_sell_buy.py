def max_profit(prices):
    ""
    
    ""
    max_profit = 0
    for i in range(1, len(prices)):
        if (prices[i] > prices[i - 1]):
            print("Price at next day is greater than previous day...Next day and prev day ", prices[i], prices[i - 1])
            max_profit += prices[i] - prices[i - 1]
            print("Current profit", max_profit)
    return max_profit
ar = [7,1,5,3,6,4]
print(ar)
print(max_profit(ar))
def fib_tabulation(n):
    """
    Bottom up
    """
    sum = 0
    prev = 0
    for i in range(1, n - 1):
        sum = sum + (prev + i)
        prev = i
    return sum


n = 5
print("Fib up to..... ", n)
print(fib_tabulation(5))


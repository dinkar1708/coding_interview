def fib_memoization(n):
    """
    using recursion, memoization / top down approach
    :param n:
    :return:
    """
    if n == 0:
        return 0
    if n == 1:
        return 1
    return fib_memoization(n - 1) + fib_memoization(n - 2)

n = 5
print("Fib up to..... ", n)
fib = fib_memoization(n)
print("Output....")
print(fib)

"""
Fib up to.....  5
Output....
5
"""

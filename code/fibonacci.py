import math


def fib_recursion(n):
    """
    using recursion
    :param n:
    :return:
    """
    if n == 0:
        return 0
    if n == 1:
        return 1

    return fib_recursion(n - 1) + fib_recursion(n - 2)


def fib_memoization(ar, n):
    """
    Top down approach
    https://www.geeksforgeeks.org/tabulation-vs-memoization/
    """
    if n == 0:
        return 1
    if ar[n] is not None:
        return ar[n]
    else:
        print("call---" + str(n))
        ar[n] = n * fib_memoization(ar, n - 1)
        return ar[n]


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


# Fibonacci Series using
# Optimized Method

# function that returns nth
# Fibonacci number
def fib_matrix(n):
    """
    fibonacci using the matrix
    :param n:
    :return:
    """
    F = [[1, 1],
         [1, 0]]
    if n == 0:
        return 0
    power(F, n - 1)

    print(F)
    return F[0][0]


def multiply(F, M):
    """
    multiple array
    :param F:
    :param M:
    :return:
    """
    x = (F[0][0] * M[0][0] +
         F[0][1] * M[1][0])
    y = (F[0][0] * M[0][1] +
         F[0][1] * M[1][1])
    z = (F[1][0] * M[0][0] +
         F[1][1] * M[1][0])
    w = (F[1][0] * M[0][1] +
         F[1][1] * M[1][1])

    F[0][0] = x
    F[0][1] = y
    F[1][0] = z
    F[1][1] = w


# Optimized version of
# power() in method 4
def power(F, n):
    if n == 0 or n == 1:
        return;
    M = [[1, 1],
         [1, 0]];

    power(F, n // 2)
    multiply(F, F)

    if n % 2 != 0:
        multiply(F, M)

    # Driver Code


def fib_formula(n):
    """
    Another approach:(Using formula)
    In this method we directly implement the formula for nth term in the fibonacci series.
    Fn = {[(√5 + 1)/2] ^ n} / √5
    :param n:
    :return:
    """
    phi = (1 + math.sqrt(5)) / 2
    return math.pow(phi, n) / math.sqrt(5)


print(fib_matrix(8181))

print(fib_formula(818))

# print(fact_tabulation(3))
# 0,1,1,2,3,5,8,13
# n = 4
# ar = [None]*(n+1)
# print(fact_memoization(ar, n))

# print(fact(4))

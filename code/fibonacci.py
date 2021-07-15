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


def fib_formula(n):
    """
    Using formula - bigo n 1 time
    :param n:
    :return:
    """
    phi = (1 + math.sqrt(5)) / 2
    return math.ceil(math.pow(phi, n) / math.sqrt(5))


def fib_simple(n, current, prev, index):
    """
    Using current and prev value 
    fib is fib(n) = fib(n) + fib(n-1)
    """
    # logs shows how current and prev is progressing in each step....
    print("Each recursion....", index)
    print("Current recursion....current prev ", current, prev)
    if index == n:
        # print if want to print number one by one
        print(current)
        return 0
    # print if want to print number one by one
    print(current)
    return current + prev + fib_simple(n, current+prev, current, index+1)


n = 5
print("Fib up to..... ", n)
# print(fib_formula(818))

# print(fact_tabulation(3))
# 0,1,1,2,3,5,8,13
# ar = [None]*(n+1)
# print(fact_memoization(ar, n))

# print(fact(4))
fib = fib_simple(n, 0, 1, 1)
print("Output....")
print(fib)
"""
Fib up to.....  5
Each recursion.... 1
Current recursion....current prev  0 1
0
Each recursion.... 2
Current recursion....current prev  1 0
1
Each recursion.... 3
Current recursion....current prev  1 1
1
Each recursion.... 4
Current recursion....current prev  2 1
2
Each recursion.... 5
Current recursion....current prev  3 2
3
Output....
7
"""

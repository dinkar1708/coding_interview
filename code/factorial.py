def fact(n):
    if n == 0:
        return 1
    else:
        print("call---" + str(n))
        return n * fact(n - 1)


def fact_memoization(ar, n):
    """
    Top down aproach
    https://www.geeksforgeeks.org/tabulation-vs-memoization/
    """
    if n == 0:
        return 1
    if ar[n] is not None:
        return ar[n]
    else:
        print("call---" + str(n))
        ar[n] = n * fact_memoization(ar, n - 1)
        return ar[n]


def fact_tabulation(n):
    """
    Bottom up
    """
    ar = [1] * (n + 1)
    for i in range(1, len(ar)):
        print(i)
        ar[i] = ar[i - 1] * i
        print(ar)
    return ar[n - 1]


print(fact_tabulation(400))
# n = 4
# ar = [None]*(n+1)
# print(fact_memoization(ar, n))
# print(fact(4))

def fib_top_down(A, n):
    """
    """
    if n == 0:
        return 0
    if n == 1:
        return 1
    if A[n]:
        print("Already calculated for ", n, A)
        return A[n]
    A[n] = fib_top_down(A, n - 1) + fib_top_down(A, n - 2)
    return A[n]

n = 5
print("Fib up to..... ", n)
A = [0]*(n+1)
fib = fib_top_down(A, n)
print("Output....")
print(A)
print(fib)

"""
Fib up to.....  5
Already calculated for  2 [0, 0, 1, 2, 0, 0]
Already calculated for  3 [0, 0, 1, 2, 3, 0]
Output....
[0, 0, 1, 2, 3, 5]
5
"""

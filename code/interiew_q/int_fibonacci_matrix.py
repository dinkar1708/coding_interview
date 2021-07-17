import math


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


def power(F, n):
    if n == 0 or n == 1:
        return
    M = [[1, 1],
         [1, 0]]

    power(F, n // 2)
    multiply(F, F)

    if n % 2 != 0:
        multiply(F, M)


def fib_formula(n):
    """
    Using formula - bigo n 1 time
    :param n:
    :return:
    """
    phi = (1 + math.sqrt(5)) / 2
    return math.ceil(math.pow(phi, n) / math.sqrt(5))


print(fib_matrix(31))

print(fib_formula(31))

"""
[[1346269, 832040], [832040, 514229]]
1346269
1346269
"""

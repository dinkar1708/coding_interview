import math

def fib_formula(n):
    """
    Using formula - big o n 1 time
    :param n:
    :return:
    """
    phi = (1 + math.sqrt(5)) / 2
    return math.ceil(math.pow(phi, n) / math.sqrt(5))

n = 5
fib = fib_formula(n)
print("Output....")
print(fib)
"""
Output....
5
"""

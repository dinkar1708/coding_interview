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

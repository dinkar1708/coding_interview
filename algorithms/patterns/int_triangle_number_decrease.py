def triangle(n):
    """
    5 columns
    54321 - row first
    4321
    321
    21
    1

    :param n:
    :return:
    """
    # first argument is length, second is before 0 run, third is decrement by -1
    for col in range(n, 0, -1):
        # do from i before 0 ie. up to 1
        for row in range(col, 0, -1):
            print(row, end="")
        print()


def triangle_recursion(row, col):
    """
    5 columns
    54321 - row first, after finished -  next row call-       triangle_recursion(col - 1, col - 1)
    4321
    321
    21
    1
    :return:
    """

    # print in single line
    print(row, end="")
    if col == 1 and row == 1:
        # break recursion call, base case
        print("Break recursion...")
        return
    elif row == 1:
        # this is the end of current tow
        # one row finished
        print()  # print next line
        # do call for next row, now current column should be decreased for one row down
        triangle_recursion(col - 1, col - 1)
    else:
        # this is the case it will call current function with decreased row ie. print next value of current row
        triangle_recursion(row - 1, col)


triangle(5)
triangle_recursion(5, 5)

"""
Output1- without recursion
54321
4321
321
21
1
"""

"""
Output1 - using recursion
54321
4321
321
21
1Break recursion...

"""

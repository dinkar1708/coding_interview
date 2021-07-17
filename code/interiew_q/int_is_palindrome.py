def is_palindrome(n):
    """
    Time - o(n)
    Space - o(1)
    Reverse number and check with current number
    :param n:
    :return:
    """
    # store locally
    temp = n
    rev = 0
    while n > 0:
        # get digit one by one
        digit = n % 10
        # find reverse number
        rev = rev * 10 + digit
        # divide the number
        n = n // 10
    return temp == rev


def is_palindrome_2(n):
    """
    Time - o(n)
    Space - o(1)
    Check for first and last value of int digit
    :param n:
    :return:
    """
    number_as_list = list(str(n))
    for n in number_as_list:
        # check if current item is not equal to last item in the list
        print("List at each iteration...", number_as_list)
        if n != number_as_list.pop():
            return False
    return True


output = is_palindrome(121)

if output:
    print("Palindrome")
else:
    print("Not palindrome")

output = is_palindrome_2(1211)

if output:
    print("Palindrome")
else:
    print("Not palindrome")

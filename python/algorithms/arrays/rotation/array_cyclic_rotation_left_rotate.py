def left_rotate(ar, k):
    """
    Cyclic rotation take k from end and append in beg
    [3, 8, 9, 7, 6]
    [7, 6, 3, 8, 9]
    :param ar:
    :return:
    """
    i = 0
    while i < k:
        # remove all k items from start and keep adding in the end
        ar.insert(0, ar.pop())
        print(ar)
        print(i)
        i = i + 1


ar = [3, 8, 9, 7, 6]
k = 3
left_rotate(ar, k)
print(ar)
"""
[6, 3, 8, 9, 7]
0
[7, 6, 3, 8, 9]
1
[9, 7, 6, 3, 8]
2
[9, 7, 6, 3, 8]
"""

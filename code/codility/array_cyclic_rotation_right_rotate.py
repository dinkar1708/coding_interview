def right_rotate(ar, k):
    """
    Cyclic rotation take k from beg and append end
    :param ar:
    :return:
    """
    i = 0
    while i < k:
        # remove all k items from start and keep adding in the end
        ar.append(ar.pop(0))
        print(ar)
        print(i)
        i = i + 1

ar = [3, 8, 9, 7, 6]
k = 3
right_rotate(ar, k)
print(ar)
"""
[8, 9, 7, 6, 3]
0
[9, 7, 6, 3, 8]
1
[7, 6, 3, 8, 9]
2
[7, 6, 3, 8, 9]
"""

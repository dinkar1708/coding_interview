from math import gcd


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


def gcd1(a, b):
    """
    greatest common divisor
    :param a:
    :param b:
    :return:
    """
    if b == 0:
        return a
    else:
        return gcd1(b, a % b)


def left_rotate_juggling(arr, d, n):
    """
    move by factor
    use gcd to count how many time ie. number of sets
    [3, 8, 9, 7, 6]
    [7, 6, 3, 8, 9]
    :param arr:
    :param d:
    :param n:
    :return:
    """
    # do this to rotate only required rotation -
    d = d % n
    #  use inbuilt function
    g_c_d = gcd(d, n)
    print("gcd " + str(g_c_d))
    for i in range(g_c_d):
        print("steps...")
        # move i-th values of blocks
        temp = arr[i]
        j = i
        while 1:
            # swap every d elements at distance
            print(ar)
            k = j + d
            if k >= n:
                k = k - n
            if k == i:
                break
            print("  " + str(j) + " <-> " + str(k))
            arr[j] = arr[k]
            j = k
        arr[j] = temp


ar = [3, 8, 9, 7, 6]
k = 3
# For example, given array A = [3, 8, 9, 7, 6] and K = 3, the function should return [9, 7, 6, 3, 8].

# ar = [1, 2, 3, 4, 5, 6, 7]
# k = 2
print(ar)

# move remaining items from start to end of array
# left_rotate(ar, len(ar) - k)
# ar = [3, 8, 9, 7, 6]

left_rotate_juggling(ar, k, len(ar))

print(ar)

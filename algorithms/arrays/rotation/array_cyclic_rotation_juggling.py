def gcd(a, b):
    """
    greatest common divisor
    :param a:
    :param b:
    :return:
    """
    if b == 0:
        return a
    else:
        return gcd(b, a % b)
def left_rotate_juggling(arr, d, n):
    """
    move by factor
    use gcd to count how many time ie. number of sets
    """
    # do this to rotate only required rotation -
    d = d % n
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
print(ar)
left_rotate_juggling(ar, k, len(ar))
print(ar)
"""
[3, 8, 9, 7, 6]
gcd 1
steps...
[3, 8, 9, 7, 6]
  0 <-> 3
[7, 8, 9, 7, 6]
  3 <-> 1
[7, 8, 9, 8, 6]
  1 <-> 4
[7, 6, 9, 8, 6]
  4 <-> 2
[7, 6, 9, 8, 9]
[7, 6, 3, 8, 9]
"""
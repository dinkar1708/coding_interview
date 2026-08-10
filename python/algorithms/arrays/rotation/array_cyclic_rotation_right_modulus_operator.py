def solution(A, K):
    """
    https://app.codility.com/demo/results/training57YRQV-PD7/

    Sift any array position by number it shift max by (index + shift)%len(A)
    example shift last element 6(index is 5) by 3 (5+3)%5 = 2 - it goes to 2 position
    :param A:
    :param K:
    :return:
    """
    len_n = len(A)
    temp = [0] * len_n
    for i, n in enumerate(A):
        # find new index by modulus operator
        temp[(i + K) % len_n] = A[i]
    return temp


if __name__ == '__main__':
    A = [3, 8, 9, 7, 6]
    K = 3
    print(solution(A, K))

"""
[9, 7, 6, 3, 8]
"""

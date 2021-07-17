def solution(A, K):
    """
    https://app.codility.com/demo/results/training57YRQV-PD7/
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

def solution(A):
    """
    https://app.codility.com/demo/results/trainingBY9CAY-RBU/
    :param A:
    :return:
    """
    len_ar = len(A) + 1
    sum_length = int(len_ar * (len_ar + 1) / 2)
    return sum_length - sum(A)


if __name__ == '__main__':
    a = [2, 3, 1, 5]
    print(solution(a))

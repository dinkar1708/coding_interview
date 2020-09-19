def solution(A, B):
    """
    :param A:
    :return:
    """
    len_str1 = len(A)
    len_str2 = len(B)
    if len_str1 != len_str2:
        return False
    # max number in ascii
    temp_storage = [0] * 256
    for c in A:
        temp_storage[ord(c)] += 1
    for c in B:
        temp_storage[ord(c)] -= 1

    for num in temp_storage:
        # greater than zero
        if num:
            return False
    return True


if __name__ == '__main__':
    a = [2, 3, 1, 5]
    print(solution("abc", "bca"))

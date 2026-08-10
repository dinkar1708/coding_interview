def solution(A, B):
    """
    Problem - https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
    Idea - count for each character of both strings to be exactly same counts
    take temporary storage, store count of all character of first string, 
    in same temporary storage, remove count of all character of second string, 
    at the end count for each character must be zero
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
    print("First storage ", temp_storage)
    for c in B:
        temp_storage[ord(c)] -= 1
    print("After remove first second array counts ", temp_storage)
    for num in temp_storage:
        # greater than zero
        if num:
            return False
    return True


if __name__ == '__main__':
    print(solution("abc", "acb"))
    print(solution("abc", "acbaabbbbgggyy"))

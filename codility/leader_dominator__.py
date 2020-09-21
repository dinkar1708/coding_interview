def solution(A):
    window_size = 0
    candidate = 0
    for n in A:
        if window_size == 0:
            candidate += 1
            candidate = n
        elif candidate == n:
            candidate += 1
        else:
            candidate -= 1
    return A.index(candidate) if A.count(candidate) > len(A) / 2 else -1


if __name__ == '__main__':
    result = solution([3, 4, 3, 2, 3 - 1, 3, 3])
    print("")
    print("Solution " + str(result))

def solution(A):
    """

    :param A:
    :return:
    """
    continuous_block_size = 0
    candidate = 0
    for element in A:
        print()
        print("process element "+str(element))
        # when counter become zero it means new item starts and new item can be candidate
        if continuous_block_size == 0:
            candidate = element
            continuous_block_size += 1
        # for adjacent same and different counter becomes the zero
        elif candidate == element:
            # for same element counter increase
            continuous_block_size += 1
        else:
            # for different element counter decrease
            continuous_block_size -= 1
        print("candidate "+str(candidate))
        print("continuous_block_size "+str(continuous_block_size))
    return A.index(candidate) if A.count(candidate) > len(A) / 2 else -1


if __name__ == '__main__':
    result = solution([3, 4, 3, 2, 3, - 1, 3, 3])
    print("")
    print("Solution " + str(result))

def solution(X, A):
    """
    https://app.codility.com/demo/results/trainingQ28RU6-FFE/
    100%
    idea is use array item as K_index in covered time array
    covered time array set the value to be covered as soon as it finds some value
    if position is already covered it checks for all items in array until any of the item in array can be covered
    :param X:
    :param A:
    :return:
    """
    # assume all position is covered
    covered_time = [-1] * X
    for K_index in range(0, len(A)):
        print("Covered position count " + str(X))
        print(X)
        print(covered_time)
        if covered_time[A[K_index] - 1] != -1:
            # A[K] represents the position where one leaf falls at time K
            # position is already covered
            # time is being spent
            continue
        else:
            # This position is to be covered
            # cover this position ie. make array element with marker(array value)
            covered_time[A[K_index] - 1] = K_index
            # reduce position to be cover
            X -= 1
            # as soon as positions are covered return
            if X == 0:
                # now all positions are covered
                return K_index
    # if we are here it means time spent but we can not cover all positions
    return -1


result = solution(5, [1, 3, 1, 4, 2, 3, 5, 4])
print("Sol " + str(result))

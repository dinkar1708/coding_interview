def solution(A):
    """
    https://app.codility.com/demo/results/training9C4Y6E-SV5/
    Idea is at each step take delta change in price(current and current -1)
    local max is sum of local max and delta at each point
    Keep track of global max
    """
    print(A)
    local_max = 0
    global_max = 0
    for i in range(1, len(A)):
        print()
        print("Start from index " + str(i))
        print("local_max " + str(local_max))
        delta = A[i] - A[i - 1]
        print("i " + str(A[i]) + " i-1 " + str(A[i - 1]) + " delta " + str(delta))
        local_max = max(delta, local_max + delta)
        print("local max changed to " + str(local_max))
        global_max = max(local_max, global_max)
        print("global max changed to " + str(global_max))
    return global_max


if __name__ == '__main__':
    result = solution([23171, 21011, 21123, 21366, 21013, 21367])
    print("")
    print("Solution " + str(result))

"""
[23171, 21011, 21123, 21366, 21013, 21367]

Start from index 1
local_max 0
i 21011 i-1 23171 delta -2160
local max changed to -2160
global max changed to 0

Start from index 2
local_max -2160
i 21123 i-1 21011 delta 112
local max changed to 112
global max changed to 112

Start from index 3
local_max 112
i 21366 i-1 21123 delta 243
local max changed to 355
global max changed to 355

Start from index 4
local_max 355
i 21013 i-1 21366 delta -353
local max changed to 2
global max changed to 355

Start from index 5
local_max 2
i 21367 i-1 21013 delta 354
local max changed to 356
global max changed to 356

Solution 356
"""

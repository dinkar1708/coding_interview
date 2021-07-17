# https://app.codility.com/demo/results/trainingZFQV6A-GT2/
def solution(A):
    """
    DINAKAR
    keep item as key in dict, it will keep overriding repeated numbers at same key
    length of temp should be uniuqe element
    """
    temp = {}
    for i in range(len(A)):
        temp[A[i]] = True
    print(temp)
    return len(temp)


result = solution([2, 1, 1, 2, 3, 1])
print("")
print("Solution " + str(result))

"""
{2: True, 1: True, 3: True}

Solution 3
"""

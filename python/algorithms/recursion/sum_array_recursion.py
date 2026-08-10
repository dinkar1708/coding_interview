def solution(A, lenA, index):
    """
    Time - O(lenA)
    Space - O(lenA)
    """
    print("First and rest part ", index, lenA-index)
    if index == lenA:
        # problem is solved assume sum is zero here, because index is outside the arrary
        return 0
    sum = A[index] + solution(A, lenA, index + 1)
    print("Processing sum from here... ", A[index])
    return sum


arr = [1, 5, 7, -2]
print(arr)
print(solution(arr, len(arr), 0))
"""
[1, 5, 7, -2]
First and rest part  0 4
First and rest part  1 3
First and rest part  2 2
First and rest part  3 1
First and rest part  4 0
Processing sum from here...  -2
Processing sum from here...  7
Processing sum from here...  5
Processing sum from here...  1
11
"""

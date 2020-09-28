# https://app.codility.com/demo/results/trainingUJUBFH-2H3/
def solution(X, A):
    """
    DINAKAR

     Clue -> Find the earliest time when the frog can jump to the other side of the river. The frog can cross only when leaves appear at every position across the river from 1 to X

     [1, 3, 1, 4, 2, 3, 5, 4]
     X = 5 ie.  {1, 2, 3, 4, 5} here 1 to X/5 is completed so we return the index which is time

    """
    positions = set()
    for index, item in enumerate(A):
        print("Current Time " + str(index))
        print("Position " + str(item))
        positions.add(item)
        print("Positions covered..." + str(positions))
        if len(positions) == X:
            return index
    return -1


result = solution(5, [1, 3, 1, 4, 2, 3, 5, 4])
print("Sol " + str(result))

"""
Current Time 0
Position 1
Positions covered...{1}
Current Time 1
Position 3
Positions covered...{1, 3}
Current Time 2
Position 1
Positions covered...{1, 3}
Current Time 3
Position 4
Positions covered...{1, 3, 4}
Current Time 4
Position 2
Positions covered...{1, 2, 3, 4}
Current Time 5
Position 3
Positions covered...{1, 2, 3, 4}
Current Time 6
Position 5
Positions covered...{1, 2, 3, 4, 5}
Sol 6

"""

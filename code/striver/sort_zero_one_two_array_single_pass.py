def swap(pos1, pos2, numbers):
    """
    Swap number in numbers at position pos1 and pos2
    """
    print("Swap...", pos1, pos2)
    numbers[pos1], numbers[pos2] = numbers[pos2], numbers[pos1]


def sort(numbers):
    """
    Dutch National Flag Problem, first was proposed by Edsger W. Dijkstra.
    Sort zero one and two array in single pass
    Time - o(n)
    Space - o(1)

    Consider below placement of values for the posinters
    0 to low-1 = 0
    low to mid-1 = 1
    hight+1 to end of array = 2
    """
    print("Count input in ", numbers)
    low = 0
    mid = 0
    high = len(numbers)-1
    while mid <= high:
        num = numbers[mid]
        print("low mid, hight ", low, mid, high, " For num ", num)
        if num == 0:
            swap(low, mid, numbers)
            low += 1
            mid += 1
        elif num == 1:
            mid += 1
        else:
            swap(mid, high, numbers)
            high -= 1
        print("Sorting in single pass ", numbers)
    return numbers


print(sort([2, 0, 2, 1, 1, 0]))

"""
Count input in  [2, 0, 2, 1, 1, 0]
low mid, hight  0 0 5  For num  2
Swap... 0 5
Sorting in single pass  [0, 0, 2, 1, 1, 2]
low mid, hight  0 0 4  For num  0
Swap... 0 0
Sorting in single pass  [0, 0, 2, 1, 1, 2]
low mid, hight  1 1 4  For num  0
Swap... 1 1
Sorting in single pass  [0, 0, 2, 1, 1, 2]
low mid, hight  2 2 4  For num  2
Swap... 2 4
Sorting in single pass  [0, 0, 1, 1, 2, 2]
low mid, hight  2 2 3  For num  1
Sorting in single pass  [0, 0, 1, 1, 2, 2]
low mid, hight  2 3 3  For num  1
Sorting in single pass  [0, 0, 1, 1, 2, 2]
[0, 0, 1, 1, 2, 2]
"""

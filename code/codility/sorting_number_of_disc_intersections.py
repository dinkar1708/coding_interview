def solution(A):
    """
    Codility -
    https://app.codility.com/demo/results/trainingKC23TS-77G/
    100%
    
    Idea is to maintain the start and end point marker for each disc/circle
    Sort by start position of disc/circle
    On each start position count the active circle/disc
    On each end position reduce the active circle/disc
    Count the total intersection using the active circle in each iteration

    Reference -
    https://www.youtube.com/watch?v=HV8tzIiidSw
    http://www.lucainvernizzi.net/blog/2014/11/21/codility-beta-challenge-number-of-disc-intersections/
    :param A:
    :return: total intersections
    """
    # contains start and end points of circle
    circle_points = []
    for i, a in enumerate(A):
        # store start points and end points of circle
        # as per problem statement -> The J-th disc is drawn with its center at (J, 0) and radius A[J].
        # example 0-1, 0+1 second 1-5, 1+5
        circle_points += [(i - a, True), (i + a, False)]
    print("Original disc positions " + str(circle_points))
    # Sort the array of disc, making sure that the start of a disk in a particular point P comes before the end of any disk at P.
    circle_points.sort(key=lambda x: (x[0], not x[1]))
    print("Sorted disc positions " + str(circle_points))
    intersections, active_circles = 0, 0

    # We now walk this array, keeping track of how thick the set of disk is at each new disc (below, the variable is called active_circles).
    # Furthermore, we increase the number of intersection by active_circles if a new disk starts.
    for _, is_beginning in circle_points:
        # active circle found ie. starting disc found
        if is_beginning:
            # counting intersections by active circles ie. each active circle must be intersecting the comming new circle,
            # we already know they are in sorted order by start position
            intersections += active_circles
            active_circles += 1
            print(
                "This is start disc -> intersections " + str(intersections) + " active_circles " + str(active_circles))
        # ending circle position found, now active circle should be reduced by one
        else:
            print("Closing disc found.....")
            print("Reduce active circle " + str(active_circles))
            print()
            active_circles -= 1
        # ** is 10 to the power of 7
        if intersections > 10 ** 7:
            return -1

    return intersections


result = solution([1, 5, 2, 1, 4, 0])
print("")
print("Solution " + str(result))

"""
solution_(A - steps run---
    Original disc positions [(-1, True), (1, False), (-4, True), (6, False), (0, True), (4, False), (2, True), (4, False), (0, True), (8, False), (5, True), (5, False)]
    Sorted disc positions [(-4, True), (-1, True), (0, True), (0, True), (1, False), (2, True), (4, False), (4, False), (5, True), (5, False), (6, False), (8, False)]
    This is start disc -> intersections 0 active_circles 1
    This is start disc -> intersections 1 active_circles 2
    This is start disc -> intersections 3 active_circles 3
    This is start disc -> intersections 6 active_circles 4
    Closing disc found.....
    Reduce active circle 4

    This is start disc -> intersections 9 active_circles 4
    Closing disc found.....
    Reduce active circle 4

    Closing disc found.....
    Reduce active circle 3

    This is start disc -> intersections 11 active_circles 3
    Closing disc found.....
    Reduce active circle 3

    Closing disc found.....
    Reduce active circle 2

    Closing disc found.....
    Reduce active circle 1


    Solution 11
"""

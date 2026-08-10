# https://app.codility.com/demo/results/training8WPGD5-8QC/
def solution(A):
    """
    DINAKAR
    idea is find the start and end point by simply just adding and subtracting their index from radius
    index is treated as radius

    Start pos [-4, -1, 0, 0, 2, 5]
    End pos [1, 4, 4, 5, 6, 8]

    Process all start points one by one ->
        For each start point, process each end point, find out find out all the intersections until end point is
        greater than the start position
        eg. for End pos 1 traverse Start pos - -4, -1, 0, 0 next pos 2 can not take [2>=1 false here]
        #while doing this count the intersections
         - just by subtracting current processing index from left pointer
         - maintain total counter also

    """
    end_points = [k + v for k, v in enumerate(A)]
    end_points.sort()
    start_points = [k - v for k, v in enumerate(A)]
    start_points.sort()
    start_point = 0
    print("Start pos " + str(start_points))
    print("End pos " + str(end_points))
    counter = 0
    for index, end_point in enumerate(end_points):  # processing all end points
        print("Start finding for start point")
        print()
        print("For start_point " + str(start_point))
        print("Process index " + str(index) + " end_point " + str(end_point))
        while start_point < len(end_points) and end_point >= start_points[start_point]:  # processing all start points
            print("start_point " + str(start_point) + " < length " + str(len(end_points)))
            print("Each end_point " + str(end_point) + " >= Each start point " + str(start_points[start_point]))
            intersect = start_point - index
            print("Intersect value calculated " + str(intersect))
            counter += intersect
            print("Total intersect changed to " + str(counter))
            start_point += 1
            print()
        if counter > 10 ** 7:
            return -1

    return counter


result = solution([1, 5, 2, 1, 4, 0])
print("")
print("Solution " + str(result))

"""
Start pos [-4, -1, 0, 0, 2, 5]
End pos [1, 4, 4, 5, 6, 8]
Start finding for start point

For start_point 0
Process index 0 end_point 1
start_point 0 < length 6
Each end_point 1 >= Each start point -4
Intersect value calculated 0
Total intersect changed to 0

start_point 1 < length 6
Each end_point 1 >= Each start point -1
Intersect value calculated 1
Total intersect changed to 1

start_point 2 < length 6
Each end_point 1 >= Each start point 0
Intersect value calculated 2
Total intersect changed to 3

start_point 3 < length 6
Each end_point 1 >= Each start point 0
Intersect value calculated 3
Total intersect changed to 6

Start finding for start point

For start_point 4
Process index 1 end_point 4
start_point 4 < length 6
Each end_point 4 >= Each start point 2
Intersect value calculated 3
Total intersect changed to 9

Start finding for start point

For start_point 5
Process index 2 end_point 4
Start finding for start point

For start_point 5
Process index 3 end_point 5
start_point 5 < length 6
Each end_point 5 >= Each start point 5
Intersect value calculated 2
Total intersect changed to 11

Start finding for start point

For start_point 6
Process index 4 end_point 6
Start finding for start point

For start_point 6
Process index 5 end_point 8

Solution 11

"""

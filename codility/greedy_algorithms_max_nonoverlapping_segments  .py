# https://app.codility.com/demo/results/trainingU7UZV2-R3Y/
def solution(A, B):
    """
    DINAKAR
    start points A
    end points B - sorted
    take first point of segment and compare with all other segment points
    when overlap is found, mark current segment to prev_seg_end_point
    prev_seg_end_point - is the last non overlap segment end point
    (1,5) - self first is overlap
    (3,6) - check overlap with [1,5]
    (7,8) - check overlap with [1,5] - do not overlap here - pen paper draw and check

    see below do not overlap dot is one unit->
    1...5
    ......78
    7, 8 is outside 1,5

    """
    # end point of each segment
    prev_seg_end_point = -1
    count = 0
    for i in range(len(B)):
        # change if not overlap
        # smaller start is greather than end point start  - not overlapp
        print("Compare for prev_seg_end_point  " + str(prev_seg_end_point))
        if A[i] > prev_seg_end_point:
            print("Overlapped - A(start, end) => " + str(A[i]) + " , " + str(B[i]))
            print("prev_seg_end_point " + str(prev_seg_end_point))
            prev_seg_end_point = B[i]
            count += 1
    return count


if __name__ == '__main__':
    result = solution([1, 3, 7, 9, 9], [5, 6, 8, 9, 10])
    print("Solution " + str(result))
"""
Input-
A [1, 3, 7, 9, 9]
B [5, 6, 8, 9, 10]

Compare for prev_seg_end_point  -1
Overlapped - A(start, end) => 1 , 5
prev_seg_end_point -1
Compare for prev_seg_end_point  5
Compare for prev_seg_end_point  5
Overlapped - A(start, end) => 7 , 8
prev_seg_end_point 5
Compare for prev_seg_end_point  8
Overlapped - A(start, end) => 9 , 9
prev_seg_end_point 8
Compare for prev_seg_end_point  9
Solution 3
"""

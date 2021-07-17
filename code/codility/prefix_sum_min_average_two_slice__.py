# https://app.codility.com/demo/results/trainingJZX9X9-PGY/
def solution(A):
    """
    DINAKAR
    Idea is minimum average can be either 2 or 3 element ie. if you want to choose 4 then that again can be divided in
    to 2,2 groups, same for 6 can be divided in 3,3 group
    We calculate prefix sum to find average easily for given start and end position
    We do average either for 2 oe 3 element of array as shown in inner loop
    for j in range(i + 1, min(i + 3, len_ar)):
    at the end it is going to be order or n+2or3 = order of n o(n)
    :param A:
    :return:
    """
    len_ar = len(A)
    prefix_sum = [0] * (len_ar + 1)
    for i in range(1, len_ar + 1):
        prefix_sum[i] = prefix_sum[i - 1] + A[i - 1]
    print(prefix_sum)
    # max element in array by 3
    max_average = 100000 / 3
    min_idx_start = 0
    for i in range(len_ar):
        print()
        for j in range(i + 1, min(i + 3, len_ar)):
            print("For i " + str(i) + " j " + str(j))
            average = (prefix_sum[j + 1] - prefix_sum[i]) / (j - i + 1)
            print("Avg " + str(average))
            if average < max_average:
                min_idx_start = i
                max_average = average
                print("Min from here " + str(min_idx_start) + " max_average so far => " + str(max_average))
    return min_idx_start


result = solution([4, 2, 2, 5, 1, 5, 8], )
print("Solution " + str(result))

"""
Array-
[4, 2, 2, 5, 1, 5, 8]
Prefix sum-

[0, 4, 6, 8, 13, 14, 19, 27]

For i 0 j 1
Avg 3.0
Min from here 0 max_average so far => 3.0
For i 0 j 2
Avg 2.6666666666666665
Min from here 0 max_average so far => 2.6666666666666665

For i 1 j 2
Avg 2.0
Min from here 1 max_average so far => 2.0
For i 1 j 3
Avg 3.0

For i 2 j 3
Avg 3.5
For i 2 j 4
Avg 2.6666666666666665

For i 3 j 4
Avg 3.0
For i 3 j 5
Avg 3.6666666666666665

For i 4 j 5
Avg 3.0
For i 4 j 6
Avg 4.666666666666667

For i 5 j 6
Avg 6.5

Solution 1

"""

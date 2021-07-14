def solution(A, query_array, lenA, m, factor):
    """
            Question here
            https://iq.opengenus.org/multiple-array-range-increments-linear-time/
            https://www.youtube.com/watch?v=4AFWuZIbJ9g&ab_channel=TheCodingGuy

            total 4 steps as below ->
            1. sum[query_array[index][0]] += factor
            2. if ((query_array[index][1] + 1) < lenA):
                sum[query_array[index][1] + 1] -= factor

            Process for range  0 2
            After process range first element of range.....sum
            [2, 0, 0, 0]
            (query_array[index][1] + 1) < lenA)  2
            Decrement value at index  0 for  2
            After process first and second element of range.....sum
            [2, 0, 0, -2]

            what does above mean?
            [2, 0, 0, -2]   for i = 1, to n-1  >  sum[i] += sum[i-1] 
            doing cumulative sum  {2, 0+2, 0+2+0, 0+2+0 + -2}   = {2,2,2,0} last i zero because processing from index 0 to 2

            after this step we have Cumulative sum at each step..... [2, 4, 6, -2]
            3. next we find the cumulative sum Cumulative sum sum[i] += sum[i - 1] array  [2, 4, 6, 4]

            4. at the end we add corresponding element of array and sum  A[i] += sum[i] [3, 6, 9, 8]
    """
    sum = [0 for i in range(lenA)]
    for i in range(m):
        # start value of range query_array[i][0] {2,3} = start value {2}
        print("Process for range ", query_array[i][0], query_array[i][1])
        sum[query_array[i][0]] += factor
        print("After process range first element of range.....sum");
        print(sum);
        if ((query_array[i][1] + 1) < lenA):
            print("(query_array[i][1] + 1) < lenA) ", query_array[i][1])
            print("Decrement value at i ", i, "for ", query_array[i][1]) 
            sum[query_array[i][1] + 1] -= factor
        print("After process first and second element of range.....sum");
        print(sum);
        # after one iteration
        # 

    print("Original array ", A)
    print("Sum array ", sum)
    A[0] += sum[0]
    for i in range(1, lenA):
        sum[i] += sum[i - 1]
        print("Cumulative sum at each step......")
        print(sum)
    print("Original array ", A)
    print("Cumulative sum array ", sum)
    for i in range(1, lenA):
        print("Adding single sum corresponding to array original value  A[i] and sum[i]", A[i], sum[i])
        A[i] += sum[i]
        print(sum)

array = [1, 2, 3, 4]
q_arr = [[0, 2], [1, 3], [2,3]]

print("Input A:")
print(array)
print("Input range:")
print(q_arr)
solution(array, q_arr, len(array), len(q_arr), 2)
print("Output")
print(array)

"""
Dry run.....

Input A:
[1, 2, 3, 4]
Input range:
[[0, 2], [1, 3], [2, 3]]
Process for range  0 2
After process range first element of range.....sum
[2, 0, 0, 0]
(query_array[i][1] + 1) < lenA)  2
Decrement value at i  0 for  2
After process first and second element of range.....sum
[2, 0, 0, -2]
Process for range  1 3
After process range first element of range.....sum
[2, 2, 0, -2]
After process first and second element of range.....sum
[2, 2, 0, -2]
Process for range  2 3
After process range first element of range.....sum
[2, 2, 2, -2]
After process first and second element of range.....sum
[2, 2, 2, -2]
Original array  [1, 2, 3, 4]
Sum array  [2, 2, 2, -2]
Cumulative sum at each step......
[2, 4, 2, -2]
Cumulative sum at each step......
[2, 4, 6, -2]
Cumulative sum at each step......
[2, 4, 6, 4]
Original array  [3, 2, 3, 4]
Cumulative sum array  [2, 4, 6, 4]
Adding single sum corresponding to array original value  A[i] and sum[i] 2 4
[2, 4, 6, 4]
Adding single sum corresponding to array original value  A[i] and sum[i] 3 6
[2, 4, 6, 4]
Adding single sum corresponding to array original value  A[i] and sum[i] 4 4
[2, 4, 6, 4]
Output
[3, 6, 9, 8]

"""

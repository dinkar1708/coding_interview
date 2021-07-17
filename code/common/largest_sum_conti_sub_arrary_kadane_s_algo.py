def largest_sum(arr):
    """
    Problem - https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
    Time - o(n)
    Space - o(1)
    largest sum array - contiguous elements
    idea is keep the state of current max ending ie. max ending plus current itme
    if max ending at current is less than zero reset it to zero
    keep state of max so far
    :param arr:
    :return:
    """
    max_ending_here = arr[0]
    max_so_far = arr[0]

    for i in range(1, len(arr)):
        item = arr[i]
        print(">>>>>>>>>>>Process item ", item)
        # max with adding current item
        max_ending_here = max(item, max_ending_here + item)
        print("Max ending here ", max_ending_here)
        # take the maximum from max so far and max ending here
        max_so_far = max(max_ending_here, max_so_far)
        print("Max so far ", max_so_far)
    return max_so_far


arr = [-2, -3, 4, -1, -2, 1, 5, -3]
# 4, -1, -2, 1, 5
result = largest_sum(arr)
print("Largest sum " + str(result))


"""
>>>>>>>>>>>Process item  -3
Max ending here  -3
Max so far  -2
>>>>>>>>>>>Process item  4
Max ending here  4
Max so far  4
>>>>>>>>>>>Process item  -1
Max ending here  3
Max so far  4
>>>>>>>>>>>Process item  -2
Max ending here  1
Max so far  4
>>>>>>>>>>>Process item  1
Max ending here  2
Max so far  4
>>>>>>>>>>>Process item  5
Max ending here  7
Max so far  7
>>>>>>>>>>>Process item  -3
Max ending here  4
Max so far  7
Largest sum 7
"""

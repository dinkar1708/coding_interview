def largest_sum(arr):
    """
    largest sum array - contiguous elements
    idea is keep the state of current max ending ie. max ending plus current itme
    if max ending at current is less than zero reset it to zero
    keep state of max so far
    :param arr:
    :return:
    """
    max_ending_here = 0
    max_so_far = 0

    for item in arr:
        # max with adding current item
        max_ending_here = max_ending_here + item
        # reset to zero if less than zero
        if max_ending_here < 0:
            max_ending_here = 0
        # take the maximum from max so far and max ending here
        max_so_far = max(max_ending_here, max_so_far)
    return max_so_far


# Driver program to test above function
arr = [-2, -3, 4, -1, -2, 1, 5, -3]
# arr = [-2]
result = largest_sum(arr)
print("Largest sum " + str(result))

def is_subset_sum(arr, n, sum):
    # Base Cases
    print("fun call----" + str(arr))
    if sum == 0:
        print("true case now--")
        return True
    if n == 0 and sum != 0:
        return False

    # If last element is greater than sum, then
    # ignore it
    print("n----->")
    print(n)
    print(sum)
    print(sum - arr[n - 1])

    if arr[n - 1] > sum:
        print("last element greater than sum--")
        return is_subset_sum(arr, n - 1, sum)

    ''' else, check if sum can be obtained by any of  
    the following 
    (a) including the last element 
    (b) excluding the last element'''

    return is_subset_sum(arr, n - 1, sum) or is_subset_sum(arr, n - 1, sum - arr[n - 1])


def find_partition(arr, n):
    """
    # Returns true if arr[] can be partitioned in two
    # subsets of equal sum, otherwise false
    :param arr:
    :param n:
    :return:
    """
    # Calculate sum of the elements in array
    sum = 0
    for i in range(0, n):
        sum += arr[i]
        # If sum is odd, there cannot be two subsets
    # with equal sum
    if sum % 2 != 0:
        return False

        # Find if there is subset with sum equal to
    # half of total sum
    return is_subset_sum(arr, n, sum // 2)


# Driver program to test above function
arr = [1, 5, 11, 5]
n = len(arr)
if find_partition(arr, n):
    print("Can be divided into two subsets of equal sum")
else:
    print("Can not be divided into two subsets of equal sum")

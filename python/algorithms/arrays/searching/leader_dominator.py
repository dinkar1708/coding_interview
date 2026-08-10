def solution(A):
    """
    Codility -
    100%
    https://app.codility.com/demo/results/trainingXKTA8Q-6FM/
    
    Idea is find out the dominator candidate first and then find out its occurrence
    Use size for dominator candidate finding-
    size - the repeating count of max occurrence item except the other difference items counts
        ie. if any different item is found through scanning of items in array this size will be reduced by one
        at the end this is the nearest frequency of max occurrence item not the frequency of dominator item

    """
    print(A)
    # find the dominator
    dominator_value = -1
    size = 0
    for i, item in enumerate(A):
        print()
        if size == 0:
            # this is first time new element found
            size += 1
            dominator_value = item
            print("Size is reset at index " + str(i) + " size " + str(size))
        else:
            # finding other new element
            if dominator_value == item:
                # new element is same as previous
                size += 1
                print("Dominator is equal to current item at index " + str(i) + " size " + str(size))
            else:
                # other new element found decrease counter
                size -= 1
                print("New Dominator at index " + str(i) + " size " + str(size))

    print("Size " + str(size))
    # can not found any dominator
    if size <= 0:
        return -1
    # find the candidate now
    len_ar_half = len(A) / 2
    count = 0
    for i, item in enumerate(A):
        if item == dominator_value:
            count += 1
        if count > len_ar_half:
            return i

    return -1


# result = solution([3, 4, 3, 2, 3 - 1, 3, 3])
result = solution([3, 4, 3, 2, 2, 2, 2])
print("")
print("Solution " + str(result))

"""
[3, 4, 3, 2, 2, 3, 3]

Size is reset at index 0 size 1

New Dominator at index 1 size 0

Size is reset at index 2 size 1

New Dominator at index 3 size 0

Size is reset at index 4 size 1

New Dominator at index 5 size 0

Size is reset at index 6 size 1
Size 1

Solution 6

[3, 4, 3, 2, 2, 2, 2]

Size is reset at index 0 size 1

New Dominator at index 1 size 0

Size is reset at index 2 size 1

New Dominator at index 3 size 0

Size is reset at index 4 size 1

Dominator is equal to current item at index 5 size 2

Dominator is equal to current item at index 6 size 3
Size 3

Solution 6

"""

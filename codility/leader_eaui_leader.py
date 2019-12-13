def solution(A):
    """
    Codility -
    100%
    https://app.codility.com/demo/results/training4C4MJ6-P2D/

    Idea is find out the dominator candidate first and then find out equi leader
    Use size for dominator candidate finding-
    size - the repeating count of max occurrence item except the other difference items counts
        ie. if any different item is found through scanning of items in array this size will be reduced by one
        at the end this is the nearest frequency of max occurrence item not the frequency of dominator item
    For finding the equi leader is use the problem statement as formula, find current leader count and find right part of leader count
      and keep checking for total equi leaders

    Problem definition-
    An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S]
    and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
    For example, given array A such that:

        A[0] = 4
        A[1] = 3
        A[2] = 4
        A[3] = 4
        A[4] = 4
        A[5] = 2
    we can find two equi leaders:

    0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
    2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
    The goal is to count the number of equi leaders.

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

    ar_len = len(A)
    # find the leader count
    leader_count = len([number for number in A if number == dominator_value])
    if leader_count <= ar_len // 2:
        # candidate is not the leader
        return 0
    print("Find the qui leader ........................")
    equi_leaders = 0
    leader_count_so_far = 0
    for index in range(ar_len):
        # leader count so far, if current value of array is equal to dominator value
        if A[index] == dominator_value:
            leader_count_so_far += 1
        # leader count in right part will be the leader count minus leader so for calculated
        leader_in_right_part = leader_count - leader_count_so_far
        print("Leader count so far...." + str(leader_count_so_far))
        print("Leader in right part... " + str(leader_in_right_part) + " at index " + str(index))
        # check for both leaders
        if leader_count_so_far > (index + 1) // 2 and leader_in_right_part > (ar_len - index - 1) // 2:
            # Both the head and tail have leaders of the same value
            # as "leader"
            equi_leaders += 1
    return equi_leaders


result = solution([4, 3, 4, 4, 4, 2])
print("")
print("Solution " + str(result))

"""
[4, 3, 4, 4, 4, 2]

Size is reset at index 0 size 1

New Dominator at index 1 size 0

Size is reset at index 2 size 1

Dominator is equal to current item at index 3 size 2

Dominator is equal to current item at index 4 size 3

New Dominator at index 5 size 2
Size 2
Find the qui leader ........................
Leader count so far....1
Leader in right part... 3 at index 0
Leader count so far....1
Leader in right part... 3 at index 1
Leader count so far....2
Leader in right part... 2 at index 2
Leader count so far....3
Leader in right part... 1 at index 3
Leader count so far....4
Leader in right part... 0 at index 4
Leader count so far....4
Leader in right part... 0 at index 5

Solution 2

"""

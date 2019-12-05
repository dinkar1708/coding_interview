def solution(N, A):
    """
    Solution at 100% - https://app.codility.com/demo/results/trainingUQ95SB-4GA/
    Idea is first take the counter array of given size N
    take item from main A one by one + 1 and put in counter array , use item as index
    keep track of last max operation
    at the end replace counter items with max of local or counter item it self
    :param N:
    :param A:
    :return:
    """
    global_max = 0
    local_max = 0
    # counter array
    counter = [0] * N

    for i, item in enumerate(A):
        # take item from original array one by one - 1 - minus due to using item as index
        item_as_counter_index = item - 1
        # print(item_as_counter_index)
        # print(counter)
        # print(local_max)
        # current element less or equal value in array and greater than 1
        #         if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
        if N >= item >= 1:
            # max of local_max counter at item_as_counter_index
            # increase counter array value and put in counter array
            counter[item_as_counter_index] = max(local_max, counter[item_as_counter_index]) + 1
            # track the status of global_max counter so far
            # this is operation K
            global_max = max(global_max, counter[item_as_counter_index])
        #         if A[K] = N + 1 then operation K is max counter.
        elif item == N + 1:
            # now operation k is as local max
            # here we need to replace all items in array with this global max
            # we can do using for loop for array length but that will cost bigo n2 complexity
            # example -  for i, item in A: counter[i] = global_max
            local_max = global_max
        # print("global_max each step")
        # print(global_max)

    # print("local max so far....")
    # print(local_max)
    # print("counter - ")
    # print(counter)
    # now counter array - replace all elements which are less than the local max found so far
    # all counters are set to the maximum value of any counter
    for i, item in enumerate(counter):
        counter[i] = max(item, local_max)

    return counter


result = solution(1, [3, 4, 4, 6, 1, 4, 4])
print("Sol " + str(result))

"""
You are given N counters, initially set to 0, and you have two possible operations on them:

        increase(X) − counter X is increased by 1,
        max counter − all counters are set to the maximum value of any counter.

A non-empty zero-indexed array A of M integers is given. This array represents consecutive operations:

        if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
        if A[K] = N + 1 then operation K is max counter.

For example, given integer N = 5 and array A such that:

    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4

the values of the counters after each consecutive operation will be:

    (0, 0, 1, 0, 0)
    (0, 0, 1, 1, 0)
    (0, 0, 1, 2, 0)
    (2, 2, 2, 2, 2)
    (3, 2, 2, 2, 2)
    (3, 2, 2, 3, 2)
    (3, 2, 2, 4, 2)

The goal is to calculate the value of every counter after all operations.

Write a function:

    vector<int> solution(int N, vector<int> &A);

that, given an integer N and a non-empty zero-indexed array A consisting of M integers, returns a sequence of integers representing the values of the counters.

The sequence should be returned as:

        a structure Results (in C), or
        a vector of integers (in C++), or
        a record Results (in Pascal), or
        an array of integers (in any other programming language).

For example, given:

    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4

the function should return [3, 2, 2, 4, 2], as explained above.

Assume that:

        N and M are integers within the range [1..100,000];
        each element of array A is an integer within the range [1..N + 1].

Complexity:

        expected worst-case time complexity is O(N+M);
        expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).

Elements of input arrays can be modified.
"""

from math import sqrt


def solution(A):
    """
    Problem Statement at -
    https://app.codility.com/demo/results/trainingZJ5JA3-FDE/
    Codility 100%

    Idea is to find prefix sum for each peak.
    Traverse for each possible blocks up to square root of the length of the array.

    For example, given:

    A[0] = 1
    A[1] = 2
    A[2] = 3
    A[3] = 4
    A[4] = 3
    A[5] = 4
    A[6] = 1
    A[7] = 2
    A[8] = 3
    A[9] = 4
    A[10] = 6
    A[11] = 2

    Max 3 blocks each has peak -

    three blocks (1, 2, 3, 4), (3, 4, 1, 2), (3, 4, 6, 2). Every block has a peak.
    Notice in particular that the first block (1, 2, 3, 4) has a peak at A[3],
    because A[2] < A[3] > A[4], even though A[4] is in the adjacent block.

    :param N:
    :return:
    """

    len_ar = len(A)
    print(len_ar)
    peaks_prefix_sum = [0] * len_ar
    for index in range(1, len_ar - 1):
        peaks_prefix_sum[index] = peaks_prefix_sum[index - 1]
        # as per peaks condition
        if A[index] > A[index - 1] and A[index] > A[index + 1]:
            peaks_prefix_sum[index] += 1
    print(peaks_prefix_sum)

    if len_ar < 3 or peaks_prefix_sum[-2] == 0:
        # Too short to have a peak or no peak
        return 0
    # put item from second last in to last item
    peaks_prefix_sum[-1] = peaks_prefix_sum[-2]
    max_blocks = 0
    # Find out the maximum block
    for candidate in range(1, int(sqrt(len_ar)) + 1, 1):
        print("")
        if len_ar % candidate == 0:
            print("Valid factor " + str(candidate))
            blocks, block_size = candidate, len_ar // candidate
            print("blocks " + str(blocks))
            print("block_size " + str(block_size))

            # Check the first block.
            print("Prefix sum at index 0 and block size -1 ")
            print(peaks_prefix_sum[0])
            print(peaks_prefix_sum[block_size - 1])
            if peaks_prefix_sum[0] < peaks_prefix_sum[block_size - 1]:
                # Check the following blocks.
                for each_block in range(block_size, len_ar, block_size):
                    print("Checking for block " + str(each_block))
                    if peaks_prefix_sum[each_block - 1] == peaks_prefix_sum[each_block + block_size - 1]:
                        # no peak if sound
                        break
                else:
                    # if no peak found blocks is max block
                    print("This block can be the max block " + str(blocks))
                    print("lets check for other block ")
                    max_blocks = blocks
            if candidate * candidate == len_ar:
                print("Continue with candidate........")
                # If candidate is equal to sqrt(len_ar) exactly, candidate would equal to len_ar//candidate.
                continue
            block_size, blocks = candidate, len_ar // candidate
            print("Another Check..........")
            # Check the first block.
            if peaks_prefix_sum[0] < peaks_prefix_sum[block_size - 1]:
                # Check the following blocks.
                for each_block in range(block_size, len_ar, block_size):
                    print("Again Checking for block " + str(each_block))

                    if peaks_prefix_sum[each_block - 1] == peaks_prefix_sum[each_block + block_size - 1]:
                        # no peak if sound
                        break
                else:
                    print("This block is the max block " + str(blocks))
                    return blocks
    print("Return max_blocks " + str(max_blocks))
    return max_blocks


if __name__ == '__main__':
    result = solution([1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2])
    # result = solution([1, 21, 3, 1, 31, 4])

    print("")
    print("Solution " + str(result))

"""
Example1-
12
[0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 3, 0]

Valid factor 1
blocks 1
block_size 12
Prefix sum at index 0 and block size -1 
0
3
This block can be the max block 1
lets check for other block 
Another Check..........

Valid factor 2
blocks 2
block_size 6
Prefix sum at index 0 and block size -1 
0
2
Checking for block 6
This block can be the max block 2
lets check for other block 
Another Check..........

Valid factor 3
blocks 3
block_size 4
Prefix sum at index 0 and block size -1 
0
1
Checking for block 4
Checking for block 8
This block can be the max block 3
lets check for other block 
Another Check..........
Return max_blocks 3

Solution 3


Example2- 
6
[0, 1, 1, 1, 2, 0]

Valid factor 1
blocks 1
block_size 6
Prefix sum at index 0 and block size -1 
0
2
This block can be the max block 1
lets check for other block 
Another Check..........

Valid factor 2
blocks 2
block_size 3
Prefix sum at index 0 and block size -1 
0
1
Checking for block 3
This block can be the max block 2
lets check for other block 
Another Check..........
Again Checking for block 2
Return max_blocks 2

Solution 2

"""

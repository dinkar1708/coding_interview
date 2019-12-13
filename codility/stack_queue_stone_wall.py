def solution(H):
    """
    Codility 100%
    https://app.codility.com/demo/results/trainingQKD6JP-PHA/

    Idea is to use stack concept
    Compute the minimum number of blocks needed to build the wall.
    To build the wall start taking blocks of height one by one.
    We need to take care of -
     - the blocked should not be used again
     - this is done only up to blocks height is greater than current
     - why we are using last 8 height block if there is already 8 height block used in previous step?
        reason is 8 is not present in stack top


    8,
     8,----- can not use because on stack top 8 is already there
      5,
       7,
        9,
         8,
          7,------ can not use because on stack top 7 is already there
           4,
            8,

    This is just example with height, see steps of output for details
     skip8           skip7
                |           |
    |           |   |       |
    |       |   |   |       |
    |       |   |   |       |
    |   |   |   |   |       |
    |   |   |   |   |   |   |
    |   |   |   |   |   |   |
    |   |   |   |   |   |   |
    |   |   |   |   |   |   |

Used blocks-
    8
    5
    7
    9
    8
    4
    8

    """

    block_count = 0
    # stack is used to hold height used to building and remove all the blocks from it,
    #  if any of the block of stack is greater than current block(to be added for building)
    stack = []
    for height in H:
        print(" ")
        print("Current Height " + str(height))
        print("Current stack " + str(stack))
        # Remove all blocks that are bigger than current height, stack should not be empty
        while stack and stack[-1] > height:
            stack.pop()
        print("After remove bigger blocks than current height, stack is " + str(stack))

        # stack is not empty and top item of stack is equal to current height
        if stack and stack[-1] == height:
            # Already used this size of block
            print("Already used this size of block " + str(height))
            continue
        else:
            # new block is required, push it's size to the stack
            block_count += 1
            stack.append(height)
            print("Add this block.... " + str(height) + " Minimum Blocks " + str(block_count))

    return block_count


if __name__ == '__main__':
    result = solution([8, 8, 5, 7, 9, 8, 7, 4, 8])
    print("")
    print("Solution " + str(result))

"""
Current Height 8
Current stack []
After remove bigger blocks than current height, stack is []
Add this block.... 8 Minimum Blocks 1
 
Current Height 8
Current stack [8]
After remove bigger blocks than current height, stack is [8]
Already used this size of block 8
 
Current Height 5
Current stack [8]
After remove bigger blocks than current height, stack is []
Add this block.... 5 Minimum Blocks 2
 
Current Height 7
Current stack [5]
After remove bigger blocks than current height, stack is [5]
Add this block.... 7 Minimum Blocks 3
 
Current Height 9
Current stack [5, 7]
After remove bigger blocks than current height, stack is [5, 7]
Add this block.... 9 Minimum Blocks 4
 
Current Height 8
Current stack [5, 7, 9]
After remove bigger blocks than current height, stack is [5, 7]
Add this block.... 8 Minimum Blocks 5
 
Current Height 7
Current stack [5, 7, 8]
After remove bigger blocks than current height, stack is [5, 7]
Already used this size of block 7
 
Current Height 4
Current stack [5, 7]
After remove bigger blocks than current height, stack is []
Add this block.... 4 Minimum Blocks 6
 
Current Height 8
Current stack [4]
After remove bigger blocks than current height, stack is [4]
Add this block.... 8 Minimum Blocks 7

Solution 7
"""

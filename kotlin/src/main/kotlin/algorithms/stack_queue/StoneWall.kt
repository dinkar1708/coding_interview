package algorithms.stack_queue

/*
Problem: Stone Wall
Codility 100%

Compute the minimum number of blocks needed to build a wall.

You are going to build a stone wall. The wall should be straight and N meters long,
and its thickness should be constant; however, it should have different heights in different places.
The height of the wall is specified by an array H of N positive integers.
H[I] is the height of the wall from I to I+1 meters to the right of its left end.

In particular, H[0] is the height of the wall's left end and H[N−1] is the height of the wall's right end.

The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular).
Your task is to compute the minimum number of blocks needed to build the wall.

Idea: Use stack concept
- Take blocks of height one by one
- A block should not be used again (only if it's not on stack top)
- This is done only when blocks height is greater than current height
*/

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = stoneWall(intArrayOf(8, 8, 5, 7, 9, 8, 7, 4, 8))
    println("Result: $result1")
    println("Expected: 7 blocks")
    println()

    // Test 2: Edge case - empty array
    println("=== Test 2: Edge case - empty array ===")
    val result2 = stoneWall(intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - single height
    println("=== Test 3: Edge case - single height ===")
    val result3 = stoneWall(intArrayOf(5))
    println("Result: $result3")
    println("Expected: 1")
    println()

    // Test 4: All same height
    println("=== Test 4: All same height ===")
    val result4 = stoneWall(intArrayOf(4, 4, 4, 4, 4))
    println("Result: $result4")
    println("Expected: 1 (only one block needed)")
    println()

    // Test 5: Ascending heights
    println("=== Test 5: Ascending heights ===")
    val result5 = stoneWall(intArrayOf(1, 2, 3, 4, 5))
    println("Result: $result5")
    println("Expected: 5 (need new block for each height)")
    println()

    // Test 6: Descending heights
    println("=== Test 6: Descending heights ===")
    val result6 = stoneWall(intArrayOf(5, 4, 3, 2, 1))
    println("Result: $result6")
    println("Expected: 5 (need new block for each height)")
}

// TIME COMPLEXITY: O(n) - single pass through heights with stack operations
// SPACE COMPLEXITY: O(n) - stack can hold up to n heights

private fun stoneWall(H: IntArray): Int {
    // Edge case: empty array
    if (H.isEmpty()) {
        return 0
    }

    var blockCount = 0
    // Stack holds heights currently used for building
    val stack = mutableListOf<Int>()

    for (height in H) {
        println()
        println("Current Height $height")
        println("Current stack $stack")

        // Remove all blocks that are bigger than current height
        while (stack.isNotEmpty() && stack.last() > height) {
            stack.removeAt(stack.size - 1)
        }
        println("After remove bigger blocks than current height, stack is $stack")

        // Check if stack is not empty and top item equals current height
        if (stack.isNotEmpty() && stack.last() == height) {
            // Already used this size of block
            println("Already used this size of block $height")
            continue
        } else {
            // New block is required, push it to the stack
            blockCount += 1
            stack.add(height)
            println("Add this block.... $height")
        }
    }

    return blockCount
}

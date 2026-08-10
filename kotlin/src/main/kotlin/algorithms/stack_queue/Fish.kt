package algorithms.stack_queue

/*
Problem: Fish River Eat

You are given two arrays A and B consisting of N integers.
Arrays A and B represent N voracious fish in a river.
The fish are numbered from 0 to N − 1. Fish number P is represented by A[P] and B[P].

Array A contains the sizes of the fish. All its elements are unique.
Array B contains the directions of the fish:
  - 0 represents a fish flowing upstream (left direction)
  - 1 represents a fish flowing downstream (right direction)

If two fish move in opposite directions and there are no other (living) fish between them,
they will eventually meet each other. Then only one fish can stay alive − the larger one.

Return the number of fish that will stay alive.
*/

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = fishRiverEat(intArrayOf(4, 3, 2, 1, 5), intArrayOf(0, 1, 0, 0, 0))
    println("Result: $result1")
    println("Expected: 2 (fish 4 and 5 survive)")
    println()

    // Test 2: Edge case - empty arrays
    println("=== Test 2: Edge case - empty arrays ===")
    val result2 = fishRiverEat(intArrayOf(), intArrayOf())
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - single fish
    println("=== Test 3: Edge case - single fish ===")
    val result3 = fishRiverEat(intArrayOf(5), intArrayOf(1))
    println("Result: $result3")
    println("Expected: 1")
    println()

    // Test 4: All fish going same direction (downstream)
    println("=== Test 4: All fish going downstream ===")
    val result4 = fishRiverEat(intArrayOf(3, 4, 5, 6), intArrayOf(1, 1, 1, 1))
    println("Result: $result4")
    println("Expected: 4 (no collisions)")
    println()

    // Test 5: All fish going same direction (upstream)
    println("=== Test 5: All fish going upstream ===")
    val result5 = fishRiverEat(intArrayOf(3, 4, 5, 6), intArrayOf(0, 0, 0, 0))
    println("Result: $result5")
    println("Expected: 4 (no collisions)")
    println()

    // Test 6: One big fish eats all
    println("=== Test 6: One big fish eats all ===")
    val result6 = fishRiverEat(intArrayOf(1, 2, 3, 10, 5), intArrayOf(0, 0, 0, 1, 0))
    println("Result: $result6")
    println("Expected: 4 (fish 10 eats fish 5, others survive)")
}

// TIME COMPLEXITY: O(n) - single pass through fish arrays
// SPACE COMPLEXITY: O(n) - stack can hold up to n fish

private fun fishRiverEat(A: IntArray, B: IntArray): Int {
    // Edge case: empty arrays or length mismatch
    if (A.isEmpty() || B.isEmpty() || A.size != B.size) {
        return 0
    }

    var stayAlive = 0
    val stack = mutableListOf<Int>()

    for (index in A.indices) {
        val currentWeight = A[index]
        val currentDirection = B[index]

        if (currentDirection == 0) {
            // Fish going left/upstream
            // Check if it will meet downstream fish in stack
            var weightDownStream = if (stack.isNotEmpty()) stack.removeAt(stack.size - 1) else -1

            while (weightDownStream != -1 && weightDownStream < currentWeight) {
                // Current fish is bigger, it eats the downstream fish
                weightDownStream = if (stack.isNotEmpty()) stack.removeAt(stack.size - 1) else -1
            }

            if (weightDownStream == -1) {
                // No more downstream fish, current fish survives
                stayAlive += 1
            } else {
                // Downstream fish was bigger, put it back on stack
                stack.add(weightDownStream)
            }
        } else {
            // Fish going right/downstream, add to stack
            stack.add(currentWeight)
        }
    }

    // All fish remaining in stack survive
    return stack.size + stayAlive
}

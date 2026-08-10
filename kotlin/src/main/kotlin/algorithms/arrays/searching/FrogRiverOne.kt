package algorithms.arrays.searching

/**
 * PROBLEM: Frog River One - Find Earliest Time to Cross River
 *
 * A frog wants to cross a river from position 0 to position X.
 * Leaves fall on the river at different positions over time.
 * Find the earliest time when the frog can jump to the other side.
 * The frog can cross only when leaves appear at every position from 1 to X.
 *
 * INPUT:
 *   X = 5
 *   A = [1, 3, 1, 4, 2, 3, 5, 4]
 *
 * OUTPUT:
 *   6 (at time 6, all positions 1-5 are covered)
 *
 * EXPLANATION:
 *   Time 0: Position 1 covered → {1}
 *   Time 1: Position 3 covered → {1, 3}
 *   Time 2: Position 1 (duplicate) → {1, 3}
 *   Time 3: Position 4 covered → {1, 3, 4}
 *   Time 4: Position 2 covered → {1, 2, 3, 4}
 *   Time 5: Position 3 (duplicate) → {1, 2, 3, 4}
 *   Time 6: Position 5 covered → {1, 2, 3, 4, 5} ✓
 *
 *   Use a set to track unique positions covered.
 *   When set size equals X, all positions are covered.
 *
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(X) - set to store positions
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result = frogRiverOne(5, intArrayOf(1, 3, 1, 4, 2, 3, 5, 4))
    println("Input: X = 5, A = [1, 3, 1, 4, 2, 3, 5, 4]")
    println("Result: $result")
    println("Expected: 6")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val result2 = frogRiverOne(5, intArrayOf())
    println("Input: X = 5, A = []")
    println("Result: $result2")
    println("Expected: -1")
    println()

    // Test 3: X = 1 (single position)
    println("=== Test 3: X = 1 (single position) ===")
    val result3 = frogRiverOne(1, intArrayOf(1))
    println("Input: X = 1, A = [1]")
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: Impossible to cross (missing position)
    println("=== Test 4: Impossible to cross (missing position) ===")
    val result4 = frogRiverOne(5, intArrayOf(1, 2, 3, 4))
    println("Input: X = 5, A = [1, 2, 3, 4]")
    println("Result: $result4")
    println("Expected: -1 (position 5 never appears)")
    println()

    // Test 5: All positions covered immediately
    println("=== Test 5: All positions covered immediately ===")
    val result5 = frogRiverOne(3, intArrayOf(3, 1, 2))
    println("Input: X = 3, A = [3, 1, 2]")
    println("Result: $result5")
    println("Expected: 2")
    println()

    // Test 6: X = 0 (invalid)
    println("=== Test 6: X = 0 (invalid) ===")
    val result6 = frogRiverOne(0, intArrayOf(1, 2, 3))
    println("Input: X = 0, A = [1, 2, 3]")
    println("Result: $result6")
    println("Expected: -1")
    println()

    // Test 7: Large gaps in positions
    println("=== Test 7: Large gaps in positions ===")
    val result7 = frogRiverOne(3, intArrayOf(1, 1, 1, 2, 3))
    println("Input: X = 3, A = [1, 1, 1, 2, 3]")
    println("Result: $result7")
    println("Expected: 4")
}

// TIME COMPLEXITY: O(n) - single pass through array, set operations are O(1) average
// SPACE COMPLEXITY: O(X) - set stores at most X unique positions
private fun frogRiverOne(X: Int, A: IntArray): Int {
    // Edge case: X <= 0
    if (X <= 0) {
        return -1
    }

    // Edge case: empty array
    if (A.isEmpty()) {
        return -1
    }

    val positions = mutableSetOf<Int>()

    for ((index, item) in A.withIndex()) {
        // Add the position where leaf falls
        positions.add(item)

        // Check if all positions from 1 to X are covered
        if (positions.size == X) {
            return index
        }
    }

    // Not all positions covered
    return -1
}

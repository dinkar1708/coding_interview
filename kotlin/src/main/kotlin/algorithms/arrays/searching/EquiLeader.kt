package algorithms.arrays.searching


/**
 * PROBLEM: Count Equi Leaders
 *
 * An equi leader is an index S such that 0 ≤ S < N − 1 where sequences
 * A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders
 * of the same value.
 *
 * INPUT:
 *   A = [4, 3, 4, 4, 4, 2]
 *
 * OUTPUT:
 *   2 (equi leaders at indices 0 and 2)
 *
 * EXPLANATION:
 *   First find the dominator (leader) of the entire array using Boyer-Moore algorithm.
 *   For array [4, 3, 4, 4, 4, 2], the leader is 4 (appears 4 times out of 6).
 *
 *   At index 0: Left=[4] has leader 4, Right=[3,4,4,4,2] has leader 4 → Equi leader
 *   At index 2: Left=[4,3,4] has leader 4, Right=[4,4,2] has leader 4 → Equi leader
 *
 *   For each position, check if both left and right parts have the same leader value
 *   and that value appears more than half the elements in each part.
 *
 * Time Complexity: O(n) - two passes through array
 * Space Complexity: O(1) - constant space
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val testArray = intArrayOf(4, 3, 4, 4, 4, 2)
    val result = findEquiLeader(testArray)
    println("Input: ${testArray.contentToString()}")
    println("Result: $result")
    println("Expected: 2")
    println()

    // Test 2: Empty array
    println("=== Test 2: Empty array ===")
    val arr2 = intArrayOf()
    val result2 = findEquiLeader(arr2)
    println("Input: ${arr2.contentToString()}")
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Single element
    println("=== Test 3: Single element ===")
    val arr3 = intArrayOf(1)
    val result3 = findEquiLeader(arr3)
    println("Input: ${arr3.contentToString()}")
    println("Result: $result3")
    println("Expected: 0 (cannot split into two parts)")
    println()

    // Test 4: Two elements - same value
    println("=== Test 4: Two elements - same value ===")
    val arr4 = intArrayOf(5, 5)
    val result4 = findEquiLeader(arr4)
    println("Input: ${arr4.contentToString()}")
    println("Result: $result4")
    println("Expected: 1")
    println()

    // Test 5: No dominator
    println("=== Test 5: No dominator ===")
    val arr5 = intArrayOf(1, 2, 3, 4, 5)
    val result5 = findEquiLeader(arr5)
    println("Input: ${arr5.contentToString()}")
    println("Result: $result5")
    println("Expected: 0")
    println()

    // Test 6: All same elements
    println("=== Test 6: All same elements ===")
    val arr6 = intArrayOf(7, 7, 7, 7, 7)
    val result6 = findEquiLeader(arr6)
    println("Input: ${arr6.contentToString()}")
    println("Result: $result6")
    println("Expected: 4 (all positions are equi leaders)")
}

// TIME COMPLEXITY: O(n) - two passes: one for Boyer-Moore to find leader, one to count equi leaders
// SPACE COMPLEXITY: O(1) - only constant variables for counting
private fun findEquiLeader(A: IntArray): Int {
    // Edge case: empty array
    if (A.isEmpty()) {
        return 0
    }

    // Edge case: single element (cannot split into two parts)
    if (A.size == 1) {
        return 0
    }

    // Find the dominator candidate using Boyer-Moore algorithm
    var dominatorValue = -1
    var size = 0

    for ((i, item) in A.withIndex()) {
        if (size == 0) {
            // First time or reset - new element found
            size += 1
            dominatorValue = item
        } else {
            if (dominatorValue == item) {
                // Same element as candidate
                size += 1
            } else {
                // Different element found, decrease counter
                size -= 1
            }
        }
    }

    val arLen = A.size

    // Count how many times the candidate appears
    val leaderCount = A.count { it == dominatorValue }

    if (leaderCount <= arLen / 2) {
        // Candidate is not the leader
        return 0
    }

    // Find equi leaders
    var equiLeaders = 0
    var leaderCountSoFar = 0

    for (index in 0 until arLen) {
        // Count leader occurrences up to current index
        if (A[index] == dominatorValue) {
            leaderCountSoFar += 1
        }

        // Leader count in right part
        val leaderInRightPart = leaderCount - leaderCountSoFar

        // Check if both left and right parts have the same leader
        // Left part: indices 0 to index (size = index + 1)
        // Right part: indices index+1 to arLen-1 (size = arLen - index - 1)
        if (leaderCountSoFar > (index + 1) / 2 &&
            leaderInRightPart > (arLen - index - 1) / 2) {
            equiLeaders += 1
        }
    }

    return equiLeaders
}

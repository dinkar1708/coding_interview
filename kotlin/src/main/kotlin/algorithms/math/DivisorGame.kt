package algorithms.math

/**
 * PROBLEM: Divisor Game
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 * Initially, there is a number N on the chalkboard. On each player's turn,
 * that player makes a move consisting of:
 * - Choosing any x with 0 < x < N and N % x == 0
 * - Replacing the number N on the chalkboard with N - x
 *
 * The game ends when someone cannot make a move (N becomes 0 or no valid x exists).
 * The player who cannot make a move loses.
 *
 * Return true if Alice wins the game, assuming both play optimally.
 *
 * PATTERN: If N is even, Alice wins. If N is odd, Bob wins.
 *
 * INPUT: n = 2
 * OUTPUT: true
 * EXPLANATION: Alice chooses 1, N becomes 1. Bob cannot make a move, Alice wins.
 *
 * INPUT: n = 3
 * OUTPUT: false
 * EXPLANATION: Alice can only choose 1, N becomes 2. Bob chooses 1, N becomes 1. Alice loses.
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

fun main() {
    // Test 1: Normal case - even number
    println("=== Test 1: Normal case - even number ===")
    val result1 = divisorGame(2)
    println("Result: $result1")
    println("Expected: true (Alice wins)")
    println()

    // Test 2: Normal case - odd number
    println("=== Test 2: Normal case - odd number ===")
    val result2 = divisorGame(3)
    println("Result: $result2")
    println("Expected: false (Bob wins)")
    println()

    // Test 3: Edge case - one
    println("=== Test 3: Edge case - one ===")
    val result3 = divisorGame(1)
    println("Result: $result3")
    println("Expected: false (Alice cannot move)")
    println()

    // Test 4: Edge case - zero
    println("=== Test 4: Edge case - zero ===")
    val result4 = divisorGame(0)
    println("Result: $result4")
    println("Expected: false")
    println()

    // Test 5: Large even number
    println("=== Test 5: Large even number ===")
    val result5 = divisorGame(100)
    println("Result: $result5")
    println("Expected: true (Alice wins)")
    println()

    // Test 6: Large odd number
    println("=== Test 6: Large odd number ===")
    val result6 = divisorGame(31)
    println("Result: $result6")
    println("Expected: false (Bob wins)")
}

// TIME COMPLEXITY: O(log n) - divide by 2 in each recursive call
// SPACE COMPLEXITY: O(log n) - recursion stack depth

private fun divisorGame(n: Int): Boolean {
    // Edge case: n <= 0
    if (n <= 0) {
        return false
    }

    // https://www.programmersought.com/article/63582113860/
    // Alice wins if n is even, loses if n is odd
    return n % 2 == 0
}

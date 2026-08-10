package algorithms.math

/**
 * PROBLEM: Chocolates By Numbers
 *
 * Two positive integers N and M are given. Integer N represents the number of chocolates
 * arranged in a circle, numbered from 0 to N − 1.
 *
 * You start to eat the chocolates. After eating a chocolate you leave only a wrapper.
 * You begin with eating chocolate number 0. Then you omit the next M − 1 chocolates or
 * wrappers on the circle, and eat the following one.
 *
 * More precisely, you eat the following chocolates: 0, M, 2 * M, 3 * M, ...
 * You stop eating when you encounter an empty wrapper.
 *
 * The goal is to count how many chocolates you will eat.
 *
 * INPUT: N = 10, M = 4
 * OUTPUT: 5
 * EXPLANATION: You eat chocolates at positions: 0, 4, 8, 2, 6, then 0 again (wrapper)
 *
 * APPROACH:
 * - We eat chocolates at positions: 0, M, 2M, 3M, ... until we return to position 0
 * - Position k*M mod N cycles back to 0 when k*M = LCM(N, M)
 * - LCM(N, M) = N * M / GCD(N, M)
 * - Number of chocolates eaten = LCM(N, M) / M = N / GCD(N, M)
 *
 * Time Complexity: O(log(min(N, M)))
 * Space Complexity: O(1)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = chocolatesByNumbers(10, 4)
    println("Result: $result1")
    println("Expected: 5 (positions: 0, 4, 8, 2, 6)")
    println()

    // Test 2: Edge case - M = 1
    println("=== Test 2: Edge case - M = 1 ===")
    val result2 = chocolatesByNumbers(10, 1)
    println("Result: $result2")
    println("Expected: 10 (eat all chocolates)")
    println()

    // Test 3: Edge case - N = M
    println("=== Test 3: Edge case - N = M ===")
    val result3 = chocolatesByNumbers(5, 5)
    println("Result: $result3")
    println("Expected: 1 (only eat position 0)")
    println()

    // Test 4: Edge case - coprime numbers
    println("=== Test 4: Edge case - coprime numbers ===")
    val result4 = chocolatesByNumbers(7, 3)
    println("Result: $result4")
    println("Expected: 7 (GCD=1, eat all)")
    println()

    // Test 5: Large N and M
    println("=== Test 5: Large N and M ===")
    val result5 = chocolatesByNumbers(947853, 4453)
    println("Result: $result5")
    println("Expected: N / GCD(N, M)")
    println()

    // Test 6: M divides N
    println("=== Test 6: M divides N ===")
    val result6 = chocolatesByNumbers(12, 4)
    println("Result: $result6")
    println("Expected: 3 (GCD=4, so 12/4=3)")
}

// TIME COMPLEXITY: O(log(min(N,M))) - GCD calculation using Euclidean algorithm
// SPACE COMPLEXITY: O(1) - only constant variables

private fun chocolatesByNumbers(N: Int, M: Int): Int {
    // Edge case: N <= 0 or M <= 0
    if (N <= 0 || M <= 0) {
        return 0
    }

    // Number of chocolates = N / GCD(N, M)
    return N / gcd(N, M)
}

private fun gcd(a: Int, b: Int): Int {
    return if (b == 0) {
        a
    } else {
        gcd(b, a % b)
    }
}

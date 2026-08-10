package algorithms.math

/**
 * PROBLEM: Count Factors
 *
 * A positive integer D is a factor of a positive integer N if there exists an integer M
 * such that N = D * M.
 *
 * For example, 6 is a factor of 24 because M = 4 satisfies the above condition (24 = 6 * 4).
 *
 * Write a function that, given a positive integer N, returns the number of its factors.
 *
 * INPUT: N = 24
 * OUTPUT: 8
 * EXPLANATION: 24 has 8 factors: 1, 2, 3, 4, 6, 8, 12, 24
 *
 * INPUT: N = 35
 * OUTPUT: 4
 * EXPLANATION: 35 has 4 factors: 1, 5, 7, 35
 *
 * APPROACH:
 * - Count factors in pairs by checking divisibility up to sqrt(N)
 * - If N is divisible by i, then both i and N/i are factors
 * - Special case: if i*i == N, count only once (perfect square)
 *
 * Time Complexity: O(√N)
 * Space Complexity: O(1)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case (N=24) ===")
    var result1 = countFactors(24)
    println("Result: $result1")
    println("Expected: 8 (factors: 1,2,3,4,6,8,12,24)")
    println()

    // Test 2: Normal case - prime number
    println("=== Test 2: Prime number (N=7) ===")
    var result2 = countFactors(7)
    println("Result: $result2")
    println("Expected: 2 (factors: 1,7)")
    println()

    // Test 3: Edge case - one
    println("=== Test 3: Edge case - one (N=1) ===")
    var result3 = countFactors(1)
    println("Result: $result3")
    println("Expected: 1 (factor: 1)")
    println()

    // Test 4: Perfect square
    println("=== Test 4: Perfect square (N=16) ===")
    var result4 = countFactors(16)
    println("Result: $result4")
    println("Expected: 5 (factors: 1,2,4,8,16)")
    println()

    // Test 5: Large prime
    println("=== Test 5: Large prime (N=97) ===")
    var result5 = countFactors(97)
    println("Result: $result5")
    println("Expected: 2 (factors: 1,97)")
    println()

    // Test 6: Highly composite number
    println("=== Test 6: Highly composite (N=36) ===")
    var result6 = countFactors(36)
    println("Result: $result6")
    println("Expected: 9 (factors: 1,2,3,4,6,9,12,18,36)")
}

// TIME COMPLEXITY: O(sqrt(N)) - iterate up to square root to find divisors
// SPACE COMPLEXITY: O(1) - only constant variables for counting

private fun countFactors(N: Int): Int {
    // Edge case: N <= 0
    if (N <= 0) {
        return 0
    }

    println(N)
    var count = 0
    var i = 1

    while (i * i <= N) {
        if (N % i == 0) {
            println("Divisible by $i")
            if (i * i == N) {
                count += 1
                println("Count increase by one $count")
            } else {
                count += 2
                println("Also divisible by ${N / i}")
                println("Count increase by two count $count")
            }
        }
        i += 1
    }

    return count
}

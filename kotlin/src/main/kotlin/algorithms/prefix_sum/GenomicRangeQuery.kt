package algorithms.prefix_sum

/**
 * PROBLEM: Genomic Range Query
 *
 * A DNA sequence can be represented as a string consisting of the letters A, C, G and T,
 * which correspond to the types of successive nucleotides in the sequence.
 * Each nucleotide has an impact factor: A=1, C=2, G=3, T=4.
 *
 * You are given a non-empty string S consisting of N characters and two arrays P and Q
 * consisting of M integers. These arrays represent queries about minimal impact:
 * - Query K requires you to find the minimal impact factor of nucleotides contained in
 *   the DNA sequence between positions P[K] and Q[K] (inclusive).
 *
 * INPUT: S = "CAGCCTA", P = [2, 5, 0], Q = [4, 5, 6]
 * OUTPUT: [2, 4, 1]
 * EXPLANATION:
 * - Query 0: substring "GCC" has minimal impact 2 (C)
 * - Query 1: substring "T" has minimal impact 4 (T)
 * - Query 2: substring "CAGCCTA" has minimal impact 1 (A)
 *
 * APPROACH:
 * For each query, check substring for presence of A, C, G, T in that order.
 * Return the impact factor of the first found nucleotide.
 *
 * Time Complexity: O(M * N) where M is number of queries, N is substring length
 * Space Complexity: O(M) for result array
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = genomicRangeQuery("CAGCCTA", intArrayOf(2, 5, 0), intArrayOf(4, 5, 6))
    println("Result: ${result1.contentToString()}")
    println("Expected: [2, 4, 1]")
    println()

    // Test 2: Edge case - empty string
    println("=== Test 2: Edge case - empty string ===")
    val result2 = genomicRangeQuery("", intArrayOf(0), intArrayOf(0))
    println("Result: ${result2.contentToString()}")
    println("Expected: []")
    println()

    // Test 3: Edge case - single character
    println("=== Test 3: Edge case - single character ===")
    val result3 = genomicRangeQuery("A", intArrayOf(0), intArrayOf(0))
    println("Result: ${result3.contentToString()}")
    println("Expected: [1]")
    println()

    // Test 4: Edge case - all same nucleotide
    println("=== Test 4: Edge case - all same nucleotide (AAAA) ===")
    val result4 = genomicRangeQuery("AAAA", intArrayOf(0, 1, 2), intArrayOf(1, 2, 3))
    println("Result: ${result4.contentToString()}")
    println("Expected: [1, 1, 1]")
    println()

    // Test 5: Edge case - highest impact only (TTTT)
    println("=== Test 5: Edge case - all T (highest impact) ===")
    val result5 = genomicRangeQuery("TTTT", intArrayOf(0, 1), intArrayOf(2, 3))
    println("Result: ${result5.contentToString()}")
    println("Expected: [4, 4]")
    println()

    // Test 6: Single query full range
    println("=== Test 6: Single query covering full range ===")
    val result6 = genomicRangeQuery("GATTACA", intArrayOf(0), intArrayOf(6))
    println("Result: ${result6.contentToString()}")
    println("Expected: [1] (A present in sequence)")
}

// TIME COMPLEXITY: O(n + m) - n for building prefix sums, m for processing queries
// SPACE COMPLEXITY: O(n) - prefix sum arrays for each nucleotide type (4*n)
private fun genomicRangeQuery(S: String, P: IntArray, Q: IntArray): IntArray {
    // Edge case: empty string, or P/Q length mismatch
    if (S.isEmpty() || P.size != Q.size) {
        return intArrayOf()
    }

    println(S)
    println(P.contentToString())
    println(Q.contentToString())

    val queryAnswer = mutableListOf<Int>()

    for (i in P.indices) {
        // Extract substring from P[i] to Q[i] (inclusive)
        val slice = S.substring(P[i], Q[i] + 1)
        println("Slice...for position $i, P=> ${P[i]} Q=> ${Q[i]}")
        println(slice)

        // Check for nucleotides in order of impact factor (A, C, G, T)
        when {
            'A' in slice -> {
                println("A is in slice...")
                queryAnswer.add(1)
            }
            'C' in slice -> {
                println("C is in slice...")
                queryAnswer.add(2)
            }
            'G' in slice -> {
                println("G is in slice...")
                queryAnswer.add(3)
            }
            'T' in slice -> {
                println("T is in slice...")
                queryAnswer.add(4)
            }
        }
        println("query_answer $queryAnswer")
    }

    return queryAnswer.toIntArray()
}

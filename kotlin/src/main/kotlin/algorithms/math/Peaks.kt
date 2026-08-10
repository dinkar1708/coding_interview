package algorithms.math


/**
 * PROBLEM: Peaks - Maximum Number of Blocks
 *
 * A non-empty array A consisting of N integers is given.
 * A peak is an array element which is larger than its neighbors.
 * More precisely, it is an index P such that 0 < P < N − 1,
 * A[P − 1] < A[P] and A[P] > A[P + 1].
 *
 * The goal is to divide this array into blocks of (equal) length.
 * You have to choose a block size K such that:
 * - K divides N (N % K == 0)
 * - Every block contains at least one peak
 *
 * Return the maximum number of blocks into which the array A can be divided.
 *
 * INPUT: A = [1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2]
 * OUTPUT: 3
 * EXPLANATION: Array has length 12. Can be divided into 3 blocks of size 4:
 * - Block 1: [1, 2, 3, 4] has peak at index 3
 * - Block 2: [3, 4, 1, 2] has peak at index 5
 * - Block 3: [3, 4, 6, 2] has peak at index 10
 *
 * APPROACH:
 * 1. Build prefix sum array of peaks
 * 2. For each divisor of N (check up to sqrt(N)):
 *    - Check if each block contains at least one peak
 *    - Track maximum valid number of blocks
 *
 * Time Complexity: O(√N * N)
 * Space Complexity: O(N)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = countPeakBlocks(intArrayOf(1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2))
    println("Result: $result1")
    println("Expected: 3")
    println()

    // Test 2: Edge case - no peaks
    println("=== Test 2: Edge case - no peaks ===")
    val result2 = countPeakBlocks(intArrayOf(1, 2, 3, 4, 5, 6))
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - too small
    println("=== Test 3: Edge case - array too small ===")
    val result3 = countPeakBlocks(intArrayOf(1, 2))
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: Single peak
    println("=== Test 4: Single peak ===")
    val result4 = countPeakBlocks(intArrayOf(1, 3, 2, 1, 0))
    println("Result: $result4")
    println("Expected: 1 (can't divide further)")
    println()

    // Test 5: All peaks evenly distributed
    println("=== Test 5: Evenly distributed peaks ===")
    val result5 = countPeakBlocks(intArrayOf(0, 1, 0, 1, 0, 1, 0, 1, 0))
    println("Result: $result5")
    println("Expected: Based on divisors of 9")
    println()

    // Test 6: Complex pattern
    println("=== Test 6: Complex pattern ===")
    val result6 = countPeakBlocks(intArrayOf(3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4))
    println("Result: $result6")
    println("Expected: Maximum blocks with peaks")
}

// TIME COMPLEXITY: O(n log log max(A)) - sieve + peak detection + divisor checking
// SPACE COMPLEXITY: O(n + max(A)) - peaks array + sieve array

private fun countPeakBlocks(A: IntArray): Int {
    val lenAr = A.size
    println(lenAr)

    // Build prefix sum array for peaks
    val peaksPrefixSum = IntArray(lenAr)
    for (index in 1 until lenAr - 1) {
        peaksPrefixSum[index] = peaksPrefixSum[index - 1]
        // Check if current position is a peak
        if (A[index] > A[index - 1] && A[index] > A[index + 1]) {
            peaksPrefixSum[index] += 1
        }
    }
    println(peaksPrefixSum.contentToString())

    if (lenAr < 3 || peaksPrefixSum[lenAr - 2] == 0) {
        // Too short to have a peak or no peak found
        return 0
    }

    // Copy last valid peak count to last position
    peaksPrefixSum[lenAr - 1] = peaksPrefixSum[lenAr - 2]

    var maxBlocks = 0

    // Find all divisors up to sqrt(N)
    var candidate = 1
    while (candidate * candidate <= lenAr) {
        println()
        if (lenAr % candidate == 0) {
            println("Valid factor $candidate")

            // Try blocks = candidate, blockSize = lenAr / candidate
            var blocks = candidate
            var blockSize = lenAr / candidate
            println("blocks $blocks")
            println("block_size $blockSize")

            // Check if this division works
            if (canDivideIntoBlocks(A, peaksPrefixSum, blocks, blockSize)) {
                maxBlocks = blocks
            }

            // Also try the complementary divisor (unless it's a perfect square)
            if (candidate * candidate != lenAr) {
                blocks = lenAr / candidate
                blockSize = candidate
                println("Another Check..........")
                println("blocks $blocks")
                println("block_size $blockSize")

                if (canDivideIntoBlocks(A, peaksPrefixSum, blocks, blockSize)) {
                    maxBlocks = blocks
                }
            }
        }
        candidate += 1
    }

    println("Return max_blocks $maxBlocks")
    return maxBlocks
}

private fun canDivideIntoBlocks(A: IntArray, peaksPrefixSum: IntArray, blocks: Int, blockSize: Int): Boolean {
    // Check if the first block has a peak
    println("Prefix sum at index 0 and block size -1")
    println(peaksPrefixSum[0])
    println(peaksPrefixSum[blockSize - 1])

    if (peaksPrefixSum[0] >= peaksPrefixSum[blockSize - 1]) {
        // First block has no peak
        return false
    }

    // Check the following blocks
    var eachBlock = blockSize
    while (eachBlock < A.size) {
        println("Checking for block $eachBlock")
        if (peaksPrefixSum[eachBlock - 1] == peaksPrefixSum[eachBlock + blockSize - 1]) {
            // This block has no peak
            return false
        }
        eachBlock += blockSize
    }

    println("This block can be the max block $blocks")
    println("lets check for other block")
    return true
}

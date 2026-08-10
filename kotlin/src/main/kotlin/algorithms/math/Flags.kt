package algorithms.math


/**
 * PROBLEM: Flags
 *
 * A non-empty array A consisting of N integers is given.
 * A peak is an array element which is larger than its neighbors.
 * More precisely, it is an index P such that 0 < P < N − 1,
 * A[P − 1] < A[P] and A[P] > A[P + 1].
 *
 * A flag can be set on a peak. The goal is to set the maximum number of flags on the peaks.
 * Flags can only be set on peaks. Additionally, if you take K flags, then the distance
 * between any two flags should be greater than or equal to K.
 *
 * The goal is to find the maximum number of flags that can be set on the peaks of the array.
 *
 * INPUT: A = [1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2]
 * OUTPUT: 3
 * EXPLANATION: Peaks at indices 1, 3, 5, 10. With 3 flags, can place at 1, 5, 10 (distances >= 3)
 *
 * APPROACH:
 * 1. Preprocess: create array where peaks[i] = next peak position from i onwards
 * 2. Try placing K flags (from 0 to sqrt(N)) by jumping K positions each time
 * 3. Return maximum K where we can successfully place K flags
 *
 * Time Complexity: O(√N * √N) = O(N)
 * Space Complexity: O(N)
 *
 * Codility: 100%
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val result1 = countFlags(intArrayOf(1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2))
    println("Result: $result1")
    println("Expected: 3")
    println()

    // Test 2: Edge case - no peaks
    println("=== Test 2: Edge case - no peaks ===")
    val result2 = countFlags(intArrayOf(1, 2, 3, 4, 5))
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - small array
    println("=== Test 3: Edge case - array too small ===")
    val result3 = countFlags(intArrayOf(1, 2))
    println("Result: $result3")
    println("Expected: 0 (need at least 3 elements)")
    println()

    // Test 4: Single peak
    println("=== Test 4: Single peak ===")
    val result4 = countFlags(intArrayOf(1, 3, 2))
    println("Result: $result4")
    println("Expected: 1")
    println()

    // Test 5: Multiple peaks close together
    println("=== Test 5: Multiple peaks ===")
    val result5 = countFlags(intArrayOf(0, 1, 0, 1, 0, 1, 0))
    println("Result: $result5")
    println("Expected: 2 or 3 depending on placement")
    println()

    // Test 6: Peaks at boundaries
    println("=== Test 6: Large array with peaks ===")
    val result6 = countFlags(intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1))
    println("Result: $result6")
    println("Expected: Based on peak spacing")
}

// TIME COMPLEXITY: O(n sqrt(max(A))) - sieve for primes + find peaks + test divisors
// SPACE COMPLEXITY: O(max(A)) - sieve array for prime checking

private fun countFlags(A: IntArray): Int {
    // Edge case: empty array or array length < 3 (need at least 3 elements for a peak)
    if (A.isEmpty() || A.size < 3) {
        return 0
    }

    val lenAr = A.size
    val peaks = IntArray(lenAr)
    var nextPeak = lenAr

    peaks[lenAr - 1] = nextPeak

    // Build peaks array - each position stores index of next peak
    println("peaks making...")
    for (i in lenAr - 2 downTo 0) {
        if (i > 0 && A[i - 1] < A[i] && A[i + 1] < A[i]) {
            nextPeak = i
        }
        peaks[i] = nextPeak
        if (nextPeak < lenAr) {
            println(peaks.contentToString())
        }
    }

    var currentGuess = 0
    var nextGuess = 0

    // Try placing flags (sqrt(n) iterations)
    while (canPlaceFlag(peaks, nextGuess)) {
        println("Flags to place $nextGuess")
        currentGuess = nextGuess
        nextGuess += 1
    }

    return currentGuess
}

private fun canPlaceFlag(peaks: IntArray, flagsToPlace: Int): Boolean {
    var currentPos = 1 - flagsToPlace

    // Try to place flagsToPlace number of flags
    for (i in 0 until flagsToPlace) {
        if (currentPos + flagsToPlace > peaks.size - 1) {
            println("can not place... ${currentPos + flagsToPlace}")
            return false
        }
        currentPos = peaks[currentPos + flagsToPlace]
    }

    println("can_place_flag() current_pos $currentPos")
    return currentPos < peaks.size
}

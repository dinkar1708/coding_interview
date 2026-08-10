package algorithms.strings.anagram

/**
 * PROBLEM: Check Whether Two Strings Are Anagrams
 * https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
 *
 * Two strings are anagrams if they contain the same characters with the same frequencies,
 * just arranged differently.
 *
 * INPUT: "abc" and "acb"
 * OUTPUT: true
 *
 * EXPLANATION:
 * - Count for each character of both strings to be exactly same
 * - Use temporary storage to store count of all characters of first string
 * - In same temporary storage, remove count of all characters of second string
 * - At the end, count for each character must be zero
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) - fixed size array of 256
 */

fun main() {
    // Test 1: Normal case - anagrams
    println("=== Test 1: Normal case - anagrams ===")
    val result1 = isAnagram("abc", "acb")
    println("Result: $result1")
    println("Expected: true")
    println()

    // Test 2: Normal case - not anagrams
    println("=== Test 2: Normal case - not anagrams ===")
    val result2 = isAnagram("abc", "acbaabbbbgggyy")
    println("Result: $result2")
    println("Expected: false")
    println()

    // Test 3: Edge case - empty strings
    println("=== Test 3: Edge case - empty strings ===")
    val result3 = isAnagram("", "")
    println("Result: $result3")
    println("Expected: true (both empty)")
    println()

    // Test 4: Edge case - single character
    println("=== Test 4: Edge case - single character ===")
    val result4 = isAnagram("a", "a")
    println("Result: $result4")
    println("Expected: true")
    println()

    // Test 5: Different lengths
    println("=== Test 5: Different lengths ===")
    val result5 = isAnagram("abc", "abcd")
    println("Result: $result5")
    println("Expected: false")
    println()

    // Test 6: Same characters, different frequencies
    println("=== Test 6: Same characters, different frequencies ===")
    val result6 = isAnagram("aab", "abb")
    println("Result: $result6")
    println("Expected: false")
}

// TIME COMPLEXITY: O(n) - iterate through both strings to count character frequencies
// SPACE COMPLEXITY: O(1) - fixed size array of 256 for ASCII characters
//
// OPTIMIZATION IDEA:
// Current: IntArray(256) with ch.code for Extended ASCII (works for any character)
// Can optimize based on character set:
// - Lowercase only (a-z): Use IntArray(26) with index = ch - 'a'  → 90% memory reduction!
// - Uppercase only (A-Z): Use IntArray(26) with index = ch - 'A'
// - Both (a-z, A-Z): Use IntArray(128) with ch.code (keep .code)
// - ASCII + symbols: Keep IntArray(256) with ch.code (current approach - keep .code)
//
// Example for lowercase optimization:
//   val count = IntArray(26)
//   for (ch in A) count[ch - 'a'] += 1
//   for (ch in B) count[ch - 'a'] -= 1
//
private fun isAnagram(A: String, B: String): Boolean {
    val lenStr1 = A.length
    val lenStr2 = B.length

    if (lenStr1 != lenStr2) {
        return false
    }

    // Max number in ASCII
    val tempStorage = IntArray(256)

    // Count characters in first string
    for (c in A) {
        tempStorage[c.code] += 1
    }
    println("First storage: ${tempStorage.filter { it != 0 }}")

    // Subtract characters from second string
    for (c in B) {
        tempStorage[c.code] -= 1
    }
    println("After remove first second array counts: ${tempStorage.filter { it != 0 }}")

    // Check if all counts are zero
    for (num in tempStorage) {
        if (num != 0) {
            return false
        }
    }

    return true
}

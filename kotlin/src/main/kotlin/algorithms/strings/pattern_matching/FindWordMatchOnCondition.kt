package algorithms.strings.pattern_matching

/**
 * PROBLEM: Find Word Match In List On Condition
 *
 * Given a string and a list of words, find the count of words from the list
 * which are made of the same characters (regardless of count) as the input string.
 *
 * INPUT: str = "aza", list = ["aza", "aaaa", "bbb", "ccc", "azaaaa", "ddd", "a"]
 * OUTPUT: 2 (for "aza" and "azaaaa")
 *
 * EXPLANATION:
 * - Create a character frequency counter for the input string
 * - For each word in the list, create a character frequency counter
 * - Check if both counters have the same character presence (non-zero positions)
 *
 * Time Complexity: O(n * m * 26) where n is list size, m is avg word length
 * Space Complexity: O(26) = O(1)
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    val str1 = "aza"
    val list1 = arrayOf("aza", "aaaa", "bbb", "ccc", "azaaaa", "ddd", "a")
    val result1 = findMatches(str1, list1)
    println("Result: $result1")
    println("Expected: 2 (for 'aza' and 'azaaaa')")
    println()

    // Test 2: Edge case - empty string
    println("=== Test 2: Edge case - empty string ===")
    val str2 = ""
    val list2 = arrayOf("abc", "def")
    val result2 = findMatches(str2, list2)
    println("Result: $result2")
    println("Expected: 0")
    println()

    // Test 3: Edge case - empty list
    println("=== Test 3: Edge case - empty list ===")
    val str3 = "abc"
    val list3 = arrayOf<String>()
    val result3 = findMatches(str3, list3)
    println("Result: $result3")
    println("Expected: 0")
    println()

    // Test 4: Edge case - single character
    println("=== Test 4: Edge case - single character ===")
    val str4 = "a"
    val list4 = arrayOf("a", "aa", "aaa", "b", "ab")
    val result4 = findMatches(str4, list4)
    println("Result: $result4")
    println("Expected: 3 (for 'a', 'aa', 'aaa')")
    println()

    // Test 5: No matches
    println("=== Test 5: No matches ===")
    val str5 = "xyz"
    val list5 = arrayOf("abc", "def", "ghi")
    val result5 = findMatches(str5, list5)
    println("Result: $result5")
    println("Expected: 0")
    println()

    // Test 6: All match
    println("=== Test 6: All match ===")
    val str6 = "ab"
    val list6 = arrayOf("ab", "ba", "aab", "abb", "abab")
    val result6 = findMatches(str6, list6)
    println("Result: $result6")
    println("Expected: 5")
}

// TIME COMPLEXITY: O(n*m) - check n words, each requiring O(m) character counting and comparison
// SPACE COMPLEXITY: O(1) - fixed size arrays (26 letters) for character counting
private fun findMatches(str: String, list: Array<String>): Int {
    if (str.isEmpty() || list.isEmpty()) {
        return 0
    }

    val countFirstInput = count(str)
    var totalMatch = 0

    for (word in list) {
        val countWord = count(word)
        var isSame = true

        // Check if both have the SAME set of characters (ignore frequency)
        // We check if both arrays have non-zero values at the SAME positions
        for (i in countWord.indices) {
            // Case 1: input has char (>0) but word doesn't (=0) → different sets
            // Case 2: word has char (>0) but input doesn't (=0) → different sets
            // If either case is true → not the same character set
            if ((countFirstInput[i] == 0 && countWord[i] != 0) ||
                (countWord[i] == 0 && countFirstInput[i] != 0)) {
                isSame = false
                break
            }
        }

        if (isSame) {
            totalMatch += 1
        }
    }

    return totalMatch
}

// ALTERNATIVE APPROACH 1: Clearer Logic with Boolean Variables
// TIME COMPLEXITY: O(n*m)
// SPACE COMPLEXITY: O(1)
private fun findMatchesClear(str: String, list: Array<String>): Int {
    if (str.isEmpty() || list.isEmpty()) {
        return 0
    }

    val countFirstInput = count(str)
    var totalMatch = 0

    for (word in list) {
        val countWord = count(word)
        var isSame = true

        // Compare character sets using boolean logic (easier to understand)
        for (i in 0 until 26) {
            val inputHasChar = countFirstInput[i] > 0
            val wordHasChar = countWord[i] > 0

            // If one has the character but other doesn't → different sets
            if (inputHasChar != wordHasChar) {
                isSame = false
                break
            }
        }

        if (isSame) {
            totalMatch += 1
        }
    }

    return totalMatch
}

// ALTERNATIVE APPROACH 2: Using Helper Function for Clarity
// TIME COMPLEXITY: O(n*m)
// SPACE COMPLEXITY: O(1)
private fun findMatchesWithHelper(str: String, list: Array<String>): Int {
    if (str.isEmpty() || list.isEmpty()) {
        return 0
    }

    val countFirstInput = count(str)
    var totalMatch = 0

    for (word in list) {
        val countWord = count(word)

        if (hasSameCharacterSet(countFirstInput, countWord)) {
            totalMatch += 1
        }
    }

    return totalMatch
}

// Helper function: Check if two frequency arrays have same character set
private fun hasSameCharacterSet(count1: IntArray, count2: IntArray): Boolean {
    for (i in 0 until 26) {
        val has1 = count1[i] > 0
        val has2 = count2[i] > 0

        // If presence differs → different character sets
        if (has1 != has2) {
            return false
        }
    }
    return true
}

private fun count(str: String): IntArray {
    // Edge case: empty string
    if (str.isEmpty()) {
        return IntArray(26)
    }

    val counter = IntArray(26)
    for (ch in str) {
        counter[ch - 'a'] += 1
    }
    return counter
}

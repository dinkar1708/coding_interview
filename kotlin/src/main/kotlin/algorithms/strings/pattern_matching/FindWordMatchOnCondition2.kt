package algorithms.strings.pattern_matching

/**
 * PROBLEM: Find Word Match In List On Condition (Version 2)
 *
 * Given a string and a list of words, find the count of words from the list
 * which are made of the same characters (regardless of count) as the input string.
 *
 * INPUT: str = "aza", list = ["aza", "aaaa", "bbb", "ccc", "azaaaa", "ddd"]
 * OUTPUT: 2 (for "aza" and "azaaaa")
 *
 * EXPLANATION:
 * - Use a Set to identify unique characters in each string
 * - Compare sets to check if both strings contain the same characters
 *
 * Time Complexity: O(n * m) where n is list size, m is avg word length
 * Space Complexity: O(k) where k is number of unique characters
 */

fun main() {
    // Test 1: Normal case
    println("=== Test 1: Normal case ===")
    solve("aza", arrayOf("aza", "aaaa", "bbb", "ccc", "azaaaa", "ddd"))
    println("Expected: 2 (for 'aza' and 'azaaaa')")
    println()

    // Test 2: Edge case - empty string
    println("=== Test 2: Edge case - empty string ===")
    solve("", arrayOf("abc", "def"))
    println("Expected: 0")
    println()

    // Test 3: Edge case - empty list
    println("=== Test 3: Edge case - empty list ===")
    solve("abc", arrayOf())
    println("Expected: 0")
    println()

    // Test 4: Edge case - single character
    println("=== Test 4: Edge case - single character ===")
    solve("a", arrayOf("a", "aa", "aaa", "b", "ab"))
    println("Expected: 3 (for 'a', 'aa', 'aaa')")
    println()

    // Test 5: No matches
    println("=== Test 5: No matches ===")
    solve("xyz", arrayOf("abc", "def", "ghi"))
    println("Expected: 0")
    println()

    // Test 6: All match
    println("=== Test 6: All match ===")
    solve("ab", arrayOf("ab", "ba", "aab", "abb", "abab"))
    println("Expected: 5")
}

private fun makeDictionary(str: String): Set<Char> {
    // Make a set of unique characters
    return str.toSet()
}

private fun checkKeys(firstDictKey: Set<Char>, secondDictKey: Set<Char>): Boolean {
    // Check if both sets are equal
    println("All count present... $firstDictKey $secondDictKey")
    return firstDictKey == secondDictKey
}

// TIME COMPLEXITY: O(n*m) - check n words against pattern, each O(m) for mapping verification
// SPACE COMPLEXITY: O(m) - maps for pattern-to-word and word-to-pattern character mapping
private fun solve(str: String, list: Array<String>) {
    // Edge case: empty string or empty list
    if (str.isEmpty() || list.isEmpty()) {
        println("Total match.....")
        println(0)
        return
    }

    // Idea is to use Set, check for all keys to be equal for each word in list
    val countFirstInput = makeDictionary(str)
    println(countFirstInput)

    var totalMatch = 0
    for (word in list) {
        val countWord = makeDictionary(word)
        if (checkKeys(countFirstInput, countWord)) {
            println("Match...")
            totalMatch += 1
        }
    }
    println("Total match.....")
    println(totalMatch)
}

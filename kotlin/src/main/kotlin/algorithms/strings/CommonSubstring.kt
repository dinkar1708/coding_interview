package algorithms.strings

/**
 * PROBLEM: Two Strings - Common Substring
 *
 * Given two strings, determine if they share a common substring.
 * A substring may be as small as one character.
 *
 * EXAMPLES:
 *
 * Example 1:
 * Input: s1 = "hello", s2 = "world"
 * Output: "YES"
 * Explanation: They share the substring "o" and "l"
 *
 * Example 2:
 * Input: s1 = "hi", s2 = "world"
 * Output: "NO"
 * Explanation: No common characters
 *
 * CONSTRAINTS:
 * - 1 ≤ |s1|, |s2| ≤ 100,000
 * - Strings consist only of lowercase letters a-z
 *
 * INPUT FORMAT:
 * - First line: number of test cases p
 * - Next p pairs of lines: two strings s1 and s2
 *
 * OUTPUT FORMAT:
 * - For each test case, print "YES" or "NO"
 *
 * SAMPLE INPUT:
 * 2
 * hello
 * world
 * hi
 * world
 *
 * SAMPLE OUTPUT:
 * YES
 * NO
 *
 * APPROACH IDEAS:
 * 1. Brute force: Check all substrings - O(n²m²) - TOO SLOW!
 * 2. HashMap: Count characters in both strings - O(n+m) ✓
 * 3. Array: Since only a-z, use BooleanArray(26) - O(n+m), O(1) space ✓
 *
 * TIME COMPLEXITY TARGET: O(n + m)
 * SPACE COMPLEXITY TARGET: O(1) using array for a-z
 *
 * TODO: Implement and verify solution
 */

fun main() {
    // Test cases
    println(twoStrings("hello", "world"))  // Expected: YES
    println(twoStrings("hi", "world"))     // Expected: NO
}

fun twoStrings(s1: String, s2: String): String {
    // TODO: Implement solution
    // Hint: Use BooleanArray(26) to mark characters from s1
    // Then check if any character in s2 exists in the array

    return "NO"
}

/*
CLARIFYING QUESTIONS TO ASK IN INTERVIEW:
1. Are strings only lowercase a-z, or include uppercase/numbers/unicode?
   Answer: Only lowercase a-z
2. Can strings be empty?
   Answer: No, minimum length is 1
3. Should output be uppercase "YES"/"NO" or lowercase?
   Answer: Uppercase
4. Does a single character count as a substring?
   Answer: Yes
*/

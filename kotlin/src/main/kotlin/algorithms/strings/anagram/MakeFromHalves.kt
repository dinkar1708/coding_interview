package algorithms.strings.anagram

/**
 * PROBLEM: Anagram - Make Two Halves Anagrams
 *
 * Two words are anagrams of one another if their letters can be rearranged to form the other word.
 *
 * Given a string, split it into two contiguous substrings of equal length.
 * Determine the minimum number of characters to change to make the two substrings into anagrams of one another.
 *
 * EXAMPLES:
 *
 * Example 1:
 * Input: "aaabbb"
 * Output: 3
 * Explanation:
 *   Split: "aaa" and "bbb"
 *   Change all 3 'a's to 'b's to get "bbb" and "bbb" (anagrams)
 *
 * Example 2:
 * Input: "ab"
 * Output: 1
 * Explanation:
 *   Split: "a" and "b"
 *   Change 'a' to 'b' to get "b" and "b" (anagrams)
 *
 * Example 3:
 * Input: "abc"
 * Output: -1
 * Explanation: Length is odd, cannot split into two equal parts
 *
 * Example 4:
 * Input: "mnop"
 * Output: 2
 * Explanation:
 *   Split: "mn" and "op"
 *   Change both 'm' and 'n' to match 'o' and 'p'
 *
 * Example 5:
 * Input: "xyyx"
 * Output: 0
 * Explanation:
 *   Split: "xy" and "yx"
 *   Already anagrams! No changes needed
 *
 * Example 6:
 * Input: "xaxbbbxx"
 * Output: 1
 * Explanation:
 *   Split: "xaxb" and "bbxx"
 *   Left:  {x:2, a:1, b:1}
 *   Right: {b:2, x:2}
 *   Change 'a' to 'b' → both have {x:2, b:2}
 *
 * CONSTRAINTS:
 * - 1 ≤ |s| ≤ 10,000
 * - String consists only of lowercase letters a-z
 *
 * INPUT FORMAT:
 * - First line: number of test cases
 * - Next lines: one string per test case
 *
 * OUTPUT FORMAT:
 * - For each test case, print the minimum number of changes, or -1 if impossible
 *
 * APPROACH:
 * 1. Check if string length is odd → return -1
 * 2. Split string into left half and right half
 * 3. Count character frequencies in both halves using HashMap
 * 4. For each character in left half:
 *    - If leftCount > rightCount, add (leftCount - rightCount) to result
 * 5. Return total changes
 *
 * WHY THIS WORKS:
 * - Characters where left has MORE need to be changed
 * - Characters where left has LESS will be filled by changed characters
 * - Only count extras from ONE side (left OR right, both give same answer)
 *
 * TIME COMPLEXITY: O(n) - iterate string to split and count
 * SPACE COMPLEXITY: O(1) - at most 26 characters in HashMap
 *
 * TODO: Implement and verify solution
 */

fun main() {
    // Test cases
    println(anagram("aaabbb"))    // Expected: 3
    println(anagram("ab"))        // Expected: 1
    println(anagram("abc"))       // Expected: -1
    println(anagram("mnop"))      // Expected: 2
    println(anagram("xyyx"))      // Expected: 0
    println(anagram("xaxbbbxx"))  // Expected: 1
}

fun anagram(s: String): Int {
    // TODO: Implement solution

    // Step 1: Check if odd length
    // if (s.length % 2 == 1) return -1

    // Step 2: Split into halves
    // val mid = s.length / 2
    // val left = s.substring(0, mid)
    // val right = s.substring(mid)

    // Step 3: Count frequencies in both halves
    // val leftMap = mutableMapOf<Char, Int>()
    // val rightMap = mutableMapOf<Char, Int>()

    // Step 4: Count extras in left where leftCount > rightCount
    // var changes = 0
    // for ((char, leftCount) in leftMap) {
    //     val rightCount = rightMap.getOrDefault(char, 0)
    //     if (leftCount > rightCount) {
    //         changes += (leftCount - rightCount)
    //     }
    // }

    // Step 5: Return changes

    return -1
}

/*
CLARIFYING QUESTIONS TO ASK IN INTERVIEW:
1. Are strings only lowercase a-z, or include uppercase/numbers/unicode?
   Answer: Only lowercase a-z

2. Can string be empty?
   Answer: Minimum length is 1

3. What if string length is odd?
   Answer: Return -1 (cannot split into equal parts)

4. Do we count extras from left side only, or both sides?
   Answer: Only one side (left OR right, both give same answer)

KEY INSIGHT:
When you change a character in left that's extra,
it automatically fills the missing character in right.
So only count extras from ONE side, not both!

Example:
Left:  {x:2, a:1, b:1}
Right: {b:2, x:2}

Left has 1 extra 'a', Right needs 1 more 'b'
Changing that 1 'a' to 'b' solves BOTH problems!
Answer: 1 (not 2!)
*/

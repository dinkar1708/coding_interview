package algorithms.strings

/**
 * PROBLEM: Find Duplicate Characters Using Array (NO HashMap)
 *
 * Different approaches based on character set:
 * 1. Lowercase only: IntArray(26)
 * 2. Lowercase + Uppercase: IntArray(52) or IntArray(128)
 * 3. ASCII: IntArray(128)
 * 4. Extended ASCII: IntArray(256)
 * 5. Unicode (any character): HashMap needed OR use large array
 *
 * TIME COMPLEXITY: O(n) - iterate string once
 * SPACE COMPLEXITY: O(1) or O(k) where k is character set size
 */

fun main() {
    println("=== Example 1: Lowercase Only ===")
    val str1 = "abcdeffa"
    findDuplicatesLowercase(str1)
    println()

    println("=== Example 2: Lowercase + Uppercase ===")
    val str2 = "AaBbCcAa"
    findDuplicatesLowercaseUppercase(str2)
    println()

    println("=== Example 3: ASCII Characters (letters + numbers + symbols) ===")
    val str3 = "hello123!!@world123"
    findDuplicatesASCII(str3)
    println()

    println("=== Example 4: Extended ASCII ===")
    val str4 = "café§café"
    findDuplicatesExtendedASCII(str4)
    println()

    println("=== Example 5: Print Only Duplicates ===")
    val str5 = "programming"
    printOnlyDuplicates(str5)
    println()

    println("=== Example 6: First Duplicate Character ===")
    val str6 = "abcdefa"
    val firstDup = findFirstDuplicate(str6)
    println("First duplicate: $firstDup")
    println()

    println("=== Example 7: Remove Duplicates ===")
    val str7 = "aabbccdd"
    val noDups = removeDuplicates(str7)
    println("Original: $str7")
    println("After removing duplicates: $noDups")
}

// CASE 1: Lowercase English letters only (a-z)
// TIME: O(n), SPACE: O(26) = O(1)
private fun findDuplicatesLowercase(s: String) {
    println("Input: $s")
    println("Character set: lowercase only (a-z)")

    // Array size 26 for 'a' to 'z'
    val count = IntArray(26)

    // Count each character
    for (ch in s) {
        val index = ch - 'a'  // 'a' -> 0, 'b' -> 1, ..., 'z' -> 25
        count[index]++
    }

    println("Count array: ${count.contentToString()}")

    // Print characters that appear more than once
    print("Duplicates: ")
    for (i in count.indices) {
        if (count[i] > 1) {
            val ch = ('a' + i)
            print("$ch(${count[i]}) ")
        }
    }
    println()
}

// CASE 2: Lowercase + Uppercase (a-z, A-Z)
// TIME: O(n), SPACE: O(128) = O(1)
private fun findDuplicatesLowercaseUppercase(s: String) {
    println("Input: $s")
    println("Character set: lowercase + uppercase (a-z, A-Z)")

    // Use ASCII table (uppercase A=65, lowercase a=97)
    val count = IntArray(128)  // Covers all ASCII

    for (ch in s) {
        count[ch.code]++  // Use ASCII value as index
    }

    print("Duplicates: ")
    for (i in count.indices) {
        if (count[i] > 1) {
            val ch = i.toChar()
            print("$ch(${count[i]}) ")
        }
    }
    println()
}

// CASE 3: ASCII characters (letters, numbers, symbols)
// TIME: O(n), SPACE: O(128) = O(1)
private fun findDuplicatesASCII(s: String) {
    println("Input: $s")
    println("Character set: ASCII (0-127)")

    val count = IntArray(128)  // ASCII is 0-127

    for (ch in s) {
        if (ch.code < 128) {  // Only count ASCII chars
            count[ch.code]++
        }
    }

    print("Duplicates: ")
    for (i in count.indices) {
        if (count[i] > 1) {
            val ch = i.toChar()
            print("'$ch'(${count[i]}) ")
        }
    }
    println()
}

// CASE 4: Extended ASCII (0-255)
// TIME: O(n), SPACE: O(256) = O(1)
private fun findDuplicatesExtendedASCII(s: String) {
    println("Input: $s")
    println("Character set: Extended ASCII (0-255)")

    val count = IntArray(256)  // Extended ASCII is 0-255

    for (ch in s) {
        if (ch.code < 256) {
            count[ch.code]++
        }
    }

    print("Duplicates: ")
    for (i in count.indices) {
        if (count[i] > 1) {
            val ch = i.toChar()
            print("'$ch'(${count[i]}) ")
        }
    }
    println()
}

// CASE 5: Print only duplicate characters (lowercase only)
// TIME: O(n), SPACE: O(26) = O(1)
private fun printOnlyDuplicates(s: String) {
    println("Input: $s")

    val count = IntArray(26)

    for (ch in s) {
        count[ch - 'a']++
    }

    print("Duplicate characters: ")
    var found = false
    for (i in count.indices) {
        if (count[i] > 1) {
            print(('a' + i))
            found = true
        }
    }
    if (!found) {
        print("None")
    }
    println()
}

// CASE 6: Find first duplicate character
// TIME: O(n), SPACE: O(26) = O(1)
private fun findFirstDuplicate(s: String): Char? {
    println("Input: $s")

    val seen = IntArray(26)

    for (ch in s) {
        val index = ch - 'a'
        if (seen[index] == 1) {
            return ch  // Found first duplicate!
        }
        seen[index]++
    }

    return null  // No duplicates
}

// CASE 7: Remove duplicates (keep first occurrence)
// TIME: O(n), SPACE: O(26) = O(1)
private fun removeDuplicates(s: String): String {
    val seen = IntArray(26)
    val result = StringBuilder()

    for (ch in s) {
        val index = ch - 'a'
        if (seen[index] == 0) {
            result.append(ch)
            seen[index] = 1
        }
    }

    return result.toString()
}

/*
================================================================================
QUICK REFERENCE: Which Array Size to Use?
================================================================================

Problem says                        Use
-----------------------------------------
"lowercase English letters"         IntArray(26)
"uppercase English letters"         IntArray(26)
"lowercase + uppercase"             IntArray(52) OR IntArray(128)
"alphanumeric (a-z, A-Z, 0-9)"     IntArray(128)
"ASCII characters"                  IntArray(128)
"extended ASCII / bytes"            IntArray(256)
"any character" / Unicode           HashMap or IntArray(65536) for BMP

================================================================================
HOW TO CALCULATE INDEX:
================================================================================

For lowercase (a-z):
    index = ch - 'a'
    'a' -> 0, 'b' -> 1, ..., 'z' -> 25

For uppercase (A-Z):
    index = ch - 'A'
    'A' -> 0, 'B' -> 1, ..., 'Z' -> 25

For ASCII:
    index = ch.code
    Uses ASCII value directly (0-127)

For lowercase + uppercase (separate ranges):
    if (ch in 'a'..'z') index = ch - 'a'         // 0-25
    if (ch in 'A'..'Z') index = 26 + (ch - 'A')  // 26-51

================================================================================
COMMON PATTERNS:
================================================================================

1. Count frequency:
   for (ch in s) count[ch - 'a']++

2. Check if seen:
   if (count[ch - 'a'] > 0) // already seen

3. Mark as seen:
   count[ch - 'a'] = 1

4. Find duplicates:
   if (count[i] > 1) // is duplicate

5. First occurrence:
   if (count[ch - 'a'] == 0) result.append(ch)

================================================================================
WHY NOT HashMap?
================================================================================

Advantages of Array:
✓ Faster: O(1) access with no hash computation
✓ Less memory overhead
✓ Better cache locality
✓ Simpler code

Use Array when:
✓ Character set is small and known (a-z, ASCII, etc.)
✓ Need maximum performance
✓ Interviews often prefer array for simplicity

Use HashMap when:
- Unicode characters (Japanese, Chinese, emojis, etc.)
- Very large character set
- Unknown character set

For most interview problems with English letters → Use Array! ✓

================================================================================
*/

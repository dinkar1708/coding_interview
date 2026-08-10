# Strings in Kotlin

## Overview
Strings are immutable sequences of characters. String manipulation is one of the most common topics in coding interviews.

## Creating Strings

```kotlin
// String literals
val str1 = "Hello"
val str2 = "World"

// Multi-line strings (raw strings)
val multiline = """
    This is a
    multi-line
    string
""".trimIndent()

// String templates
val name = "Alice"
val greeting = "Hello, $name!"
val expression = "1 + 1 = ${1 + 1}"

// From CharArray
val chars = charArrayOf('H', 'i')
val str = String(chars)
```

## Basic Operations

### Length and Access
```kotlin
val len = str.length
val firstChar = str[0]
val lastChar = str[str.length - 1]
val lastAlt = str.last()

// Safe access
val char = str.getOrNull(100)
val char = str.getOrElse(100) { '?' }
```

### Substrings
```kotlin
val sub = str.substring(0, 5)
val sub = str.substring(5)  // From index to end
val sub = str.take(5)  // First 5 characters
val sub = str.takeLast(5)  // Last 5 characters
val sub = str.drop(5)  // Skip first 5 characters
```

### Searching
```kotlin
val contains = str.contains("Hello")
val contains = "Hello" in str

val index = str.indexOf('o')
val index = str.indexOf("lo")
val lastIndex = str.lastIndexOf('o')

val starts = str.startsWith("Hel")
val ends = str.endsWith("rld")
```

## String Modification

### Case Conversion
```kotlin
val upper = str.uppercase()  // or toUpperCase()
val lower = str.lowercase()  // or toLowerCase()
val capitalized = str.replaceFirstChar { it.uppercase() }
```

### Trimming
```kotlin
val trimmed = "  hello  ".trim()
val trimmed = "  hello  ".trimStart()
val trimmed = "  hello  ".trimEnd()
val trimmed = "###hello###".trim('#')
```

### Replacing
```kotlin
val replaced = str.replace("old", "new")
val replaced = str.replace('o', 'x')
val replacedFirst = str.replaceFirst("old", "new")

// Replace using regex
val cleaned = str.replace(Regex("[^a-zA-Z]"), "")
```

### Splitting
```kotlin
val parts = "a,b,c".split(",")
val parts = "a b c".split(" ")
val parts = "a1b2c3".split(Regex("[0-9]"))

// Limit splits
val parts = "a,b,c,d".split(",", limit = 2)  // ["a", "b,c,d"]
```

### Joining
```kotlin
val list = listOf("a", "b", "c")
val joined = list.joinToString(separator = ",")  // "a,b,c"
val joined = list.joinToString(separator = " ", prefix = "[", postfix = "]")  // "[a b c]"
```

## Character Operations

### Checking Character Types
```kotlin
val isDigit = char.isDigit()
val isLetter = char.isLetter()
val isLetterOrDigit = char.isLetterOrDigit()
val isUpperCase = char.isUpperCase()
val isLowerCase = char.isLowerCase()
val isWhitespace = char.isWhitespace()
```

### Character Conversion
```kotlin
val upper = char.uppercaseChar()
val lower = char.lowercaseChar()
val digit = char.digitToInt()  // '5' -> 5
val char = 5.digitToChar()  // 5 -> '5'
```

## Common Interview Patterns

### Reverse String
```kotlin
fun reverseString(s: String): String {
    return s.reversed()
}

// In-place (with CharArray)
fun reverseString(s: CharArray) {
    var left = 0
    var right = s.size - 1
    while (left < right) {
        val temp = s[left]
        s[left] = s[right]
        s[right] = temp
        left++
        right--
    }
}
```

### Check Palindrome
```kotlin
fun isPalindrome(s: String): Boolean {
    val cleaned = s.lowercase().filter { it.isLetterOrDigit() }
    return cleaned == cleaned.reversed()
}

// Two-pointer approach (more efficient)
fun isPalindrome(s: String): Boolean {
    var left = 0
    var right = s.length - 1

    while (left < right) {
        while (left < right && !s[left].isLetterOrDigit()) left++
        while (left < right && !s[right].isLetterOrDigit()) right--

        if (s[left].lowercaseChar() != s[right].lowercaseChar()) {
            return false
        }
        left++
        right--
    }
    return true
}
```

### Anagram Check
```kotlin
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    return s.toCharArray().sorted() == t.toCharArray().sorted()
}

// Using frequency map (more efficient)
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val freq = IntArray(26)
    for (i in s.indices) {
        freq[s[i] - 'a']++
        freq[t[i] - 'a']--
    }
    return freq.all { it == 0 }
}
```

### First Unique Character
```kotlin
fun firstUniqChar(s: String): Int {
    val freq = mutableMapOf<Char, Int>()

    for (char in s) {
        freq[char] = freq.getOrDefault(char, 0) + 1
    }

    for (i in s.indices) {
        if (freq[s[i]] == 1) return i
    }
    return -1
}
```

### Longest Substring Without Repeating Characters
```kotlin
fun lengthOfLongestSubstring(s: String): Int {
    val seen = mutableSetOf<Char>()
    var left = 0
    var maxLen = 0

    for (right in s.indices) {
        while (s[right] in seen) {
            seen.remove(s[left])
            left++
        }
        seen.add(s[right])
        maxLen = maxOf(maxLen, right - left + 1)
    }

    return maxLen
}
```

### String Compression
```kotlin
fun compress(chars: CharArray): Int {
    var write = 0
    var i = 0

    while (i < chars.size) {
        val char = chars[i]
        var count = 0

        while (i < chars.size && chars[i] == char) {
            i++
            count++
        }

        chars[write++] = char
        if (count > 1) {
            for (c in count.toString()) {
                chars[write++] = c
            }
        }
    }

    return write
}
```

## StringBuilder for Efficient Concatenation

```kotlin
// ❌ Inefficient - creates new string each time
var result = ""
for (i in 0..1000) {
    result += i.toString()
}

// ✅ Efficient - uses StringBuilder
val sb = StringBuilder()
for (i in 0..1000) {
    sb.append(i)
}
val result = sb.toString()

// StringBuilder operations
val sb = StringBuilder()
sb.append("Hello")
sb.append(' ')
sb.append("World")
sb.insert(5, "!")
sb.delete(0, 5)
sb.reverse()
val result = sb.toString()
```

## Regular Expressions

```kotlin
val regex = Regex("[0-9]+")
val hasDigits = regex.containsMatchIn("abc123")
val match = regex.find("abc123")?.value  // "123"
val allMatches = regex.findAll("a1b2c3").map { it.value }.toList()  // ["1", "2", "3"]

// Replace using regex
val cleaned = "a1b2c3".replace(Regex("[0-9]"), "")  // "abc"

// Split using regex
val parts = "a1b2c3".split(Regex("[0-9]"))  // ["a", "b", "c", ""]
```

## Performance Considerations

### Time Complexity
- Access by index: O(1)
- Search: O(n)
- Substring: O(k) where k is substring length
- Concatenation: O(n) - creates new string
- StringBuilder append: O(1) amortized

### Space Complexity
- Strings are immutable: each modification creates new string
- Use StringBuilder for multiple concatenations

## Interview Tips

1. **Strings are immutable** - every modification creates a new string
2. **Use StringBuilder** for multiple concatenations
3. **Consider character arrays** for in-place modifications
4. **Remember Unicode** - some characters take more than one Char
5. **Use string templates** instead of concatenation when possible
6. **Know your string methods** - many problems have built-in solutions

## Common Pitfalls

```kotlin
// ❌ Wrong - inefficient in loop
var result = ""
for (i in 1..1000) {
    result += i  // Creates 1000 new strings!
}

// ✅ Correct - use StringBuilder
val sb = StringBuilder()
for (i in 1..1000) {
    sb.append(i)
}
val result = sb.toString()

// ❌ Wrong - can throw exception
val char = str[100]

// ✅ Correct - safe access
val char = str.getOrNull(100)
```

## String Comparison

```kotlin
// Equality
val equal = str1 == str2  // Compares content
val equal = str1.equals(str2, ignoreCase = true)

// Compare
val comparison = str1.compareTo(str2)  // Returns <0, 0, or >0
```

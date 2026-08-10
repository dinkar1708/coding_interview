# Pattern Matching

## Overview
Pattern matching involves finding occurrences of a pattern within a text, matching patterns with wildcards, or finding strings that satisfy certain conditions.

## Types of Pattern Matching

### 1. Exact String Matching
Find all occurrences of pattern in text

**Naive Approach**:
```python
def find_pattern(text, pattern):
    positions = []
    for i in range(len(text) - len(pattern) + 1):
        if text[i:i+len(pattern)] == pattern:
            positions.append(i)
    return positions
```
- **Time**: O(n × m) where n = len(text), m = len(pattern)
- **Space**: O(1)

**Optimized Algorithms**:
- KMP (Knuth-Morris-Pratt): O(n + m)
- Boyer-Moore: O(n/m) average case
- Rabin-Karp: O(n + m) with rolling hash

### 2. Wildcard Pattern Matching
Pattern with `*` (matches any sequence) and `?` (matches single char)

**Example**:
- Pattern: "a\*b", Text: "aabab" → True
- Pattern: "a?b", Text: "acb" → True
- Pattern: "a?b", Text: "aab" → False

**Dynamic Programming Solution**:
```python
def is_match(text, pattern):
    m, n = len(text), len(pattern)
    dp = [[False] * (n + 1) for _ in range(m + 1)]
    dp[0][0] = True

    # Handle patterns starting with *
    for j in range(1, n + 1):
        if pattern[j-1] == '*':
            dp[0][j] = dp[0][j-1]

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if pattern[j-1] == '*':
                # * matches zero or more characters
                dp[i][j] = dp[i][j-1] or dp[i-1][j]
            elif pattern[j-1] == '?' or text[i-1] == pattern[j-1]:
                # ? or exact match
                dp[i][j] = dp[i-1][j-1]

    return dp[m][n]
```

**Complexity**: O(m × n) time and space

### 3. Regular Expression Matching
Pattern with `.` (any character) and `*` (0 or more of preceding element)

**Example**:
- s = "aa", p = "a*" → True
- s = "ab", p = ".*" → True
- s = "mississippi", p = "mis\*is\*p*." → False

**DP Solution**:
```python
def is_match(s, p):
    m, n = len(s), len(p)
    dp = [[False] * (n + 1) for _ in range(m + 1)]
    dp[0][0] = True

    # Handle patterns like a*, a*b*, etc.
    for j in range(2, n + 1):
        if p[j-1] == '*':
            dp[0][j] = dp[0][j-2]

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if p[j-1] == '*':
                # Check if * matches zero or more
                dp[i][j] = dp[i][j-2]  # zero occurrence
                if p[j-2] == '.' or p[j-2] == s[i-1]:
                    dp[i][j] |= dp[i-1][j]  # one or more
            elif p[j-1] == '.' or p[j-1] == s[i-1]:
                dp[i][j] = dp[i-1][j-1]

    return dp[m][n]
```

### 4. Substring Search Problems

#### Find All Occurrences
```python
# Python built-in
text.find(pattern)  # first occurrence
text.count(pattern)  # count occurrences

# Manual search
def find_all(text, pattern):
    start = 0
    positions = []
    while True:
        pos = text.find(pattern, start)
        if pos == -1:
            break
        positions.append(pos)
        start = pos + 1
    return positions
```

#### Longest Common Substring
Find longest substring common to two strings (contiguous)

**DP Solution**: O(m × n)

#### Shortest Uncommon Substring
Find shortest substring in one string not in another

### 5. Word Pattern Matching
Check if strings follow same pattern

**Example**:
- pattern = "abba", str = "dog cat cat dog" → True
- pattern = "abba", str = "dog cat cat fish" → False

```python
def word_pattern(pattern, s):
    words = s.split()
    if len(pattern) != len(words):
        return False

    char_to_word = {}
    word_to_char = {}

    for char, word in zip(pattern, words):
        if char in char_to_word:
            if char_to_word[char] != word:
                return False
        else:
            char_to_word[char] = word

        if word in word_to_char:
            if word_to_char[word] != char:
                return False
        else:
            word_to_char[word] = char

    return True
```

### 6. String Matching with Conditions
Find words/strings that satisfy certain conditions:
- Starts with prefix
- Ends with suffix
- Contains substring
- Matches length
- Character count constraints

**Trie Data Structure**: Excellent for prefix matching

## Advanced Algorithms

### KMP (Knuth-Morris-Pratt)
```python
def kmp_search(text, pattern):
    # Build LPS (Longest Prefix Suffix) array
    def build_lps(pattern):
        lps = [0] * len(pattern)
        length = 0
        i = 1

        while i < len(pattern):
            if pattern[i] == pattern[length]:
                length += 1
                lps[i] = length
                i += 1
            else:
                if length != 0:
                    length = lps[length - 1]
                else:
                    lps[i] = 0
                    i += 1
        return lps

    lps = build_lps(pattern)
    i = j = 0
    positions = []

    while i < len(text):
        if text[i] == pattern[j]:
            i += 1
            j += 1

        if j == len(pattern):
            positions.append(i - j)
            j = lps[j - 1]
        elif i < len(text) and text[i] != pattern[j]:
            if j != 0:
                j = lps[j - 1]
            else:
                i += 1

    return positions
```

### Rabin-Karp (Rolling Hash)
Useful for multiple pattern matching or plagiarism detection

**Time**: O(n + m) average, O(nm) worst case

## Problem-Solving Strategies

### 1. Identify the Pattern Type
- Exact match → KMP, Boyer-Moore, or built-in
- Wildcard (* and ?) → DP with wildcard rules
- Regex (. and *) → DP with regex rules
- Word/character pattern → Hash maps

### 2. Choose Right Algorithm
| Problem Type | Best Algorithm | Complexity |
|-------------|----------------|------------|
| Single exact match | Built-in find() | O(n) |
| Multiple patterns | KMP or Rabin-Karp | O(n+m) |
| Wildcard matching | DP | O(nm) |
| Regex matching | DP | O(nm) |
| Prefix matching | Trie | O(m) |

### 3. Consider Edge Cases
- Empty pattern or text
- Pattern longer than text
- Special characters in pattern
- Case sensitivity
- Unicode characters

## Interview Tips

1. **Clarify Requirements**:
   - Exact match or pattern with wildcards?
   - Case-sensitive?
   - First occurrence or all occurrences?
   - Return positions or count?

2. **Start Simple**:
   - Begin with naive O(nm) approach
   - Optimize if asked
   - Explain trade-offs

3. **Know When to Use DP**:
   - Wildcard/regex patterns → DP
   - Exact substring → KMP or built-in
   - Multiple patterns → Trie or Aho-Corasick

4. **Common Follow-ups**:
   - "Can you optimize?" → Discuss KMP
   - "Multiple patterns?" → Trie or Aho-Corasick
   - "What if pattern changes?" → Preprocess text instead

## Related Problems
- Implement strStr() / indexOf()
- Repeated Substring Pattern
- String Compression
- Longest Repeating Character Replacement
- Minimum Window Substring
- Group Shifted Strings

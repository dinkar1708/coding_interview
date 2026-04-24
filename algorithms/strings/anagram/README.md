# Anagram Problems

## Overview
An anagram is a word or phrase formed by rearranging the letters of another word or phrase, using all original letters exactly once.

**Examples**:
- "listen" and "silent"
- "anagram" and "nagaram"
- "race" and "care"

## Basic Anagram Check

### Method 1: Sorting
```python
def is_anagram(s1, s2):
    return sorted(s1) == sorted(s2)
```
- **Time Complexity**: O(n log n)
- **Space Complexity**: O(n)
- Simple and clean

### Method 2: Character Count (Hash Map)
```python
def is_anagram(s1, s2):
    if len(s1) != len(s2):
        return False

    count = {}
    for char in s1:
        count[char] = count.get(char, 0) + 1
    for char in s2:
        if char not in count:
            return False
        count[char] -= 1
        if count[char] < 0:
            return False

    return all(v == 0 for v in count.values())
```
- **Time Complexity**: O(n)
- **Space Complexity**: O(1) - at most 26 letters
- More efficient for large strings

### Method 3: Counter (Python)
```python
from collections import Counter

def is_anagram(s1, s2):
    return Counter(s1) == Counter(s2)
```
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- Most Pythonic

### Method 4: Array Count (Fixed Alphabet)
```python
def is_anagram(s1, s2):
    if len(s1) != len(s2):
        return False

    count = [0] * 26
    for i in range(len(s1)):
        count[ord(s1[i]) - ord('a')] += 1
        count[ord(s2[i]) - ord('a')] -= 1

    return all(c == 0 for c in count)
```
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- Best for lowercase English letters only

## Advanced Anagram Problems

### 1. Group Anagrams
**Problem**: Group strings that are anagrams of each other

**Example**:
Input: ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

**Solution**:
```python
from collections import defaultdict

def group_anagrams(strs):
    groups = defaultdict(list)
    for s in strs:
        key = ''.join(sorted(s))  # or tuple(sorted(s))
        groups[key].append(s)
    return list(groups.values())
```

**Alternative Key** (faster for long strings):
```python
def get_key(s):
    count = [0] * 26
    for c in s:
        count[ord(c) - ord('a')] += 1
    return tuple(count)
```

### 2. Find All Anagrams in String
**Problem**: Find all start indices of anagrams of pattern in text

**Example**:
- s = "cbaebabacd", p = "abc"
- Output: [0, 6] (anagrams: "cba", "bac")

**Sliding Window Approach**:
```python
def find_anagrams(s, p):
    if len(p) > len(s):
        return []

    result = []
    p_count = Counter(p)
    window_count = Counter(s[:len(p)-1])

    for i in range(len(p)-1, len(s)):
        # Add new character to window
        window_count[s[i]] += 1

        # Check if window is anagram
        if window_count == p_count:
            result.append(i - len(p) + 1)

        # Remove leftmost character from window
        left_char = s[i - len(p) + 1]
        window_count[left_char] -= 1
        if window_count[left_char] == 0:
            del window_count[left_char]

    return result
```

**Complexity**: O(n) time, O(1) space

### 3. Valid Anagram with Different Alphabets
Handle Unicode characters, case sensitivity, or special characters

### 4. Minimum Window Substring Containing All Characters
Similar to anagram but doesn't require exact count

## Common Patterns

### Pattern 1: Character Frequency
Most anagram problems boil down to comparing character frequencies

### Pattern 2: Sliding Window
For finding anagrams in a larger string, use sliding window technique

### Pattern 3: Hash Map Key
Use sorted string or character count as hash map key for grouping

## Interview Tips

### When to Use Each Method
1. **Sorting**: Quick to implement, good for single comparisons
2. **Hash Map**: Better time complexity, required for interviews
3. **Array (26 size)**: Best performance for English lowercase only
4. **Sliding Window**: Essential for substring anagram problems

### Edge Cases to Consider
- [ ] Empty strings (are they anagrams? Usually yes)
- [ ] Different lengths (can't be anagrams)
- [ ] Case sensitivity (usually case-insensitive)
- [ ] Spaces and punctuation (usually ignore)
- [ ] Unicode characters (use hash map, not array)
- [ ] Very long strings (prefer O(n) over O(n log n))

### Common Follow-ups
1. "What if strings are very long?" → Use hash map over sorting
2. "What if we have many comparisons?" → Precompute signatures
3. "How to handle Unicode?" → Use Counter or dict
4. "Can you do it in O(1) space?" → Yes, with 26-size array (English only)

## Related Problems
- Palindrome Permutation (check if string can form palindrome)
- Permutation in String (find if permutation of s1 exists in s2)
- Minimum Window Substring
- Longest Substring Without Repeating Characters
- String Compression

## Time Complexity Summary
| Method | Time | Space | Best Use Case |
|--------|------|-------|---------------|
| Sorting | O(n log n) | O(n) | Quick implementation |
| Hash Map | O(n) | O(k) | General case, Unicode |
| Array Count | O(n) | O(1) | English letters only |
| Sliding Window | O(n) | O(1) | Find anagrams in string |

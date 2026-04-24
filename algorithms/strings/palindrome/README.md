# Palindrome Problems

## Overview
A palindrome is a string that reads the same forwards and backwards. Palindrome problems are common in string manipulation interviews.

## Types of Palindrome Problems

### 1. Check if Palindrome
**Basic Check**:
```python
def is_palindrome(s):
    return s == s[::-1]
```

**Two Pointer Approach**:
```python
def is_palindrome(s):
    left, right = 0, len(s) - 1
    while left < right:
        if s[left] != s[right]:
            return False
        left += 1
        right -= 1
    return True
```

**Complexity**: O(n) time, O(1) space

### 2. Valid Palindrome (Alphanumeric Only)
Ignore non-alphanumeric characters and case:
```python
def is_palindrome(s):
    s = ''.join(c.lower() for c in s if c.isalnum())
    return s == s[::-1]
```

### 3. Longest Palindromic Substring
**Problem**: Find longest palindromic substring in a string

**Approaches**:
- Expand around center: O(n²) time, O(1) space
- Dynamic Programming: O(n²) time, O(n²) space
- Manacher's Algorithm: O(n) time, O(n) space (optimal but complex)

**Expand Around Center**:
```python
def longest_palindrome(s):
    def expand(left, right):
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return s[left+1:right]

    result = ""
    for i in range(len(s)):
        # Odd length palindrome
        odd = expand(i, i)
        # Even length palindrome
        even = expand(i, i+1)
        result = max(result, odd, even, key=len)
    return result
```

### 4. Palindromic Substrings Count
Count all palindromic substrings in a string

**Approach**: Expand around each center
**Complexity**: O(n²) time, O(1) space

### 5. Longest Palindromic Subsequence
**Problem**: Find longest palindromic subsequence (not necessarily contiguous)

**Example**: "bbbab" → "bbbb" (length 4)

**DP Solution**:
```python
def longest_palindrome_subseq(s):
    n = len(s)
    dp = [[0] * n for _ in range(n)]

    # Every single character is a palindrome of length 1
    for i in range(n):
        dp[i][i] = 1

    # Build table in reverse
    for i in range(n-1, -1, -1):
        for j in range(i+1, n):
            if s[i] == s[j]:
                dp[i][j] = 2 + dp[i+1][j-1]
            else:
                dp[i][j] = max(dp[i+1][j], dp[i][j-1])

    return dp[0][n-1]
```

**Complexity**: O(n²) time and space

### 6. Valid Palindrome II
**Problem**: Check if string can be palindrome after removing at most one character

**Approach**: Two pointers, skip one mismatch

### 7. Palindrome Partitioning
**Problem**: Partition string into all palindromic substrings

**Approach**: Backtracking with palindrome check
**Complexity**: O(n × 2^n) worst case

## Common Techniques

### 1. Two Pointers
- Most efficient for checking palindromes
- O(1) space, O(n) time
```python
left, right = 0, len(s) - 1
while left < right:
    if s[left] != s[right]:
        return False
    left += 1
    right -= 1
```

### 2. Expand Around Center
- Find palindromes by expanding from middle
- Check both odd and even length palindromes
- Good for finding longest palindrome

### 3. Dynamic Programming
- For palindromic subsequence problems
- dp[i][j] = is s[i:j+1] a palindrome?
- Build from smaller to larger substrings

### 4. Manacher's Algorithm
- Linear time palindrome finding
- Complex but optimal
- Rarely asked in interviews

## Problem-Solving Checklist
- [ ] Does case matter? (Usually ignore case)
- [ ] Do spaces/punctuation count? (Usually ignore)
- [ ] Is it substring (contiguous) or subsequence?
- [ ] Do we need to return the palindrome or just length?
- [ ] Can we modify the string?
- [ ] What about empty strings or single characters?

## Common Edge Cases
- Empty string: Usually considered palindrome
- Single character: Always palindrome
- No palindrome exists: Return empty string or -1
- Multiple palindromes of same length: Return any or first
- Special characters: Clarify if they should be ignored

## Interview Tips
1. Start with basic two-pointer approach
2. For longest palindrome, explain expand-around-center before DP
3. Remember to check both odd and even length palindromes
4. For subsequence problems, think DP
5. Practice explaining time/space trade-offs

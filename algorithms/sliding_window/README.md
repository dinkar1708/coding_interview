# Sliding Window (Caterpillar Method)

## Overview
The sliding window technique (also called caterpillar method or two-pointer technique) is used to solve problems involving contiguous subarrays or substrings. It reduces time complexity from O(n²) or O(n³) to O(n) by maintaining a window that slides through the array.

## When to Use Sliding Window

### Problem Indicators:
- Find/count subarrays/substrings satisfying a condition
- Keywords: "contiguous", "subarray", "substring", "window"
- Optimization from brute force O(n²) or O(n³)
- Problems involving sequences or ranges

### Types of Problems:
1. Fixed window size
2. Variable window size
3. Shrinkable window
4. Multiple windows

## Fixed Size Window

### Template
```python
def fixed_window(arr, k):
    """Process all windows of size k"""
    if len(arr) < k:
        return []

    # Initialize window
    window_sum = sum(arr[:k])
    result = [window_sum]

    # Slide window
    for i in range(k, len(arr)):
        # Remove leftmost element of previous window
        window_sum -= arr[i - k]
        # Add new element
        window_sum += arr[i]
        result.append(window_sum)

    return result
```

### Example: Maximum Sum of Subarray of Size K
```python
def max_sum_subarray(arr, k):
    max_sum = current_sum = sum(arr[:k])

    for i in range(k, len(arr)):
        current_sum = current_sum - arr[i-k] + arr[i]
        max_sum = max(max_sum, current_sum)

    return max_sum
```

**Time**: O(n), **Space**: O(1)

## Variable Size Window

### Template
```python
def variable_window(arr, target):
    """Find window satisfying condition"""
    left = 0
    window_property = initial_value

    for right in range(len(arr)):
        # Expand window
        window_property = update(window_property, arr[right])

        # Shrink window while condition violated
        while not is_valid(window_property):
            window_property = remove(window_property, arr[left])
            left += 1

        # Process valid window
        process_window(left, right)

    return result
```

### Example: Longest Substring Without Repeating Characters
```python
def length_of_longest_substring(s):
    char_set = set()
    left = 0
    max_length = 0

    for right in range(len(s)):
        # Shrink window until no duplicates
        while s[right] in char_set:
            char_set.remove(s[left])
            left += 1

        char_set.add(s[right])
        max_length = max(max_length, right - left + 1)

    return max_length
```

**Time**: O(n), **Space**: O(min(n, alphabet size))

## Common Sliding Window Problems

### 1. Minimum Window Substring
**Problem**: Find minimum window in s containing all characters of t

```python
from collections import Counter

def min_window(s, t):
    if not t or not s:
        return ""

    dict_t = Counter(t)
    required = len(dict_t)
    formed = 0
    window_counts = {}

    left = 0
    min_len = float('inf')
    min_left = 0

    for right in range(len(s)):
        char = s[right]
        window_counts[char] = window_counts.get(char, 0) + 1

        if char in dict_t and window_counts[char] == dict_t[char]:
            formed += 1

        # Shrink window
        while left <= right and formed == required:
            if right - left + 1 < min_len:
                min_len = right - left + 1
                min_left = left

            char = s[left]
            window_counts[char] -= 1
            if char in dict_t and window_counts[char] < dict_t[char]:
                formed -= 1
            left += 1

    return "" if min_len == float('inf') else s[min_left:min_left + min_len]
```

### 2. Longest Substring with At Most K Distinct Characters
```python
def length_of_longest_substring_k_distinct(s, k):
    if k == 0:
        return 0

    left = 0
    char_count = {}
    max_length = 0

    for right in range(len(s)):
        char_count[s[right]] = char_count.get(s[right], 0) + 1

        # Shrink if more than k distinct
        while len(char_count) > k:
            char_count[s[left]] -= 1
            if char_count[s[left]] == 0:
                del char_count[s[left]]
            left += 1

        max_length = max(max_length, right - left + 1)

    return max_length
```

### 3. Subarray Sum Equals K
```python
def subarray_sum(nums, k):
    """Count subarrays with sum = k"""
    count = 0
    prefix_sum = 0
    sum_count = {0: 1}

    for num in nums:
        prefix_sum += num
        if prefix_sum - k in sum_count:
            count += sum_count[prefix_sum - k]
        sum_count[prefix_sum] = sum_count.get(prefix_sum, 0) + 1

    return count
```

### 4. Sliding Window Maximum
```python
from collections import deque

def max_sliding_window(nums, k):
    """Find max in each window of size k"""
    dq = deque()  # Stores indices
    result = []

    for i in range(len(nums)):
        # Remove indices outside window
        while dq and dq[0] <= i - k:
            dq.popleft()

        # Remove smaller elements (they won't be max)
        while dq and nums[dq[-1]] < nums[i]:
            dq.pop()

        dq.append(i)

        if i >= k - 1:
            result.append(nums[dq[0]])

    return result
```

**Time**: O(n), **Space**: O(k)

### 5. Permutation in String
```python
def check_inclusion(s1, s2):
    """Check if s2 contains permutation of s1"""
    if len(s1) > len(s2):
        return False

    s1_count = Counter(s1)
    window_count = Counter(s2[:len(s1)])

    if s1_count == window_count:
        return True

    for i in range(len(s1), len(s2)):
        # Add new character
        window_count[s2[i]] += 1
        # Remove old character
        window_count[s2[i - len(s1)]] -= 1
        if window_count[s2[i - len(s1)]] == 0:
            del window_count[s2[i - len(s1)]]

        if window_count == s1_count:
            return True

    return False
```

### 6. Longest Repeating Character Replacement
```python
def character_replacement(s, k):
    """Longest substring with same char after k replacements"""
    left = 0
    max_count = 0
    max_length = 0
    count = {}

    for right in range(len(s)):
        count[s[right]] = count.get(s[right], 0) + 1
        max_count = max(max_count, count[s[right]])

        # If replacements needed > k, shrink
        while (right - left + 1) - max_count > k:
            count[s[left]] -= 1
            left += 1

        max_length = max(max_length, right - left + 1)

    return max_length
```

## Monotonic Deque Pattern

Used when need to maintain min/max in sliding window

### Template
```python
from collections import deque

def sliding_window_with_deque(arr, k):
    dq = deque()  # Stores indices
    result = []

    for i in range(len(arr)):
        # Remove elements outside window
        while dq and dq[0] <= i - k:
            dq.popleft()

        # Maintain monotonic property
        while dq and arr[dq[-1]] < arr[i]:  # For max
            dq.pop()

        dq.append(i)

        if i >= k - 1:
            result.append(arr[dq[0]])

    return result
```

## Caterpillar Method (Two Pointers)

Originated from counting distinct slices

### Example: Count Distinct Slices
```python
def count_distinct_slices(arr, max_val):
    """Count slices with all distinct elements"""
    seen = set()
    left = 0
    count = 0

    for right in range(len(arr)):
        # Shrink until arr[right] can be added
        while arr[right] in seen:
            seen.remove(arr[left])
            left += 1

        seen.add(arr[right])
        # All subarrays ending at right
        count += right - left + 1

    return count
```

## Problem-Solving Strategy

### 1. Identify Window Type
- **Fixed size**: Window size given (k)
- **Variable size**: Find optimal window
- **Shrinkable**: Minimize/maximize window

### 2. Track Window State
- Sum/product
- Character frequency (hash map)
- Distinct count
- Max/min (deque)

### 3. Decide When to Shrink
- Window size exceeds k
- Condition violated (e.g., too many distinct)
- Found better result

### 4. Update Result
- After each expansion
- After each shrink
- When window is valid

## Complexity Analysis

### Time Complexity
- Usually O(n) where n is array length
- Each element added once, removed once
- Deque operations: O(1) amortized

### Space Complexity
- O(k) for fixed window
- O(alphabet size) for string problems
- O(n) worst case for variable window

## Interview Tips

### Common Mistakes
1. **Off-by-one errors**: Window boundaries
2. **Not shrinking properly**: Check while vs if
3. **Forgetting to initialize**: First window
4. **Not handling edge cases**: Empty array, k > n

### Optimization Tricks
1. **Hash map vs array**: Use array for limited alphabet
2. **Deque for min/max**: O(1) amortized
3. **Early termination**: If result can't improve
4. **Counter vs manual**: Counter is cleaner but may be slower

### Variations
- Two separate pointers vs left+right
- Inclusive vs exclusive boundaries
- Process during expansion vs after shrinking

## Related Techniques
- Two Pointers
- Prefix Sum
- Monotonic Stack/Queue
- Hash Map
- Dynamic Programming

## Practice Problems by Difficulty

### Easy
- Maximum Average Subarray
- Minimum Size Subarray Sum
- Contains Duplicate II

### Medium
- Longest Substring Without Repeating Characters
- Fruit Into Baskets
- Max Consecutive Ones III
- Longest Repeating Character Replacement

### Hard
- Minimum Window Substring
- Sliding Window Maximum
- Substring with Concatenation of All Words
- Subarrays with K Different Integers

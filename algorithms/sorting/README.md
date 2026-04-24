# Sorting

## Overview
Sorting algorithms arrange elements in a specific order (ascending or descending). Understanding sorting is fundamental to many algorithmic problems.

## Common Sorting Algorithms

### Comparison-Based Sorts

| Algorithm | Best | Average | Worst | Space | Stable |
|-----------|------|---------|-------|-------|--------|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | No |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No |
| Tim Sort | O(n) | O(n log n) | O(n log n) | O(n) | Yes |

### Non-Comparison Sorts

| Algorithm | Time | Space | Notes |
|-----------|------|-------|-------|
| Counting Sort | O(n+k) | O(k) | k = range of values |
| Radix Sort | O(d×(n+k)) | O(n+k) | d = digits |
| Bucket Sort | O(n+k) | O(n+k) | k = buckets |

## Python Built-in Sort

```python
# Sort list in-place
arr.sort()  # O(n log n)

# Return new sorted list
sorted_arr = sorted(arr)  # O(n log n)

# Custom comparator
arr.sort(key=lambda x: x[1])  # Sort by second element
arr.sort(key=lambda x: (x[0], -x[1]))  # Multi-key sort

# Reverse order
arr.sort(reverse=True)
```

**Python uses Timsort**: Hybrid of merge sort and insertion sort
- Stable
- O(n log n) worst case
- O(n) best case for partially sorted

## Common Sorting Problems

### 1. Sort Different Data Types

#### Sort by Multiple Keys
```python
# Sort by age, then by name
people.sort(key=lambda p: (p.age, p.name))
```

#### Custom Comparator
```python
from functools import cmp_to_key

def compare(x, y):
    # Return -1 if x < y, 0 if equal, 1 if x > y
    if x[0] != y[0]:
        return x[0] - y[0]
    return y[1] - x[1]  # Reverse for second key

arr.sort(key=cmp_to_key(compare))
```

### 2. Find Kth Largest/Smallest Element

**Quick Select** (Average O(n)):
```python
import random

def quick_select(arr, k):
    """Find kth smallest element (0-indexed)"""
    if len(arr) == 1:
        return arr[0]

    pivot = random.choice(arr)
    left = [x for x in arr if x < pivot]
    mid = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]

    if k < len(left):
        return quick_select(left, k)
    elif k < len(left) + len(mid):
        return mid[0]
    else:
        return quick_select(right, k - len(left) - len(mid))
```

**Heap** (O(n log k)):
```python
import heapq

def kth_largest(arr, k):
    return heapq.nlargest(k, arr)[-1]

def kth_smallest(arr, k):
    return heapq.nsmallest(k, arr)[-1]
```

### 3. Merge Intervals
```python
def merge_intervals(intervals):
    if not intervals:
        return []

    intervals.sort(key=lambda x: x[0])
    merged = [intervals[0]]

    for current in intervals[1:]:
        if current[0] <= merged[-1][1]:
            # Overlapping - merge
            merged[-1] = [merged[-1][0], max(merged[-1][1], current[1])]
        else:
            # Non-overlapping - add
            merged.append(current)

    return merged
```

### 4. Largest Number from Array
Arrange numbers to form largest number

```python
from functools import cmp_to_key

def largest_number(nums):
    # Compare by concatenation
    nums = [str(n) for n in nums]
    nums.sort(key=cmp_to_key(lambda x, y: -1 if x+y > y+x else 1))
    result = ''.join(nums)
    return result if result[0] != '0' else '0'
```

### 5. Sort Colors (Dutch National Flag)
Sort array with 3 distinct values

```python
def sort_colors(nums):
    """In-place sort of 0s, 1s, 2s"""
    low, mid, high = 0, 0, len(nums) - 1

    while mid <= high:
        if nums[mid] == 0:
            nums[low], nums[mid] = nums[mid], nums[low]
            low += 1
            mid += 1
        elif nums[mid] == 1:
            mid += 1
        else:  # nums[mid] == 2
            nums[mid], nums[high] = nums[high], nums[mid]
            high -= 1
```

### 6. Count Distinct Elements
```python
def count_distinct(arr):
    return len(set(arr))  # O(n) time and space

# Or after sorting
def count_distinct_sorted(arr):
    if not arr:
        return 0
    arr.sort()
    count = 1
    for i in range(1, len(arr)):
        if arr[i] != arr[i-1]:
            count += 1
    return count
```

## When to Use Sorting

### Sorting is Useful When:
1. **Finding duplicates**: Sort then check adjacent elements
2. **Merge operations**: Merge sorted arrays
3. **Searching**: Binary search requires sorted data
4. **Interval problems**: Sort intervals by start/end
5. **Greedy algorithms**: Often need sorted input
6. **Two pointer technique**: Usually on sorted arrays
7. **Finding pairs/triplets**: Two pointers on sorted array

### Avoid Sorting When:
1. **Order matters**: Need to preserve original order
2. **Already have O(n) solution**: Hash map might be better
3. **Partial sorting**: Quick select for kth element
4. **Streaming data**: Can't sort infinite stream

## Sorting Stability

**Stable Sort**: Preserves relative order of equal elements

**Important for**:
- Multi-key sorting
- Sorting objects by multiple attributes
- Maintaining order of tied elements

**Stable Algorithms**: Merge Sort, Insertion Sort, Timsort
**Unstable**: Quick Sort, Heap Sort, Selection Sort

## Interview Tips

### 1. Clarify Requirements
- Can we modify the input array?
- What's the range of values?
- Any duplicates?
- Need stable sort?

### 2. Choose Right Algorithm
- **Small array (< 50)**: Insertion sort
- **General purpose**: Quick sort or merge sort
- **Need stability**: Merge sort or Timsort
- **Limited range**: Counting sort
- **Nearly sorted**: Insertion sort or Timsort
- **Guaranteed O(n log n)**: Merge sort or heap sort

### 3. Common Optimizations
- Use built-in sort when possible
- For kth element, use quick select not full sort
- For finding duplicates, consider hash set
- For pair sums, sort + two pointers

### 4. Time Complexity Awareness
- Sorting is O(n log n), adding this to O(n) is still O(n log n)
- Two nested loops on sorted array might be O(n) with two pointers
- Binary search after sorting: O(n log n + k log n)

## Related Problems
- Meeting Rooms (interval overlap)
- H-Index (citation count)
- Maximum Product of Three Numbers
- 3Sum, 4Sum (sorted + two pointers)
- Car Fleet (sorting by position)
- Non-overlapping Intervals
- Maximum Gap (bucket sort)

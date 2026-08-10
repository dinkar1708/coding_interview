# Array Searching & Counting

## Overview
This folder contains problems related to searching for elements, counting occurrences, finding patterns, and identifying special elements (like leaders) in arrays.

## Key Concepts

### Counting Elements
- Frequency counting using hash maps or arrays
- Finding missing/extra elements
- Permutation checking

### Leader Elements
- **Leader**: An element where all elements to its right are smaller
- Can be found efficiently using right-to-left traversal

### Odd Occurrences
- Finding elements that appear an odd number of times
- XOR trick for finding single occurrence in pairs

## Common Patterns

### 1. Hash Map/Dictionary Approach
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)
- Best for frequency counting and lookups

### 2. Sorting Approach
- **Time Complexity**: O(n log n)
- **Space Complexity**: O(1)
- Useful when order doesn't matter

### 3. Mathematical Approach
- Using XOR for paired elements
- Sum formula for missing numbers
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)

### 4. Single Pass Techniques
- Leaders from right to left
- Running counters/flags
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)

## Common Problems
- Find missing element in array
- Count elements that satisfy a condition
- Find element occurring odd number of times
- Identify all leaders in array
- Permutation check (1 to N)
- First non-repeating element

## Tips
- Consider if array is sorted or can be sorted
- Hash maps provide O(1) lookup but use extra space
- XOR is powerful for finding unpaired elements
- Leaders can always be found in O(n) with single pass from right

# Kotlin Coding Interview Examples

This directory contains Kotlin implementations of common coding interview algorithms and data structures for interview preparation and practice.

## Structure

```
kotlin/
├── index.kt
└── algorithms/
    ├── arrays/
    │   ├── rotation/                # Array rotation algorithms
    │   ├── searching/               # Array searching algorithms
    │   └── subarrays/               # Subarray problems
    ├── bit_manipulation/            # Bit manipulation problems
    ├── dynamic_programming/
    │   ├── fibonacci/               # Fibonacci implementations
    │   ├── knapsack/                # Knapsack problem
    │   ├── subsequence/             # Subsequence problems
    │   └── coin_change/             # Coin change problem
    ├── greedy/                      # Greedy algorithms
    ├── math/                        # Mathematical algorithms
    ├── patterns/                    # Pattern printing
    ├── prefix_sum/                  # Prefix sum techniques
    ├── recursion/                   # Recursive solutions
    ├── sliding_window/              # Sliding window technique
    ├── sorting/                     # Sorting algorithms
    ├── stack_queue/                 # Stack and Queue problems
    └── strings/
        ├── anagram/                 # Anagram problems
        ├── palindrome/              # Palindrome problems
        └── pattern_matching/        # Pattern matching
```

## Algorithm Categories

### Arrays (19 files)
- Array Rotation: Cyclic rotation using modulus operator and juggling algorithm
- Array Searching: Leader elements, odd occurrences, permutation checks
- Subarrays: Maximum sum, maximum profit, double slice problems

### Bit Manipulation (1 file)
- Longest Binary Gap: Finding longest sequence of zeros in binary representation

### Dynamic Programming (16 files)
- Fibonacci: Multiple approaches including top-down, bottom-up, matrix exponentiation
- Coin Change: Counting ways to make change with different denominations
- Knapsack: 0-1 Knapsack problem
- Subsequence: Longest common subsequence, house robber, stock buy-sell

### Greedy Algorithms (2 files)
- Tie Ropes: Maximizing rope count
- Max Non-Overlapping Segments: Segment selection

### Math (7 files)
- Factorial and recursive calculations
- Prime and composite number problems
- GCD and divisibility problems

### Patterns (4 files)
- Triangle patterns
- Pyramid patterns

### Prefix Sum (4 files)
- Passing Cars: Counting pairs
- Genomic Range Query: DNA sequence analysis
- Count Divisible: Counting divisible numbers in range

### Recursion (1 file)
- Array Sum: Recursive array summation

### Sliding Window (1 file)
- Count Distinct Slices: Two-pointer caterpillar method

### Sorting (4 files)
- Triangle triplet detection
- Maximum product of three
- Disc intersections

### Stack and Queue (4 files)
- Balanced Brackets: Checking properly balanced brackets
- Nesting: Proper nesting validation
- Fish River: Stack-based simulation
- Stone Wall: Minimum blocks calculation

### Strings (4 files)
- Anagram Checker: Verifying anagrams
- Palindrome: Checking palindrome numbers
- Pattern Matching: Word matching with conditions

## File Structure

Each file follows this structure:

```kotlin
/**
 * PROBLEM: Clear problem statement
 * Description of what the problem asks
 * INPUT: Example input
 * OUTPUT: Example output
 * EXPLANATION: How the algorithm works
 * Time Complexity: O(?)
 * Space Complexity: O(?)
 */

fun main() {
    // Test cases with example inputs
    // Prints results and expected outputs
}

fun solution(...) {
    // Algorithm implementation
}

// Additional helper functions
```

## Running the Examples

### Using Kotlin Command Line Compiler

1. Compile a Kotlin file:
   ```bash
   kotlinc FileName.kt -include-runtime -d FileName.jar
   ```

2. Run the compiled program:
   ```bash
   java -jar FileName.jar
   ```

3. Or compile and run in one step:
   ```bash
   kotlinc FileName.kt -include-runtime -d FileName.jar && java -jar FileName.jar
   ```

### Using Android Studio or IntelliJ IDEA

1. Right-click on the file and select Run
2. Or press Ctrl+Shift+F10 (Windows/Linux) or Ctrl+Shift+R (Mac)
3. Or click the green play button next to the main function

## Key Kotlin Features Used

- Type Inference: Letting Kotlin infer types automatically
- String Templates: String interpolation with dollar sign
- Array Operations: contentToString, withIndex, indices
- Functional Programming: maxOf, minOf, filter, map
- Null Safety: Using nullable types and safe calls
- Ranges: 0 until n, 0..n
- Extension Functions: Additional functionality on existing classes
- When Expressions: Enhanced switch statements

## Notes

- All files include comprehensive problem statements and examples
- Each algorithm includes time and space complexity analysis
- Many examples reference Codility problems with solution links
- All files have been tested and verified to produce correct outputs

## Documentation

See the **docs/** folder for detailed explanations:
- IntArray reference behavior
- IntArray vs Array differences
- Map vs MutableMap usage

## Practice Problems

### HackerRank - Linked Lists
- [Reverse a Linked List](https://www.hackerrank.com/challenges/reverse-a-linked-list/problem)
- [Detect Cycle](https://www.hackerrank.com/challenges/detect-whether-a-linked-list-contains-a-cycle/problem)
- [Detect Cycle (Interview Prep)](https://www.hackerrank.com/challenges/ctci-linked-list-cycle/problem)
- [Merge Two Sorted Linked Lists](https://www.hackerrank.com/challenges/merge-two-sorted-linked-lists/problem)

### HackerRank - Strings
- [Two Strings (Common Substring)](https://www.hackerrank.com/challenges/two-strings/problem)
- [Anagram (Split Halves)](https://www.hackerrank.com/challenges/anagram/problem)
- [Making Anagrams](https://www.hackerrank.com/challenges/making-anagrams/problem)
- [Making Anagrams (Interview Prep)](https://www.hackerrank.com/challenges/ctci-making-anagrams/problem)

### LeetCode - Arrays & Hash Tables
- [Two Sum](https://leetcode.com/problems/two-sum/)
- [Container With Most Water](https://leetcode.com/problems/container-with-most-water/)
- [Valid Anagram](https://leetcode.com/problems/valid-anagram/)
- [Group Anagrams](https://leetcode.com/problems/group-anagrams/)

### LeetCode - Linked Lists
- [Middle of Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)
- [Remove Nth Node From End](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
- [Reverse Nodes in k-Group (Hard)](https://leetcode.com/problems/reverse-nodes-in-k-group/)

### LeetCode - Design
- [LRU Cache](https://leetcode.com/problems/lru-cache/)
- [Min Stack](https://leetcode.com/problems/min-stack/)

## Additional Resources

- Kotlin Official Documentation: https://kotlinlang.org/docs/home.html
- Kotlin Coding Conventions: https://kotlinlang.org/docs/coding-conventions.html
- Codility: https://www.codility.com/
- HackerRank: https://www.hackerrank.com/
- LeetCode: https://leetcode.com/

## Contributing

When adding new examples:
1. Follow Kotlin naming conventions
2. Include problem statement with INPUT and OUTPUT examples
3. Add EXPLANATION section describing the algorithm
4. Document time and space complexity
5. Add main function with test cases
6. Verify output matches expected results

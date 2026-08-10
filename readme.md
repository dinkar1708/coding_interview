# Coding Interview Practice Repository

A comprehensive collection of coding interview problems and solutions in Kotlin, organized by algorithm types and data structures. This repository contains solutions to problems from Codility, LeetCode, and common technical interview questions.

## Repository Structure

This repository contains implementations in two languages:
- **kotlin/** - Kotlin implementations (primary, with comprehensive documentation)
- **algorithms/** - Python implementations (earlier versions)

### kotlin/ - Kotlin Implementations

The primary Kotlin codebase with comprehensive documentation and organized structure.

#### Code Organization
```
kotlin/src/main/kotlin/algorithms/
├── arrays/            - Array manipulation, rotation, searching, subarrays
├── strings/           - String problems (anagrams, palindromes, pattern matching)
├── dynamic_programming/ - DP solutions (fibonacci, knapsack, coin change, subsequence)
├── prefix_sum/        - Prefix sum technique problems
├── sliding_window/    - Sliding window pattern (caterpillar method)
├── stack_queue/       - Stack and queue implementations
├── sorting/           - Sorting algorithms and problems
├── greedy/            - Greedy algorithm solutions
├── math/              - Mathematical problems (primes, factorials, GCD)
├── bit_manipulation/  - Bitwise operations
├── recursion/         - Recursive solutions
├── patterns/          - Pattern printing problems
└── basics/            - Kotlin basics (iteration, collections)
```

#### Documentation
```
kotlin/docs/
├── kotlin-basics/     - Kotlin fundamentals (arrays, strings, maps, numbers)
├── algorithm-theory/  - Complexity analysis, DP patterns, sorting algorithms
├── data-structures/   - Trees, linked lists, graphs
├── interview-prep/    - Problem patterns, tips, study plan, problem types
├── techniques/        - HashMap, sliding window, prefix sum
└── README.md          - Complete documentation guide
```

**Start here:** Read `kotlin/docs/README.md` for a complete guide to all documentation and study plans.

### algorithms/ - Python Implementations

Earlier Python implementations with similar algorithm coverage:
- Array manipulation, rotation, searching
- Dynamic programming solutions
- String problems, prefix sum, sorting
- Greedy algorithms, stack/queue, recursion
- Math problems, bit manipulation, sliding window

### Other Folders

- **code/** - Additional problem sets (Striver series)
- **practice/** - Practice problems from various sources

## Quick Start

### For Interview Preparation
1. Navigate to `kotlin/docs/README.md` for the complete documentation index
2. Review `kotlin/docs/interview-prep/problem-types-index.md` to identify problem patterns
3. Study specific topics in `kotlin/docs/algorithm-theory/` and `kotlin/docs/data-structures/`
4. Practice with code in `kotlin/src/main/kotlin/algorithms/`

### Study Path
1. **Week 1-2:** Kotlin basics, arrays, strings, two pointers
2. **Week 3-4:** Dynamic programming patterns (all DP types A-G)
3. **Week 5-6:** Trees, graphs, advanced data structures
4. **Week 7+:** Mock interviews, company-specific problems

### By Problem Type
- **Arrays:** `kotlin/docs/interview-prep/array-problems.md`
- **Dynamic Programming:** `kotlin/docs/algorithm-theory/dp_patterns.md`
- **Trees:** `kotlin/docs/data-structures/tree_interview_questions.md`
- **Patterns:** `kotlin/docs/interview-prep/interview_patterns.md`

## Running Code

Each Kotlin file contains:
- Problem description with examples
- Time and space complexity analysis
- Multiple test cases
- Clear implementation with comments

To run any file:
```bash
kotlinc filename.kt -include-runtime -d filename.jar && java -jar filename.jar
```

Or use the test scripts in `kotlin/outputs/` for batch testing.

## Practice Platforms

### Coding Challenge Sites
- **LeetCode** - https://leetcode.com/
  - Best for interview preparation
  - Company-tagged problems
  - Discussion forums with solutions

- **HackerRank** - https://www.hackerrank.com/
  - Skill certification
  - Language-specific tracks
  - Interview preparation kits

- **Codility** - https://www.codility.com/
  - Timed assessments
  - Company screening tests
  - Detailed performance reports

- **Codeforces** - https://codeforces.com/
  - Competitive programming
  - Regular contests
  - Rating system

- **HackerEarth** - https://www.hackerearth.com/
  - Hiring challenges
  - Practice problems
  - Coding competitions

### Algorithm Visualization
- **VisuAlgo** - https://visualgo.net/
  - Algorithm animations
  - Interactive learning
  - Step-by-step execution

## Study Resources

### Video Tutorials
- **Dynamic Programming Playlist** - https://www.youtube.com/watch?v=nqowUJzG-iM&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go
  - Aditya Verma DP series
  - All DP patterns covered

- **Back to Back SWE** - https://www.youtube.com/watch?v=Zq4upTEaQyM&list=PLiQ766zSC5jM2OKVr8sooOuGgZkvnOCTI
  - Interview problem explanations
  - Detailed walkthroughs

- **Codility Lessons** - https://www.youtube.com/watch?v=sACjpw8BtTc&list=PLRq88ERCRTVbaPOTIKD5CTDiHfOFxvrKX
  - Codility problem solutions
  - Common patterns

- **Byte by Byte** - https://www.youtube.com/watch?v=c0OMPDLef08&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj
  - Interview questions breakdown
  - Coding tips

- **5 Hour DP Training** - https://youtu.be/oBt53YbR9Kk
  - Comprehensive DP coverage
  - Multiple problem types

- **LeetCode Video Solutions** - https://youtube.com/playlist?list=PLU_sdQYzUj2keVENTP0a5rdykRSgg9Wp-
  - Problem walkthroughs
  - Visual explanations

### Books and Reading
- **Cracking the Coding Interview** by Gayle Laakmann McDowell
- **Elements of Programming Interviews** by Adnan Aziz
- **Algorithm Design Manual** by Steven Skiena

## Features

- 100+ algorithm implementations in Kotlin
- Comprehensive documentation covering all major topics
- Problem pattern recognition guides
- Multiple implementations for comparison (6+ rotation algorithms, 4+ fibonacci variations)
- Interview preparation strategies and tips
- Organized by technique, data structure, and problem type

## Documentation Highlights

- **Complete DP Guide:** All 7 DP types (Fibonacci, Knapsack, LCS, LIS, Grid, State Machine, String)
- **Problem Patterns:** 15 common interview patterns with recognition tips
- **Complexity Reference:** Big O cheatsheet for all algorithms
- **Interview Tips:** Communication strategies, common gotchas, time management

See `kotlin/docs/README.md` for complete details.

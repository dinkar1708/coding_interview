# Coding Problem Types - Quick Reference

## How to Use This Guide

When you see a problem, ask yourself:

1. **What is being asked?** (Max/Min, Count, Yes/No, Find all)
2. **What constraints exist?** (Sorted? Adjacent? Unlimited?)
3. **What's the input structure?** (Array, Tree, Graph, String)

Then match to a type below and read that specific guide.

---

## 📚 Problem Type Categories

### 🎯 By Technique

| Problem Type | When to Use | Doc Link |
|--------------|-------------|----------|
| **Two Pointers** | Sorted array, finding pairs, reversing | `two-pointers.md` |
| **Sliding Window** | Subarray/substring with condition | `sliding-window.md` |
| **Binary Search** | Sorted data, finding in O(log n) | `binary-search.md` |
| **Greedy** | Local optimal → global optimal | `greedy-algorithms.md` |
| **Backtracking** | Generate all combinations/permutations | `backtracking.md` |
| **Dynamic Programming** | Overlapping subproblems, optimization | `../algorithm-theory/dp_patterns.md` |

### 🌳 By Data Structure

| Data Structure | Common Problems | Doc Link |
|----------------|-----------------|----------|
| **Arrays** | Sorting, searching, two pointers | `array-problems.md` |
| **Strings** | Anagrams, palindromes, pattern matching | `string-problems.md` |
| **Linked Lists** | Reversal, cycles, merging | `../data-structures/linked_list_reversal.md` |
| **Trees** | Traversals, paths, LCA | `../data-structures/tree_interview_questions.md` |
| **Graphs** | BFS, DFS, shortest path | `graph-problems.md` |
| **Heaps** | Top K, merge K, median | `heap-problems.md` |

### 🔍 By Problem Goal

| Goal | Common Patterns | Example Problems |
|------|----------------|------------------|
| **Find Max/Min** | DP, Greedy, Kadane's | Max Subarray Sum, Stock Profit |
| **Count Ways** | DP (Type A), Combinatorics | Climbing Stairs, Unique Paths |
| **Yes/No (Feasible)** | DP, Greedy, Two Pointers | Subset Sum, Valid Parentheses |
| **Find All** | Backtracking, DFS | All Permutations, All Paths |
| **Top K** | Heap, Quickselect | Kth Largest, Top K Frequent |

---

## 🚀 Quick Decision Tree

```
Start Here
    ↓
Is it sorted?
    ├─ Yes → Try Binary Search or Two Pointers
    └─ No
        ↓
    Is it about subarrays/substrings?
        ├─ Yes → Try Sliding Window
        └─ No
            ↓
        Does it say "subsequence"?
            ├─ Yes → Dynamic Programming
            └─ No
                ↓
            Is it a tree/graph?
                ├─ Tree → BFS/DFS (see tree guide)
                └─ Graph → BFS/DFS (see graph guide)
                    ↓
                Neither
                    ↓
                Does it need all combinations?
                    ├─ Yes → Backtracking
                    └─ No
                        ↓
                    Can greedy work?
                        ├─ Yes → Greedy
                        └─ No → Dynamic Programming
```

---

## 📖 Detailed Guides

### Core Techniques
1. **[Two Pointers](two-pointers.md)** - Sorted arrays, pairs, partitioning
2. **[Sliding Window](sliding-window.md)** - Fixed/variable window, optimization
3. **[Binary Search](binary-search.md)** - Search variations, rotated arrays
4. **[Greedy Algorithms](greedy-algorithms.md)** - Activity selection, intervals
5. **[Backtracking](backtracking.md)** - Generate all, constraint satisfaction
6. **[Dynamic Programming](../algorithm-theory/dp_patterns.md)** - All DP types A-G

### Data Structure Focused
7. **[Array Problems](array-problems.md)** - Common array patterns
8. **[String Problems](string-problems.md)** - Anagrams, palindromes
9. **[Graph Problems](graph-problems.md)** - BFS, DFS, shortest path
10. **[Heap Problems](heap-problems.md)** - Top K, merge, median

---

## 🎯 Interview Strategy

### Before Coding
1. **Identify the type** using the decision tree
2. **Choose the pattern** from the corresponding guide
3. **State your approach** to the interviewer
4. **Discuss complexity** before coding

### Signal Words to Problem Types

| You Hear | Think |
|----------|-------|
| "sorted array" | Binary Search or Two Pointers |
| "subarray/substring" | Sliding Window |
| "subsequence" | Dynamic Programming |
| "all combinations" | Backtracking |
| "minimum/maximum" | DP or Greedy |
| "top K" | Heap |
| "shortest path" | BFS/Dijkstra |
| "connected components" | Union Find or DFS |
| "no adjacent" | DP Type B (House Robber) |

---

## 💡 Pro Tips

1. **Pattern recognition is key** - The more you practice, the faster you'll identify types
2. **Multiple approaches exist** - Some problems can be solved with different techniques
3. **Start simple** - Brute force → Optimize → Code
4. **Time constraints matter** - n ≤ 100 → O(n³) OK, n ≤ 10⁶ → need O(n) or O(n log n)

---

**Next Steps:**
1. Read the guides for each problem type
2. Practice 5-10 problems per type
3. Review patterns before interviews
4. Keep this index handy during practice!

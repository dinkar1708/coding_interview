# Algorithms Complexity Cheatsheet

## Common Data Structures

| Data Structure | Access | Search | Insert | Delete | Space |
|----------------|--------|--------|--------|--------|-------|
| Array | O(1) | O(n) | O(n) | O(n) | O(n) |
| ArrayList | O(1) | O(n) | O(n) | O(n) | O(n) |
| LinkedList | O(n) | O(n) | O(1)* | O(1)* | O(n) |
| Stack | O(n) | O(n) | O(1) | O(1) | O(n) |
| Queue | O(n) | O(n) | O(1) | O(1) | O(n) |
| HashMap | - | O(1)† | O(1)† | O(1)† | O(n) |
| TreeMap | - | O(log n) | O(log n) | O(log n) | O(n) |
| HashSet | - | O(1)† | O(1)† | O(1)† | O(n) |
| TreeSet | - | O(log n) | O(log n) | O(log n) | O(n) |
| PriorityQueue | O(n) | O(n) | O(log n) | O(log n) | O(n) |

*with reference to node  
†average case; worst O(n)

## Sorting Algorithms

| Algorithm | Best | Average | Worst | Space | Stable |
|-----------|------|---------|-------|-------|--------|
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No |
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | No |
| Counting Sort | O(n+k) | O(n+k) | O(n+k) | O(k) | Yes |
| Radix Sort | O(nk) | O(nk) | O(nk) | O(n+k) | Yes |
| Bucket Sort | O(n+k) | O(n+k) | O(n²) | O(n) | Yes |

## Searching Algorithms

| Algorithm | Best | Average | Worst | Space | Notes |
|-----------|------|---------|-------|-------|-------|
| Linear Search | O(1) | O(n) | O(n) | O(1) | Unsorted |
| Binary Search | O(1) | O(log n) | O(log n) | O(1) | Sorted |
| BFS | - | O(V+E) | O(V+E) | O(V) | Graph |
| DFS | - | O(V+E) | O(V+E) | O(V) | Graph |

## Tree Operations

| Operation | BST Avg | BST Worst | AVL | Red-Black |
|-----------|---------|-----------|-----|-----------|
| Search | O(log n) | O(n) | O(log n) | O(log n) |
| Insert | O(log n) | O(n) | O(log n) | O(log n) |
| Delete | O(log n) | O(n) | O(log n) | O(log n) |

## Graph Algorithms

| Algorithm | Time | Space | Use Case |
|-----------|------|-------|----------|
| BFS | O(V+E) | O(V) | Shortest path (unweighted) |
| DFS | O(V+E) | O(V) | Connectivity, cycles |
| Dijkstra | O((V+E)log V) | O(V) | Shortest path (weighted) |
| Bellman-Ford | O(VE) | O(V) | Negative weights |
| Floyd-Warshall | O(V³) | O(V²) | All pairs shortest path |
| Kruskal | O(E log E) | O(V) | MST |
| Prim | O((V+E)log V) | O(V) | MST |
| Topological Sort | O(V+E) | O(V) | DAG ordering |

## String Algorithms

| Algorithm | Time | Space | Use Case |
|-----------|------|-------|----------|
| Brute Force | O(mn) | O(1) | Pattern matching |
| KMP | O(m+n) | O(m) | Pattern matching |
| Rabin-Karp | O(m+n) | O(1) | Pattern matching |
| Trie Insert/Search | O(k) | O(ALPHABET_SIZE * N * K) | Prefix matching |

## Dynamic Programming Patterns

| Pattern | Time | Space | Example |
|---------|------|-------|---------|
| Fibonacci | O(n) | O(n) or O(1) | Climbing stairs |
| 0/1 Knapsack | O(nW) | O(nW) or O(W) | Subset sum |
| LCS | O(mn) | O(mn) or O(min(m,n)) | Edit distance |
| LIS | O(n²) or O(n log n) | O(n) | Longest increasing |

## Common Patterns Time Complexity

| Pattern | Complexity | Example |
|---------|-----------|---------|
| Single loop | O(n) | Sum array |
| Nested loop | O(n²) | Bubble sort |
| Binary search | O(log n) | Find in sorted |
| Two pointers | O(n) | Two sum sorted |
| Sliding window | O(n) | Max sum subarray |
| Divide & conquer | O(n log n) | Merge sort |
| Dynamic programming | O(n²) typically | Knapsack |
| Backtracking | O(2ⁿ) or O(n!) | N-Queens |
| Heap operations | O(log n) | Priority queue |

## Space Complexity Patterns

| Type | Space | Example |
|------|-------|---------|
| Constant | O(1) | Variables |
| Linear | O(n) | Array/List |
| Logarithmic | O(log n) | Recursion stack (balanced tree) |
| Quadratic | O(n²) | 2D DP table |
| Exponential | O(2ⁿ) | Recursion tree |

## Big O Ranking (fastest to slowest)

1. O(1) - Constant
2. O(log n) - Logarithmic  
3. O(n) - Linear
4. O(n log n) - Linearithmic
5. O(n²) - Quadratic
6. O(n³) - Cubic
7. O(2ⁿ) - Exponential
8. O(n!) - Factorial

## Common Rules

1. **Drop constants**: O(2n) → O(n)
2. **Drop non-dominant terms**: O(n² + n) → O(n²)
3. **Different inputs**: O(a + b), O(a * b)
4. **Amortized time**: Average over sequence
5. **Worst case is default**: Unless specified

## Interview Tips

- **Always analyze** time AND space complexity
- **State assumptions**: sorted? duplicates? constraints?
- **Optimize step-by-step**: Brute force → Better → Optimal
- **Trade-offs**: Time vs Space
- **Real constraints**: n ≤ 10⁵ usually means O(n) or O(n log n)

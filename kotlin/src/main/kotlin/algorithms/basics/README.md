# Kotlin Basics - Iteration Patterns

This folder contains executable examples demonstrating iteration patterns in Kotlin.

## Files

### 1. ArrayIteration.kt
**Demonstrates:** How to iterate through IntArray
- ✓ Full array iteration with/without index
- ✓ Offset iteration (pairs, triplets)
- ✓ Safe vs unsafe patterns
- ✗ Common mistakes (crashes)

**Run to see:**
```bash
kotlinc ArrayIteration.kt -include-runtime -d ArrayIteration.jar
java -jar ArrayIteration.jar
```

### 2. StringIteration.kt
**Demonstrates:** How to iterate through String
- ✓ Character iteration with/without index
- ✓ Character pairs for substring patterns
- ✓ Safe vs unsafe patterns
- ✗ Common mistakes (crashes)

**Run to see:**
```bash
kotlinc StringIteration.kt -include-runtime -d StringIteration.jar
java -jar StringIteration.jar
```

### 3. StringArrayIteration.kt
**Demonstrates:** How to iterate through Array<String>
- ✓ String array iteration with/without index
- ✓ Word pairs
- ✓ Safe vs unsafe patterns
- ✗ Common mistakes (crashes)

**Run to see:**
```bash
kotlinc StringArrayIteration.kt -include-runtime -d StringArrayIteration.jar
java -jar StringArrayIteration.jar
```

### 4. MapIteration.kt
**Demonstrates:** How to iterate through Map (key-value pairs)
- ✓ Why maps DON'T have indices
- ✓ Access by key (not index)
- ✓ Iterate key-value pairs, keys only, values only
- ✓ Frequency counter example
- ✗ Why you can't use indices with maps

**Run to see:**
```bash
kotlinc MapIteration.kt -include-runtime -d MapIteration.jar
java -jar MapIteration.jar
```

### 5. Array2DIteration.kt
**Demonstrates:** How to iterate through 2D arrays (matrices)
- ✓ Row and column iteration with indices
- ✓ Value iteration without indices
- ✓ Different printing patterns (row-by-row, column-by-column, diagonal)
- ✓ Common operations (sum, max, search)
- ✓ Creating 2D arrays

**Run to see:**
```bash
kotlinc Array2DIteration.kt -include-runtime -d Array2DIteration.jar
java -jar Array2DIteration.jar
```

### 6. NumberIteration.kt
**Demonstrates:** How to iterate on simple numbers
- ✓ `until` vs `..` (exclusive vs inclusive)
- ✓ When `0 until n` includes or excludes n
- ✓ Relationship to array indices
- ✓ Common patterns (factorial, sum, countdown)
- ✓ Step iteration and reverse iteration

**Run to see:**
```bash
kotlinc NumberIteration.kt -include-runtime -d NumberIteration.jar
java -jar NumberIteration.jar
```

## Quick Reference

| Collection | Use Index? | Best Pattern |
|------------|-----------|--------------|
| Number (n) | N/A | `for (i in 0 until n)` or `for (i in 1..n)` |
| IntArray | ✓ YES | `for (i in arr.indices)` or `for (num in arr)` |
| String | ✓ YES | `for (i in text.indices)` or `for (char in text)` |
| Array<String> | ✓ YES | `for (i in words.indices)` or `for (word in words)` |
| Array<IntArray> | ✓ YES | `for (row in matrix.indices) { for (col in matrix[row].indices) }` |
| Map | ✗ NO | `for ((key, value) in map)` |

## Key Rules

**When to use `.indices`:**
- ✓ Full array/string iteration with index
- ✗ NOT for maps (maps don't have indices)

**When to use `0 until size - N`:**
- ✓ When stopping N elements before end (pairs, triplets)
- ✓ Clearer than adding bounds check inside loop

**When to use direct iteration:**
- ✓ When you don't need the index
- ✓ Simplest and cleanest code

## See Also
- `/kotlin/docs/Number-Iteration-in-Kotlin.md` - Complete number iteration reference
- `/kotlin/docs/Arrays-in-Kotlin.md` - Complete array reference
- `/kotlin/docs/2D-Arrays-in-Kotlin.md` - Complete 2D array reference
- `/kotlin/docs/Strings-in-Kotlin.md` - Complete string reference
- `/kotlin/docs/Maps-in-Kotlin.md` - Complete map reference
- `/kotlin/docs/When-To-Use-What.md` - Decision guide

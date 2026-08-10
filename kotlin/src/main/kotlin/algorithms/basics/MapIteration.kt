package algorithms.basics

/**
 * MAP ITERATION - Complete Guide
 *
 * This file demonstrates all map iteration patterns.
 * Covers the iteration table from Maps-in-Kotlin.md
 *
 * IMPORTANT: Maps use KEYS, not indices!
 * Maps are unordered - no concept of position 0, 1, 2
 */

// TIME COMPLEXITY: O(n) - iterate through n key-value pairs in map
// SPACE COMPLEXITY: O(1) - only loop variables

fun main() {
    println("MAP ITERATION PATTERNS")
    val map = mutableMapOf("a" to 1, "b" to 2, "c" to 3)
    println("Map: $map")  // Map: {a=1, b=2, c=3}
    println("Size: ${map.size}")  // Size: 3
    println()

    // WHY NO INDICES?
    println("WHY MAPS DON'T HAVE INDICES:")
    println("- Maps are UNORDERED (no position 0, 1, 2...)")
    println("- Maps use KEYS (like \"name\", \"age\") not numeric indices")
    println("- There is NO map.indices property!")
    println()

    // COMPARISON: Array vs Map
    println("ARRAY vs MAP - Different access patterns:")
    val arr = intArrayOf(10, 20, 30)
    println("Array: ${arr.contentToString()}")  // Array: [10, 20, 30]
    println("arr[0] = ${arr[0]}              // ✓ Access by INDEX")
    println("arr.indices exists              // ✓ Has .indices property")
    println()
    println("Map: $map")  // Map: {a=1, b=2, c=3}
    println("map[\"a\"] = ${map["a"]}              // ✓ Access by KEY")
    println("map.indices does NOT exist      // ✗ No .indices property")
    // println("map[0] = ${map[0]}")  // ✗ ERROR! Can't use numeric index
    println()

    // Pattern 1: Access by key (returns nullable)
    println("Pattern 1: map[key] - Access value by key (can return null)")
    val value1: Int? = map["a"]  // Type is Int? (nullable)
    val value2: Int? = map["z"]  // Returns null if key doesn't exist
    println("val value1: Int? = map[\"a\"]  // value1 = ${value1}")  // value1 = 1
    println("val value2: Int? = map[\"z\"]  // value2 = ${value2}  (null - key doesn't exist)")
    println()

    // Using nullable value safely
    println("Safe access with nullable:")
    val result = map["a"]?.let { it * 2 } ?: 0  // Safe call with Elvis operator
    println("val result = map[\"a\"]?.let { it * 2 } ?: 0  // result = $result")  // result = 2
    println()

    // Pattern 2: Access with default (non-nullable)
    println("Pattern 2: map.getOrDefault(key, default) - Returns non-nullable")
    val value3: Int = map.getOrDefault("a", 0)  // Type is Int (not nullable)
    val value4: Int = map.getOrDefault("z", 0)  // Returns default if key not found
    println("val value3: Int = map.getOrDefault(\"a\", 0)  // value3 = $value3")  // value3 = 1
    println("val value4: Int = map.getOrDefault(\"z\", 0)  // value4 = $value4  (returns default)")
    println()

    // Pattern 3: Iterate key-value pairs (BEST)
    println("Pattern 3: for ((key, value) in map) - BEST for maps")
    for ((key, value) in map) {
        println("$key -> $value")  // a -> 1
                                    // b -> 2
                                    // c -> 3
    }
    println()

    // Pattern 4: Iterate keys only
    println("Pattern 4: for (key in map.keys) - Keys only")
    for (key in map.keys) {
        println("Key: $key")  // Key: a
                              // Key: b
                              // Key: c
    }
    println()

    // Pattern 5: Iterate values only
    println("Pattern 5: for (value in map.values) - Values only")
    for (value in map.values) {
        println("Value: $value")  // Value: 1
                                   // Value: 2
                                   // Value: 3
    }
    println()

    // COMMON USE CASE: Frequency counter
    println("COMMON USE CASE: Frequency Counter")
    val text = "hello"
    val freq = mutableMapOf<Char, Int>()
    for (char in text) {
        freq[char] = freq.getOrDefault(char, 0) + 1
    }
    println("Text: \"$text\"")  // Text: "hello"
    println("Character frequencies:")
    for ((char, count) in freq) {
        println("'$char': $count")  // 'h': 1
                                     // 'e': 1
                                     // 'l': 2
                                     // 'o': 1
    }
    println()

    // WRONG PATTERNS - DO NOT USE
    println("WRONG PATTERNS - These DON'T work with maps!")
    println("✗ map.indices           // ERROR! No .indices property")
    println("✗ map[0]                // ERROR! Can't use numeric index")
    println("✗ for (i in map.indices) // ERROR! Maps don't have indices")
    println()

    // SUMMARY
    println("------------------------------------------------------------")
    println("SUMMARY:")
    println("✓ map[key]                        - Returns Int? (nullable)")
    println("✓ map.getOrDefault(key, default)  - Returns Int (non-nullable)")
    println("✓ map[key]?.let { } ?: default    - Safe nullable access")
    println("✓ for ((key, value) in map)       - BEST for iteration")
    println("✓ for (key in map.keys)           - Keys only")
    println("✓ for (value in map.values)       - Values only")
    println("✗ map.indices                     - DOESN'T EXIST!")
    println("✗ map[0]                          - ERROR!")
    println("------------------------------------------------------------")
}

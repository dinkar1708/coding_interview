# Array Problem Types

## Overview
Array problems are most common in interviews. Master these 4 types.

---

## Type 1: Two Pointers

WHEN: Sorted array, finding pairs, partitioning, reversing

PATTERN:
```kotlin
var left = 0
var right = arr.size - 1
while (left < right) {
    if (condition) left++ else right--
}
```

### Subtypes

#### 1A. Opposite Direction
Example: Two Sum in sorted array

CODE: TODO - Add TwoSum.kt

```kotlin
fun twoSum(arr: IntArray, target: Int): IntArray {
    var left = 0
    var right = arr.size - 1
    while (left < right) {
        val sum = arr[left] + arr[right]
        when {
            sum == target -> return intArrayOf(left, right)
            sum < target -> left++
            else -> right--
        }
    }
    return intArrayOf(-1, -1)
}
```

#### 1B. Same Direction
Example: Remove duplicates

CODE: TODO - Add RemoveDuplicates.kt

```kotlin
fun removeDuplicates(arr: IntArray): Int {
    var slow = 0
    for (fast in 1 until arr.size) {
        if (arr[fast] != arr[slow]) {
            slow++
            arr[slow] = arr[fast]
        }
    }
    return slow + 1
}
```

PROBLEMS:
- Two Sum (sorted)
- 3Sum, 4Sum
- Container With Most Water - CODE: src/main/kotlin/algorithms/arrays/ContainerWithMostWater.kt
- Remove Duplicates

---

## Type 2: Subarray Sum

WHEN: Find max/min sum of contiguous elements

PATTERN:
```kotlin
var curr = arr[0]
var best = arr[0]
for (i in 1 until arr.size) {
    curr = maxOf(arr[i], curr + arr[i])
    best = maxOf(best, curr)
}
```

### Subtypes

#### 2A. Maximum Subarray Sum
CODE: src/main/kotlin/algorithms/arrays/subarrays/KadanesAlgorithm.kt

#### 2B. Fixed Size K
CODE: src/main/kotlin/algorithms/arrays/subarrays/MaxSliceSum.kt

#### 2C. Maximum Product
CODE: TODO - Add MaxProductSubarray.kt

```kotlin
fun maxProduct(nums: IntArray): Int {
    var maxSoFar = nums[0]
    var minSoFar = nums[0]
    var result = nums[0]
    for (i in 1 until nums.size) {
        val temp = maxSoFar
        maxSoFar = maxOf(nums[i], maxOf(nums[i] * maxSoFar, nums[i] * minSoFar))
        minSoFar = minOf(nums[i], minOf(nums[i] * temp, nums[i] * minSoFar))
        result = maxOf(result, maxSoFar)
    }
    return result
}
```

PROBLEMS:
- Maximum Subarray - CODE: KadanesAlgorithm.kt
- Best Time to Buy/Sell Stock - CODE: subarrays/MaxProfit.kt
- Maximum Product Subarray

---

## Type 3: Array Rotation

WHEN: Cyclic operations, rotated sorted arrays

### Subtypes

#### 3A. Rotation Operations
CODE: src/main/kotlin/algorithms/arrays/rotation/
- CyclicRotationJuggling.kt
- LeftRotateModulus.kt
- RightRotateModulus.kt

#### 3B. Search in Rotated
CODE: TODO - Add SearchRotatedArray.kt

```kotlin
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    while (left <= right) {
        val mid = left + (right - left) / 2
        if (nums[mid] == target) return mid
        if (nums[left] <= nums[mid]) {
            if (target >= nums[left] && target < nums[mid]) right = mid - 1
            else left = mid + 1
        } else {
            if (target > nums[mid] && target <= nums[right]) left = mid + 1
            else right = mid - 1
        }
    }
    return -1
}
```

PROBLEMS:
- Rotate Array - CODE: Multiple implementations in rotation/
- Search in Rotated Sorted Array
- Find Minimum in Rotated Array

---

## Type 4: Prefix Sum

WHEN: Multiple queries on subarray sums

PATTERN:
```kotlin
val prefix = IntArray(arr.size + 1)
for (i in arr.indices) {
    prefix[i + 1] = prefix[i] + arr[i]
}
// Sum from i to j = prefix[j+1] - prefix[i]
```

### Subtypes

#### 4A. Basic Prefix Sum
CODE: src/main/kotlin/algorithms/prefix_sum/
- PassingCars.kt
- CountDivisible.kt

#### 4B. Prefix Sum + HashMap
Example: Subarray sum equals K

CODE: TODO - Add SubarraySumEqualsK.kt

```kotlin
fun subarraySum(nums: IntArray, k: Int): Int {
    val map = mutableMapOf<Int, Int>()
    map[0] = 1
    var sum = 0
    var count = 0
    for (num in nums) {
        sum += num
        count += map.getOrDefault(sum - k, 0)
        map[sum] = map.getOrDefault(sum, 0) + 1
    }
    return count
}
```

PROBLEMS:
- Range Sum Query
- Subarray Sum Equals K
- Passing Cars - CODE: prefix_sum/PassingCars.kt
- Count Divisible - CODE: prefix_sum/CountDivisible.kt

---

## Quick Reference

| Problem Contains | Type | Have Code? |
|------------------|------|------------|
| find pair sums to X | Two Pointers | TODO |
| maximum sum subarray | Kadane | YES |
| rotate array by k | Rotation | YES (6 files) |
| sum from i to j | Prefix Sum | YES |
| search in rotated | Binary Search | TODO |
| container most water | Two Pointers | YES |

---

## Interview Tips

1. Sorted? Think Two Pointers or Binary Search
2. Contiguous sum? Think Kadane or Prefix Sum
3. Multiple queries? Think Prefix Sum
4. Rotation? We have 6+ implementations

## Practice Order

1. Start: Two Sum, Maximum Subarray
2. Medium: Container With Most Water, Rotate Array
3. Advanced: 3Sum, Subarray Sum Equals K

SEE ALSO:
- algorithm-theory/sorting_searching_algorithms.md
- techniques/prefix-sum-technique.md
- sliding-window.md

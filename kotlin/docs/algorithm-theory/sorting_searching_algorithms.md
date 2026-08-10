# Sorting & Searching Algorithms

## Sorting Algorithms

### 1. Bubble Sort

**Concept:** Repeatedly swap adjacent elements if they're in wrong order

**Time:** O(n²) | **Space:** O(1) | **Stable:** Yes

```kotlin
fun bubbleSort(arr: IntArray) {
    val n = arr.size

    for (i in 0 until n - 1) {
        var swapped = false

        for (j in 0 until n - i - 1) {
            if (arr[j] > arr[j + 1]) {
                arr[j] = arr[j + 1].also { arr[j + 1] = arr[j] }
                swapped = true
            }
        }

        if (!swapped) break  // Optimization: already sorted
    }
}
```

**When to use:** Small datasets, nearly sorted data

### 2. Selection Sort

**Concept:** Find minimum element and place it at beginning

**Time:** O(n²) | **Space:** O(1) | **Stable:** No

```kotlin
fun selectionSort(arr: IntArray) {
    val n = arr.size

    for (i in 0 until n - 1) {
        var minIdx = i

        for (j in i + 1 until n) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j
            }
        }

        if (minIdx != i) {
            arr[i] = arr[minIdx].also { arr[minIdx] = arr[i] }
        }
    }
}
```

**When to use:** Small datasets, memory is limited

### 3. Insertion Sort

**Concept:** Build sorted array one element at a time

**Time:** O(n²) avg, O(n) best | **Space:** O(1) | **Stable:** Yes

```kotlin
fun insertionSort(arr: IntArray) {
    for (i in 1 until arr.size) {
        val key = arr[i]
        var j = i - 1

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }

        arr[j + 1] = key
    }
}
```

**When to use:** Small datasets, nearly sorted data, online sorting

### 4. Merge Sort

**Concept:** Divide and conquer - split, sort, merge

**Time:** O(n log n) | **Space:** O(n) | **Stable:** Yes

```kotlin
fun mergeSort(arr: IntArray, left: Int = 0, right: Int = arr.size - 1) {
    if (left < right) {
        val mid = left + (right - left) / 2

        mergeSort(arr, left, mid)
        mergeSort(arr, mid + 1, right)
        merge(arr, left, mid, right)
    }
}

fun merge(arr: IntArray, left: Int, mid: Int, right: Int) {
    val leftArr = arr.copyOfRange(left, mid + 1)
    val rightArr = arr.copyOfRange(mid + 1, right + 1)

    var i = 0
    var j = 0
    var k = left

    while (i < leftArr.size && j < rightArr.size) {
        if (leftArr[i] <= rightArr[j]) {
            arr[k++] = leftArr[i++]
        } else {
            arr[k++] = rightArr[j++]
        }
    }

    while (i < leftArr.size) arr[k++] = leftArr[i++]
    while (j < rightArr.size) arr[k++] = rightArr[j++]
}
```

**When to use:** Large datasets, stable sort needed, linked lists

### 5. Quick Sort

**Concept:** Pick pivot, partition around it, recursively sort

**Time:** O(n log n) avg, O(n²) worst | **Space:** O(log n) | **Stable:** No

```kotlin
fun quickSort(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
    if (low < high) {
        val pi = partition(arr, low, high)

        quickSort(arr, low, pi - 1)
        quickSort(arr, pi + 1, high)
    }
}

fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high]
    var i = low - 1

    for (j in low until high) {
        if (arr[j] < pivot) {
            i++
            arr[i] = arr[j].also { arr[j] = arr[i] }
        }
    }

    arr[i + 1] = arr[high].also { arr[high] = arr[i + 1] }
    return i + 1
}
```

**When to use:** General purpose, good average performance, in-place sorting

### 6. Heap Sort

**Concept:** Build max heap, extract maximum repeatedly

**Time:** O(n log n) | **Space:** O(1) | **Stable:** No

```kotlin
fun heapSort(arr: IntArray) {
    val n = arr.size

    // Build max heap
    for (i in n / 2 - 1 downTo 0) {
        heapify(arr, n, i)
    }

    // Extract elements from heap
    for (i in n - 1 downTo 1) {
        arr[0] = arr[i].also { arr[i] = arr[0] }
        heapify(arr, i, 0)
    }
}

fun heapify(arr: IntArray, n: Int, i: Int) {
    var largest = i
    val left = 2 * i + 1
    val right = 2 * i + 2

    if (left < n && arr[left] > arr[largest]) {
        largest = left
    }

    if (right < n && arr[right] > arr[largest]) {
        largest = right
    }

    if (largest != i) {
        arr[i] = arr[largest].also { arr[largest] = arr[i] }
        heapify(arr, n, largest)
    }
}
```

**When to use:** Large datasets, guaranteed O(n log n), in-place sorting

### 7. Counting Sort

**Concept:** Count occurrences, calculate positions

**Time:** O(n + k) | **Space:** O(k) | **Stable:** Yes

```kotlin
fun countingSort(arr: IntArray) {
    if (arr.isEmpty()) return

    val max = arr.maxOrNull()!!
    val min = arr.minOrNull()!!
    val range = max - min + 1

    val count = IntArray(range)
    val output = IntArray(arr.size)

    // Count occurrences
    for (num in arr) {
        count[num - min]++
    }

    // Calculate positions
    for (i in 1 until range) {
        count[i] += count[i - 1]
    }

    // Build output array
    for (i in arr.size - 1 downTo 0) {
        val num = arr[i]
        output[count[num - min] - 1] = num
        count[num - min]--
    }

    // Copy to original
    for (i in arr.indices) {
        arr[i] = output[i]
    }
}
```

**When to use:** Small range of integers, need linear time

### 8. Radix Sort

**Concept:** Sort by digit/character position

**Time:** O(d * (n + k)) | **Space:** O(n + k) | **Stable:** Yes

```kotlin
fun radixSort(arr: IntArray) {
    val max = arr.maxOrNull() ?: return

    var exp = 1
    while (max / exp > 0) {
        countingSortByDigit(arr, exp)
        exp *= 10
    }
}

fun countingSortByDigit(arr: IntArray, exp: Int) {
    val output = IntArray(arr.size)
    val count = IntArray(10)

    for (num in arr) {
        count[(num / exp) % 10]++
    }

    for (i in 1..9) {
        count[i] += count[i - 1]
    }

    for (i in arr.size - 1 downTo 0) {
        val digit = (arr[i] / exp) % 10
        output[count[digit] - 1] = arr[i]
        count[digit]--
    }

    for (i in arr.indices) {
        arr[i] = output[i]
    }
}
```

**When to use:** Integers with limited digits, strings

## Searching Algorithms

### 1. Linear Search

**Concept:** Check each element sequentially

**Time:** O(n) | **Space:** O(1)

```kotlin
fun linearSearch(arr: IntArray, target: Int): Int {
    for (i in arr.indices) {
        if (arr[i] == target) return i
    }
    return -1
}
```

**When to use:** Unsorted data, small datasets

### 2. Binary Search

**Concept:** Divide and conquer on sorted data

**Time:** O(log n) | **Space:** O(1) iterative, O(log n) recursive

```kotlin
// Iterative
fun binarySearch(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        when {
            arr[mid] == target -> return mid
            arr[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return -1
}

// Recursive
fun binarySearchRecursive(arr: IntArray, target: Int, left: Int = 0, right: Int = arr.size - 1): Int {
    if (left > right) return -1

    val mid = left + (right - left) / 2

    return when {
        arr[mid] == target -> mid
        arr[mid] < target -> binarySearchRecursive(arr, target, mid + 1, right)
        else -> binarySearchRecursive(arr, target, left, mid - 1)
    }
}

// Find insert position
fun searchInsert(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        when {
            arr[mid] == target -> return mid
            arr[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return left
}
```

**Variants:**
- First occurrence
- Last occurrence
- Floor/Ceiling
- Search in rotated array

```kotlin
// First occurrence
fun findFirst(arr: IntArray, target: Int): Int {
    var left = 0
    var right = arr.size - 1
    var result = -1

    while (left <= right) {
        val mid = left + (right - left) / 2

        when {
            arr[mid] == target -> {
                result = mid
                right = mid - 1  // Continue searching left
            }
            arr[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }

    return result
}

// Search in rotated sorted array
fun searchRotated(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        if (nums[mid] == target) return mid

        // Left half is sorted
        if (nums[left] <= nums[mid]) {
            if (target >= nums[left] && target < nums[mid]) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        // Right half is sorted
        else {
            if (target > nums[mid] && target <= nums[right]) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
    }

    return -1
}
```

**When to use:** Sorted data, large datasets

### 3. Jump Search

**Concept:** Jump ahead by fixed steps, then linear search

**Time:** O(√n) | **Space:** O(1)

```kotlin
fun jumpSearch(arr: IntArray, target: Int): Int {
    val n = arr.size
    var step = Math.sqrt(n.toDouble()).toInt()
    var prev = 0

    while (arr[minOf(step, n) - 1] < target) {
        prev = step
        step += Math.sqrt(n.toDouble()).toInt()
        if (prev >= n) return -1
    }

    while (arr[prev] < target) {
        prev++
        if (prev == minOf(step, n)) return -1
    }

    return if (arr[prev] == target) prev else -1
}
```

**When to use:** Sorted data, jumping is cheaper than comparison

### 4. Interpolation Search

**Concept:** Binary search with better guessing for uniformly distributed data

**Time:** O(log log n) best, O(n) worst | **Space:** O(1)

```kotlin
fun interpolationSearch(arr: IntArray, target: Int): Int {
    var low = 0
    var high = arr.size - 1

    while (low <= high && target >= arr[low] && target <= arr[high]) {
        if (low == high) {
            return if (arr[low] == target) low else -1
        }

        val pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low])

        when {
            arr[pos] == target -> return pos
            arr[pos] < target -> low = pos + 1
            else -> high = pos - 1
        }
    }

    return -1
}
```

**When to use:** Uniformly distributed sorted data

## Kotlin Built-in Functions

```kotlin
// Sort in-place
arr.sort()
arr.sortDescending()

// Return sorted (new array)
val sorted = arr.sorted()
val sortedDesc = arr.sortedDescending()

// Custom comparator
arr.sortWith(compareBy { it.abs() })

// Binary search (returns index or -(insertion point) - 1)
val index = arr.binarySearch(target)

// Sort objects
data class Person(val name: String, val age: Int)
people.sortBy { it.age }
people.sortWith(compareBy({ it.age }, { it.name }))
```

## Comparison Table

| Algorithm | Time (Best) | Time (Avg) | Time (Worst) | Space | Stable |
|-----------|-------------|------------|--------------|-------|--------|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | No |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | No |
| Counting Sort | O(n+k) | O(n+k) | O(n+k) | O(k) | Yes |
| Radix Sort | O(nk) | O(nk) | O(nk) | O(n+k) | Yes |

## Interview Tips

1. **Ask about data**: size, range, sorted?, duplicates?
2. **Choose appropriately**:
   - Small data → Insertion sort
   - Need stable → Merge sort
   - General purpose → Quick sort
   - Limited range integers → Counting sort
3. **Binary search variants** are very common
4. **Know Kotlin built-ins** - often sufficient
5. **Explain tradeoffs** - time vs space, stable vs unstable

## Common Mistakes

```kotlin
// ❌ Wrong - integer overflow
val mid = (left + right) / 2

// ✅ Correct
val mid = left + (right - left) / 2

// ❌ Wrong - off by one
while (left < right)  // May miss elements

// ✅ Correct
while (left <= right)
```

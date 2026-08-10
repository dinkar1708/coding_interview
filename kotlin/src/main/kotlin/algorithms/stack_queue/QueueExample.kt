package algorithms.stacks

/**
 * QUEUE EXAMPLES - Using Native Queue Library
 *
 * Queue: First-In-First-Out (FIFO) data structure
 * Think of it like a line at a store - first person in line is served first
 *
 * Common Queue Methods:
 * - add(item) / offer(item): Add item to rear
 * - remove() / poll(): Remove and return front item
 * - element() / peek(): Look at front item without removing
 * - isEmpty(): Check if queue is empty
 * - size: Get number of items
 *
 * Difference between methods:
 * - add() throws exception if fails, offer() returns false
 * - remove() throws exception if empty, poll() returns null
 * - element() throws exception if empty, peek() returns null
 *
 * Time Complexity: All operations O(1)
 * Space Complexity: O(n) where n is number of elements
 */

import java.util.LinkedList
import java.util.Queue
import java.util.ArrayDeque
import java.util.PriorityQueue

fun main() {
    println("=== Example 1: Basic Queue Operations ===")
    basicQueueOperations()
    println()

    println("=== Example 2: offer() vs add() ===")
    offerVsAdd()
    println()

    println("=== Example 3: poll() vs remove() ===")
    pollVsRemove()
    println()

    println("=== Example 4: peek() vs element() ===")
    peekVsElement()
    println()

    println("=== Example 5: Print Queue Order ===")
    printQueueOrder()
    println()

    println("=== Example 6: Reverse First K Elements ===")
    reverseFirstK(5, 3)
    println()

    println("=== Example 7: Using ArrayDeque as Queue ===")
    arrayDequeAsQueue()
    println()

    println("=== Example 8: Priority Queue ===")
    priorityQueueExample()
    println()

    println("=== Example 9: Check Empty Queue ===")
    checkEmptyQueue()
}

// EXAMPLE 1: Basic Queue Operations
private fun basicQueueOperations() {
    val queue: Queue<String> = LinkedList()

    // add() - Add items to rear
    queue.add("First")
    queue.add("Second")
    queue.add("Third")

    println("Queue after add: $queue")
    println("Size: ${queue.size}")

    // remove() - Remove and return front item
    val removed = queue.remove()
    println("Removed: $removed")
    println("Queue after remove: $queue")
}

// EXAMPLE 2: offer() vs add()
private fun offerVsAdd() {
    val queue: Queue<Int> = LinkedList()

    // add() - Adds item, throws exception if fails
    queue.add(10)
    println("After add(10): $queue")

    // offer() - Adds item, returns false if fails (better for bounded queues)
    val success = queue.offer(20)
    println("After offer(20): $queue, Success: $success")

    // For LinkedList, both work the same
    // But offer() is preferred for bounded capacity queues
}

// EXAMPLE 3: poll() vs remove()
private fun pollVsRemove() {
    val queue: Queue<String> = LinkedList()
    queue.add("A")
    queue.add("B")

    println("Queue: $queue")

    // remove() - Removes front, throws exception if empty
    val removed = queue.remove()
    println("remove(): $removed, Queue: $queue")

    // poll() - Removes front, returns null if empty (safer!)
    val polled1 = queue.poll()
    println("poll(): $polled1, Queue: $queue")

    val polled2 = queue.poll()  // Queue is now empty
    println("poll() on empty queue: $polled2 (null, no exception)")

    // Uncomment to see exception:
    // val error = queue.remove()  // Throws NoSuchElementException!
}

// EXAMPLE 4: peek() vs element()
private fun peekVsElement() {
    val queue: Queue<Int> = LinkedList()

    // peek() - Returns null if empty (safer!)
    val peeked1 = queue.peek()
    println("peek() on empty queue: $peeked1 (null)")

    // Uncomment to see exception:
    // val error = queue.element()  // Throws NoSuchElementException!

    queue.add(100)
    queue.add(200)

    println("Queue: $queue")

    // Both return front without removing
    val peeked2 = queue.peek()
    val element = queue.element()
    println("peek(): $peeked2")
    println("element(): $element")
    println("Queue after peek/element: $queue (unchanged)")
}

// EXAMPLE 5: Print Queue Order (FIFO)
private fun printQueueOrder() {
    val queue: Queue<String> = LinkedList()

    println("Adding items: Apple, Banana, Cherry")
    queue.add("Apple")
    queue.add("Banana")
    queue.add("Cherry")

    println("Queue: $queue")
    println("\nRemoving in FIFO order:")

    while (!queue.isEmpty()) {
        val item = queue.remove()
        println("  Removed: $item")
    }
}

// EXAMPLE 6: Reverse First K Elements Using Stack
// Time: O(n), Space: O(k)
private fun reverseFirstK(n: Int, k: Int) {
    val queue: Queue<Int> = LinkedList()

    // Add numbers 1 to n
    for (i in 1..n) {
        queue.add(i)
    }

    println("Original queue: $queue")
    println("Reverse first $k elements")

    // Use stack to reverse first k elements
    val stack = java.util.Stack<Int>()

    // Remove first k elements and push to stack
    for (i in 1..k) {
        stack.push(queue.remove())
    }

    // Pop from stack and add back to queue
    while (!stack.isEmpty()) {
        queue.add(stack.pop())
    }

    // Move remaining elements to back
    for (i in 1..(n - k)) {
        queue.add(queue.remove())
    }

    println("After reversing: $queue")
}

// EXAMPLE 7: Using ArrayDeque as Queue (Recommended!)
private fun arrayDequeAsQueue() {
    // ArrayDeque is faster than LinkedList for queue operations
    val queue: Queue<String> = ArrayDeque()

    queue.add("First")
    queue.add("Second")
    queue.add("Third")

    println("ArrayDeque as Queue: $queue")

    while (!queue.isEmpty()) {
        println("  Removed: ${queue.poll()}")
    }

    println("ArrayDeque is recommended over LinkedList for better performance!")
}

// EXAMPLE 8: Priority Queue (Elements sorted by priority)
private fun priorityQueueExample() {
    // PriorityQueue: Elements come out in sorted order (min heap by default)
    val pq = PriorityQueue<Int>()

    println("Adding: 30, 10, 50, 20, 40")
    pq.add(30)
    pq.add(10)
    pq.add(50)
    pq.add(20)
    pq.add(40)

    println("PriorityQueue (internal order may vary): $pq")
    println("\nRemoving in priority order (ascending):")

    while (!pq.isEmpty()) {
        println("  Removed: ${pq.poll()}")
    }

    // Custom comparator (descending order)
    println("\nWith custom comparator (descending):")
    val pqDesc = PriorityQueue<Int>(compareByDescending { it })
    pqDesc.addAll(listOf(30, 10, 50, 20, 40))

    while (!pqDesc.isEmpty()) {
        println("  Removed: ${pqDesc.poll()}")
    }
}

// EXAMPLE 9: Check Empty Queue
private fun checkEmptyQueue() {
    val queue: Queue<String> = LinkedList()

    println("Is queue empty? ${queue.isEmpty()}")

    queue.add("Item")
    println("After add, is empty? ${queue.isEmpty()}")

    queue.poll()
    println("After poll, is empty? ${queue.isEmpty()}")
}

/*
================================================================================
NATIVE QUEUE METHODS (java.util.Queue)
================================================================================

METHOD                  DESCRIPTION                         EXCEPTION?
----------------------------------------------------------------------
add(item)              Add to rear                         Yes (if full)
offer(item)            Add to rear                         No (returns false)
remove()               Remove from front                   Yes (if empty)
poll()                 Remove from front                   No (returns null)
element()              Look at front                       Yes (if empty)
peek()                 Look at front                       No (returns null)
isEmpty()              Check if empty                      No
size                   Get number of elements              No
clear()                Remove all elements                 No

RECOMMENDATION: Use offer(), poll(), peek() - they're safer! ✓

================================================================================
QUEUE IMPLEMENTATIONS
================================================================================

Implementation          Best For                    Notes
----------------------------------------------------------------------
LinkedList             General purpose             Simple, versatile
ArrayDeque             Performance (recommended!)  Faster than LinkedList
PriorityQueue          Sorted order                Min/Max heap

Example:
val queue: Queue<Int> = LinkedList()      // Simple
val queue: Queue<Int> = ArrayDeque()      // Faster ✓
val pq = PriorityQueue<Int>()             // Sorted

================================================================================
COMMON QUEUE OPERATIONS
================================================================================

Operation               Code Example
----------------------------------------------------------------------
Create queue           val queue: Queue<Int> = LinkedList()
Add item               queue.offer(10)
Remove item            val item = queue.poll()
Look at front          val front = queue.peek()
Check empty            if (queue.isEmpty()) { ... }
Get size               val size = queue.size
Clear all              queue.clear()

================================================================================
QUEUE vs STACK
================================================================================

Queue (FIFO - First In First Out):
- Like a line at a store
- First person added is first served
- Example: [1, 2, 3] → remove() → 1

Stack (LIFO - Last In First Out):
- Like a stack of plates
- Last item added is first removed
- Example: [1, 2, 3] → pop() → 3

Visual:
Queue: [Front] 1 ← 2 ← 3 [Rear]  (add to rear, remove from front)
Stack: [Top] 3, 2, 1 [Bottom]     (add/remove from top only)

================================================================================
COMMON USE CASES
================================================================================

1. BFS (Breadth-First Search)
2. Task scheduling
3. Print queue
4. Customer service (first come, first served)
5. Buffer for data streams
6. Level-order tree traversal
7. Request handling in servers
8. Message queues

================================================================================
SAFE vs UNSAFE METHODS
================================================================================

SAFE (Recommended):          UNSAFE:
offer() → returns false      add() → throws exception
poll() → returns null        remove() → throws exception
peek() → returns null        element() → throws exception

Use safe methods to avoid exceptions! ✓

================================================================================
*/

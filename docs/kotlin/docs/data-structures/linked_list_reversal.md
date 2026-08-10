# Linked List Reversal - Complete Guide

## Node Definition

```kotlin
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
```

## 1. Reverse Entire Linked List

### Iterative Approach
**Time:** O(n) | **Space:** O(1)

```kotlin
fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var curr = head

    while (curr != null) {
        val nextTemp = curr.next  // Save next
        curr.next = prev          // Reverse link
        prev = curr               // Move prev forward
        curr = nextTemp           // Move curr forward
    }

    return prev
}
```

**Step-by-step:**
```
Initial: 1 → 2 → 3 → 4 → null

Step 1: null ← 1   2 → 3 → 4 → null
        prev  curr

Step 2: null ← 1 ← 2   3 → 4 → null
              prev  curr

Step 3: null ← 1 ← 2 ← 3   4 → null
                    prev  curr

Step 4: null ← 1 ← 2 ← 3 ← 4
                          prev  curr(null)

Result: 4 → 3 → 2 → 1 → null
```

### Recursive Approach
**Time:** O(n) | **Space:** O(n) for call stack

```kotlin
fun reverseListRecursive(head: ListNode?): ListNode? {
    // Base case
    if (head?.next == null) return head

    // Reverse rest of list
    val newHead = reverseListRecursive(head.next)

    // Reverse current node
    head.next?.next = head
    head.next = null

    return newHead
}
```

## 2. Reverse First K Nodes

**Time:** O(n) | **Space:** O(1)

```kotlin
fun reverseFirstK(head: ListNode?, k: Int): ListNode? {
    var prev: ListNode? = null
    var curr = head
    var count = 0

    // Reverse first k nodes
    while (curr != null && count < k) {
        val nextTemp = curr.next
        curr.next = prev
        prev = curr
        curr = nextTemp
        count++
    }

    // Connect with remaining list
    head?.next = curr

    return prev
}
```

## 3. Reverse Between Positions (Left to Right)

**Problem:** Reverse nodes from position left to right (1-indexed)

**Time:** O(n) | **Space:** O(1)

```kotlin
fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    if (head == null || left == right) return head

    val dummy = ListNode(0)
    dummy.next = head
    var prev: ListNode? = dummy

    // Move to node before 'left'
    for (i in 1 until left) {
        prev = prev?.next
    }

    // Reverse from left to right
    var curr = prev?.next
    for (i in left until right) {
        val nextTemp = curr?.next
        curr?.next = nextTemp?.next
        nextTemp?.next = prev?.next
        prev?.next = nextTemp
    }

    return dummy.next
}
```

**Example:**
```
Input: 1 → 2 → 3 → 4 → 5, left = 2, right = 4
Output: 1 → 4 → 3 → 2 → 5
```

## 4. Reverse in Groups of K

**Problem:** Reverse every k nodes

**Time:** O(n) | **Space:** O(1)

```kotlin
fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    if (head == null || k == 1) return head

    val dummy = ListNode(0)
    dummy.next = head
    var prev: ListNode? = dummy
    var curr = head

    // Count total nodes
    var count = 0
    var temp = head
    while (temp != null) {
        count++
        temp = temp.next
    }

    // Reverse in groups of k
    while (count >= k) {
        curr = prev?.next
        var next = curr?.next

        for (i in 1 until k) {
            curr?.next = next?.next
            next?.next = prev?.next
            prev?.next = next
            next = curr?.next
        }

        prev = curr
        count -= k
    }

    return dummy.next
}
```

**Example:**
```
Input: 1 → 2 → 3 → 4 → 5 → 6, k = 3
Output: 3 → 2 → 1 → 6 → 5 → 4

Input: 1 → 2 → 3 → 4 → 5, k = 3
Output: 3 → 2 → 1 → 4 → 5  (last group not reversed if < k)
```

## 5. Reverse Alternate K Nodes

**Problem:** Reverse every alternate k nodes

**Time:** O(n) | **Space:** O(1)

```kotlin
fun reverseAlternateKGroup(head: ListNode?, k: Int): ListNode? {
    var curr = head
    var prev: ListNode? = null
    var count = 0

    // Reverse first k nodes
    while (curr != null && count < k) {
        val nextTemp = curr.next
        curr.next = prev
        prev = curr
        curr = nextTemp
        count++
    }

    // Skip next k nodes
    if (head != null) {
        head.next = curr

        count = 0
        while (count < k - 1 && curr != null) {
            curr = curr.next
            count++
        }

        // Recursively reverse next k nodes
        if (curr != null) {
            curr.next = reverseAlternateKGroup(curr.next, k)
        }
    }

    return prev
}
```

## 6. Reverse Nodes in Pairs (Swap Adjacent)

**Time:** O(n) | **Space:** O(1)

```kotlin
fun swapPairs(head: ListNode?): ListNode? {
    val dummy = ListNode(0)
    dummy.next = head
    var prev: ListNode? = dummy

    while (prev?.next != null && prev.next?.next != null) {
        val first = prev.next
        val second = first?.next

        // Swap
        first?.next = second?.next
        second?.next = first
        prev.next = second

        // Move to next pair
        prev = first
    }

    return dummy.next
}
```

**Example:**
```
Input: 1 → 2 → 3 → 4
Output: 2 → 1 → 4 → 3
```

## 7. Palindrome Linked List

**Using reversal to check palindrome**

**Time:** O(n) | **Space:** O(1)

```kotlin
fun isPalindrome(head: ListNode?): Boolean {
    if (head?.next == null) return true

    // Find middle
    var slow = head
    var fast = head
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }

    // Reverse second half
    var secondHalf = reverseList(slow)
    var firstHalf = head

    // Compare
    while (secondHalf != null) {
        if (firstHalf?.`val` != secondHalf.`val`) {
            return false
        }
        firstHalf = firstHalf.next
        secondHalf = secondHalf.next
    }

    return true
}

private fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var curr = head

    while (curr != null) {
        val next = curr.next
        curr.next = prev
        prev = curr
        curr = next
    }

    return prev
}
```

## 8. Add Two Numbers (Linked Lists in Reverse)

```kotlin
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val dummy = ListNode(0)
    var curr = dummy
    var carry = 0
    var p1 = l1
    var p2 = l2

    while (p1 != null || p2 != null || carry != 0) {
        val sum = (p1?.`val` ?: 0) + (p2?.`val` ?: 0) + carry

        carry = sum / 10
        curr.next = ListNode(sum % 10)
        curr = curr.next!!

        p1 = p1?.next
        p2 = p2?.next
    }

    return dummy.next
}
```

## 9. Rotate List

**Rotate list to the right by k places**

**Time:** O(n) | **Space:** O(1)

```kotlin
fun rotateRight(head: ListNode?, k: Int): ListNode? {
    if (head?.next == null || k == 0) return head

    // Find length and connect to make circular
    var length = 1
    var tail = head
    while (tail?.next != null) {
        tail = tail.next
        length++
    }

    tail?.next = head

    // Find new tail: length - k % length - 1
    val stepsToNewHead = length - k % length
    var newTail = head
    for (i in 1 until stepsToNewHead) {
        newTail = newTail?.next
    }

    val newHead = newTail?.next
    newTail?.next = null

    return newHead
}
```

## 10. Reorder List

**Reorder L0→L1→...→Ln-1→Ln to L0→Ln→L1→Ln-1→L2→Ln-2→...**

**Time:** O(n) | **Space:** O(1)

```kotlin
fun reorderList(head: ListNode?) {
    if (head?.next == null) return

    // Find middle
    var slow = head
    var fast = head
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }

    // Reverse second half
    var secondHalf = reverseList(slow?.next)
    slow?.next = null

    // Merge
    var first = head
    var second = secondHalf
    while (second != null) {
        val temp1 = first?.next
        val temp2 = second.next

        first?.next = second
        second.next = temp1

        first = temp1
        second = temp2
    }
}
```

## Common Techniques

### 1. Dummy Node
```kotlin
val dummy = ListNode(0)
dummy.next = head
// Simplifies edge cases
```

### 2. Two Pointers (Fast & Slow)
```kotlin
var slow = head
var fast = head
while (fast?.next != null) {
    slow = slow?.next
    fast = fast.next?.next
}
// slow is at middle
```

### 3. Pointer Manipulation Pattern
```kotlin
val nextTemp = curr.next  // 1. Save next
curr.next = prev          // 2. Reverse link
prev = curr               // 3. Move prev
curr = nextTemp           // 4. Move curr
```

## Interview Tips

1. **Draw it out** - visualize with arrows
2. **Dummy node** - simplifies edge cases (empty list, single node)
3. **Save next** - before changing pointers
4. **Null checks** - always check for null
5. **Edge cases**: empty list, single node, k > length
6. **Test thoroughly** - walk through with examples

## Common Mistakes

```kotlin
// ❌ Wrong - loses reference to next
curr.next = prev
curr = curr.next  // This is now prev!

// ✅ Correct - save next first
val nextTemp = curr.next
curr.next = prev
curr = nextTemp

// ❌ Wrong - modifying while iterating
while (curr != null) {
    curr.next = prev
    curr = curr.next  // Lost!
}

// ✅ Correct - save before modifying
while (curr != null) {
    val next = curr.next
    curr.next = prev
    prev = curr
    curr = next
}
```

## Practice Problems

1. ✓ Reverse Linked List
2. ✓ Reverse Linked List II
3. ✓ Reverse Nodes in k-Group
4. ✓ Swap Nodes in Pairs
5. ✓ Palindrome Linked List
6. ✓ Reorder List
7. ✓ Rotate List
8. Add Two Numbers
9. Remove Nth Node From End
10. Odd Even Linked List

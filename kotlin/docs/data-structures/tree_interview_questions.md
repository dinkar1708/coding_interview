# Tree Interview Questions - Complete Guide

## Tree Node Definition

```kotlin
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
```

## Tree Traversals

### 1. Inorder (Left → Root → Right)
```kotlin
fun inorderTraversal(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()

    fun inorder(node: TreeNode?) {
        if (node == null) return
        inorder(node.left)
        result.add(node.`val`)
        inorder(node.right)
    }

    inorder(root)
    return result
}

// Iterative
fun inorderIterative(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    val stack = ArrayDeque<TreeNode>()
    var curr = root

    while (curr != null || stack.isNotEmpty()) {
        while (curr != null) {
            stack.addLast(curr)
            curr = curr.left
        }
        curr = stack.removeLast()
        result.add(curr.`val`)
        curr = curr.right
    }

    return result
}
```

### 2. Preorder (Root → Left → Right)
```kotlin
fun preorderTraversal(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()

    fun preorder(node: TreeNode?) {
        if (node == null) return
        result.add(node.`val`)
        preorder(node.left)
        preorder(node.right)
    }

    preorder(root)
    return result
}

// Iterative
fun preorderIterative(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    val stack = ArrayDeque<TreeNode>()
    stack.addLast(root)

    while (stack.isNotEmpty()) {
        val node = stack.removeLast()
        result.add(node.`val`)

        node.right?.let { stack.addLast(it) }
        node.left?.let { stack.addLast(it) }
    }

    return result
}
```

### 3. Postorder (Left → Right → Root)
```kotlin
fun postorderTraversal(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()

    fun postorder(node: TreeNode?) {
        if (node == null) return
        postorder(node.left)
        postorder(node.right)
        result.add(node.`val`)
    }

    postorder(root)
    return result
}
```

### 4. Level Order (BFS)
```kotlin
fun levelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()

    val result = mutableListOf<List<Int>>()
    val queue = ArrayDeque<TreeNode>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val levelSize = queue.size
        val level = mutableListOf<Int>()

        repeat(levelSize) {
            val node = queue.removeFirst()
            level.add(node.`val`)

            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }

        result.add(level)
    }

    return result
}
```

## Common Tree Problems

### 1. Maximum Depth
```kotlin
fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    return 1 + maxOf(maxDepth(root.left), maxDepth(root.right))
}
```

### 2. Minimum Depth
```kotlin
fun minDepth(root: TreeNode?): Int {
    if (root == null) return 0
    if (root.left == null) return 1 + minDepth(root.right)
    if (root.right == null) return 1 + minDepth(root.left)
    return 1 + minOf(minDepth(root.left), minDepth(root.right))
}
```

### 3. Balanced Binary Tree
```kotlin
fun isBalanced(root: TreeNode?): Boolean {
    fun height(node: TreeNode?): Int {
        if (node == null) return 0

        val leftHeight = height(node.left)
        if (leftHeight == -1) return -1

        val rightHeight = height(node.right)
        if (rightHeight == -1) return -1

        if (Math.abs(leftHeight - rightHeight) > 1) return -1

        return 1 + maxOf(leftHeight, rightHeight)
    }

    return height(root) != -1
}
```

### 4. Diameter of Binary Tree
```kotlin
fun diameterOfBinaryTree(root: TreeNode?): Int {
    var diameter = 0

    fun height(node: TreeNode?): Int {
        if (node == null) return 0

        val leftHeight = height(node.left)
        val rightHeight = height(node.right)

        diameter = maxOf(diameter, leftHeight + rightHeight)

        return 1 + maxOf(leftHeight, rightHeight)
    }

    height(root)
    return diameter
}
```

### 5. Invert Binary Tree
```kotlin
fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) return null

    val temp = root.left
    root.left = invertTree(root.right)
    root.right = invertTree(temp)

    return root
}
```

### 6. Same Tree
```kotlin
fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p == null || q == null) return false
    if (p.`val` != q.`val`) return false

    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
}
```

### 7. Symmetric Tree
```kotlin
fun isSymmetric(root: TreeNode?): Boolean {
    fun isMirror(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        if (left == null || right == null) return false

        return left.`val` == right.`val` &&
               isMirror(left.left, right.right) &&
               isMirror(left.right, right.left)
    }

    return isMirror(root?.left, root?.right)
}
```

### 8. Path Sum
```kotlin
fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) return false
    if (root.left == null && root.right == null) return root.`val` == targetSum

    return hasPathSum(root.left, targetSum - root.`val`) ||
           hasPathSum(root.right, targetSum - root.`val`)
}
```

### 9. All Paths to Target Sum
```kotlin
fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    fun dfs(node: TreeNode?, sum: Int, path: MutableList<Int>) {
        if (node == null) return

        path.add(node.`val`)

        if (node.left == null && node.right == null && sum == node.`val`) {
            result.add(path.toList())
        } else {
            dfs(node.left, sum - node.`val`, path)
            dfs(node.right, sum - node.`val`, path)
        }

        path.removeAt(path.size - 1)
    }

    dfs(root, targetSum, mutableListOf())
    return result
}
```

### 10. Lowest Common Ancestor (BST)
```kotlin
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || p == null || q == null) return null

    val rootVal = root.`val`
    val pVal = p.`val`
    val qVal = q.`val`

    return when {
        pVal < rootVal && qVal < rootVal -> lowestCommonAncestor(root.left, p, q)
        pVal > rootVal && qVal > rootVal -> lowestCommonAncestor(root.right, p, q)
        else -> root
    }
}
```

### 11. Lowest Common Ancestor (Binary Tree)
```kotlin
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || root == p || root == q) return root

    val left = lowestCommonAncestor(root.left, p, q)
    val right = lowestCommonAncestor(root.right, p, q)

    return when {
        left != null && right != null -> root
        left != null -> left
        else -> right
    }
}
```

### 12. Validate BST
```kotlin
fun isValidBST(root: TreeNode?): Boolean {
    fun validate(node: TreeNode?, min: Long, max: Long): Boolean {
        if (node == null) return true

        if (node.`val` <= min || node.`val` >= max) return false

        return validate(node.left, min, node.`val`.toLong()) &&
               validate(node.right, node.`val`.toLong(), max)
    }

    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE)
}
```

### 13. Kth Smallest in BST
```kotlin
fun kthSmallest(root: TreeNode?, k: Int): Int {
    var count = k
    var result = 0

    fun inorder(node: TreeNode?) {
        if (node == null || count == 0) return

        inorder(node.left)

        count--
        if (count == 0) {
            result = node.`val`
            return
        }

        inorder(node.right)
    }

    inorder(root)
    return result
}
```

### 14. Binary Tree Right Side View
```kotlin
fun rightSideView(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    val queue = ArrayDeque<TreeNode>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val levelSize = queue.size

        repeat(levelSize) { i ->
            val node = queue.removeFirst()

            if (i == levelSize - 1) {
                result.add(node.`val`)
            }

            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }
    }

    return result
}
```

### 15. Zigzag Level Order
```kotlin
fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()

    val result = mutableListOf<List<Int>>()
    val queue = ArrayDeque<TreeNode>()
    queue.add(root)
    var leftToRight = true

    while (queue.isNotEmpty()) {
        val levelSize = queue.size
        val level = mutableListOf<Int>()

        repeat(levelSize) {
            val node = queue.removeFirst()
            level.add(node.`val`)

            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }

        if (!leftToRight) level.reverse()
        result.add(level)
        leftToRight = !leftToRight
    }

    return result
}
```

### 16. Construct Tree from Preorder and Inorder
```kotlin
fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty()) return null

    val rootVal = preorder[0]
    val root = TreeNode(rootVal)

    val rootIndex = inorder.indexOf(rootVal)

    root.left = buildTree(
        preorder.copyOfRange(1, rootIndex + 1),
        inorder.copyOfRange(0, rootIndex)
    )

    root.right = buildTree(
        preorder.copyOfRange(rootIndex + 1, preorder.size),
        inorder.copyOfRange(rootIndex + 1, inorder.size)
    )

    return root
}
```

### 17. Serialize and Deserialize Binary Tree
```kotlin
class Codec {
    fun serialize(root: TreeNode?): String {
        val result = mutableListOf<String>()

        fun preorder(node: TreeNode?) {
            if (node == null) {
                result.add("null")
                return
            }

            result.add(node.`val`.toString())
            preorder(node.left)
            preorder(node.right)
        }

        preorder(root)
        return result.joinToString(",")
    }

    fun deserialize(data: String): TreeNode? {
        val values = data.split(",").toMutableList()

        fun build(): TreeNode? {
            val value = values.removeAt(0)
            if (value == "null") return null

            val node = TreeNode(value.toInt())
            node.left = build()
            node.right = build()

            return node
        }

        return build()
    }
}
```

### 18. Flatten Binary Tree to Linked List
```kotlin
fun flatten(root: TreeNode?) {
    if (root == null) return

    flatten(root.left)
    flatten(root.right)

    val tempRight = root.right
    root.right = root.left
    root.left = null

    var curr = root
    while (curr?.right != null) {
        curr = curr.right
    }

    curr?.right = tempRight
}
```

### 19. Count Good Nodes
```kotlin
fun goodNodes(root: TreeNode?): Int {
    fun dfs(node: TreeNode?, maxSoFar: Int): Int {
        if (node == null) return 0

        val count = if (node.`val` >= maxSoFar) 1 else 0
        val newMax = maxOf(maxSoFar, node.`val`)

        return count + dfs(node.left, newMax) + dfs(node.right, newMax)
    }

    return dfs(root, Int.MIN_VALUE)
}
```

### 20. Binary Tree Maximum Path Sum
```kotlin
fun maxPathSum(root: TreeNode?): Int {
    var maxSum = Int.MIN_VALUE

    fun maxGain(node: TreeNode?): Int {
        if (node == null) return 0

        val leftGain = maxOf(maxGain(node.left), 0)
        val rightGain = maxOf(maxGain(node.right), 0)

        val pathSum = node.`val` + leftGain + rightGain
        maxSum = maxOf(maxSum, pathSum)

        return node.`val` + maxOf(leftGain, rightGain)
    }

    maxGain(root)
    return maxSum
}
```

## Tree Patterns Summary

| Pattern | When to Use | Example |
|---------|-------------|---------|
| **DFS Recursive** | Most tree problems | Max Depth, Path Sum |
| **BFS (Level Order)** | Level-based problems | Right Side View, Zigzag |
| **Inorder** | BST problems, sorted order | Validate BST, Kth Smallest |
| **Preorder** | Copy tree, serialization | Clone Tree, Serialize |
| **Postorder** | Bottom-up processing | Diameter, Delete Nodes |
| **Two Pointers** | Binary tree variants | LCA, Distance |

## Interview Tips

1. **Clarify tree type**: Binary tree vs BST vs Balanced
2. **Ask about values**: Duplicates? Negative numbers?
3. **Recursive first**: Usually cleaner for trees
4. **Handle null**: Always check for null nodes
5. **Use helper functions**: Pass additional parameters
6. **BFS for levels**: Use queue
7. **DFS for paths**: Use recursion or stack
8. **BST properties**: Left < Root < Right

## Common Mistakes

```kotlin
// ❌ Wrong - forgetting null check
fun depth(root: TreeNode): Int {
    return 1 + maxOf(depth(root.left), depth(root.right))
}

// ✅ Correct
fun depth(root: TreeNode?): Int {
    if (root == null) return 0
    return 1 + maxOf(depth(root.left), depth(root.right))
}

// ❌ Wrong - not handling leaf nodes
fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
    if (root == null) return false
    return hasPathSum(root.left, sum - root.`val`) ||
           hasPathSum(root.right, sum - root.`val`)
}

// ✅ Correct - check if leaf node
fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
    if (root == null) return false
    if (root.left == null && root.right == null) return root.`val` == sum
    return hasPathSum(root.left, sum - root.`val`) ||
           hasPathSum(root.right, sum - root.`val`)
}
```

## Time Complexities

- **Traversal**: O(n)
- **Search (BST)**: O(log n) average, O(n) worst
- **Insert (BST)**: O(log n) average, O(n) worst
- **Level Order**: O(n)
- **Path Problems**: O(n)
- **LCA**: O(n)

Remember: **Draw the tree and trace your recursion!**

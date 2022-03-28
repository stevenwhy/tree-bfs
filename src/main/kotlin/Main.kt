import java.util.*

fun main() {

    println("#1 Binary Tree Level Order Traversal -----------------")
    val root = TreeNode(12)
    root.left = TreeNode(7, left = TreeNode(9))
    root.right = TreeNode(1, left = TreeNode(10), right = TreeNode(5))
    println("Level order traversal ${levelOrderTraverse(root)}")
    println("Reverse level order traversal ${reverseLevelOrderTraverse(root)}")
}

fun reverseLevelOrderTraverse(root: TreeNode): Deque<List<Int>> {
    val result: Deque<List<Int>> = LinkedList()

    val queue: Queue<TreeNode> = LinkedList()
    queue.add(root)

    while(queue.isNotEmpty()) {
        val level = ArrayDeque<Int>()
        var i = 0
        val size = queue.size
        while(i < size) {
            val node = queue.remove()
            if(node.left != null) queue.add(node.left)
            if(node.right != null) queue.add(node.right)
            level.add(node.value)
            i++
        }
        result.addFirst(level.toList())
    }
    return result
}

fun levelOrderTraverse(root: TreeNode): MutableList<List<Int>> {
    val result = mutableListOf<List<Int>>()

    val queue: Queue<TreeNode> = LinkedList()
    queue.add(root)

    while(queue.isNotEmpty()) {
        val arr = ArrayDeque<Int>()
        val size = queue.size
        var i = 0
        while(i < size) {
            val node = queue.remove()
            if(node.left != null) queue.add(node.left)
            if(node.right != null) queue.add(node.right)
            arr.add(node.value)
            i++
        }
        result.add(arr.toList())
    }
    return result
}
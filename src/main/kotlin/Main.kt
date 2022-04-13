import java.util.*
import kotlin.math.max

fun main() {

    println("#1 Binary Tree Level Order Traversal -----------------")
    val root = TreeNode(1)
    root.left = TreeNode(2, left = TreeNode(4), right = TreeNode(5))
    root.right = TreeNode(3, left = TreeNode(6), right = TreeNode(7))
    println("Level order traversal ${levelOrderTraverse(root)}")
    println("Reverse level order traversal ${reverseLevelOrderTraverse(root)}")
    println("Zig Zag level order traversal ${zigZagTraverse(root)}")
    println("Average of level order traversal ${averageOfLevels(root)}")
    println("Minimum depth ${minimumDepth(root)}")
    println("Find level order successor ${levelOrderSuccessor(root, 3)}")
    println("Find level order successor ${levelOrderSuccessor(root, 4)}")
    println("Find level order successor ${levelOrderSuccessor(root, 12)}")

    println("Find largest number in level: ${findLargestValueInEachLevel(root)}")
}

fun findLargestValueInEachLevel(root: TreeNode) : MutableList<Int> {
    val result = mutableListOf<Int>()

    val queue: Queue<TreeNode> = LinkedList()
    queue.add(root)
    while(queue.isNotEmpty()) {
        val levelSize = queue.size
        var i = 0
        var max = -1
        while(i < levelSize) {
            val currentNode = queue.poll()
            if(currentNode.left != null) queue.add(currentNode.left)
            if(currentNode.right != null) queue.add(currentNode.right)
            max = max(max, currentNode.value)
            i++
        }
        result.add(max)
    }
    return result
}



fun levelOrderSuccessor(root: TreeNode, target: Int): Int? {
    val levels = mutableListOf<List<Int>>()

    val queue: Queue<TreeNode> = LinkedList()
    queue.add(root)

    while(queue.isNotEmpty()) {
        val level = ArrayDeque<Int>()
        val size = queue.size
        var i = 0
        while(i < size) {
            val node = queue.remove()
            if(node.value == target) {
                if(level.isNotEmpty()) return level.last
                else return levels.last().last()
            }
            level.add(node.value)
            if(node.left != null) queue.add(node.left)
            if(node.right != null) queue.add(node.right)
            i++
        }
        levels.add(level.toList())
    }
    return null
}
fun minimumDepth(root: TreeNode): Int {
    val queue: Queue<TreeNode> = LinkedList()
    queue.add(root)
    var depth = 0
    var endReached = false
    while(queue.isNotEmpty() && !endReached) {
        val size = queue.size
        var i = 0
        depth += 1
        while(i < size) {
            val node = queue.remove()
            if(node.left == null || node.right == null) {
                endReached = true
                break
            }

            if(node.left != null) queue.add(node.left)
            if(node.right != null) queue.add(node.right)
            i++
        }
    }
    return depth
}
fun averageOfLevels(root: TreeNode): MutableList<Double> {
    val result = mutableListOf<Double>()

    val queue: Queue<TreeNode> = LinkedList()
    queue.add(root)
    while(queue.isNotEmpty()) {
        var sum = 0.0
        val size = queue.size
        var i = 0
        while(i < size) {
            val node = queue.remove()
            if(node.left != null) queue.add(node.left)
            if(node.right != null) queue.add(node.right)
            sum += node.value
            i++
        }
        result.add(sum/size)
    }
    return result
}
fun zigZagTraverse(root: TreeNode): MutableList<List<Int>> {
    val result = mutableListOf<List<Int>>()

    val queue: Deque<TreeNode> = LinkedList()
    queue.add(root)
    var invert = false
    while(queue.isNotEmpty()) {
        val arr: ArrayDeque<Int> = ArrayDeque()
        val size = queue.size
        var i = 0
        while(i < size) {
            val node = queue.remove()
            if(invert) arr.addFirst(node.value)
            else arr.addLast(node.value)

            if(node.left != null) queue.add(node.left)
            if(node.right != null) queue.add(node.right)
            i++
        }
        invert = !invert
        result.add(arr.toList())
    }
    return result
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
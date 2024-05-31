/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class Codec() {
	// Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        if (root == null) {
            return ""
        }
        var sb: StringBuilder = StringBuilder()
        sb.append("#,").append(root.`val`)

        val q:Queue<TreeNode> = LinkedList()
        q.add(root)
        while (!q.isEmpty()) {
            val node = q.poll()
            if(node.left != null){
                sb.append(",").append(node.left!!.`val`)
                q.add(node.left)
            }else{
                sb.append(",#")
            }
            if(node.right != null){
                sb.append(",").append(node.right!!.`val`)
                q.add(node.right)
            }else{
                sb.append(",#")
            }
        }

        return sb.toString()
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        if (data == "") {
            return null
        }

        val nodes = data.split(",")

        val root:TreeNode = TreeNode(nodes[1].toInt())

        val q:Queue<TreeNode> = LinkedList()
        q.add(root)
        var index = 2
        while (!q.isEmpty()) {
            val node = q.poll()

            if (nodes[index] != "#") {
                node.left = TreeNode(nodes[index].toInt())
                q.add(node.left)
            }
            index++

            if (nodes[index] != "#") {
                node.right = TreeNode(nodes[index].toInt())
                q.add(node.right)
            }
            index++
        }
        return root
    }
}

/**
 * Your Codec object will be instantiated and called as such:
 * var ser = Codec()
 * var deser = Codec()
 * var data = ser.serialize(longUrl)
 * var ans = deser.deserialize(data)
 */
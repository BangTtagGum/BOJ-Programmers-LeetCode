class MyHashMap() {
    // 노드 클래스
    data class Node(var key: Int, var value: Int, var next: Node? = null)

    // 노드 배열 생성
    private val nodes = arrayOfNulls<Node?>(1000000)

    // 삽입
    fun put(key: Int, value: Int) {
        val index = key % nodes.size
        if (nodes[index] == null) {
            nodes[index] = Node(key, value)
            return
        }
        var node = nodes[index]
        while (node != null) {
            if (node.key == key) {
                node.value = value
                return
            }
            if (node.next == null)
                break
            node = node.next
        }
        node?.next = Node(key, value)
    }

    // 조회
    fun get(key: Int): Int {
        val index = key % nodes.size
        var node = nodes[index]
        while (node != null) {
            if (node.key == key) {
                return node.value
            }
            node = node.next
        }
        return -1
    }

    // 삭제
    fun remove(key: Int) {
        val index = key % nodes.size
        var node = nodes[index]
        var prev: Node? = null
        while (node != null) {
            if (node.key == key) {
                if (prev == null) {
                    nodes[index] = node.next
                } else {
                    prev.next = node.next
                }
                return
            }
            prev = node
            node = node.next
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * var obj = MyHashMap()
 * obj.put(key,value)
 * var param_2 = obj.get(key)
 * obj.remove(key)
 */
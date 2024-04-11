import java.util.*

class MyCircularDeque(k: Int) {
    data class DoublyLinkedList(var `val`: Int){
        var left : DoublyLinkedList? = null
        var right : DoublyLinkedList? = null
    }
    
    var len = 0
    var k = 0
    var head : DoublyLinkedList? = null
    var tail : DoublyLinkedList? = null
    
    init {
        head = DoublyLinkedList(0)
        tail = DoublyLinkedList(0)
        head!!.right = tail
        tail!!.left = head
        this.k = k
        len = 0
    }
    
    fun insertFront(value: Int): Boolean {
        if(isFull()) 
            return false
        val node = DoublyLinkedList(value)
        node.right = head!!.right
        node.left = head
        head!!.right!!.left = node
        head!!.right = node
        len++
        return true
    }

    fun insertLast(value: Int): Boolean {
        if(isFull())
            return false
        val node = DoublyLinkedList(value)
        node.right = tail!!.left!!.right
        node.left = tail!!.left
        tail!!.left!!.right = node
        tail!!.left = node
        len++
        return true
    }

    fun deleteFront(): Boolean {
        if(isEmpty())
            return false
        head!!.right!!.right!!.left = head
        head!!.right = head!!.right!!.right
        len--
        return true
    }

    fun deleteLast(): Boolean {
        if(isEmpty())
            return false
        tail!!.left!!.left!!.right = tail
        tail!!.left = tail!!.left!!.left
        len--
        return true
    }

    fun getFront(): Int {
        return if(isEmpty()) -1 else head!!.right!!.`val`
    }

    fun getRear(): Int {
        return if(isEmpty()) -1 else tail!!.left!!.`val`
    }

    fun isEmpty(): Boolean {
        return len == 0 
    }

    fun isFull(): Boolean {
        return len == k
    }

}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * var obj = MyCircularDeque(k)
 * var param_1 = obj.insertFront(value)
 * var param_2 = obj.insertLast(value)
 * var param_3 = obj.deleteFront()
 * var param_4 = obj.deleteLast()
 * var param_5 = obj.getFront()
 * var param_6 = obj.getRear()
 * var param_7 = obj.isEmpty()
 * var param_8 = obj.isFull()
 */
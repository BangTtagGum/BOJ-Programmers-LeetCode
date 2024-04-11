class MyCircularQueue(k: Int) {

    var len: Int = 0
    var front:Int = 0
    var rear : Int = -1
    var q : IntArray = IntArray(k)
    fun enQueue(value: Int): Boolean {
        return if(isFull()){
            false
        } else{
            rear = (rear+1) % q.size
            q[rear] = value
            len++
            true
        }
    }

    fun deQueue(): Boolean {
        return if(isEmpty()){
            false
        } else{
            front = (front+1) % q.size
            len--
            true
        }
    }

    fun Front(): Int {
        return if(isEmpty())
            -1
        else
            q[front]
    }

    fun Rear(): Int {
        return if(isEmpty())
            -1
        else
            q[rear]
    }

    fun isEmpty(): Boolean {
        return len == 0
    }

    fun isFull(): Boolean {
        return len == q.size
    }

}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * var obj = MyCircularQueue(k)
 * var param_1 = obj.enQueue(value)
 * var param_2 = obj.deQueue()
 * var param_3 = obj.Front()
 * var param_4 = obj.Rear()
 * var param_5 = obj.isEmpty()
 * var param_6 = obj.isFull()
 */
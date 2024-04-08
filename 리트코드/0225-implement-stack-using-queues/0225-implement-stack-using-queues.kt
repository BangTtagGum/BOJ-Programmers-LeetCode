class MyStack() {
    val q:Queue<Int> = LinkedList()
    
    fun push(x: Int) {
        q.add(x)

        for(i in 1 until q.size)
            q.add(q.remove())    
    }

    fun pop(): Int {
        return q.remove()
    }

    fun top(): Int {
        return q.peek()
    }

    fun empty(): Boolean {
        return q.isEmpty()
    }

}

/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */
class Solution {
    fun validUtf8(data: IntArray): Boolean {
        var start = 0
        fun check(size:Int): Boolean{
            for (i in start + 1 until start + size + 1) {
                if (i >= data.size || data[i] shr 6 != 0b10) {
                    return false
                }
            }
            return true
        }

        while (start < data.size) {
            var first = data[start]
            if(first shr 3 == 0b11110 && check(3)){
                start += 4
            }else if (first shr 4 == 0b1110 && check(2)) {
                start += 3
                
            }else if (first shr 5 == 0b110 && check(1)) {
                start += 2
            }else if (first shr 7 == 0) {
                start++
            } else {
                return false
            }
        }
        return true
    }
    
}
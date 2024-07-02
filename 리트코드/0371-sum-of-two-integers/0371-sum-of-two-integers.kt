class Solution {
    fun getSum(a: Int, b: Int): Int {
        
        val binA:String = String.format("%32s", Integer.toBinaryString(a)).replace(' ', '0');
        val binB:String = String.format("%32s", Integer.toBinaryString(b)).replace(' ', '0');

        var carry = 0
        var sum = 0

        val result: MutableList<Char> = mutableListOf()

        for(i in 0 until 32){
            val A = Character.getNumericValue(binA[31-i])
            val B = Character.getNumericValue(binB[31-i])

            val q1 = A and B
            val q2 = A xor B
            val q3 = q2 and carry
            sum = q2 xor carry
            carry = q1 or q3

            result.add(0, Character.forDigit(sum,2))

        }


        return Integer.parseUnsignedInt(String(result.toCharArray()), 2)
    }
}
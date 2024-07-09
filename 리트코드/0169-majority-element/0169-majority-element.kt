class Solution {
    fun majorityElement(nums: IntArray): Int {
        
        fun majorityElement(left:Int, right:Int, nums: IntArray): Int{
            if(left == right)
                return nums[left]
            
            val mid = left + (right - left) / 2
            val a = majorityElement(left,mid,nums)
            val b = majorityElement(mid + 1,right,nums)

            var countA = 0
            for(i in left..right){
                if(nums[i] == a){
                    countA++
                }
            }

            return if(countA > (right - left) / 2)
                a
            else
                b
        }

        return majorityElement(0,nums.size-1, nums)
    }
}
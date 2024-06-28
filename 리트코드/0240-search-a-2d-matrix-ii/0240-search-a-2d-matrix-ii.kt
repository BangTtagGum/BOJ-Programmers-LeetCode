class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var row = 0
        var column = matrix[0].size - 1
        while(row < matrix.size && column >= 0){
            when{
                matrix[row][column] < target -> row++
                matrix[row][column] > target -> column--
                else -> return true
            }
        }
        return false 
    }
}
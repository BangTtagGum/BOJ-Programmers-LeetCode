class Solution {
    public int search(int[] nums, int target) {
    
        return binarySearch(nums, target, 0, nums.length - 1);
    
    }

    public int binarySearch(int[] nums, int target, int left, int right){
        if(left <= right){
            int mid = (left + right)/2;
            if(nums[mid] > target){
                return binarySearch(nums,target, left ,mid - 1);
            }else if(nums[mid] < target){
                return binarySearch(nums,target, mid + 1,right);
            }else{
                return mid;
            }
        } else{
            return -1;
        }
    }
}
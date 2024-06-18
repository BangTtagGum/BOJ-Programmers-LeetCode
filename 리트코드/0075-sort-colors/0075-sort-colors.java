class Solution {
    public void sortColors(int[] nums) {
        int red = 0;
        int white = 0;
        int blue = nums.length;

        while(white < blue){
            if(nums[white] < 1){
                int tmp = nums[white];
                nums[white] = nums[red];
                nums[red] = tmp;
                red++;
                white++;
            }else if(nums[white] == 1){
                white++; 
            }else {
                blue--;
                int tmp = nums[white];
                nums[white] = nums[blue];
                nums[blue] = tmp;
            }
        }
    }
}
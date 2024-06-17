import java.math.BigInteger;

class Solution {
     public static boolean toSwap(int n1, int n2) {
        if ((String.valueOf(n1) + n2).compareTo((String.valueOf(n2) + n1)) < 0) {
            return true;
        }
        return false;
    }

    public static String largestNumber(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if (toSwap(nums[j - 1], nums[j])) {
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
            }
        }

        if (nums[0] == 0) {
            return "0";
        } else {
            return Arrays.toString(nums).replaceAll("\\[|\\]|,|\\s", "");
        }
    }
}
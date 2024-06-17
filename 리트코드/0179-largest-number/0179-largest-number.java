import java.math.BigInteger;

class Solution {
     public static boolean toSwap(int n1, int n2) {
        if ((String.valueOf(n1) + n2).compareTo((String.valueOf(n2) + n1)) < 0) {
            return true;
        }
        return false;
    }

    public static String largestNumber(int[] nums) {

        String[] s = new String[nums.length];
        
        for (int i = 0; i < nums.length; i++) s[i] = String.valueOf(nums[i]);
        Arrays.sort(s, (a,b) -> (b + a).compareTo(a + b));
        
        if (nums[0] == 0) {
            return "0";
        } else {
            return String.join("",s);
        }
    }
}
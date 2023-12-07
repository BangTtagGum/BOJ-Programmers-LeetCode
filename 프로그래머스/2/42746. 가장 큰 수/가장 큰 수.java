import java.util.*;

class Solution {

    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(arr, new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String num : arr) {
            sb.append(num);
        }
        
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] != 0){
                return sb.toString();
            }    
        }
        
        return "0";

        
    }

    
}
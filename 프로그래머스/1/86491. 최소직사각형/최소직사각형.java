class Solution {
    public int solution(int[][] sizes) {
        
        
        
        int maxW = 0;
        int maxH = 0;
        for(int[] size: sizes){
            maxW = Math.max(maxW,size[0]);
            maxW = Math.max(maxW,size[1]);    
        }
        for(int[] size: sizes){
            int min = Math.min(size[0],size[1]);
            maxH = Math.max(min,maxH);
        }
        
        
        
        return maxW * maxH;
    }
}
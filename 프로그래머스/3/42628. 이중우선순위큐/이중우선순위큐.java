import java.util.*;
class Solution {
    
    static class MinMaxPriorityQueue{
        Queue<Integer> minQueue = new PriorityQueue<>();
        Queue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        
        void operation(String instruct){
            String[] op = instruct.split(" ");
            if(op[0].equals("I")){
                insert(Integer.parseInt(op[1]));
                
            } else if(op[0].equals("D")){
                if(op[1].equals("1")){
                    pollLargest();
                } else if(op[1].equals("-1")){
                    pollSmallest();
                }
            }
        }
        
        void insert(int i){
            minQueue.add(i);
            maxQueue.add(i);
        }
        
        int pollLargest(){
            
            if(maxQueue.isEmpty()){
                return 0;
            }
            
            Integer val = maxQueue.poll();
            minQueue.remove(val);
            
            return val;
        }
        
        int pollSmallest(){
            
            if(minQueue.isEmpty()){
                return 0;
            }
            
            Integer val = minQueue.poll();
            maxQueue.remove(val);
            
            return val;
        }
        
        
    }
    
    
    public int[] solution(String[] operations) {
        MinMaxPriorityQueue mmpq = new MinMaxPriorityQueue();
        
        for(String operation : operations){
            mmpq.operation(operation);
        }
        
        return new int[]{mmpq.pollLargest(),mmpq.pollSmallest()};
    }
}
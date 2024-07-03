class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];

        for(int i = 0; i < nums.length; i++){
            // 데크 맨 앞이 윈도우 밖에 있다면 삭제
            if(!dq.isEmpty() && dq.peek() < i - k + 1){
                dq.poll();
            }
            
            // 현재 값보다 작으면 데크에서 모두 삭제, 데크에는 현재 값 보다 큰 인덱스만 남음
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }
            // 데크에 현재 인덱스 추가
            dq.add(i);
            // 데크의 첫번째 값은 가장 큰 값이므로 정답으로 추가
            if(i >= k - 1){
                result[i - k + 1] = nums[dq.peek()];
            }
        }
        return result;
    }
}
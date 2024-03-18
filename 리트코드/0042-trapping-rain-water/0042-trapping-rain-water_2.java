class Solution {

    /**
     * 스택을 이용한 풀이 [0,1,0,2,1,0,1,3,2,1,2,1]
     */
    public static int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int volume = 0;

        for (int i = 0; i < height.length; i++) {
            // 높이가 변하는 경우
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                Integer top = stack.pop();
                System.out.println(top);
                if (stack.isEmpty()) {
                    break;
                }

                int distance = i - stack.peek() - 1;

                int waters = Math.min(height[i], height[stack.peek()]) - height[top];

                volume += distance * waters;
            }

            stack.push(i);
        }
        return volume;
    }
}
class Solution {
    public static boolean isPalindrome(ListNode head) {

        Deque<Integer> dq = new ArrayDeque<>();

        while (head != null) {
            dq.add(head.val);
            head = head.next;
        }
        
        while (!dq.isEmpty() && dq.size() > 1) {
            if (dq.pollFirst() != dq.pollLast()) {
                return false;
            }            
        }
        return true;
    }
}
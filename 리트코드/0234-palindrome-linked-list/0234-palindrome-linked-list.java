class Solution {
    public static boolean isPalindrome(ListNode head) {

        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 노드의 개수가 홀수 일 경우 가운데를 비교 대상에서 제거
        if (fast != null) {
            slow = slow.next;
        }

        ListNode rev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = rev;
            rev = slow;
            slow = next;
        }

        while (rev != null) {
            if (rev.val != head.val) {
                return false;
            }
            rev = rev.next;
            head = head.next;
        }
        return true;
    }
}
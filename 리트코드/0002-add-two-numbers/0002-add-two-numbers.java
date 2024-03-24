import java.math.BigInteger;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reverseL1 = reverseNode(l1);
        ListNode reverseL2 = reverseNode(l2);

        BigInteger answer = toBigInteger(reverseL1).add(toBigInteger(reverseL2));

        return toReverseLinkedList(answer);
    }

    public ListNode toReverseLinkedList(BigInteger val){
        ListNode prev = null, node = null;

        for(char c : String.valueOf(val).toCharArray()){
            node = new ListNode(Character.getNumericValue(c));

            node.next = prev;
            prev = node;
        }
        return node;
    }

    public BigInteger toBigInteger(ListNode node){
        StringBuilder sb = new StringBuilder();
        while(node != null){
            sb.append(node.val);
            node = node.next;
        }
        return new BigInteger(sb.toString());
    }

    public ListNode reverseNode(ListNode node){
        ListNode prev = null;
        while(node != null){
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        
        return prev;
    }
}
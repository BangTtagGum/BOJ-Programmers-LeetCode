class Solution {
    public int getSum(int a, int b) {
        String binA = String.format("%32s", Integer.toBinaryString(a)).replace(' ','0');
        String binB = String.format("%32s", Integer.toBinaryString(b)).replace(' ','0');
        
        List<Character> result = new ArrayList<>();

        int carry = 0;

        int sum;

        for (int i = 0; i < 32; i++) {
            // int A = Character.getNumericValue(binA.charAt(31-i));
            // int B = Character.getNumericValue(binB.charAt(31-i));
            int A = binA.charAt(31-i) - '0';
            int B = binB.charAt(31-i) - '0';
            int q1 = A & B;
            int q2 = A ^ B;
            int q3 = q2 & carry;
            sum = carry ^ q2;
            carry = q1 | q3;

            result.add(0,Character.forDigit(sum,2));
        }

        return Integer.parseUnsignedInt(
            result.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(""))
        ,2);
    }
}
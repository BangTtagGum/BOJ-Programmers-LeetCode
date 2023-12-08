class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {

            sb.append(Integer.toString(i).repeat(food[i] / 2));
        }
        String answer = sb + "0";
        sb.reverse();
        answer += sb;
        System.out.println("answer = " + answer);

        return answer;
    }
}
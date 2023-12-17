class Solution {
    public int[] solution(int[] numbers) {
        boolean save[] = new boolean[201]; // 합으로 나올 수 있는지 저장
        int cnt = 0; // 중복되지 않는 합의 수
    
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                if (!save[numbers[i] + numbers[j]]) { 
                    save[numbers[i] + numbers[j]] = true;
                    cnt++;
                }
            }
        }
        
        int[] answer = new int[cnt];
        int idx = 0;
        for (int i = 0; i < 201; i++) {
            if (save[i]) {
                answer[idx++] = i;
            }
        }

        return answer;
    }
}
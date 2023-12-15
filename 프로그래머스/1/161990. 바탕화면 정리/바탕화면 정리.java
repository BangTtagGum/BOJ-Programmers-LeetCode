class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        int left = 100, right = 0, up = 100, down = 0;

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    left = Math.min(left, j);
                    right = Math.max(right, j + 1);
                    up = Math.min(up, i);
                    down = Math.max(down, i+1);
                }
            }
        }
        answer[0] = up;
        answer[1] = left;
        answer[2] = down;
        answer[3] = right;

        return answer;
    }
}
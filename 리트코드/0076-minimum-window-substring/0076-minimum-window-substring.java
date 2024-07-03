import java.util.StringTokenizer;

class Solution {
    public static String minWindow(String s, String t) {
        // 포함해야할 문자수를 담은 맵
        Map<Character, Integer> need = new HashMap<>();

        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int start = 0, end = 0, left = 0, right = 0;
        // String 배열에서의 인덱스
        int leftIndex = 0, rightIndex = 0;
        int minLen = Integer.MAX_VALUE;
        int missing = t.length();

        // t의 문자들을 기준으로 s를 쪼갬
        StringTokenizer leftST = new StringTokenizer(s, t, true);
        StringTokenizer rightST = new StringTokenizer(s, t, true);

        while (rightST.hasMoreTokens()) {
            String token = rightST.nextToken();
            rightIndex++;

            // 덩어리 인 경우
            if (token.length() != 1) {
                right += token.length();
                continue;
            }

            char c = token.charAt(0);
            right++;
            // t에 포함되지 않는 알파벳일 경우
            if (!need.containsKey(c)) {
                continue;
            }
            // 알파벳을 찾은 경우
            if (need.get(c) > 0) {
                missing--;
            }
            need.put(c, need.get(c) - 1);

            // 다 찾았을 경우 왼쪽을 최대한 줄여 최소 길이로 만들기
            if (missing == 0) {
                String leftToken = null;
                while (leftIndex < rightIndex) {
                    leftToken = leftST.nextToken();
                    // 덩어리는 스킵
                    if (leftToken.length() != 1) {
                        left += leftToken.length();
                        leftIndex++;
                        continue;
                    }
                    // 찾으려는 숫자가 아닌 경우도 스킵
                    char tmp = leftToken.charAt(0);
                    if (!need.containsKey(tmp)) {
                        left++;
                        leftIndex++;
                        continue;
                    }
                    // 찾는 숫자긴 한데, 더 많이 찾아서 줄여도 되는 경우
                    if (need.get(tmp) < 0) {
                        need.put(tmp, need.get(tmp) + 1);
                        left++;
                        leftIndex++;
                        continue;
                    }
                    break;
                }

                if (minLen > right - left) {
                    minLen = right - left;
                    start = left;
                    end = right;
                }
                char tmp = leftToken.charAt(0);
                need.put(tmp, need.get(tmp) + 1);
                left++;
                leftIndex++;
                missing++;
            }
        }

        return s.substring(start, end);
    }

}
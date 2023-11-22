

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 2023.11.09 문자열 폭발
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        char[] charArray = s.toCharArray();

        String bomb = br.readLine();
        char[] bombArray = bomb.toCharArray();

        Stack<Character> stack = new Stack<>();
        Stack<Character> storage = new Stack<>();

        int bombLength = bomb.length();
        for (int i = 0; i < s.length(); i++) {
//            폭탄의 마지막 문자와 다음 문자가 일치하면 이전에 문자들을 탐색
            if (charArray[i] == bombArray[bombLength-1]) {
                storage.push(charArray[i]);
                for (int j = bombLength - 2; j >= 0; j--) {
                    if (stack.isEmpty()) {
                        break;
                    }
                    Character c = stack.pop();
                    if (c == bombArray[j]) {
                        storage.push(c);
                    } else {
                        stack.push(c);
                        break;
                    }
                }
                //폭탄이 터질 경우
                if (storage.size() == bombLength) {
                    storage.clear();
                } else {    //폭탄이 터지지 않으면 뺐던 그대로 다시 스택에 넣기
                    while (!storage.isEmpty()) {
                        stack.push(storage.pop());
                    }
                }
            } else {
                stack.push(charArray[i]);
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse());
        }
    }
}

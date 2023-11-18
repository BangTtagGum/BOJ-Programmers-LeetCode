import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static int h, w, r, h0, w0, h1, w1;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        h = sc.nextInt();
        w = sc.nextInt();
        r = sc.nextInt();
        h0 = sc.nextInt();
        w0 = sc.nextInt();
        h1 = sc.nextInt();
        w1 = sc.nextInt();
        n = sc.nextInt();
        ArrayList<String> inputList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            String input = pushButton(a, b);
            if (input.equals("")) {
                continue;
            }
            if (input.equals("x")) {
                int len = sb.length();
                if (len == 0) {
                    continue;
                }
                sb.deleteCharAt(len - 1);
            } else if (input.equals("push")) {
                if (sb.length() == 0) {
                    continue;
                }
                inputList.add(sb.toString());
                sb.delete(0, sb.length());
            } else {
                sb.append(input);
            }
        }
        int size = inputList.size();
        System.out.println(size);
        for (String s : inputList) {
            System.out.println(s);
        }
        sc.close();
    }

    public static String pushButton(int a, int b) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                h = h0 + r + (h1 + 2 * r) * j;
                w = w0 + r + (w1 + 2 * r) * i;
                if ((h - a) * (h - a) + (w - b) * (w - b) <= r * r) {
                    if (j == 3) {
                        if (i == 0) {
                            return "x";
                        } else if (i == 1) {
                            return "0";
                        } else {
                            return "push";
                        }
                    }
                    int value = j * 3 + i + 1;
                    String num = String.valueOf(value);
                    return num;
                }
            }
        }
        return "";
    }
}
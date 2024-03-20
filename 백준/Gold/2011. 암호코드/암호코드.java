import java.io.*;
import java.util.*;
class Main{
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input = br.readLine();

            // close 뒤에 더이상 숫자를 붙히지 못하는 경우
            // open 뒤에 숫자가 붙히거나 붙히지 않을 수 있는 경우
            int[] close = new int[input.length()];
            int[] open = new int[input.length()];

            char[] charArray = input.toCharArray();

            if (charArray[0] == '1' || charArray[0] == '2') {
                open[0] = 1;
            } else if (charArray[0] == '0') {
                close[0] = 0;
            } else {
                close[0] = 1;
            }

            for (int i = 1; i < charArray.length; i++) {
                if (charArray[i] == '1') {
                    close[i] = open[i - 1] % 1000000;
                    open[i] = (close[i - 1] + open[i - 1]) % 1000000;
                } else if (charArray[i] == '2') {
                    close[i] = open[i - 1] % 1000000;
                    open[i] = (close[i - 1] + open[i - 1]) % 1000000;
                } else if (charArray[i] == '0') {
                    if (charArray[i - 1] == '0') {
                        continue;
                    }
                    close[i] = open[i - 1] % 1000000;
                } else if (3 <= charArray[i] - 48 && charArray[i] - 48 <= 6) {
                    close[i] = (close[i - 1] + open[i - 1] * 2) % 1000000;
                } else {
                    if (charArray[i - 1] == '1') {
                        close[i] = (close[i - 1] + open[i - 1] * 2) % 1000000;
                    } else if (charArray[i - 1] == '2') {
                        close[i] = (close[i - 1] + open[i - 1]) % 1000000;
                    } else {
                        close[i] = close[i - 1];
                    }
                }
            }

            int answer = (open[charArray.length - 1] + close[charArray.length - 1]) % 1000000;
            System.out.println(answer);
    }
}
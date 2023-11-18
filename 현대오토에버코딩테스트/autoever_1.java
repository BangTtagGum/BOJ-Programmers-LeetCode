import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 1);
        for (int i = 1; i <= 11; i++) {
            map.put(i + 1, 2 * i);
        }
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }
        Collections.sort(arr);
        int maxValue = 0;
        int sum = 0;
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                sum = arr.get(i) + arr.get(j);
                if (map.get(maxValue) > sum) {
                    System.out.println(maxValue);
                    return;
                }
                int value = getValue(sum);
                if (maxValue < value) {
                    maxValue = value;
                }
            }
        }
        System.out.println(maxValue);
        return;
    }

    public static int getValue(int sum) {
        int value = 0;
        while (sum > 0) {
            value += sum % 2;
            sum /= 2;
        }
        return value;
    }
}
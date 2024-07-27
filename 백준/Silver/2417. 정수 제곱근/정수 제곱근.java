import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long left = 0;
        long right = n;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (Math.pow(mid, 2) < n) {
                left = mid + 1;
            } else if (Math.pow(mid, 2) > n){
                if(Math.pow(mid - 1, 2) < n){
                    System.out.println(mid);
                    break;
                }
                right = mid - 1;
            } else{
                System.out.println(mid);
                break;
            }
        }
    }
}
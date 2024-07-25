import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
class Main {

    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            // 연종이가 외운 수
            int[] note_1 = new int[n];
            for (int i = 0; i < n; i++) {
                note_1[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(note_1);

            //질문
            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                bw.write(isRemember(Integer.parseInt(st.nextToken()), note_1) + "\n");
            }

            bw.flush();
        }
    }

    private static char isRemember(int target, int[] note_1) {
        int left = 0;
        int right = note_1.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(target == note_1[mid]){
                return '1';
            } else if(target < note_1[mid]){
                right = mid - 1;
            } else if(target > note_1[mid]){
                left = mid + 1;
            }
        }
        return '0';
    }
}
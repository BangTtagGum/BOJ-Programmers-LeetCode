import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int junsung = Integer.parseInt(st.nextToken());
        int ikjun = Integer.parseInt(st.nextToken());

        if (junsung == 5) {
            if (ikjun == 5) {
                System.out.println('=');
                
            } else if (ikjun == 2) {
                System.out.println('<');
            } else if (ikjun == 0) {
                System.out.println('>');
            } else {
                System.out.println('>');
            }
        } else if(junsung == 2) {
            if (ikjun == 5) {
                System.out.println('>');

            } else if (ikjun == 2) {
                System.out.println('=');
            } else if (ikjun == 0) {
                System.out.println('<');
            } else {
                System.out.println('>');
            }
        } else if (junsung == 0) {
            if (ikjun == 5) {
                System.out.println('<');

            } else if (ikjun == 2) {
                System.out.println('>');
            } else if (ikjun == 0) {
                System.out.println('=');
            } else {
                System.out.println('>');
            }
        } else {
            if (ikjun == 5) {
                System.out.println('<');

            } else if (ikjun == 2) {
                System.out.println('<');
            } else if (ikjun == 0) {
                System.out.println('<');
            } else {
                System.out.println('=');
            }
        }
    }
}
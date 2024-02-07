import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader bf;
	private static StringTokenizer st;
	
	private static long X, Y, percent, result=-1;
	
	public static void main(String[] args) throws IOException {

		bf=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(bf.readLine());
		X=Long.parseLong(st.nextToken());
		Y=Long.parseLong(st.nextToken());
		
		percent=getPercent(X, Y);
		long start=0;
		long end=(long)1e9;
		while(start<=end) {
			long mid=(start+end)/2;
			long gP=getPercent(X+mid, Y+mid);
			if(gP!=percent) {	
				result=mid;
				end=mid-1;
			} else start=mid+1;	
		}
		System.out.println(result);
	}
	
	public static long getPercent(long x, long y) {	
		return (long)(100*y/x);
	}
}
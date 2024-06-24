
import java.io.*;
import java.util.*;

public class Main {
    static int n,m,k,s,d;
    static int[][] mindist;
    static List<Pair>[] adjList;

    static void init() throws IOException{
        n=rstn(); m=rstn(); k=rstn(); s=rstn(); d=rstn();
        adjList = new ArrayList[n+1];
        mindist = new int[n+1][n+1];
        for(int i=0; i<=n; ++i) Arrays.fill(mindist[i], 0x3f3f3f3f);
        for(int i=1; i<=n; ++i) adjList[i] = new ArrayList<>();
        for(int i=0; i<m; ++i){
            int v1=rstn(),v2=rstn(),w=rstn();
            adjList[v1].add(new Pair(v2,w));
            adjList[v2].add(new Pair(v1,w));
        }
    }

    static void dijk(){
        PriorityQueue<Triple> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.y));
        pq.add(new Triple(s,0,0));
        mindist[s][0] = 0;

        while(!pq.isEmpty()){
            Triple cur = pq.poll();
            if(mindist[cur.x][cur.z] < cur.y) continue;
            if(cur.z == n) continue;
            for(Pair nxt : adjList[cur.x]){
                int nd = cur.y+nxt.y;
                if(mindist[nxt.x][cur.z+1] > nd){
                    pq.add(new Triple(nxt.x,nd,cur.z+1));
                    mindist[nxt.x][cur.z+1] = nd;
                }
            }
        }
    
    }
    

    
    public static void main (String[] args) throws IOException{
        init();
        dijk();
        //초기 최소비용
        bw.append(Integer.toString(Arrays.stream(mindist[d]).min().getAsInt())).append("\n");
        while(k-->0){
            int up = rn();
            int min = Integer.MAX_VALUE;
            for(int i=1; i<=n; ++i){
                mindist[d][i] += i*up;
                min = Math.min(min,mindist[d][i]);
            }
            bw.append(Integer.toString(min)).append("\n");
        }
        bw.flush();
    }
    
    
    ///////////////////////////////////////////////////////////

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int rn() throws IOException {return Integer.parseInt(br.readLine());}
	static void est() throws IOException {st = new StringTokenizer(br.readLine());}
	static int rstn() throws IOException {if(st==null||!st.hasMoreTokens()) est(); return Integer.parseInt(st.nextToken());}
	static int[] ra() throws IOException {return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();}
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static boolean chk(int x, int y, int n, int m){return x<0 || y<0 || x>=n || y>=m;}
	static class Pair{int x; int y;public Pair(int x, int y) {this.x = x;this.y = y;}}
	static class Triple{ int x,y,z;public Triple(int x, int y,int z) {this.x = x;this.y = y;this.z = z;}}
	static class Quad{ int w,x,y,z;public Quad(int w, int x, int y,int z) {this.w = w; this.x = x;this.y = y;this.z = z;}}
    static void inputinit()throws IOException{System.setIn(new FileInputStream("input.txt"));br = new BufferedReader(new InputStreamReader(System.in));}
}
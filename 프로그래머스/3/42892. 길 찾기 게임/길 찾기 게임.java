import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    static class Node{
        int x;
        int y;
        int num;
        
        int left = 0;
        int right = 0;
        
        Node(int x,int y,int num){
            this.x  = x;
            this.y = y;
            this.num = num;
        }
        Node(int[] node){
            this.x = node[0];
            this.y = node[1];
            this.num = node[2];
        }
        
    }
    
    int[] xPoints;
    Node[] nodes;
    
    public int[][] solution(int[][] nodeinfo) {
        
        Queue<int[]> pq = new PriorityQueue<>((a,b) ->{
            // 낮은 level을 우선순위로
            if(a[1]  == b[1]){
                // 낮은 x를 우선순위로 
                return a[0]- b[0];
            }
            return b[1] - a[1];
        });
        
        for(int i =0 ;i < nodeinfo.length; i++){
            pq.add(new int[]{nodeinfo[i][0],nodeinfo[i][1],i+1});
        }  
        
        
        nodes = new Node[nodeinfo.length+1];
        xPoints = new int[100001];
        
        int[] node = pq.poll();
        
        nodes[node[2]] = new Node(node);
        xPoints[node[0]] = node[2];
        
        Node root = nodes[node[2]];
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            nodes[cur[2]] = new Node(cur);
            xPoints[cur[0]] = cur[2];
            
            LinkParent(cur[0],cur[2]);
        }
        
        ArrayList<Integer> order = new ArrayList<>();
        
        int[] pre = getPreorder(root);
        int[] post = getPostorder(root);
        
        int[][] answer = new int[2][nodeinfo.length+1];
        answer[0] = pre;
        answer[1] = post;
        
        return answer;
    }
    
    private int[] getPreorder(Node root){
        ArrayList<Integer> pre = new ArrayList<>();
        
        preorder(root,pre);
        
        return pre.stream().mapToInt(Integer::valueOf).toArray();
    }
    
    private void preorder(Node node, ArrayList<Integer> pre){
        
        pre.add(node.num);
        if(node.left != 0){
            preorder(nodes[node.left],pre);
        }
        if(node.right != 0){
            preorder(nodes[node.right],pre);
        }
    }
    
    private int[] getPostorder(Node root){
        ArrayList<Integer> post = new ArrayList<>();
        
        postorder(root,post);
        
        return post.stream().mapToInt(Integer::valueOf).toArray();
    }
    
    private void postorder(Node node, ArrayList<Integer> post){
        
        if(node.left != 0){
            postorder(nodes[node.left],post);
        }
        if(node.right != 0){
            postorder(nodes[node.right],post);
        }
        post.add(node.num);
    }
    
    
    private void LinkParent(int x,int num){
        //오른쪽 탐색
        int cur = x + 1;
        while(cur <= 100000){
            // 이미 x 좌표에 노드가 존재할 때
            if(xPoints[cur] != 0){
                // 자식노드가 존재하지 않는다면 자식노드로 지정
                if(nodes[xPoints[cur]].left == 0){
                    nodes[xPoints[cur]].left = num;
                    return;
                }
                //이미 자식 노드가 존재한다면 반대쪽 탐색
                break;
            }
            cur++;
        }
        
        //왼쪽 탐색
        cur = x - 1;
        while(cur >= 0){
            // 해당 x좌표에 이미 노드가 있는경우
            if(xPoints[cur] != 0){
                // 자식노드가 존재하지 않는다면 자식노드로 지정
                if(nodes[xPoints[cur]].right == 0){
                    nodes[xPoints[cur]].right = num;
                    return;
                }
                //이미 자식 노드가 존재한다면 반대쪽 탐색
                break;
            }
            cur--;
        }
    }
}
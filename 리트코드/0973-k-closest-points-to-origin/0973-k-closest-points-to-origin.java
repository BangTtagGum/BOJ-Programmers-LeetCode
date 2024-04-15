class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>(
            (p1,p2) -> {
                int p1Distance = p1.x* p1.x + p1.y * p1.y;
                int p2Distance = p2.x* p2.x + p2.y * p2.y;
                return p1Distance - p2Distance;
            }
        );

        for(int[] point : points){
            pq.add(new Point(point[0],point[1]));
        }

        int[][] result = new int[k][2];
        for(int i = 0; i < k; i++){
            Point point = pq.poll();
            result[i][0] = point.x;
            result[i][1] = point.y;
        }

        return result;
    }

    class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
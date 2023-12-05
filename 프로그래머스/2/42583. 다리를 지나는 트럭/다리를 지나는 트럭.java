import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int time = 0;

        Queue<Truck> q = new LinkedList<>();

        int w = 0;
        for (int i = 0; i < truck_weights.length; i++) {

            while (true) {

                // 다리가 비어있는 경우
                if (q.isEmpty()) {
                    if (i == truck_weights.length - 1) {
                        time += bridge_length + 1;
                        break;
                    }
                    q.add(new Truck(truck_weights[i]));
                    w += truck_weights[i];
                    time++;
                    break;

                // 다리가 가득 차지 않은 경우
                } else if (q.size() < bridge_length) {

                    // 트럭이 올라갈 수 있을 경우
                    if (w + truck_weights[i] <= weight) {
                        if (i == truck_weights.length - 1) {
                            time += bridge_length + 1;
                            break;
                        }
                        q.add(new Truck(truck_weights[i]));
                        w += truck_weights[i];
                        time++;
                        break;

                    // 트럭이 올라갈 수 없을 경우
                    } else {
                        q.add(new GhostTruck());
                        time++;
                    }

                // 다리가 가득 찬 경우
                } else {
                    Truck out = q.poll();
                    w -= out.weight;
                }
            }
        }

        return time;
    }
    class Truck {

        int weight;

        public Truck(int weight) {
            this.weight = weight;
        }

        public Truck() {
        }
    }

    class GhostTruck extends Truck {

        public GhostTruck() {
            this.weight = 0;
        }
    }
}
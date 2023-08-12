package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 2023.07.28
 * 11279 최대 힙
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        //힙 생성
        ArrayList<Integer> heap = new ArrayList<>();

//         힙의 인덱스가 1 부터 시작할 수 있도록 빈 공간 삽입
        heap.add(0);

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int ins = Integer.parseInt(br.readLine());
            if (ins == 0) { // 삭제연산
                if (heap.size() == 1) { // heap 이 비어있는 경우
                    sb.append(0).append("\n");
                } else { // Root 노드 반환 후 삭제 및 힙 정렬
                    sb.append(heap.get(1)).append("\n");
                    heap.remove(1);
                    heap.add(1, heap.get(heap.size() - 1));
                    heap.remove(heap.size()-1);

                    // 힙 정렬
                    int idx = 1;
                    while (idx * 2 < heap.size()) {
                        int childIdx; // 부모와 비교할 자식 인덱스
                        if (idx * 2 + 1 >= heap.size()) { // 오른쪽 자식 노드가 없을 경우
                            childIdx = idx * 2;
                            if (heap.get(idx) < heap.get(childIdx)) {    // 자식노드가 부모노드보다 더 클 경우 교환
                                int temp = heap.get(idx);
                                heap.set(idx, heap.get(childIdx));
                                heap.set(childIdx, temp);
                            }
                        } else {
                            if (heap.get(idx * 2) > heap.get(idx * 2 + 1)) { // 왼쪽 자식노드가 더 클경우 왼쪽과 비교
                                childIdx = idx * 2;
                            } else {                                         // 오른쪽 자식노드가 더 클 경우 오른쪽과 비교
                                childIdx = idx * 2 + 1;
                            }
                            if (heap.get(idx) < heap.get(childIdx)) {    // 자식노드가 부모노드보다 더 클 경우 교환
                                int temp = heap.get(idx);
                                heap.set(idx, heap.get(childIdx));
                                heap.set(childIdx, temp);
                            }
                        }
                        idx = childIdx;
                    }
                }
            } else {        // 삽입연산
                // 힙 트리의 맨 마지막에 삽입
                heap.add(ins);
                // 부모노드와 크기 비교 및 교환 반복
                int idx = heap.size() - 1;
                while (idx / 2 != 0) {
                    if (heap.get(idx) > heap.get(idx / 2)) {    // 부모노드보다 클 경우
                        // 부모노드와 자식노드의 위치 교환
                        int temp = heap.get(idx);
                        heap.set(idx, heap.get(idx / 2));
                        heap.set(idx / 2, temp);
                        idx /= 2;
                    } else {
                        break;
                    }
                }
            }
        }
        System.out.println(sb);
    }
}


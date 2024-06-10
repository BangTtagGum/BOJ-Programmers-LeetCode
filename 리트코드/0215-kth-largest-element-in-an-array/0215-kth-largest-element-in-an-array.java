class Solution {
    static class BinaryHeap {
        List<Integer> elems;

        public BinaryHeap(){
            elems = new ArrayList<>();
            elems.add(null);
        }

        void insert(int k){
            elems.add(k);
            perlocateUp();
        }

        void perlocateUp(){
            int idx = elems.size() - 1;
            int parentIdx = idx / 2;

            while(parentIdx > 0){
                if(elems.get(idx) > elems.get(parentIdx)){
                    swap(idx, parentIdx);
                }
                idx = parentIdx;
                parentIdx = idx / 2;
            }
        }

        void swap(int i, int j){
            int tmp = elems.get(i);
            elems.set(i, elems.get(j));
            elems.set(j, tmp);
        }

        void maxHeapify(int k){
            int left = k * 2;
            int right = k * 2 + 1;
            int largest = k;

            if(left <= elems.size() - 1 && elems.get(left) > elems.get(largest)){
                largest = left;
            }
            if(right <= elems.size() - 1 && elems.get(right) > elems.get(largest)){
                largest = right;
            }
            if(largest != k){
                swap(largest, k);
                maxHeapify(largest);
            }
        }

        int extract(){
            int extracted = elems.get(1);

            elems.set(1, elems.get(elems.size() - 1));
            elems.remove(elems.size() - 1);

            maxHeapify(1);

            return extracted;

        }
    }

    public int findKthLargest(int[] nums, int k) {
        BinaryHeap bh = new BinaryHeap();

        for(int i : nums){
            bh.insert(i);
        }

        while(k-- > 1){
            bh.extract();
        }

        return bh.extract();
    }
}
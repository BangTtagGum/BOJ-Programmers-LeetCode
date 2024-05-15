class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new LinkedList<>(), n, 1, k);
        return result;
    }

    public void dfs(List<List<Integer>> result, List<Integer> elements, int n, int start, int k) {
        if (k == 0) {
            result.add(elements.stream().collect(Collectors.toList()));
            return;
        }
        for (int i = start; i <= n; i++) {
            elements.add(i);
            dfs(result, elements, n, i + 1, k - 1);
            elements.removeLast();
        }
    }

    /**
     * 
     * 순열에서는 elements 리스트를 ArrayList로 선언했는데 조합에서는 ArrayList로 똑같이 하면 Exception이 발생하는 이유
     * -> ArrayList.remove()는 
     *              remove(int index) 와
     *              remove(Object o) 두가지로 오버로딩 되어있는데,
     *              int를 전달하면 remove(int index)가 실행되어 해당 인덱스를 삭제하고,
     *              Integer를 전달하면 remove(Object o)가 실행되어 해당 객체의 값과 일치하는 요소를 삭제하게 된다.
     *              
     *    LinkedList가 아니라 ArrayList로 구현하고 싶다면, elements.remove(Integer.valueOf(i)); 로 
     *    int를 래퍼클래스인 Integer로 변환하여 전달한다면 가능하긴 하다. 
     * 
     * public void dfs(List<List<Integer>> result, List<Integer> prevElements,
     *         List<Integer> elements) {
     *     if (elements.isEmpty()) {
     *         result.add(new ArrayList<>(prevElements));
     *     }
     *     for (Integer e : elements) {
     *         List<Integer> nextElements = new ArrayList<>(elements);
     *         nextElements.remove(e);
     *         
     *         prevElements.add(e);
     *         dfs(result, prevElements, nextElements);
     *         prevElements.remove(e);
     *     }
     * }
     * 
     */
}
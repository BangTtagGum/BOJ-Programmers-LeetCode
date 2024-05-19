class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> map = new HashMap<>();

        for(int pre[] : prerequisites){
            map.putIfAbsent(pre[0], new ArrayList<>());
            map.get(pre[0]).add(pre[1]);
        }

        List<Integer> takes = new ArrayList<>();
        List<Integer> taken = new ArrayList<>();
        for (Integer i : map.keySet()) {
            if (!dfs(map, i, takes, taken)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(Map<Integer,List<Integer>> map,Integer finish, List<Integer> takes, List<Integer> taken){
        if (takes.contains(finish)){
            return false;
        }

        if(taken.contains(finish)){
            return true;
        }

        if(map.containsKey(finish)){
            takes.add(finish);
            for(Integer take : map.get(finish)){
                if(!dfs(map,take,takes,taken)){
                    return false;
                }
            }
            takes.remove(finish);
            taken.add(finish);
        }
        return true;
    }
}
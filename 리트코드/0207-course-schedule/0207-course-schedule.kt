class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val finishToTakeMap: MutableMap<Int, MutableList<Int>> = mutableMapOf()
        for (pre in prerequisites){
            finishToTakeMap.putIfAbsent(pre[0], mutableListOf())
            finishToTakeMap[pre[0]]!!.add(pre[1])
        }

        val takes: MutableList<Int> = mutableListOf()
        val taken: MutableList<Int> = mutableListOf()
        fun dfs(finish: Int, takes: MutableList<Int>, taken: MutableList<Int>) : Boolean {
            if (takes.contains(finish)) {
                return false
            }
            if (taken.contains(finish)) {
                return true
            }
            
            if(finishToTakeMap.containsKey(finish)){
                takes.add(finish)
                for (take in finishToTakeMap[finish]!!) {
                    if(!dfs(take,takes,taken)){
                        return false
                    }
                }
                taken.add(finish)
                takes.remove(finish)
            }

            return true
        }

        for (finish in finishToTakeMap.keys) {
            if (!dfs(finish, takes, taken)) {
                return false
            }
        }
        return true
    }
}
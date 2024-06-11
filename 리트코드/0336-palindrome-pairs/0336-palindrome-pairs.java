class Solution {
    static class TrieNode{
        int wordId;
        TrieNode[] children;
        List<Integer> palindromeWordIds;

        public TrieNode(){
            wordId = -1;
            children = new TrieNode[26];
            palindromeWordIds = new ArrayList<>();
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public boolean isPalindrome(String str,int start, int end){
            while (start < end) {
                if (str.charAt(start++) != str.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }

        public void insert(int index, String word) {
            TrieNode cur = root ;
            // 단어를 뒤집어서 트라이로 저장
            for (int i = word.length() - 1; i >= 0; i--) {
                //단어에서 해당 위치의 문자 추출
                char c = word.charAt(i);
                // 정방향으로 해당 위치까지 팰린드롬인 경우 단어의 인덱스 저장
                if (isPalindrome(word, 0, i)) {
                    cur.palindromeWordIds.add(index);
                }
                // 해당 문자의 자식 노드가 존재하지 않으면 신규 트라이 노드 생성
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            // 단어가 완성 된 후 단어의 인덱스 저장
            cur.wordId = index;
        }

        public List<List<Integer>> search(int index, String word) {
            TrieNode cur = root;
            List<List<Integer>> result = new ArrayList<>();

            for (int j = 0; j < word.length(); j++) {
                //(3) 탐색 중에 단어 ID 가 있고, 나머지 문자가 팰린드롬인 경우
                if (cur.wordId >= 0 && isPalindrome(word, j, word.length() - 1)) {
                    result.add(Arrays.asList(index, cur.wordId));
                }
                // 자식노드가 없으면 더 이상 팰린드롬이 아니므로 지금까지 결과를 리턴하면서 종료
                if (cur.children[word.charAt(j) - 'a'] == null) {
                    return result;
                }
                cur = cur.children[word.charAt(j) - 'a'];
            }
            // (1) 끝까지 탐색 했을 때 단어 ID 가 있는 경우
            if (cur.wordId >= 0 && cur.wordId != index) {
                result.add(Arrays.asList(index, cur.wordId));
            }
            // (2) 끝까지 탐색했을 때 팰린드롬 단어 ID가 있는 경우
            for (int palindromeWordId : cur.palindromeWordIds) {
                result.add(Arrays.asList(index, palindromeWordId));
            }

            return result;
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        Trie t = new Trie();

        List<List<Integer>> answer = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            t.insert(i, words[i]);
        }

        for (int i = 0; i < words.length; i++) {
            answer.addAll(t.search(i, words[i]));
        }
        return answer;
    }
}
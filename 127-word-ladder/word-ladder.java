class Pair {
    String str;
    int level;

    Pair(String str, int level) {
        this.str = str;
        this.level = level;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord))
            return 0;

        int n = wordList.size();
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(beginWord, 1));

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            String word = curr.str;
            int currlevel = curr.level;

            if (word.equals(endWord)) return currlevel;

            StringBuilder sb = new StringBuilder(word);

            for (int i = 0; i < sb.length(); i++) {
                char originalChar = sb.charAt(i);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    sb.setCharAt(i, ch);
                    String newWord = sb.toString();

                    if (set.contains(newWord)) {
                        q.offer(new Pair(newWord, currlevel + 1));
                        set.remove(newWord); 
                    }
                    sb.setCharAt(i, originalChar);
                }
            }
        }
        return 0;
    }
}
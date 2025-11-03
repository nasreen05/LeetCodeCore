class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words.length == 0 || s.length() == 0) return result;

        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;

        // Count the frequency of each word
        Map<String, Integer> wordCount = new HashMap<>();
        for (String w : words)
            wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);

        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();
            for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                String sub = s.substring(j, j + wordLen);
                if (wordCount.containsKey(sub)) {
                    seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                    count++;

                    while (seen.get(sub) > wordCount.get(sub)) {
                        String leftSub = s.substring(left, left + wordLen);
                        seen.put(leftSub, seen.get(leftSub) - 1);
                        left += wordLen;
                        count--;
                    }

                    if (count == numWords) {
                        result.add(left);
                        String leftSub = s.substring(left, left + wordLen);
                        seen.put(leftSub, seen.get(leftSub) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    seen.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }
        return result;
    }
}

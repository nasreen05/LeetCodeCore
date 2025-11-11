import java.util.*;
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // HashMap to store sorted string as key and list of anagrams as value
        Map<String, List<String>> anagramGroups = new HashMap<>();
        
        for (String str : strs) {
            // Convert string to char array and sort it
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            // If the sorted string key exists, add the original string to the list
            if (anagramGroups.containsKey(sortedStr)) {
                anagramGroups.get(sortedStr).add(str);
            } else {
                // Otherwise, create a new list for this sorted key
                List<String> group = new ArrayList<>();
                group.add(str);
                anagramGroups.put(sortedStr, group);
            }
        }

        // Return all groups of anagrams as a list of lists
        return new ArrayList<>(anagramGroups.values());
    }
}

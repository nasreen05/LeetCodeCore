import java.util.*;

class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] mentions = new int[numberOfUsers];
        int[] offlineUntil = new int[numberOfUsers]; // time when user becomes online again (exclusive)

        // Group events by timestamp preserving input order
        Map<Integer, List<List<String>>> map = new HashMap<>();
        for (List<String> ev : events) {
            int time = Integer.parseInt(ev.get(1));
            map.computeIfAbsent(time, k -> new ArrayList<>()).add(ev);
        }

        // Gather timestamps in sorted order
        List<Integer> times = new ArrayList<>(map.keySet());
        Collections.sort(times);

        for (int t : times) {
            List<List<String>> list = map.get(t);

            // First: process OFFLINE events at this timestamp (in original relative order)
            for (List<String> ev : list) {
                if (ev.get(0).equals("OFFLINE")) {
                    int user = Integer.parseInt(ev.get(2));
                    offlineUntil[user] = t + 60; // goes offline until t+60 (exclusive)
                }
            }

            // Compute online status after applying OFFLINEs at this timestamp
            boolean[] online = new boolean[numberOfUsers];
            for (int u = 0; u < numberOfUsers; u++) {
                online[u] = (t >= offlineUntil[u]);
            }

            // Then: process MESSAGE events at this timestamp (in original relative order)
            for (List<String> ev : list) {
                if (ev.get(0).equals("MESSAGE")) {
                    String msg = ev.get(2);
                    if (msg.equals("ALL")) {
                        // mention all users
                        for (int u = 0; u < numberOfUsers; u++) mentions[u]++;
                    } else if (msg.equals("HERE")) {
                        // mention only online users
                        for (int u = 0; u < numberOfUsers; u++) if (online[u]) mentions[u]++;
                    } else {
                        // explicit ids, may contain duplicates
                        String[] parts = msg.split(" ");
                        for (String p : parts) {
                            if (p.startsWith("id")) {
                                int user = Integer.parseInt(p.substring(2));
                                mentions[user]++; // count every occurrence
                            }
                        }
                    }
                }
            }
        }

        return mentions;
    }
}

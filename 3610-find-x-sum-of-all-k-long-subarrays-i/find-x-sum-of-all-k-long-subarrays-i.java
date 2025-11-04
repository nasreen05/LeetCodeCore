import java.util.*;

class Solution {
    int windowSum = 0;

    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        Map<Integer, Integer> count = new HashMap<>();
        TreeSet<Pair> top = new TreeSet<>((a, b) -> {
            if (a.freq != b.freq) return Integer.compare(a.freq, b.freq);
            else return Integer.compare(a.num, b.num);
        });
        TreeSet<Pair> bot = new TreeSet<>((a, b) -> {
            if (a.freq != b.freq) return Integer.compare(a.freq, b.freq);
            else return Integer.compare(a.num, b.num);
        });

        for (int i = 0; i < n; i++) {
            update(nums[i], 1, count, top, bot);

            if (i >= k) update(nums[i - k], -1, count, top, bot);

            // Move bottom elements to top if needed
            while (!bot.isEmpty() && top.size() < x) {
                Pair p = bot.pollLast();
                top.add(p);
                windowSum += p.freq * p.num;
            }

            // Swap bottom and top elements as needed
            while (!bot.isEmpty() && top.size() > 0) {
                Pair botLast = bot.last();
                Pair topFirst = top.first();
                if (botLast.freq > topFirst.freq || (botLast.freq == topFirst.freq && botLast.num > topFirst.num)) {
                    bot.remove(botLast);
                    top.remove(topFirst);

                    top.add(botLast);
                    bot.add(topFirst);

                    windowSum += botLast.freq * botLast.num - topFirst.freq * topFirst.num;
                } else {
                    break;
                }
            }

            if (i >= k - 1) {
                result[i - k + 1] = windowSum;
            }
        }
        return result;
    }

    private void update(int num, int delta, Map<Integer, Integer> count,
                        TreeSet<Pair> top, TreeSet<Pair> bot) {
        int oldCount = count.getOrDefault(num, 0);
        if (oldCount > 0) {
            Pair oldPair = new Pair(oldCount, num);
            if (!bot.remove(oldPair)) {
                top.remove(oldPair);
                windowSum -= (long) oldCount * num;
            }
        }
        int newCount = oldCount + delta;
        count.put(num, newCount);
        if (newCount > 0) {
            Pair newPair = new Pair(newCount, num);
            bot.add(newPair);
        }
    }

    class Pair {
        int freq, num;

        Pair(int freq, int num) {
            this.freq = freq;
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return freq == p.freq && num == p.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(freq, num);
        }
    }
}

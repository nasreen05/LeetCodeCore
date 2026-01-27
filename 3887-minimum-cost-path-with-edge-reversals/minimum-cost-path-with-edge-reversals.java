import java.util.*;

class Solution {

    static class Edge {
        int to, cost;
        Edge(int t, int c) {
            to = t;
            cost = c;
        }
    }

    static class State {
        int node;
        long dist;
        State(int n, long d) {
            node = n;
            dist = d;
        }
    }

    public int minCost(int n, int[][] edges) {

        List<Edge>[] out = new ArrayList[n];
        List<Edge>[] in = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            out[i] = new ArrayList<>();
            in[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            out[e[0]].add(new Edge(e[1], e[2]));
            in[e[1]].add(new Edge(e[0], e[2]));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        boolean[] visited = new boolean[n];

        PriorityQueue<State> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));

        dist[0] = 0;
        pq.offer(new State(0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int u = cur.node;

            if (visited[u]) continue;
            visited[u] = true;

            if (u == n - 1) break;

            // normal outgoing edges
            for (Edge e : out[u]) {
                if (!visited[e.to] && dist[e.to] > dist[u] + e.cost) {
                    dist[e.to] = dist[u] + e.cost;
                    pq.offer(new State(e.to, dist[e.to]));
                }
            }

            // reversed incoming edges (use switch at u)
            for (Edge e : in[u]) {
                if (!visited[e.to] && dist[e.to] > dist[u] + 2L * e.cost) {
                    dist[e.to] = dist[u] + 2L * e.cost;
                    pq.offer(new State(e.to, dist[e.to]));
                }
            }
        }

        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}

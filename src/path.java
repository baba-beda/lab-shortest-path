import java.io.*;
import java.util.*;

/**
 * Created by daria on 21.10.14.
 */
public class path {
    class FastScanner {
        StreamTokenizer st;

        FastScanner() {
            st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        }

        FastScanner(File f) {
            try {
                st = new StreamTokenizer(new BufferedReader(new FileReader(f)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        int nextInt() throws IOException {
            st.nextToken();
            return (int) st.nval;
        }

        long nextLong() throws IOException {
            st.nextToken();
            return (long) st.nval;
        }

        String nextString() throws IOException {
            st.nextToken();
            return st.sval;
        }
    }


    class Pair implements Comparable<Pair> {
        int first; long second;
        Pair (int a, long b) {
            first = a;
            second = b;
        }
        public int compareTo(Pair a) {
            if (a.first != this.first)
                return Integer.compare(a.first, this.first);
            else
                return Long.compare(a.second, this.second);
        }
    }

    class Edge {
        int u, v; long weight;

        Edge(int a, int b, long c) {
            u = a;
            v = b;
            weight = c;
        }
    }

    FastScanner in;
    PrintWriter out;
    ArrayList<Pair>[] graph;
    long d[];
    final long INF = 5000000000000000000L;
    Edge[] edges;
    int n, m;
    Vector<Integer> cycle;
    boolean cycled[];
    int x = 1;

    public void solve() throws IOException {
        try {
            n = in.nextInt();
            m = in.nextInt();
            int start = in.nextInt() - 1;
            edges = new Edge[m];
            graph = new ArrayList[n];
            cycled = new boolean[n];

            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Pair>();
            }

            for (int i = 0; i < m; i++) {
                int a = in.nextInt() - 1, b = in.nextInt() - 1;
                long c = in.nextLong();
                graph[a].add(new Pair(b, c));
                edges[i] = new Edge(a, b, c);
            }

            d = new long[n];
            Arrays.fill(d, INF);
            while (x != -1)
                ford_bellman(start);
            for (int i = 0; i < n; i++) {
                if (cycled[i]) {
                    for (Pair p : graph[i])
                        cycled[p.first] = true;
                }
            }
            for (int j = 0; j < n; j++) {
                if (d[j] == INF)
                    out.println("*");
                if (cycled[j])
                    out.println("-");
                else if (d[j] > -INF && d[j] < INF)
                    out.println(d[j]);
            }
        } catch (Exception e) {

        }
    }

    void ford_bellman(int start) {
        d[start] = 0;
        int[] p = new int[n];
        Arrays.fill(p, -1);
        for (int i = 0; i < n; i++) {
            x = -1;
            for (Edge edge : edges) {
                if (d[edge.u] < INF && !cycled[edge.u]) {
                    if (d[edge.v] > d[edge.u] + edge.weight) {
                        d[edge.v] = Math.max(-INF, d[edge.u] + edge.weight);
                        p[edge.v] = edge.u;
                        x = edge.v;
                    }
                }
            }
        }
        if (x != -1) {
            int y = x;
            for (int i = 0; i < n; i++) {
                y = p[y];
            }
            cycle = new Vector<Integer>();
            for (int cur = y; ; cur = p[cur]) {
                cycle.add(cur);
                cycled[cur] = true;
                if (cur == y && cycle.size() > 1)
                    break;
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("path.in"));
            out = new PrintWriter("path.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new path().run();
    }
}

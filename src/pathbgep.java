
import java.io.*;
import java.util.*;

/**
 * Created by daria on 18.10.14.
 */
public class pathbgep {
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

        String nextString() throws IOException {
            st.nextToken();
            return st.sval;
        }
    }


    class Pair implements Comparable<Pair> {
        long first; int second;
        Pair (long a, int b) {
            first = a;
            second = b;
        }
        public int compareTo(Pair a) {
            if (a.first != this.first)
                return Long.compare(a.first, this.first);
            else
                return Integer.compare(a.second, this.second);
        }
    }

    FastScanner in;
    PrintWriter out;
    ArrayList<Pair>[] graph;
    long d[];
    final long INF = 4000000000L;
    int n;
    boolean visited[];
    PriorityQueue<Long> queue = new PriorityQueue<Long>();

    public void solve() throws IOException {
            n = in.nextInt();
            int m = in.nextInt();
            visited = new boolean[n];
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Pair>();
            }
            d = new long[n];
            for (int i = 0; i < m; i++) {
                int a = in.nextInt() - 1, b = in.nextInt() - 1, w = in.nextInt();
                graph[a].add(new Pair(w, b));
                graph[b].add(new Pair(w, a));
            }
            //int start = in.nextInt() - 1, finish = in.nextInt() - 1;
            int start = 0;
            Arrays.fill(d, INF);
            extendedDijkstra(start);
            //out.print((d[finish] != INF ? d[finish] : -1) + " ");
            for (long dest : d)
                out.print((dest != INF ? dest : -1) + " ");
    }

    void extendedDijkstra(int start) {
        d[start] = 0;
        queue.add((long)start);

        while(!queue.isEmpty()) {
            long cur = queue.remove();
            int v = (int) cur;
            visited[v] = true;
            if (cur >>> 32  != d[v])
                continue;

            for (Pair u : graph[v]) {
                if (d[u.second] > d[v] + u.first) {
                    d[u.second] = d[v] + u.first;
                    if (!visited[u.second])
                        queue.add((d[u.second] << 32) + u.second);
                }
            }
        }
    }
    public void run() {
        try {
            in = new FastScanner(new File("pathbgep.in"));
            out = new PrintWriter("pathbgep.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new pathbgep().run();
    }
}
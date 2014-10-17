import java.io.*;
import java.util.*;

/**
 * Created by daria on 17.10.14.
 */
public class pathbge1 {
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


    FastScanner in;
    PrintWriter out;
    ArrayList<Integer>[] graph;
    int d[];
    final int INF = 400001;
    Deque<Integer> queue = new ArrayDeque<Integer>();

    public void solve() throws IOException {

        int n = in.nextInt(), m = in.nextInt();
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        d = new int[n];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            graph[a].add(b);
            graph[b].add(a);
        }
        Arrays.fill(d, INF);
        bfs(0);
        for(int dest : d) {
            out.print(dest + " ");
        }
    }

    void bfs(int v) {
        d[v] = 0;
        queue.clear();
        queue.add(v);
        while (!queue.isEmpty()) {
            int u = queue.pollFirst();
            for (int to : graph[u]) {
                if (d[to] == INF) {
                    d[to] = d[u] + 1;
                    queue.add(to);
                }
            }
        }

    }
    public void run() {
        try {
            in = new FastScanner(new File("pathbge1.in"));
            out = new PrintWriter("pathbge1.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new pathbge1().run();
    }
}

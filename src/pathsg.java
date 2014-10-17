import java.io.*;
import java.util.*;

/**
 * Created by daria on 18.10.14.
 */
public class pathsg {
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
    class Pair {
        int first, second;
        Pair (int a, int b) {
            first = a;
            second = b;
        }
    }

    FastScanner in;
    PrintWriter out;
    ArrayList<Pair>[] graph;
    int d[][];
    final int INF = 400001;
    boolean u[];
    int n;
    Deque<Integer> queue = new ArrayDeque<Integer>();

    public void solve() throws IOException {

        n = in.nextInt(); int  m = in.nextInt();
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Pair>();
        }
        u = new boolean[n];
        d = new int[n][n];
        for (int i = 0; i < m; i++) {
            graph[in.nextInt() - 1].add(new Pair(in.nextInt() - 1, in.nextInt()));
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
            Dijkstra(i);
            for (int j = 0; j < n; j++) {
                out.print((d[i][j] != INF ? d[i][j] : -1) + " ");
            }
            out.println();
        }

    }

    void Dijkstra(int start) {
        Arrays.fill(u, false);
        for (int i = 0; i < n; i++) {
            int v = - 1;
            for (int j = 0; j < n; j++) {
                if (!u[j] && (v == -1 || d[start][j] < d[start][v]))
                    v = j;
            }
            if (d[start][v] == INF)
                break;
            u[v] = true;

            for (Pair p : graph[v]) {
                d[start][p.first] = Math.min(d[start][p.first], d[start][v] + p.second);
            }
        }
    }
    public void run() {
        try {
            in = new FastScanner(new File("pathsg.in"));
            out = new PrintWriter("pathsg.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new pathsg().run();
    }
}

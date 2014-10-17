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
    long d[];
    final long INF = Integer.MAX_VALUE;
    int n;
    Deque<Integer> queue = new ArrayDeque<Integer>();

    public void solve() throws IOException {

        n = in.nextInt(); int  m = in.nextInt();
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Pair>();
        }
        d = new long[n];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1, w = in.nextInt();
            graph[a].add(new Pair(b, w));
            graph[b].add(new Pair(a, w));
        }
        Arrays.fill(d, INF);
        levit(0);
        for(long dest : d) {
            out.print((dest != INF ? dest : -1) + " ");
        }
    }

    void levit(int start) {
        d[start] = 0;
        int id[] = new int[n];
        queue.add(start);

        while(!queue.isEmpty()) {
            int v = queue.pollFirst();
            id[v] = 1;
            for (Pair u : graph[v]) {
                if (d[u.first] > d[v] + u.second) {
                    d[u.first] = d[v] + u.second;
                    if (id[u.first] == 0)
                        queue.add(u.first);
                    else if (id[u.first] == 1)
                        queue.addFirst(u.first);
                    id[u.first] = 1;
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

import sun.font.FontRunIterator;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by daria on 18.10.14.
 */
public class pathmgep {
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
    class Edge {
        int u, v, weight;
        Edge(int a, int b, int c){
            u = a;
            v = b;
            weight = c;
        }
    }

    FastScanner in;
    PrintWriter out;
    long d[];
    ArrayList<Edge> edges;
    final long INF = 2000000000;
    int n;

    public void solve() throws IOException {

        n = in.nextInt(); int start = in.nextInt() - 1, finish = in.nextInt() - 1;
        edges = new ArrayList<Edge>();
        d = new long[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = in.nextInt();
                if (k >= 0)
                    edges.add(new Edge(i, j, k));
            }
        }
        Arrays.fill(d, INF);
        if(BellmanFord(start) && d[finish] != INF)
            out.println(d[finish]);
        else
            out.println(-1);
    }

    boolean BellmanFord(int start) {
        d[start] = 0;
        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                d[edge.v] = Math.min(d[edge.v], d[edge.u] + edge.weight);
            }
        }
        for (Edge edge : edges) {
            if (d[edge.v] > d[edge.u] + edge.weight)
                return false;
        }
        return true;
    }


    public void run() {
        try {
            in = new FastScanner(new File("pathmgep.in"));
            out = new PrintWriter("pathmgep.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new pathmgep().run();
    }
}

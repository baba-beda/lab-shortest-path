import java.io.*;
import java.util.*;

/**
 * Created by daria on 20.10.14.
 */
public class negcycle {
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
        Edge (int a, int b, int c) {
            u = a;
            v = b;
            weight = c;
        }
    }

    FastScanner in;
    PrintWriter out;
    ArrayList<Edge> edges;
    int d[], p[];
    final int INF = 1000000000;

    public void solve() throws IOException {
        edges = new ArrayList<Edge>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = in.nextInt();
                if (a != INF)
                    edges.add(new Edge(i, j, a));
            }
        }
        d = new int[n];
        p = new int[n];
        Arrays.fill(p, -1);
        
        int x = -1;
        for (int i = 0; i < n; i++) {
            x = -1;
            for (Edge edge : edges) {
                if (d[edge.v] > d[edge.u] + edge.weight) {
                    d[edge.v] = Math.max(-INF, d[edge.u] + edge.weight);
                    p[edge.v] = edge.u;
                    x = edge.v;
                }
            }
        }
        
        if (x == -1)
            out.println("NO");
        else {
            int y = x;
            for (int i = 0; i < n; i++) {
                y = p[y];
            }
            
            Vector<Integer> path = new Vector<Integer>();
            for (int cur = y; ; cur = p[cur]) {
                path.add(cur);
                if (cur == y && path.size() > 1)
                    break;
            }
            Collections.reverse(path);
            out.println("YES");
            out.println(path.size());
            for (int k : path)
                out.print((k + 1) + " ");
        }
    }


    public void run() {
        try {
            in = new FastScanner(new File("negcycle.in"));
            out = new PrintWriter("negcycle.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new negcycle().run();
    }
}

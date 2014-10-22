import java.io.*;
import java.util.*;

/**
 * Created by daria on 22.10.14.
 */
public class planaritycheck {
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextString() {
            return next();
        }
    }


    FastScanner in;
    PrintWriter out;
    ArrayList<Integer>[] graph;
    int color[];
    boolean ok;
    int n;

    public void solve() throws IOException {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            String t = in.nextString();
            int len = t.length();
            if (len == 1)
                n = 2;
            else if (len == 3)
                n = 3;
            else if (len == 6)
                n = 4;
            else if (len == 10)
                n = 5;
            else
                n = 6;
            if (n <= 4 || (n == 5 && !t.equals("1111111111"))) {
                out.println("YES");
                continue;
            }
            if (t.equals("1111111111")) {
                out.println("NO");
                continue;
            }
            graph = new ArrayList[n];
            int m = 0;
            for (int j = 0; j < n; j++) {
                graph[j] = new ArrayList<Integer>();
            }
            int k = n;
            for (int j = n - 1; j >= 0; j--) {
                for (int l = k - 1; l > 0; l--) {
                    if (t.charAt(t.length() - 1) == '1') {
                        m++;
                        graph[j].add(l - 1);
                        graph[l - 1].add(j);
                    }
                    t = t.substring(0, t.length() - 1);
                }
                k--;
            }
            if (homeo_k33() || homeo_k5(m))
                out.println("NO");
            else
                out.println("YES");
        }
    }

    boolean homeo_k33() {
        color = new int[graph.length];
        ok = true;
        for (int i = 0; i < color.length; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                dfs(i);
            }
        }
        if (!ok)
            return false;
        Vector<Integer> second = new Vector<Integer>();
        for (int i = 0; i < color.length; i++) {
            if (color[i] == 2)
                second.add(i);
        }
        for (int i = 0; i < color.length; i++) {
            if (color[i] == 1)
            {
                for (int v : second) {
                    if (!graph[i].contains(v))
                        return false;
                }
            }
        }
        return true;
    }

    boolean homeo_k5(int edges) {
        for (int i = 0; i < 6; i++) {
            if (graph[i].size() > 1 && edges - graph[i].size() + 1 == 10 || edges - graph[i].size() == 10)
                return true;
        }
        return false;
    }

    void dfs(int u) {
        for(int v : graph[u]) {
            if (color[v] == 0) {
                color[v] = 3 - color[u];
                dfs(v);
            }
            else if (color[v] == color[u]) {
                ok = false;
            }
        }
    }
    public void run() {
        try {
            in = new FastScanner(new File("planaritycheck.in"));
            out = new PrintWriter("planaritycheck.out");

            solve();

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new planaritycheck().run();
    }
}

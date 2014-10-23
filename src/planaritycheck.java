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
        boolean flag = false;
        for (int i = 0; i < 6 && !flag; i++) {
            for (int j = 0; j < 6 && !flag; j++) {
                if (j == i)
                    continue;
                for (int k = 0; k < 6; k++) {
                    if (k == i || k == j)
                        continue;
                    int count = 0;
                    for (int l = 0; l < 6; l++) {
                        if (l != i && l != j && l != k && graph[i].contains(l) && graph[j].contains(l) && graph[k].contains(l))
                            count ++;
                    }
                    if (count == 3) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    boolean homeo_k5(int edges) {
        for (int i = 0; i < 6; i++) {
            if (graph[i].size() <= 1 && edges - graph[i].size() == 10)
                return true;
            int count = 0;
            boolean[] visited = new boolean[6];
            if (graph[i].size() >= 2) {
                for (int neigh : graph[i]) {
                    for (int to : graph[neigh])
                        if (graph[i].contains(to) && !visited[to] && to != i) {
                            count++;
                        }
                    visited[neigh] = true;
                }
            }
            if (graph[i].size() - count == 1 && edges - graph[i].size() + 1 == 10 && graph[i].size() > 2 || count == 0 && edges - graph[i].size() + 1 == 10 && graph[i].size() == 2 || edges - graph[i].size() == 10)
                return true;
        }
        return false;
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

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

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
    int d[][];

    public void solve() throws IOException {

        int n = in.nextInt(),  m = in.nextInt();

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

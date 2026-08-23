import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MergeToMatch{
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        FastReader in = new FastReader();
        String tStr = in.next();
        if (tStr == null) return;
        int t = Integer.parseInt(tStr);

        StringBuilder out = new StringBuilder();

        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            int[] b = new int[m];
            for (int i = 0; i < m; i++) {
                b[i] = in.nextInt();
            }

            if (n < 2 * m) {
                out.append("NO\n");
                continue;
            }

            Arrays.sort(a);
            Arrays.sort(b);

            boolean possible = true;

            for (int i = 0; i < m; i++) {
                if (a[i] >= b[i]) {
                    possible = false;
                    break;
                }
                if (b[i] >= a[n - m + i]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }

        System.out.print(out);
    }
}

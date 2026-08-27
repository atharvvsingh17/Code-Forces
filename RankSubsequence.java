import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class RankSubsequence {
    static class Element {
        int l, r, u, v;

        Element(int l, int r, int u, int v) {
            this.l = l;
            this.r = r;
            this.u = u;
            this.v = v;
        }
    }

    private static boolean isValid(Element el, int j, int m) {
        boolean leftOk = (j < el.l || j > el.r);
        int rightRank = m - j + 1;
        boolean rightOk = (rightRank < el.u || rightRank > el.v);
        return leftOk && rightOk;
    }

    private static boolean canForm(Element[] a, int n, int m) {
        int currentIdx = 0;
        for (int j = 1; j <= m; j++) {
            int nextIdx = -1;
            for (int i = currentIdx + 1; i <= n - (m - j); i++) {
                if (isValid(a[i], j, m)) {
                    nextIdx = i;
                    break;
                }
            }
            if (nextIdx == -1) {
                return false;
            }
            currentIdx = nextIdx;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        FastReader scanner = new FastReader();
        StringBuilder out = new StringBuilder();

        String token = scanner.next();
        if (token == null) return;
        int t = Integer.parseInt(token);

        while (t-- > 0) {
            int n = scanner.nextInt();
            Element[] a = new Element[n + 1];

            for (int i = 1; i <= n; i++) {
                int l = scanner.nextInt();
                int r = scanner.nextInt();
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                a[i] = new Element(l, r, u, v);
            }

            int ans = 0;
            for (int m = n; m >= 1; m--) {
                if (canForm(a, n, m)) {
                    ans = m;
                    break;
                }
            }
            out.append(ans).append("\n");
        }

        System.out.print(out);
    }

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
}

import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;

public class ArrayReplacement {
    static class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1024 * 64];
        private int head = 0;
        private int tail = 0;

        public FastScanner(InputStream stream) {
            this.stream = stream;
        }

        private int read() {
            if (head >= tail) {
                head = 0;
                try {
                    tail = stream.read(buf, 0, buf.length);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (tail <= 0) return -1;
            }
            return buf[head++];
        }

        public int nextInt() {
            int c = read();
            while (c <= 32 && c != -1) c = read();
            if (c == -1) return -1;
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            while (c > 32) {
                if (c < '0' || c > '9') break;
                res = res * 10 + c - '0';
                c = read();
            }
            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (c <= 32 && c != -1) c = read();
            if (c == -1) return -1;
            long sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            while (c > 32) {
                if (c < '0' || c > '9') break;
                res = res * 10 + c - '0';
                c = read();
            }
            return res * sgn;
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int t = in.nextInt();
        if (t == -1) return;
        
        StringBuilder out = new StringBuilder();

        while (t-- > 0) {
            int n = in.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextLong();
            }

            int m = n - 1;
            long[] d = new long[m];
            for (int i = 0; i < m; i++) {
                d[i] = a[i + 1] - a[i];
            }

            int i = 0;
            while (i < m) {
                int j = i;
                long p = (d[i] % 2 + 2) % 2;
                while (j < m && (d[j] % 2 + 2) % 2 == p) {
                    j++;
                }
                Arrays.sort(d, i, j);
                i = j;
            }

            long[] ans = new long[n];
            ans[0] = a[0];
            for (int k = 1; k < n; k++) {
                ans[k] = ans[k - 1] + d[k - 1];
            }

            for (int k = 0; k < n; k++) {
                out.append(ans[k]).append(k + 1 == n ? "" : " ");
            }
            out.append("\n");
        }

        System.out.print(out);
    }
}

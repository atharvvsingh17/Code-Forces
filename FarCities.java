import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class FarCities {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[32768];
        private int head = 0;
        private int tail = 0;

        public FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (head >= tail) {
                head = 0;
                tail = in.read(buffer, 0, buffer.length);
                if (tail <= 0) return -1;
            }
            return buffer[head++];
        }

        public int nextInt() throws IOException {
            int c = read();
            while (c <= 32 && c != -1) {
                c = read();
            }
            if (c == -1) return -1;
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int res = 0;
            while (c > 32) {
                if (c >= '0' && c <= '9') {
                    res = res * 10 + (c - '0');
                }
                c = read();
            }
            return res * sign;
        }
    }

    static FastScanner in = new FastScanner(System.in);

    static int query(int u, int v, int d) throws IOException {
        System.out.print("? " + u + " " + v + " " + d + "\n");
        System.out.flush();
        int res = in.nextInt();
        if (res == -1) {
            System.exit(0);
        }
        return res;
    }

    static void answer(int u, int v, int d) {
        System.out.print("! " + u + " " + v + " " + d + "\n");
        System.out.flush();
    }

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        if (t == -1) return;

        while (t-- > 0) {
            int n = in.nextInt();
            if (n == -1) break;

            int root = 1;
            int maxDistA = 0;
            int nodeA = 1;

            for (int i = 2; i <= n; i++) {
                while (query(root, i, maxDistA + 1) == 1) {
                    maxDistA++;
                    nodeA = i;
                }
            }

            int maxDistB = maxDistA;
            int nodeB = root;

            for (int i = 1; i <= n; i++) {
                if (i == nodeA || i == root) continue;
                while (query(nodeA, i, maxDistB + 1) == 1) {
                    maxDistB++;
                    nodeB = i;
                }
            }

            answer(nodeA, nodeB, maxDistB);
        }
    }
}

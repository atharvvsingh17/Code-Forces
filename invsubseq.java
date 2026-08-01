import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class invsubseq {

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];

            boolean allA0 = true;
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                if (a[i] == 1) {
                    allA0 = false;
                }
            }

            boolean allB1 = true;
            boolean equals = true;
            int countMismatched1s = 0;

            for (int i = 0; i < n; i++) {
                b[i] = sc.nextInt();
                if (b[i] == 0) {
                    allB1 = false;
                }
                if (a[i] != b[i]) {
                    equals = false;
                    if (a[i] == 1) {
                        countMismatched1s++;
                    }
                }
            }

            if (equals) {
                sb.append(0).append("\n");
            } else if (allA0 || allB1) {
                sb.append(-1).append("\n");
            } else if (countMismatched1s % 2 != 0) {
                sb.append(1).append("\n");
            } else {
                sb.append(2).append("\n");
            }
        }

        System.out.print(sb.toString());
    }

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
                if (tail <= 0)
                    return -1;
            }
            return buffer[head++];
        }

        public int nextInt() throws IOException {
            int c = read();
            while (c <= ' ') {
                if (c == -1)
                    return -1;
                c = read();
            }
            int res = 0;
            while (c >= '0' && c <= '9') {
                res = res * 10 + c - '0';
                c = read();
            }
            return res;
        }
    }
}

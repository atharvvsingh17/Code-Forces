import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class zerosum {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        StringBuilder output = new StringBuilder();

        if (!scanner.hasNextInt())
            return;
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int initialSum = 0;

            for (int i = 0; i < n; i++) {
                initialSum += scanner.nextInt();
            }

            if (n % 2 == 0 && Math.abs(initialSum) % 4 == 0) {
                output.append("YES\n");
            } else {
                output.append("NO\n");
            }
        }

        System.out.print(output);
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

        public boolean hasNextInt() throws IOException {
            int c = read();
            while (c <= 32) {
                if (c == -1)
                    return false;
                c = read();
            }
            head--;
            return true;
        }

        public int nextInt() throws IOException {
            int c = read();
            while (c <= 32) {
                if (c == -1)
                    return 0;
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            while (c >= '0' && c <= '9') {
                res = res * 10 + c - '0';
                c = read();
            }
            return res * sgn;
        }
    }
}

import java.io.InputStream;
import java.io.IOException;

public class evanescent {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        StringBuilder output = new StringBuilder();

        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            String s = scanner.next();

            int initialBlocks = 1;
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) != s.charAt(i - 1)) {
                    initialBlocks++;
                }
            }

            int minBlocks = initialBlocks;

            for (int i = 1; i < n - 1; i++) {
                char prev = s.charAt(i - 1);
                char curr = s.charAt(i);
                char next = s.charAt(i + 1);

                int delta = 0;

                if (prev == curr && curr == next) {
                    delta = 0;
                } else if (prev == curr && curr != next) {
                    delta = 0;
                } else if (prev != curr && curr == next) {
                    delta = 0;
                } else { // prev != curr && curr != next
                    if (prev == next) {
                        delta = -2;
                    } else {
                        delta = -1;
                    }
                }

                int currentBlocks = initialBlocks + delta;
                if (currentBlocks < minBlocks) {
                    minBlocks = currentBlocks;
                }
            }

            output.append(minBlocks).append('\n');
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

        public String next() throws IOException {
            int c = read();
            while (c <= ' ') {
                if (c == -1)
                    return null;
                c = read();
            }
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }
    }
}
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpyingOnTheBeaver {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int head = 0;
        private int tail = 0;

        public FastScanner(InputStream in) {
            this.in = in;
        }

        private byte read() throws IOException {
            if (head >= tail) {
                head = 0;
                tail = in.read(buffer, 0, buffer.length);
                if (tail <= 0) return -1;
            }
            return buffer[head++];
        }

        public int nextInt() throws IOException {
            byte c = read();
            while (c <= ' ') {
                if (c == -1) return -1;
                c = read();
            }
            int res = 0;
            while (c > ' ') {
                if (c < '0' || c > '9') {
                    c = read();
                    continue;
                }
                res = res * 10 + (c - '0');
                c = read();
            }
            return res;
        }
    }

    static class FastPrinter {
        private final OutputStream out;
        private final byte[] buffer = new byte[1 << 16];
        private int head = 0;

        public FastPrinter(OutputStream out) {
            this.out = out;
        }

        public void print(int x) throws IOException {
            if (x == 0) {
                write((byte) '0');
                return;
            }
            int d = 0;
            byte[] temp = new byte[12];
            while (x > 0) {
                temp[d++] = (byte) ('0' + (x % 10));
                x /= 10;
            }
            while (d > 0) {
                write(temp[--d]);
            }
        }

        public void printSpace() throws IOException {
            write((byte) ' ');
        }

        public void println() throws IOException {
            write((byte) '\n');
        }

        private void write(byte b) throws IOException {
            if (head >= buffer.length) {
                flush();
            }
            buffer[head++] = b;
        }

        public void flush() throws IOException {
            if (head > 0) {
                out.write(buffer, 0, head);
                head = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        FastPrinter pr = new FastPrinter(System.out);

        int t = sc.nextInt();
        if (t == -1) return;

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] p = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                p[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            boolean[] isDam = new boolean[n + 1];
            for (int i = 0; i < m; i++) {
                isDam[sc.nextInt()] = true;
            }

            List<Integer>[] activeChildren = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                activeChildren[i] = new ArrayList<>();
            }

            boolean[] hasActive = new boolean[n + 1];
            List<Integer> cameras = new ArrayList<>();

            for (int i = n; i >= 1; i--) {
                List<Integer> children = activeChildren[i];
                if (isDam[i]) {
                    for (int child : children) {
                        cameras.add(child);
                    }
                    hasActive[i] = true;
                } else {
                    if (children.isEmpty()) {
                        hasActive[i] = false;
                    } else {
                        for (int j = 1; j < children.size(); j++) {
                            cameras.add(children.get(j));
                        }
                        hasActive[i] = true;
                    }
                }

                if (i > 1 && hasActive[i]) {
                    activeChildren[p[i]].add(i);
                }
            }

            pr.print(cameras.size());
            for (int u : cameras) {
                pr.printSpace();
                pr.print(u);
            }
            pr.println();
        }

        pr.flush();
    }
}

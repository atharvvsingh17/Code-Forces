import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;

public class Whiplash {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            
            int xorA = 0;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                xorA ^= a[i];
            }
            
            int xorB = 0;
            for (int i = 0; i < n; i++) {
                b[i] = in.nextInt();
                xorB ^= b[i];
            }
            
            int[] ca = new int[n + 1];
            for (int i = 0; i < n; i++) {
                ca[i] = a[i] ^ xorA;
            }
            ca[n] = xorA;
            
            int[] cb = new int[n + 1];
            for (int i = 0; i < n; i++) {
                cb[i] = b[i] ^ xorB;
            }
            cb[n] = xorB;
            
            Arrays.sort(ca);
            Arrays.sort(cb);
            
            boolean possible = Arrays.equals(ca, cb);
            sb.append(possible ? "YES\n" : "NO\n");
        }
        
        System.out.print(sb);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
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
            while (c <= ' ') {
                if (c == -1) return -1;
                c = read();
            }
            int res = 0;
            while (c > ' ') {
                if (c >= '0' && c <= '9') {
                    res = res * 10 + c - '0';
                }
                c = read();
            }
            return res;
        }
    }
}

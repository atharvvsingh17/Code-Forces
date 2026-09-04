import java.io.InputStream;
import java.io.IOException;

public class ARibbonForTomorrow {
    static final int MOD = 998244353;
    static final int MAX = 1000005;
    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];

    static long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    static long modInverse(long n) {
        return power(n, MOD - 2);
    }

    static void precompute() {
        fact[0] = 1;
        invFact[0] = 1;
        for (int i = 1; i < MAX; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invFact[MAX - 1] = modInverse(fact[MAX - 1]);
        for (int i = MAX - 2; i >= 1; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    public static void main(String[] args) throws IOException {
        precompute();
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();
        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            byte[] s = fs.nextStringBytes();

            int c1 = 0, c2 = 0;
            int s1 = 0, s2 = 0;
            int curLen = 1;
            int runIndex = 0;

            for (int i = 1; i < n; i++) {
                if (s[i] == s[i - 1]) {
                    curLen++;
                } else {
                    if (runIndex % 2 == 0) {
                        c1++;
                        s1 += curLen;
                    } else {
                        c2++;
                        s2 += curLen;
                    }
                    runIndex++;
                    curLen = 1;
                }
            }

            if (runIndex % 2 == 0) {
                c1++;
                s1 += curLen;
            } else {
                c2++;
                s2 += curLen;
            }

            long ways1 = nCr(s1 - 1, c1 - 1);
            long ways2 = (c2 == 0) ? 1 : nCr(s2 - 1, c2 - 1);
            long ans = (ways1 * ways2) % MOD;

            out.append(ans).append('\n');
        }
        System.out.print(out);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[32768];
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
                if (c >= '0' && c <= '9') {
                    res = res * 10 + c - '0';
                }
                c = read();
            }
            return res;
        }

        public byte[] nextStringBytes() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            byte[] res = new byte[1024];
            int len = 0;
            while (c > ' ') {
                if (len == res.length) {
                    byte[] newRes = new byte[res.length * 2];
                    System.arraycopy(res, 0, newRes, 0, len);
                    res = newRes;
                }
                res[len++] = c;
                c = read();
            }
            if (len == res.length) return res;
            byte[] exact = new byte[len];
            System.arraycopy(res, 0, exact, 0, len);
            return exact;
        }
    }
}

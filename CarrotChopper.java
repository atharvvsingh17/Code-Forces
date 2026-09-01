import java.io.InputStream;
import java.io.IOException;

public class Main {
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private byte readByte() {
            return hasNextByte() ? buffer[ptr++] : -1;
        }

        public boolean hasNext() {
            while (hasNextByte() && buffer[ptr] <= ' ') {
                ptr++;
            }
            return hasNextByte();
        }

        public int nextInt() {
            if (!hasNext()) return -1;
            int num = 0;
            byte b = readByte();
            while (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
                b = readByte();
            }
            return num;
        }
    }

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        if (!fs.hasNext()) return;
        int t = fs.nextInt();

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            int[] cnt = new int[m + 1];
            for (int i = 0; i < n; i++) {
                int val = fs.nextInt();
                cnt[val]++;
            }

            int[] suffixSum = new int[m + 2];
            for (int i = m; i >= 1; i--) {
                suffixSum[i] = suffixSum[i + 1] + cnt[i];
            }

            int maxAns = 0;
            for (int L = 1; L <= m; L++) {
                int count2L = (2 * L <= m) ? cnt[2 * L] : 0;
                int current = suffixSum[L] + count2L;
                if (current > maxAns) {
                    maxAns = current;
                }
            }

            sb.append(maxAns).append("\n");
        }

        System.out.print(sb);
    }
}

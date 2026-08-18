import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BossFight {
    static class FastScanner {
        private InputStream is = System.in;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1) return -1;
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = is.read(buf);
                } catch (IOException e) {
                    return -1;
                }
                if (numChars <= 0) return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (c <= ' ' && c != -1) {
                c = read();
            }
            if (c == -1) return -1;
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

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        int t = scanner.nextInt();
        if (t == -1) return;

        StringBuilder out = new StringBuilder();
        while (t-- > 0) {
            int n = scanner.nextInt();
            Map<Integer, Integer> freq = new HashMap<>();
            long totalSum = 0;
            int maxFreq = 0;
            int majorityVal = 0;

            for (int i = 0; i < n; i++) {
                int val = scanner.nextInt();
                totalSum += val;
                int count = freq.getOrDefault(val, 0) + 1;
                freq.put(val, count);

                if (count > maxFreq) {
                    maxFreq = count;
                    majorityVal = val;
                }
            }

            int R = n - maxFreq;
            if (maxFreq <= R + 1) {
                out.append(totalSum).append("\n");
            } else {
                long sumOthers = totalSum - (long) maxFreq * majorityVal;
                long ans = sumOthers + (long) (R + 2) * majorityVal;
                out.append(ans).append("\n");
            }
        }

        System.out.print(out);
    }
}

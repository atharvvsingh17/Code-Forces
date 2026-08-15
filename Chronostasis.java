import java.util.*;
import java.io.*;

public class Chronostasis {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int t = nextInt(in);
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = nextInt(in);
            long[] b = new long[n];
            long total = 0;
            TreeMap<Long, Integer> map = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                b[i] = nextLong(in);
                total += b[i];
                map.merge(b[i], 1, Integer::sum);
            }
            if (total <= 0) {
                sb.append(-1).append('\n');
                continue;
            }
            long currentSum = 0;
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                long threshold = -currentSum;
                Long key = map.higherKey(threshold);
                currentSum += key;
                a[i] = currentSum;
                int cnt = map.get(key);
                if (cnt == 1) map.remove(key);
                else map.put(key, cnt - 1);
            }
            for (int i = 0; i < n; i++) {
                sb.append(a[i]);
                sb.append(i < n - 1 ? ' ' : '\n');
            }
        }
        System.out.print(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0;
        int b = in.read();
        while (b != '-' && (b < '0' || b > '9')) b = in.read();
        boolean neg = false;
        if (b == '-') { neg = true; b = in.read(); }
        while (b >= '0' && b <= '9') {
            ret = ret * 10 + (b - '0');
            b = in.read();
        }
        return neg ? -ret : ret;
    }

    private static long nextLong(DataInputStream in) throws IOException {
        long ret = 0;
        int b = in.read();
        while (b != '-' && (b < '0' || b > '9')) b = in.read();
        boolean neg = false;
        if (b == '-') { neg = true; b = in.read(); }
        while (b >= '0' && b <= '9') {
            ret = ret * 10 + (b - '0');
            b = in.read();
        }
        return neg ? -ret : ret;
    }
}

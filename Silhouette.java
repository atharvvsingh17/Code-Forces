import java.util.*;
import java.io.*;

public class Silhouette {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int t = nextInt(in);
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = nextInt(in);
            long[] b = new long[n];
            for (int i = 0; i < n; i++) b[i] = nextLong(in);

            Integer[] idx = new Integer[n];
            for (int i = 0; i < n; i++) idx[i] = i;
            Arrays.sort(idx, (x, y) -> Long.compare(b[x], b[y]));

            int[] gStart = new int[n];
            long[] gB = new long[n];
            int[] gCnt = new int[n];
            int k = 0, start = 0;
            while (start < n) {
                int end = start;
                long curB = b[idx[start]];
                while (end < n && b[idx[end]] == curB) end++;
                gStart[k] = start; gB[k] = curB; gCnt[k] = end - start;
                k++; start = end;
            }

            boolean ok = (gB[0] == 0);
            long[] v = new long[k];
            if (ok) {
                if (k == 1) {
                    v[0] = 1;
                } else {
                    for (int g = 0; g < k - 1 && ok; g++) {
                        long diff = gB[g + 1] - gB[g];
                        long cnt = gCnt[g];
                        if (diff % cnt != 0) { ok = false; break; }
                        long val = diff / cnt;
                        if (val <= 0) { ok = false; break; }
                        if (g > 0 && val <= v[g - 1]) { ok = false; break; }
                        v[g] = val;
                    }
                    if (ok) v[k - 1] = v[k - 2] + 1;
                }
            }

            if (!ok) {
                sb.append(-1).append('\n');
            } else {
                long[] a = new long[n];
                for (int g = 0; g < k; g++) {
                    int s = gStart[g], c = gCnt[g];
                    long val = v[g];
                    for (int p = s; p < s + c; p++) a[idx[p]] = val;
                }
                for (int i = 0; i < n; i++) {
                    sb.append(a[i]);
                    sb.append(i + 1 < n ? ' ' : '\n');
                }
            }
        }
        System.out.print(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0, b = in.read();
        while (b < '0' || b > '9') b = in.read();
        while (b >= '0' && b <= '9') { ret = ret * 10 + (b - '0'); b = in.read(); }
        return ret;
    }

    private static long nextLong(DataInputStream in) throws IOException {
        long ret = 0; int b = in.read();
        while (b < '0' || b > '9') b = in.read();
        while (b >= '0' && b <= '9') { ret = ret * 10 + (b - '0'); b = in.read(); }
        return ret;
    }
}

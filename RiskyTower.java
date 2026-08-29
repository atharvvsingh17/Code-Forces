import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class RiskyTower {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() throws IOException {
            while (!st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }

    static class SegmentTree {
        int n;
        int[] count;
        long[] sum;

        SegmentTree(int n) {
            this.n = n;
            int size = 1;
            while (size < n) size <<= 1;
            size <<= 1;
            count = new int[size];
            sum = new long[size];
        }

        void update(int node, int l, int r, int idx, long val) {
            count[node]++;
            sum[node] += val;
            if (l == r) return;
            int mid = (l + r) >> 1;
            if (idx <= mid) {
                update(node << 1, l, mid, idx, val);
            } else {
                update((node << 1) | 1, mid + 1, r, idx, val);
            }
        }

        int query(int node, int l, int r, long target, long[] uniqueVals) {
            if (sum[node] < target) return 1000000000;
            if (l == r) {
                return (int) ((target + uniqueVals[l] - 1) / uniqueVals[l]);
            }
            int mid = (l + r) >> 1;
            int rightChild = (node << 1) | 1;
            if (sum[rightChild] >= target) {
                return query(rightChild, mid + 1, r, target, uniqueVals);
            } else {
                return count[rightChild] + query(node << 1, l, mid, target - sum[rightChild], uniqueVals);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        String tStr = fs.next();
        if (tStr == null) return;
        int t = Integer.parseInt(tStr);

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();

            long[] v = new long[n];
            for (int i = 0; i < n; i++) {
                v[i] = fs.nextLong();
            }

            long[][] a = new long[n][m];
            long[] allVals = new long[n * m];
            int ptr = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j] = fs.nextLong();
                    allVals[ptr++] = a[i][j];
                }
            }

            Arrays.sort(allVals);
            int uniqueCount = 0;
            for (int i = 0; i < allVals.length; i++) {
                if (i == 0 || allVals[i] != allVals[i - 1]) {
                    uniqueCount++;
                }
            }

            long[] uniqueVals = new long[uniqueCount];
            uniqueCount = 0;
            for (int i = 0; i < allVals.length; i++) {
                if (i == 0 || allVals[i] != allVals[i - 1]) {
                    uniqueVals[uniqueCount++] = allVals[i];
                }
            }

            SegmentTree st = new SegmentTree(uniqueCount);
            int ans = m;

            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < m; j++) {
                    int rank = Arrays.binarySearch(uniqueVals, a[i][j]);
                    st.update(1, 0, uniqueCount - 1, rank, a[i][j]);
                }
                int needed = st.query(1, 0, uniqueCount - 1, v[i], uniqueVals);
                ans = Math.min(ans, needed);
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb.toString());
    }
}

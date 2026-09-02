import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        String token = fs.next();
        if (token == null) return;
        int t = Integer.parseInt(token);
        StringBuilder out = new StringBuilder();

        while (t-- > 0) {
            long S = fs.nextLong();
            int q = fs.nextInt();

            ArrayList<Long> divList = new ArrayList<>();
            for (long i = 1; i * i <= S; i++) {
                if (S % i == 0) {
                    divList.add(i);
                    if (i * i != S) {
                        divList.add(S / i);
                    }
                }
            }
            int k = divList.size();
            long[] D = new long[k];
            for (int i = 0; i < k; i++) {
                D[i] = divList.get(i);
            }
            Arrays.sort(D);

            long[] H = new long[k];
            for (int i = 0; i < k; i++) {
                H[i] = S / D[i];
            }

            long[] pref = new long[k];
            pref[0] = D[0] * H[0];
            for (int i = 1; i < k; i++) {
                pref[i] = pref[i - 1] + (D[i] - D[i - 1]) * H[i];
            }

            for (int query = 0; query < q; query++) {
                long x = fs.nextLong();
                long y = fs.nextLong();

                int left = 0, right = k - 1;
                int p = -1;
                while (left <= right) {
                    int mid = (left + right) >>> 1;
                    if (H[mid] >= y) {
                        p = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                left = 0;
                right = k - 1;
                int m = -1;
                while (left <= right) {
                    int mid = (left + right) >>> 1;
                    if (D[mid] <= x) {
                        m = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                if (m < 0) {
                    out.append(0).append('\n');
                    continue;
                }

                long effectiveX = Math.min(x, D[k - 1]);
                if (effectiveX <= 0) {
                    out.append(0).append('\n');
                    continue;
                }

                long cutX = -1;
                if (p >= 0) {
                    cutX = D[p];
                }

                if (effectiveX <= cutX) {
                    out.append(effectiveX * y).append('\n');
                } else {
                    long ans = 0;
                    if (p >= 0) {
                        ans += D[p] * y;
                        ans += (pref[m] - pref[p]);
                    } else {
                        ans += pref[m];
                    }
                    if (m < k - 1) {
                        ans += (effectiveX - D[m]) * H[m + 1];
                    }
                    out.append(ans).append('\n');
                }
            }
        }
        System.out.print(out);
    }
}

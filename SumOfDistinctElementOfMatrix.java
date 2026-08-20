import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class SumOfDistinctElementOfMatrix {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        String firstToken = fs.next();
        if (firstToken == null) return;
        int TestsNumT = Integer.parseInt(firstToken);

        StringBuilder sb = new StringBuilder();

        while (TestsNumT-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();
            int x = fs.nextInt();
            int y = fs.nextInt();

            int[] a = new int[x];
            for (int i = 0; i < x; i++) {
                a[i] = fs.nextInt();
            }

            int[] b = new int[y];
            for (int i = 0; i < y; i++) {
                b[i] = fs.nextInt();
            }

            int maxVal = n + m + 5;
            boolean[] inA = new boolean[maxVal];
            boolean[] inB = new boolean[maxVal];

            for (int v : a) inA[v] = true;
            for (int v : b) inB[v] = true;

            ArrayList<Integer> onlyA = new ArrayList<>();
            ArrayList<Integer> onlyB = new ArrayList<>();
            ArrayList<Integer> both = new ArrayList<>();

            for (int v = 1; v < maxVal; v++) {
                if (inA[v] && inB[v]) {
                    both.add(v);
                } else if (inA[v]) {
                    onlyA.add(v);
                } else if (inB[v]) {
                    onlyB.add(v);
                }
            }

            Collections.sort(onlyA, Collections.reverseOrder());
            Collections.sort(onlyB, Collections.reverseOrder());
            Collections.sort(both, Collections.reverseOrder());

            long[] prefA = new long[onlyA.size() + 1];
            for (int i = 0; i < onlyA.size(); i++) {
                prefA[i + 1] = prefA[i] + onlyA.get(i);
            }

            long[] prefB = new long[onlyB.size() + 1];
            for (int i = 0; i < onlyB.size(); i++) {
                prefB[i + 1] = prefB[i] + onlyB.get(i);
            }

            long[] prefBoth = new long[both.size() + 1];
            for (int i = 0; i < both.size(); i++) {
                prefBoth[i + 1] = prefBoth[i] + both.get(i);
            }

            long ans = 0;
            ans = Math.max(ans, compute(n, 0, x, y, onlyA.size(), onlyB.size(), both.size(), prefA, prefB, prefBoth));
            ans = Math.max(ans, compute(0, m, x, y, onlyA.size(), onlyB.size(), both.size(), prefA, prefB, prefBoth));
            ans = Math.max(ans, compute(n, m - 1, x, y, onlyA.size(), onlyB.size(), both.size(), prefA, prefB, prefBoth));
            ans = Math.max(ans, compute(n - 1, m, x, y, onlyA.size(), onlyB.size(), both.size(), prefA, prefB, prefBoth));

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

    private static long compute(int capR, int capC, int x, int y, int szA, int szB, int szBoth, long[] prefA, long[] prefB, long[] prefBoth) {
        capR = Math.min(capR, x);
        capC = Math.min(capC, y);

        long best = 0;
        int maxK = Math.min(szBoth, capR + capC);

        for (int k = 0; k <= maxK; k++) {
            int minR = Math.max(0, k - capC);
            int maxR = Math.min(k, capR);

            if (minR <= maxR) {
                int rUsed = minR;
                int cUsed = k - rUsed;
                int takeA = Math.min(szA, capR - rUsed);
                int takeB = Math.min(szB, capC - cUsed);
                long val1 = prefBoth[k] + prefA[takeA] + prefB[takeB];

                rUsed = maxR;
                cUsed = k - rUsed;
                takeA = Math.min(szA, capR - rUsed);
                takeB = Math.min(szB, capC - cUsed);
                long val2 = prefBoth[k] + prefA[takeA] + prefB[takeB];

                best = Math.max(best, Math.max(val1, val2));
                
                int optR = Math.min(maxR, Math.max(minR, capR - szA));
                int optC = k - optR;
                long val3 = prefBoth[k] + prefA[Math.min(szA, capR - optR)] + prefB[Math.min(szB, capC - optC)];
                best = Math.max(best, val3);

                int optC2 = Math.min(capC, Math.max(0, capC - szB));
                int optR2 = k - optC2;
                if (optR2 >= minR && optR2 <= maxR) {
                    long val4 = prefBoth[k] + prefA[Math.min(szA, capR - optR2)] + prefB[Math.min(szB, capC - optC2)];
                    best = Math.max(best, val4);
                }
            }
        }

        return best;
    }
}

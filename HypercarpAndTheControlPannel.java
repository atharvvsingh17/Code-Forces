import java.util.*;
import java.io.*;

public class HypercarpAndTheControlPannel {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TestsNumT = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (TestsNumT-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());
            sb.append(solve(a, n)).append('\n');
        }
        System.out.print(sb);
    }

    static int greedyCount(int[] a, int n) {
        int cnt = 0;
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (last == -1 || a[i] != last) {
                cnt++;
                last = a[i];
            }
        }
        return cnt;
    }

    static int solve(int[] a, int n) {
        int best = greedyCount(a, n);

        int[] prefCount = new int[n + 1];
        int[] prefLast = new int[n + 1];
        prefLast[0] = -1;
        for (int i = 0; i < n; i++) {
            if (prefLast[i] == -1 || a[i] != prefLast[i]) {
                prefCount[i + 1] = prefCount[i] + 1;
                prefLast[i + 1] = a[i];
            } else {
                prefCount[i + 1] = prefCount[i];
                prefLast[i + 1] = prefLast[i];
            }
        }

        int[] nextDiff = new int[n + 1];
        nextDiff[n] = n;
        for (int i = n - 1; i >= 0; i--) {
            if (i + 1 < n && a[i + 1] == a[i]) {
                nextDiff[i] = nextDiff[i + 1];
            } else {
                nextDiff[i] = i + 1;
            }
        }

        int[] sufFresh = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int j = nextDiff[i];
            int rest = (j >= n) ? 0 : sufFresh[j];
            sufFresh[i] = 1 + rest;
        }

        for (int i = 0; i + 1 < n; i++) {
            if (a[i] == a[i + 1]) continue;

            int prevColor = prefLast[i];
            int cnt = prefCount[i];

            int lastColor;
            int v1 = a[i + 1];
            if (v1 != prevColor) {
                cnt++;
                lastColor = v1;
            } else {
                lastColor = prevColor;
            }
            int v2 = a[i];
            if (v2 != lastColor) {
                cnt++;
                lastColor = v2;
            }

            int start = i + 2;
            int total;
            if (start >= n) {
                total = cnt;
            } else {
                if (a[start] == lastColor) {
                    int j = nextDiff[start];
                    total = cnt + ((j >= n) ? 0 : sufFresh[j]);
                } else {
                    total = cnt + sufFresh[start];
                }
            }
            if (total > best) best = total;
        }

        return best;
    }
}

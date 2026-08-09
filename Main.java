import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1 << 16);
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            String s = br.readLine().trim();
            String tt = br.readLine().trim();

            int[] prefB = new int[n + 1];
            int[] prefC = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                char cs = s.charAt(i - 1);
                char ct = tt.charAt(i - 1);
                prefB[i] = prefB[i - 1] + ((cs == '0' && ct == '1') ? 1 : 0);
                prefC[i] = prefC[i - 1] + ((cs == '1' && ct == '0') ? 1 : 0);
            }

            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                int cb = prefB[r] - prefB[l - 1];
                int cc = prefC[r] - prefC[l - 1];
                int len = r - l + 1;
                int mx = Math.max(cb, cc);

                sb.append((2 * mx <= len) ? "YES" : "NO").append('\n');
            }
        }

        System.out.print(sb);
    }
}
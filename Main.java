import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final int MOD = 998244353;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine().trim();
            char[] arr = s.toCharArray();
            long ans = 1;
            for (int parity = 0; parity < 2; parity++) {
                boolean valid0 = true, valid1 = true;
                int k = 0;
                for (int i = parity; i < n; i += 2, k++) {
                    char c = arr[i];
                    if (c == '?') continue;
                    int digit = c - '0';
                    int expected0 = k % 2;
                    int expected1 = (k + 1) % 2;
                    if (digit != expected0) valid0 = false;
                    if (digit != expected1) valid1 = false;
                }
                int cnt = (valid0 ? 1 : 0) + (valid1 ? 1 : 0);
                ans = (ans * cnt) % MOD;
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
}
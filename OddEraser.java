import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class OddEraser {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String line = br.readLine();
        if (line == null) return;
        st = new StringTokenizer(line);
        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }
            int n = Integer.parseInt(st.nextToken());

            long first = 0;
            long last = 0;
            for (int i = 0; i < n; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    line = br.readLine();
                    st = new StringTokenizer(line);
                }
                long val = Long.parseLong(st.nextToken());
                if (i == 0) first = val;
                if (i == n - 1) last = val;
            }

            if (n == 1) {
                sb.append(first).append("\n");
            } else {
                sb.append(gcd(first, last)).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}

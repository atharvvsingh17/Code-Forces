import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Gigantomachy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }
            if (line == null) break;

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            long a1 = Long.parseLong(st.nextToken());
            for (int i = 1; i < n; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                st.nextToken();
            }

            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            long b1 = Long.parseLong(st.nextToken());
            for (int i = 1; i < m; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                st.nextToken();
            }

            long hpA = a1 + (long) n - 1;
            long hpB = b1 + (long) m - 1;

            if (hpA >= hpB) {
                sb.append(1).append("\n");
            } else {
                sb.append(2).append("\n");
            }
        }
        System.out.print(sb);
    }
}

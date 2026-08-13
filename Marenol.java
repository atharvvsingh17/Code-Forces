import java.io.*;
import java.util.*;

public class Marenol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String a = br.readLine().trim();
            String b = br.readLine().trim();
            int ae0 = 0, ao0 = 0, be0 = 0, bo0 = 0;
            for (int i = 0; i < n; i++) {
                if (a.charAt(i) == '1') {
                    if ((i & 1) == 0) ae0++; else ao0++;
                }
                if (b.charAt(i) == '1') {
                    if ((i & 1) == 0) be0++; else bo0++;
                }
            }
            if (ae0 == be0 && ao0 == bo0) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.print(sb);
    }
}

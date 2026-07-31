import java.util.*;
import java.io.*;

public class destroyingTowers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            
            long sum = 0;
            int mn = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                mn = Math.min(mn, a[i]);
                sum += mn;
            }
            
            sb.append(sum).append("\n");
        }
        
        System.out.print(sb);
    }
}
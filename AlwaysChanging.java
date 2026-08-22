import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class AlwaysChanging{
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();
        
        int t = fs.nextInt();
        while (t-- > 0) {
            int n = fs.nextInt();
            String s = fs.next();
            
            int c0 = 0, c1 = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '0') c0++;
                else c1++;
            }
            
            int deltaS = c0 - c1;
            
            int numBlocks = 0;
            int first0 = -1, last0 = -1;
            int first1 = -1, last1 = -1;
            
            for (int i = 0; i < n; i++) {
                if (i == 0 || s.charAt(i) != s.charAt(i - 1)) {
                    if (s.charAt(i) == '0') {
                        if (first0 == -1) first0 = numBlocks;
                        last0 = numBlocks;
                    } else {
                        if (first1 == -1) first1 = numBlocks;
                        last1 = numBlocks;
                    }
                    numBlocks++;
                }
            }
            
            int maxLen00 = (first0 != -1 && last0 >= first0) ? (last0 - first0 + 1) : 0;
            int maxLen11 = (first1 != -1 && last1 >= first1) ? (last1 - first1 + 1) : 0;
            int maxLen01 = (first0 != -1 && last1 >= first0) ? (last1 - first0 + 1) : 0;
            int maxLen10 = (first1 != -1 && last0 >= first1) ? (last0 - first1 + 1) : 0;
            
            int maxLen = -1;
            
            for (int deltaT = -1; deltaT <= 1; deltaT++) {
                if (Math.abs(deltaS - deltaT) <= 1) {
                    if (deltaT == 1) {
                        if (maxLen00 > 0) maxLen = Math.max(maxLen, maxLen00);
                    } else if (deltaT == -1) {
                        if (maxLen11 > 0) maxLen = Math.max(maxLen, maxLen11);
                    } else {
                        if (maxLen01 > 0) maxLen = Math.max(maxLen, maxLen01);
                        if (maxLen10 > 0) maxLen = Math.max(maxLen, maxLen10);
                    }
                }
            }
            
            if (maxLen == -1) {
                sb.append("-1\n");
            } else {
                sb.append(n - maxLen).append("\n");
            }
        }
        System.out.print(sb);
    }

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
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

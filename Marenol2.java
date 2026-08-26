import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Marenol2 {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() throws IOException {
            while (!st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        String tStr = fs.next();
        if (tStr == null) return;
        int t = Integer.parseInt(tStr);

        while (t-- > 0) {
            int n = fs.nextInt();
            String a = fs.next();
            String b = fs.next();

            ArrayList<Integer> aEven = new ArrayList<>();
            ArrayList<Integer> bEven = new ArrayList<>();
            ArrayList<Integer> aOdd = new ArrayList<>();
            ArrayList<Integer> bOdd = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    if (a.charAt(i) == '1') aEven.add(i / 2);
                    if (b.charAt(i) == '1') bEven.add(i / 2);
                } else {
                    if (a.charAt(i) == '1') aOdd.add(i / 2);
                    if (b.charAt(i) == '1') bOdd.add(i / 2);
                }
            }

            if (aEven.size() != bEven.size() || aOdd.size() != bOdd.size()) {
                sb.append("-1\n");
                continue;
            }

            long operations = 0;

            for (int i = 0; i < aEven.size(); i++) {
                operations += Math.abs(aEven.get(i) - bEven.get(i));
            }

            for (int i = 0; i < aOdd.size(); i++) {
                operations += Math.abs(aOdd.get(i) - bOdd.get(i));
            }

            sb.append(operations).append("\n");
        }

        System.out.print(sb);
    }
}

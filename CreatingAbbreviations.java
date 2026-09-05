import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) {
            line = br.readLine();
        }
        if (line == null) return;

        st = new StringTokenizer(line);
        int t = Integer.parseInt(st.nextToken());

        StringBuilder out = new StringBuilder();

        while (t-- > 0) {
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }
            if (line == null) break;

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Set<Character> available = new HashSet<>();

            for (int i = 0; i < n; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                String w = st.nextToken();
                available.add(Character.toUpperCase(w.charAt(0)));
            }

            String[] a = new String[m];
            for (int i = 0; i < m; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                a[i] = st.nextToken();
            }

            boolean[] created = new boolean[m];
            int createdCount = 0;
            boolean changed = true;

            while (changed && createdCount < m) {
                changed = false;
                for (int i = 0; i < m; i++) {
                    if (created[i]) continue;

                    boolean canCreate = true;
                    for (int j = 0; j < a[i].length(); j++) {
                        if (!available.contains(a[i].charAt(j))) {
                            canCreate = false;
                            break;
                        }
                    }

                    if (canCreate) {
                        created[i] = true;
                        createdCount++;
                        changed = true;
                        available.add(a[i].charAt(0));
                    }
                }
            }

            if (createdCount == m) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }

        System.out.print(out);
    }
}

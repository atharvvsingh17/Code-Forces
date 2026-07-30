import java.io.*;
import java.util.*;

public class stringConstruction {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int runs = n - k;

            if (runs == 1) {
                out.append("-1\n");
                continue;
            }

            int zeros = n / 2;
            int ones = n - zeros;

            int oneRuns = (runs + 1) / 2;
            int zeroRuns = runs / 2;

            int[] oneLen = new int[oneRuns];
            int[] zeroLen = new int[zeroRuns];

            Arrays.fill(oneLen, 1);
            Arrays.fill(zeroLen, 1);

            oneLen[0] += ones - oneRuns;
            zeroLen[0] += zeros - zeroRuns;

            StringBuilder ans = new StringBuilder();

            int o = 0, z = 0;

            for (int i = 0; i < runs; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < oneLen[o]; j++)
                        ans.append('1');
                    o++;
                } else {
                    for (int j = 0; j < zeroLen[z]; j++)
                        ans.append('0');
                    z++;
                }
            }

            out.append(ans).append('\n');
        }

        System.out.print(out);
    }
}
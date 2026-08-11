import java.util.*;

import java.io.*;

public class threeNoBlackboard {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            long a = Long.parseLong(st.nextToken());

            long b = Long.parseLong(st.nextToken());

            long c = Long.parseLong(st.nextToken());

            long[] arr = {a, b, c};

            Arrays.sort(arr);

            long x = arr[0], y = arr[1], z = arr[2];

            long ans = Math.min(y, z - x);

            sb.append(ans).append('\n');

        }

        System.out.print(sb);

    }

}

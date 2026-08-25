import java.util.Scanner;

public class ThresholdMovement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = sc.nextInt();
            long[] w = new long[n];
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextLong();
            }

            if (n % 2 != 0) {
                sb.append("NO\n");
                continue;
            }

            long minOdd = Long.MAX_VALUE;
            long maxEven = Long.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) { // 1-based odd position (indices 0, 2, 4...)
                    minOdd = Math.min(minOdd, w[i]);
                } else {          // 1-based even position (indices 1, 3, 5...)
                    maxEven = Math.max(maxEven, w[i]);
                }
            }

            if (minOdd - maxEven >= 2) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb);
    }
}

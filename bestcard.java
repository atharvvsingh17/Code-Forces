import java.util.*;
import java.io.*;

public class bestcard {
    public static void main(String[] args) throws IOException {
        int MAXV = 200002; // n up to 2*10^5, so n+1 up to 200001
        boolean[] composite = new boolean[MAXV + 1];
        composite[0] = true;
        if (MAXV >= 1) composite[1] = true;
        for (int i = 2; (long) i * i <= MAXV; i++) {
            if (!composite[i]) {
                for (long j = (long) i * i; j <= MAXV; j += i) {
                    composite[(int) j] = true;
                }
            }
        }

        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int t = nextInt(in);
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = nextInt(in);
            int m = n + 1;
            if (m <= MAXV && !composite[m]) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0;
        int b = in.read();
        while (b < '0' || b > '9') b = in.read();
        while (b >= '0' && b <= '9') { ret = ret * 10 + b - '0'; b = in.read(); }
        return ret;
    }
}

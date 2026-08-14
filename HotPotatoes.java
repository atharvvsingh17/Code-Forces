import java.io.*;
import java.util.*;

public class HotPotatoes {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        int t = nextInt(in);
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = nextInt(in);
            long k = nextLong(in);
            int m = 2 * n;
            char[] s = nextToken(in, m);
            long red = 0, blue = 0;
            for (int i = 0; i < m; i++) {
                if (s[i] == '1') {
                    int j = (i + 1) % m;
                    if (s[j] == '0') {
                        if (i % 2 == 0) red++; else blue++;
                    } else {
                        if (j % 2 == 0) red++; else blue++;
                    }
                }
            }
            sb.append(red).append(' ').append(blue).append('\n');
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

    private static long nextLong(DataInputStream in) throws IOException {
        long ret = 0;
        int b = in.read();
        while (b < '0' || b > '9') b = in.read();
        while (b >= '0' && b <= '9') { ret = ret * 10 + b - '0'; b = in.read(); }
        return ret;
    }

    private static char[] nextToken(DataInputStream in, int len) throws IOException {
        char[] arr = new char[len];
        int b = in.read();
        while (b == ' ' || b == '\n' || b == '\r') b = in.read();
        int idx = 0;
        while (b != -1 && b != ' ' && b != '\n' && b != '\r') { arr[idx++] = (char) b; b = in.read(); }
        return arr;
    }
}

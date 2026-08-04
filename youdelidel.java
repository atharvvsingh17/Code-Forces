import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class youdelidel {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        if (line == null)
            return;
        int t = Integer.parseInt(line.trim());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            String s = br.readLine();
            while (s != null && s.trim().isEmpty()) {
                s = br.readLine();
            }
            if (s == null)
                break;
            s = s.trim();

            int firstZero = s.indexOf('0');
            int firstOne = s.indexOf('1');

            for (int i = 0; i < s.length(); i++) {
                if (i != firstZero && i != firstOne) {
                    sb.append(s.charAt(i));
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}

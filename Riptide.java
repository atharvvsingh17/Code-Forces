import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RipTide {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        String line = br.readLine();
        if (line == null) return;
        
        int t = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int[] tokens = new int[3];
            tokens[0] = Integer.parseInt(st.nextToken());
            tokens[1] = Integer.parseInt(st.nextToken());
            tokens[2] = Integer.parseInt(st.nextToken());
            
            int rounds = 0;
            
            while (true) {
                if (tokens[0] == tokens[1] || tokens[1] == tokens[2] || tokens[0] == tokens[2]) {
                    break;
                }
                
                Arrays.sort(tokens);
                
                tokens[0]++;
                tokens[2]--;
                rounds++;
            }
            
            sb.append(rounds).append("\n");
        }
        
        System.out.print(sb);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj12919 {

    static String S, T;
    static boolean isPossible = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        dfs(T);

        if(isPossible) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static void dfs(String t) {
        if(t.length() == S.length()) {
            if (t.equals(S)) {
                isPossible = true;
            }
            return;
        }
        if (t.charAt(t.length() - 1) == 'A') {
            dfs(t.substring(0, t.length() - 1));
        }
        if (t.charAt(0) == 'B') {
            t= t.substring(1, t.length());
            StringBuilder sb = new StringBuilder(t);
            dfs(sb.reverse().toString());
        }

    }


}

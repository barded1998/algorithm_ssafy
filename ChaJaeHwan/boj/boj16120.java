import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;

public class boj16120 {

    static class Dir{
        int r1, c1, r2, c2;

        public Dir(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dir dir = (Dir) o;
            return r1 == dir.r1 && c1 == dir.c1 && r2 == dir.r2 && c2 == dir.c2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r1, c1, r2, c2);
        }
    }

    static String s;
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        for(int i = 0; i < s.length(); i++){
            stack.push(s.charAt(i));
            if(isPPAP()) {
                for(int j = 0; j < 4; j++) {
                    stack.pop();
                }
                stack.push('P');
            }
        }
        if(stack.size() == 1 && stack.peek() == 'P') {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }

    static boolean isPPAP() {
        StringBuilder sb = new StringBuilder();
        if (stack.size() >= 4) {
            for (int i = stack.size()-4; i < stack.size(); i++) {
                sb.append(stack.elementAt(i));
            }
            return "PPAP".equals(sb.toString());
        }
        return false;
    }


}

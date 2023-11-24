import java.io.*;
import java.util.*;


public class boj2251 {

    static int A, B, C;
    static TreeSet<Integer> answer = new TreeSet<>();
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int max = Math.max(A, Math.max(B, C));
        visited = new boolean[max+1][max+1];
        solve(0, 0,C);

        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);

    }

    static void solve(int a, int b, int c) {
//        System.out.println(a + " | " + b + " | " + c);
        if(visited[a][b]) {
            return;
        }
        visited[a][b] = true;
        //먼저 a 비었는지 확인하고 정답에 c넣기
        if (a == 0) {
            answer.add(c);
        }
        if (c != 0) {
            //c->a
            if (A - a >= c) {
                solve(a + c, b, 0);
            } else {
                solve(A, b, c - (A - a));
            }

            //c->b

            if (B - b >= c) {
                solve(a, b + c, 0);
            } else {
                solve(a, B, c - (B - b));
            }

        }
        if (b != 0) {
            //b->a
            if (A - a >= b) {
                solve(a + b, 0, c);
            } else {
                solve(A, b - (A - a), c);
            }
            //b->c
            if (C - c >= b) {
                solve(a, 0, c + b);
            } else {
                solve(a, b - (C - c), C);
            }

        }

        if (a != 0) {
            //a->c
            if (B - b >= a) {
                solve(0, b + a, c);
            } else {
                solve(a - (B - b), B, c);
            }

            //b->c
            if (C - c >= a) {
                solve(0, b, c + a);
            } else {
                solve(a - (C - c), b, C);
            }

        }
    }

}


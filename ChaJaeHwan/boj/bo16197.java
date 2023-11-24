import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bo16197 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    static int n, m;
    static char[][] graph;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static Node coin1, coin2;

    static int min = 10;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char ch = s.charAt(j);
                if (ch == 'o') {
                    if (coin1 == null)
                        coin1 = new Node(i, j);
                    else
                        coin2 = new Node(i, j);
                }
                graph[i][j] = ch;
            }
        }

        dfs(0, coin1.r, coin1.c, coin2.r, coin2.c);
        if (min == 10) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }

    }

    static void dfs(int depth, int r1, int c1, int r2, int c2) {
        if (depth > min || depth > 10) {
            return;
        }

            for (int i = 0; i < 4; i++) {
                int out = 0;
                int nr1 = r1 + dir[i][0];
                int nc1 = c1 + dir[i][1];
                int nr2 = r2 + dir[i][0];
                int nc2 = c2 + dir[i][1];
                if (inEdge(nr1, nc1)) {
                    out += 1;
                }
                if (inEdge(nr2, nc2)) {
                    out += 1;
                }
                if (out == 2) {
                    continue;
                } else if (out == 1) {
                    min = Math.min(min, depth+1);
                    return;
                } else {
                    if (graph[nr1][nc1] == '#') {
                        nr1 = r1;
                        nc1 = c1;
                    }
                    if (graph[nr2][nc2] == '#') {
                        nr2 = r2;
                        nc2 = c2;
                    }
                }
                dfs(depth + 1, nr1, nc1, nr2, nc2);
            }
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    static boolean inEdge(int r, int c) {
        return r == -1 || r == n || c == -1 || c == m;
    }

}

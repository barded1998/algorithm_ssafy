import java.io.*;
import java.util.*;
public class boj14711 {

    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N;
    static int[][] graph, origin;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static ArrayDeque<Node> blacks = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        origin = new int[N][N];
        String s = br.readLine();
        for (int i = 0; i < N; i++) {
            if(s.charAt(i) == '#') {
                graph[0][i] = 1;
                origin[0][i] = 1;
                blacks.add(new Node(0, i));
            } else {
                graph[0][i] = 0;
                origin[0][i] = 0;
            }
        }
        bfs();
        
    }

    static void bfs() {
//        while (!blacks.isEmpty()) {
//            int qsize = blacks.size();
//            for (int i = 0; i < qsize; i++) {
//                Node poll = blacks.poll();
//                graph[poll.r][poll.c] += 1;
//                for (int j = 0; j < 4; j++) {
//                    int nr = poll.r + dir[i][0];
//                    int nc = poll.c + dir[i][1];
//                    if (inGraph(nr, nc)) {
//                        graph[nr][nc] += 1;
//                    }
//                }
//
//            }
//        }

        for (int i = 0; i < N; i++) {
            while (!blacks.isEmpty()) {
                Node poll = blacks.poll();
                graph[poll.r][poll.c] += 1;
                for (int j = 0; j < 4; j++) {
                    int nr = poll.r + dir[j][0];
                    int nc = poll.c + dir[j][1];
                    if (inGraph(nr, nc)) {
                        graph[nr][nc] += 1;
                    }
                }
            }
            for (int j = 0; j < N; j++) {
                if(i < N-1) {
                    if (graph[i][j] % 2 == 1) {
                        origin[i + 1][j] = 1;
                        graph[i+1][j] += 1;
                        blacks.add(new Node(i + 1, j));
                    }
                }
                if (isAllBlack(i+1, j)) {
                    origin[i + 1][j] = 1;
                    graph[i+1][j] += 1;
                    blacks.add(new Node(i + 1, j));
                }
            }
        }
        print(N-1, origin);

    }
    static boolean inGraph(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static boolean isAllBlack(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            if (inGraph(nr, nc)) {
                if (graph[nr][nc] % 2 == 0) {
                    return false;
                }
            }
        }
        return false;
    }

    static void print(int n, int[][] g) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < N; j++) {
                if (g[i][j] % 2 == 0) {
                    sb.append(".");
                } else {
                    sb.append("#");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
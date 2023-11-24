import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj14466 {
    static int N, K, R;
    static boolean[][] visited;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class Road {
        int r1, c1, r2, c2;

        public Road(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Road road = (Road) o;
            return r1 == road.r1 && c1 == road.c1 && r2 == road.r2 && c2 == road.c2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r1, c1, r2, c2);
        }
    }

    static Node[] cows;
    static Set<Road> roads = new HashSet();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken())-1;
            int c1 = Integer.parseInt(st.nextToken())-1;
            int r2 = Integer.parseInt(st.nextToken())-1;
            int c2 = Integer.parseInt(st.nextToken())-1;
            roads.add(new Road(r1, c1, r2, c2));
            roads.add(new Road(r2, c2, r1, c1));
        }

        cows = new Node[K];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            cows[i] = new Node(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        }
        int answer = 0;
        for(int i = 0; i < K; i++) {
            answer += bfs(i);
        }
        System.out.println(answer);
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static int bfs(int idx) {
        visited = new boolean[N][N];
        ArrayDeque<Node> que = new ArrayDeque<>();
        que.add(cows[idx]);
        visited[cows[idx].r][cows[idx].c] = true;
        while (!que.isEmpty()) {
            Node poll = que.poll();
            for(int i = 0; i < 4; i++) {
                int nr = poll.r + dir[i][0];
                int nc = poll.c + dir[i][1];
                if (inGraph(nr, nc) && !visited[nr][nc] && !roads.contains(new Road(poll.r, poll.c, nr, nc))) {
                    que.add(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        int cnt = 0;
        for(int i = idx + 1; i < K; i++) {
            if(!visited[cows[i].r][cows[i].c]){
                cnt += 1;
            }
        }
        return cnt;
    }

}

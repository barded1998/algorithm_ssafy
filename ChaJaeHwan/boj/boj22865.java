import java.io.*;
import java.util.*;

public class boj22865 {

    static class Road implements Comparable<Road> {
        int n;
        long length;

        public Road(int n, long length) {
            this.n = n;
            this.length = length;
        }

        @Override
        public int compareTo(Road o) {
            return Long.compare(this.length, o.length);
        }
    }


    static int N, A, B, C, M, D, E, L;
    static ArrayList<ArrayList<Road>> graph;
    static long[] answer;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>(N+1);
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        answer = new long[N+1];
        Arrays.fill(answer, Long.MAX_VALUE);
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            graph.get(D).add(new Road(E, L));
            graph.get(E).add(new Road(D, L));
        }
        dijkstra(A);
        dijkstra(B);
        dijkstra(C);
        long max = -1, maxIdx = 1;
        for(int i = 1;i <= N; i++) {
            if(max < answer[i]) {
                max = answer[i];
                maxIdx = i;
            }
        }
        System.out.println(maxIdx);
    }

    static void dijkstra(int n) {
        dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[n] = 0;
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.add(new Road(n, 0));
        while(!pq.isEmpty()) {
            Road poll = pq.poll();
            for (Road road : graph.get(poll.n)) {
                long cost = road.length + dist[poll.n];
                if(cost < dist[road.n]) {
                    dist[road.n] = cost;
                    pq.add(new Road(road.n, cost));
                }
            }
        }
        for(int i = 1;i <= N; i++) {
            if(i != n){
                answer[i] = Math.min(answer[i], dist[i]);
            }
        }

    }

}

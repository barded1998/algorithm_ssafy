import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class boj1068 {
    static int N, root, remove;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] size;

    static boolean[] removed;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList(N);
        removed = new boolean[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList());
        }


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n == -1) {
                root = i;
            } else {
                graph.get(n).add(i);
                size[n]++;
            }
        }

        remove = Integer.parseInt(br.readLine());

        bfs(remove);
        for (int i = 0; i < N; i++) {
            if (graph.get(i).contains(remove)) {
                size[i]--;
            }
        }


        int cnt = 0;
        for(int i = 0; i< N; i++) {
            if(size[i] ==0 && !removed[i]){
                cnt++;
            }
        }
        System.out.println(cnt);

    }

    static void bfs(int r) {
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.add(r);
        removed[r] = true;

        while (!que.isEmpty()) {
            int poll = que.poll();
            for(int n : graph.get(poll)) {
                removed[n] = true;
                que.add(n);
            }
        }
    }

}

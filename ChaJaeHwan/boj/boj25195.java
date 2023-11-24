import java.io.*;
import java.util.*;
public class boj25195 {

    static int N, M, S;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] isGomGom;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList(N+1);
        isGomGom = new boolean[N+1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
        }

        S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < S; i++) {
            isGomGom[Integer.parseInt(st.nextToken())] = true;
        }
        if(!bfs()) {
            System.out.println("Yes ");
        } else {
            System.out.println("yes ");
        }

    }

    static boolean bfs() {
        ArrayDeque<Integer> que = new ArrayDeque();
        que.add(1);
        if(isGomGom[1]) {
            return false;
        }
        boolean[] meetGomGom = new boolean[N+1];
        Arrays.fill(meetGomGom, false);

        while(!que.isEmpty()){
            int poll = que.poll();
//            System.out.print(poll + " : ");
//            System.out.print(graph.get(poll).size() + " : ");
//            System.out.println(!meetGomGom[poll]);
            if(graph.get(poll).size() == 0 && !meetGomGom[poll]) {
//                System.out.println("yes");
                return true;
            }
            for(int i = 0; i < graph.get(poll).size(); i++) {
                int next = graph.get(poll).get(i);
                if(!isGomGom[next]) {
                    que.add(next);
                } else {
                    meetGomGom[next] = true;
                }
            }
        }

        return false;
    }

}

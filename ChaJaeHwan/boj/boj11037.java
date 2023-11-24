import java.io.*;
import java.util.*;

public class boj11037 {

    static int N;
    static int[] answer;
    static boolean[] visited;
    static ArrayList<Integer> list = new ArrayList();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i < 10; i++) {
            visited = new boolean[10];
            answer = new int[i];
            dfs(i, 0);
        }
        Collections.sort(list);
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            N = Integer.parseInt(s);
            int start = 0, end = list.size() - 1;
            int min = 1_000_000_000;
            while (start <= end) {
                int mid = (start + end);
                if (list.get(mid) > N) {
                    min = list.get(mid);
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            if(min == 1_000_000_000) {
                System.out.println(0);
                sb.append(0).append("\n");
            } else {
                System.out.println(min);
                sb.append(min).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void dfs(int D, int depth) {
        if (depth == D) {
            int sum = 0;
            int d = 1;
            for (int i = D - 1; i >= 0; i--) {
                sum += answer[i] * d;
                d *= 10;
            }
            list.add(sum);
            return;
        }
        for (int i = 1; i < 10; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer[depth] = i;
                dfs(D, depth + 1);
                visited[i] = false;
            }
        }
    }



    /*
     * 일단 모든 수 찾기 -> 백트래킹.
     * 찾고 나서 정렬
     * 정렬 후 이분탐색으로 찾기?
     * */

}

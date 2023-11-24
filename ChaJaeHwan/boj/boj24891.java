import java.io.*;
import java.util.*;

public class boj24891 {

    static int L, N;
    static String[] words;
    static char[][] map;
    static ArrayDeque<Character> que = new ArrayDeque<>();
    static List<String> list = new ArrayList<>();
    static boolean[] visited;

    static boolean possible = false;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());


        map = new char[L][L];
        visited = new boolean[N];
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }
        String s = "hello";
        String d = "hello";

        solve(0);
        if(possible){
            System.out.println(sb);
        } else {
            System.out.println("NONE");
        }


    }

    static void solve(int depth) {
        if (depth == L) {
            if(isPossible()){
                if(!possible){
                    for (int i = 0; i < L; i++) {
                        for (int j = 0; j < L; j++) {
                            sb.append(map[i][j]);
                        }
                        sb.append("\n");
                    }
                    possible = true;
                }
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                map[depth] = words[i].toCharArray();
                solve(depth + 1);
                visited[i] = false;
            }
        }
    }

    static boolean isPossible(int depth) {
        for (int i = 0; i <= depth; i++) {
            for (int j = 0; j <= i; j++) {
                if(map[j][i-j] != map[i-j][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    static boolean isPossible() {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (map[i][j] != map[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

}

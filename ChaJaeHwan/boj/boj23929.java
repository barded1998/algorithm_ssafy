import java.io.*;
import java.util.*;

public class boj23929 {
    static int N, Q, X;
    static StringBuilder sb = new StringBuilder();
    static int[] tree;
    static int[] s;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        tree = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
        s = new int[N+1];
        s[1] = tree[1];
        for(int i = 2; i <= N; i++) {
            s[i] = s[i-1] + tree[i];
        }
        System.out.println(Arrays.toString(s));

        for (int i = 0; i < Q; i++) {
            X = Integer.parseInt(br.readLine());

            sb.append(0).append("\n");
        }
        System.out.println(sb);
    }

    public int bs(int x) {
        int start = 1;
        int end = N;
        int mid = 0;
        while(start <= end){
            mid = (start + end) /2;
            if(tree[mid] > x) {
                end = mid -1;
            } else if(tree[mid] < x) {
                start = mid + 1;
            } else{
                break;
            }
        }
        return mid;
    }

}

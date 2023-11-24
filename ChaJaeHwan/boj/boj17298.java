import java.io.*;
import java.util.*;

public class boj17298 {
    static int N;
    static Stack<Integer> s;
    static int[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine());
        data = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(st.nextToken());

        s = new Stack<>();

        for (int i = 0; i < N; i++) {

            while (!s.isEmpty() && data[s.peek()] < data[i]) {
                data[s.pop()] = data[i];
            }

            s.add(i);
        }

        while (!s.isEmpty())
            data[s.pop()] = -1;

        for (int i = 0; i < N; i++)
            sb.append(data[i]).append(" ");

        System.out.println(sb);
    }
}
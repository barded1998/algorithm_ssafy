import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj2467 {

    static int n;
    static int[] arr;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i1.compareTo(i2));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n =Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int first = 0 , second = 2_000_000_000;
        int start = 0;
        int end = n-1;
        while(start < end) {
            if(Math.abs(arr[start] + arr[end]) <= Math.abs(first+second)){
                first = arr[start];
                second = arr[end];
            }
            if(arr[start] + arr[end] >= 0) {
                end--;
            } else {
                start++;
            }
        }
        System.out.println(first + " " + second);
    }

}

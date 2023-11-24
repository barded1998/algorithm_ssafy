import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj17951 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,K,l,r,arr[],min;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            r+=arr[i];
        }
        solve();
        System.out.println(min);
    }

    private static void solve() {
        while(l<=r) {
            int mid = (l+r)/2;
            if(isPossible(mid)) {
                min = mid;
                l= mid+1;
            } else {
                r = mid-1;
            }
        }
    }

    private static boolean isPossible(int num) {
        int sum =0;
        int cnt =0;
        for(int i=0; i<N; i++) {
            sum +=arr[i];
            if(sum >=num) {
                cnt++;
                sum = 0;
            }
        }
        return cnt >= K;
    }
}

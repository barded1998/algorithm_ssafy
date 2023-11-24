import java.io.*;
import java.util.*;
public class boj3151 {
    static int N;
    static long cnt = 0L;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        Arrays.sort(arr);
        bs();
        System.out.println(cnt);




    }

    static void bs() {
        int idx = 0;
        while (arr[idx] <=  0 && idx < N-2) {
            int p = arr[idx];
            int start = idx+1, end = N-1;
            int sum= 0;
            while (start < end) {
                sum = p + arr[start] + arr[end];
                if(sum == 0) {
                    if(arr[start] == arr[end]) {
                        cnt += nC2(end - start + 1);
                        break;
                    } else {
                        long s = 1L;
                        long e = 1L;
                        while(arr[start] == arr[start+1]){
                            start++;
                            s += 1;
                        }
                        while(arr[end] == arr[end-1]){
                            end--;
                            e += 1;
                        }
                        cnt += s*e;
                        start++;
                        end--;
                    }
                } else if (sum > 0) {
                    end--;
                } else {
                    start++;
                }
            }
            idx++;
        }
    }

    static long nC2(long n) {
        return n*(n-1) / 2L;
    }
}

/*
8
-10 5 5 5 5 5 5 5

ans=21

6
-8 3 3 5 5 5

ans=6
 */
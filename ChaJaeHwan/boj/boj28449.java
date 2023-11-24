import java.io.*;
import java.util.*;

public class boj28449 {
    static int N, M;
    static long HI_WIN, ARC_WIN, TIE;
    static int[] HI, ARC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        HI = new int[N];
        ARC = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            HI[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            ARC[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(HI);
        Arrays.sort(ARC);


        for (int i = 0; i < M; i++) {
            int n = ARC[i];

            int l = lowerBoundR(n, 0, N);
            int NotL = lowerBoundNotR(n ,0, N);
            int u = upperBoundR(n, 0, N);
//            int l = lowerBound(n);
//            int u = lowerBound(n);
            System.out.println("l = " + l);
            System.out.println("u = " + u);
            System.out.println("NotL = " + NotL);
            int d = u-l;
            TIE += d;
            HI_WIN += N-u;
            ARC_WIN +=u-d;
////            System.out.println("ARC_WIN = " + ARC_WIN);
//            System.out.print("HI_WIN = " + HI_WIN);
//            System.out.print(" ARC_WIN = " + ARC_WIN);
//            System.out.println(" TIE = " + TIE);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(HI_WIN).append(" ");
        sb.append(ARC_WIN).append(" ");
        sb.append(TIE).append(" ");
        System.out.println(sb);


    }

    static int upperBound(int n) {
        int start = 0;
        int end = N-1;
        while(start < end) {
            int mid = (start + end) / 2;
            if(HI[mid] <= n) {
                start = mid+1;
            } else {
                end = mid;
            }
        }

        return start-1;
    }

    static int upperBoundR(int n, int low, int high) {
        if(low == high){
            return low;
        }
        int mid = (low + high) /2;
        if(HI[mid] <= n) {
            return upperBoundR(n, mid+1, high);
        } else {
            return upperBoundR(n, low, mid);
        }
    }

    static int lowerBoundR(int n, int low, int high) {
        if(low == high){
            return low;
        }
        int mid = (low + high) /2;
        if(HI[mid] < n) {
            return lowerBoundR(n, mid+1, high);
        } else {
            return lowerBoundR(n, low, mid);
        }

    }
    static int lowerBoundNotR(int n, int low, int high) {

        while(low != high){
            int mid = (low + high) /2;
            if(HI[mid] < n) {
                low = mid+1;
            } else {
                high = mid;
            }
        }

        return low;

    }


    static int lowerBound(int n) {
        int start = 0;
        int end = N-1;
        while(start < end) {
            int mid = (start + end) / 2;
            if(HI[mid] < n) {
                start = mid+1;
            } else {
                end = mid;
            }
        }

        return start;
    }

}
/**
 7 8
 2 4 5 70 120 3023 120
 1 2 1 1 1 1 1 1
 */
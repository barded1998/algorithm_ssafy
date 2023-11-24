import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16234인구이동 {

    private static int N;
    private static int L;
    private static int R;
    private static int[][] arr;
    private static int[][] isVisited;
    private static int[][] dist = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우
    private static int flag;

    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        isVisited = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 입력 끝

        int answer = 0;
        while (true) {
            flag = 0;
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                Arrays.fill(isVisited[i], 0);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isVisited[i][j] == 0) {// 방문하지 않았을 경우
                        bfs(i, j, cnt); // 관련된 섬 짓기
                        cnt++;
                    }
                }
            }

            // isVisited 에 저장된 숫자로 계산 후 값 바꾸어주기.
//            int nClusters = cnt - 1; // 군집 개수
//            for (int k = 1; k <= nClusters; k++) { // 1~연합군의 수 까지
//                int target = k; // (숫자)
//                int total = 0; // 마을의 합
//                int index = 0; // 마을의 수
//                for (int i = 0; i < N; i++) {
//                    for (int j = 0; j < N; j++) {
//                        if (isVisited[i][j] == target) { // 마을의 번호가 target이면
//                            total += arr[i][j];
//                            index++;
//                        }
//                    }
//                } // arr 돌기
//
//                int convert = total / index;
//                convertArr(convert, target);
//
//            } // 연합군 cnt 개수 끝

            if(flag == 0) {
                break;
            }
            answer++;

        } // while 끝

        System.out.println(answer);
    }// 메인 끝

    /**
     * 배열 바꾸는 함수
     *
     * @param num
     * @param target
     */
    public static void convertArr(int num, int target) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVisited[i][j] == target) {
                    arr[i][j] = num;
                }
            }
        }
    }

    public static void bfs(int x, int y, int cnt) {
        Queue<Location> queue = new ArrayDeque<Location>();
        List<Location> list = new ArrayList<>();
        queue.offer(new Location(x, y)); // 시작점 넣기
        list.add(new Location(x, y));
        isVisited[x][y] = cnt;
        int sum = arr[x][y];

        while (!queue.isEmpty()) { // 연합군 체크 하기
            Location current = queue.poll();

            for (int i = 0; i < dist.length; i++) {
                int nx = current.x + dist[i][0];
                int ny = current.y + dist[i][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || isVisited[nx][ny] != 0) {
                    // 범위 체크 && 방문 했을 경우
                    continue;
                }

                // arr에 있는 값과 상하 좌우 값을 L이상 R 이하인지 체크 후 isVisited 에 체크 하기
                int prev = arr[current.x][current.y];
                int now = arr[nx][ny];
                if (calculate(prev, now)) { // true : 연합군일 경우 - 인구이동있음
                    isVisited[nx][ny] = cnt;
                    sum += arr[nx][ny];
                    flag = 1;
                    list.add(new Location(nx, ny));
                    queue.offer(new Location(nx, ny));
                }

            } // 상하좌우 for
        } // queue 가 빌때까지 체크

        for (Location location : list) {
            arr[location.x][location.y] = sum / list.size();
        }

    } // bfs 함수 끝

    /**
     * arr 상하좌우 값이 L이상 R 이하 인지 계산하는 함수
     */
    public static boolean calculate(int prev, int now) {

        int target = Math.abs(prev - now);
        if (target >= L && target <= R) {
            return true;
        }
        return false;
    }

}// 클래스 끝

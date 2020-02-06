/**************************************************************
    Problem: 1113
    User: genie97
    Language: Java
    Result: Success
    Time:322 ms
    Memory:12396 kb
****************************************************************/
 
 
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class JO1113_119구급대 {
    static int M, N, m, n;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int[][] dp;
 
    static class Point {
        int x, y, corner, dir;
 
        public Point(int x, int y, int corner, int dir) {
            this.x = x;
            this.y = y;
            this.corner = corner;
            this.dir = dir;
        }
    }
 
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        m = sc.nextInt();
        n = sc.nextInt();
 
        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                dp[i][j] = 1000000;
 
            }
        }
 
        dp[0][0] = -1;
 
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, -1, -1));
        dp[0][0] = 0;
 
        while (!queue.isEmpty()) {
            Point p = queue.poll();
 
            if (p.x == m && p.y == n) {
                break;
            }
 
            for (int i = 0; i < 4; i++) {
                int nx, ny;
                nx = p.x + dx[i];
                ny = p.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N)
                    continue;
                if (map[nx][ny] == 1) {
                    Point np;
                    if (p.dir == i) {
                        np = new Point(nx, ny, p.corner, i);
                    } else {
                        np = new Point(nx, ny, p.corner + 1, i);
                    }
                    if (dp[nx][ny] > np.corner) {
                        queue.add(np);
                        dp[nx][ny] = np.corner;
                    }
 
                }
 
            }
 
        }
 
        System.out.println(dp[m][n]);
    }
 
}

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// 수연이는 4방향으로 이동가능
// 악마의 손아귀를 피해 여신이 있는 공간에 도달하고자 한다
// 최소 시간으로 이동한다
// 수연이는 돌이 있는 곳은 갈 수 없다
// 악마의 손아귀는 돌이 있는 곳에도 확장되지 않는다
// 매초마다 상하좌우 인정 영역을 부식시켜 확장된다.
// 여신 있는 곳은 제외하고 부식시킨다
// 수연이는 S, 여신 D, 악마 * 돌 X 평범지역 . 

public class SWEA7793_오나의여신님 {
    public static class Pos {
        int x, y;
 
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int N;
    public static int M;
    public static char[][] map;
    public static boolean[][] h_visit, d_visit;
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };
    public static Pos human; // 수연
    public static Queue<Pos> devilSkill = new LinkedList<Pos>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            map = new char[N][M];
            devilSkill.clear();
            for (int i = 0; i < N; i++) {
                String str = sc.next();
                for (int j = 0; j < M; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == 'S') {
                        human = new Pos(i, j);
                    }
                    if (map[i][j] == '*') {
                        devilSkill.add(new Pos(i, j));
                    }
                }
            }
            h_visit = new boolean[N][M];
            d_visit = new boolean[N][M];
            int ans = bfs();
            System.out.println("#" + tc + " " + (ans == 0 ? "GAME OVER" : ans));
        }
    }
 
    public static int bfs() {
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[] { human.x, human.y, 0 });
        h_visit[human.x][human.y] = true;
 
        while (!q.isEmpty()) {
            // 수연이가 이동하기 전에 악마의 손아귀 스킬 발동
            skill();
            int size = q.size();
            for (int sz = 0; sz < size; sz++) {
                int[] data = q.poll();
                int x = data[0];
                int y = data[1];
                int cnt = data[2];
                if (map[x][y] == 'D') { // 여신이 있는 곳 도착
                    return cnt;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                        continue;
                    // 악마 스킬 발동하고 있는 곳은 가지 말기, 돌이 있는 곳은 못감
                    if (d_visit[nx][ny] || map[nx][ny] == 'X')
                        continue;
                    if (h_visit[nx][ny])
                        continue;
                    h_visit[nx][ny] = true;
                    q.add(new int[] { nx, ny, cnt + 1 });
                }
 
            }
        }
        return 0;
    }
 
    public static void skill() {
        int size = devilSkill.size();
        for (int sz = 0; sz < size; sz++) {
            int x = devilSkill.peek().x;
            int y = devilSkill.peek().y;
            devilSkill.poll();
            d_visit[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;
                // 여신이 있는 곳 / 돌이 있는 곳은 스킬 발동 불가
                if (map[nx][ny] == 'D' || map[nx][ny] == 'X')
                    continue;
                if (d_visit[nx][ny])
                    continue;
                d_visit[nx][ny] = true;
                devilSkill.add(new Pos(nx, ny));
            }
        }
    }
}

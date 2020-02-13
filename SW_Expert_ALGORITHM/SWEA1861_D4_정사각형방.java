import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SWEA1861_D4_정사각형방 {
	public static int N;
	public static int[][] map;
	public static boolean[][] visit;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int move;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int maxCnt = Integer.MIN_VALUE;
			int roomNum = 0;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visit = new boolean[N][N];
					move = 1;
					cnt = dfs(i, j);
					if (cnt >= maxCnt) {
						if (cnt == maxCnt) {
							roomNum = Math.min(roomNum, map[i][j]);
						} else {
							roomNum = map[i][j];
						}
						maxCnt = cnt;
					}
				}
			}
			System.out.println("#" + tc + " " + roomNum + " " + maxCnt);
		}

	}

	public static int dfs(int x, int y) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (visit[nx][ny])
				continue;
			if (map[x][y] + 1 != map[nx][ny])
				continue;
			visit[nx][ny] = true;
			move++;
			dfs(nx, ny);
		}
		return move;
	}
}

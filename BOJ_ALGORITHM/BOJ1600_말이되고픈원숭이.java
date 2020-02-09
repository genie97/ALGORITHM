import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1600_말이되고픈원숭이 {
	private static class Point {
		int x;
		int y;
		int c;
		int k;

		Point(int x, int y, int c, int k) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.k = k;
		}
	}

	private static int W, H, K;
	private static int[][] map;

	/* 원숭이처럼 */
	private static int[] dx = { -1, 1, 0, 0 }; // 0. 상 1. 하 2. 좌 3. 우
	private static int[] dy = { 0, 0, -1, 1 };
	/* 말처럼 */
	private static int[] hx = { -2, -2, -1, -1, 1, 1, 2, 2 };
	private static int[] hy = { -1, 1, -2, 2, -2, 2, -1, 1 };

	private static boolean visit[][][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();

		map = new int[H][W];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		visit = new boolean[H][W][32];
		visit[0][0][0] = true; // 시작점 방문
		bfs(); // x, y, step
	}

	private static void bfs() {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(0, 0, 0, K));
		while (!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			int c = p.c;
			int k = p.k;
			if (x == H - 1 && y == W - 1) {
				System.out.println(c);
				return;
			}

			if (x < 0 || y < 0 || x >= H || y >= W)
				continue;// 맵 넘어가면 continue
			if (map[x][y] == 1)
				continue;// 맵에 장애물이 있으면 continue
			if (visit[x][y][k])
				continue;// 방문한 곳인지 체크
			visit[x][y][k] = true;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				q.offer(new Point(nx, ny, c + 1, k));
			}
			if (k > 0) {
				for (int i = 0; i < 8; i++) {
					int nx = x + hx[i];
					int ny = y + hy[i];
					q.offer(new Point(nx, ny, c + 1, k - 1));
				}
			}

		}
		System.out.println("-1");
		return;
	}
}

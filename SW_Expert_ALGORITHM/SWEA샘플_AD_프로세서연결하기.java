import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA샘플_AD_프로세서연결하기 {
	private static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int[][] map;
	private static ArrayList<Point> list;
	private static int N;
	private static int[] dx = { -1, 1, 0, 0 }; // 0. 상 1. 하 2. 좌 3. 우
	private static int[] dy = { 0, 0, -1, 1 };
	private static int minLink;
	private static int maxCore;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			list = new ArrayList<Point>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1 && i > 0 && i < (N - 1) && j > 0 && j < (N - 1)) {
						list.add(new Point(i, j));
					}

				}
			}
			minLink = Integer.MAX_VALUE;
			maxCore = Integer.MIN_VALUE;
			DFS(0, 0, 0);
			System.out.println("#" + tc + " " + minLink);
		}
	}

	private static void DFS(int idx, int coreCnt, int linkCnt) {
		if (idx == list.size()) {
			if (maxCore < coreCnt) {
				maxCore = coreCnt;
				minLink = linkCnt;
			} else if (maxCore == coreCnt) {
				if (minLink > linkCnt) {
					minLink = linkCnt;
				}
			}
			return;
		}
		int x = list.get(idx).x;
		int y = list.get(idx).y;
		for (int i = 0; i < 4; i++) {
			/* 이동횟수 = 전선 깔 수있는 수 */
			int step = 0;

			/* 이동하는 좌표 */
			int nx = x;
			int ny = y;

			/* 방문 체크 좌표 */
			int ox = x;
			int oy = y;

			while (true) {
				nx += dx[i];
				ny += dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) { // 가장자리
					break;
				}
				if (map[nx][ny] == 1) {
					step = 0;
					break;
				}
				step++;
			}

			for (int j = 0; j < step; j++) {
				ox += dx[i];
				oy += dy[i];
				map[ox][oy] = 1;
			}
			if (step == 0) { // 전선을 못깔경우
				DFS(idx + 1, coreCnt, linkCnt);
			} else {
				DFS(idx + 1, coreCnt + 1, linkCnt + step);
				// 상태복구
				ox = x;
				oy = y;
				for (int j = 0; j < step; j++) {
					ox += dx[i];
					oy += dy[i];
					map[ox][oy] = 0;
				}
			}
		}
	}
}

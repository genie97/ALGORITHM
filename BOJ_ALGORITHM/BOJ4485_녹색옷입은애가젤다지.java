import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ4485_녹색옷입은애가젤다지 {

	public static class Point {
		int x, y, loopy;

		Point(int loopy, int x, int y) {
			this.loopy = loopy;
			this.x = x;
			this.y = y;
		}
	}

	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int[][] map;
	public static int N;
	public static int[][] dis;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = 1;
		while (true) {
			N = sc.nextInt();
			if (N == 0)
				break;
			map = new int[N][N];
			dis = new int[N][N];
			boolean[][] visit = new boolean[N][N];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = sc.nextInt();
					dis[i][j] = Integer.MAX_VALUE; // 그래프 무한대로 초기화
				}
			}

			// loopy를 기준으로 우선순위 부여
			PriorityQueue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>() {
				public int compare(Point p1, Point p2) {
					Integer l1 = (Integer)p1.loopy;
					Integer l2 = (Integer)p2.loopy;
					return l1.compareTo(l2);
				}
			});
			dis[0][0] = map[0][0]; // 시작점으로 거리 초기화
			pq.offer(new Point(map[0][0], 0, 0));

			while (!pq.isEmpty()) {
				int cx = pq.peek().x;
				int cy = pq.peek().y;
				pq.poll();

				for (int i = 0; i < 4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;
					if (dis[nx][ny] > dis[cx][cy] + map[nx][ny]) { // 거리 갱신
						dis[nx][ny] = dis[cx][cy] + map[nx][ny];
						pq.offer(new Point(map[nx][ny], nx, ny));
					}
				}
			}
			System.out.println("Problem " + tc + ": " + dis[N - 1][N - 1]);
			tc++;
		}
	}
}

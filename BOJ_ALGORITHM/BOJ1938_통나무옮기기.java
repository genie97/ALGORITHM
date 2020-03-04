import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1938_통나무옮기기 {

	public static int[][] map;
	public static boolean[][][] visit;
	public static Point src, dest;
	public static int[] dx = { -1, 1, 0, 0, -1, 1, 1, -1 };
	public static int[] dy = { 0, 0, -1, 1, 1, -1, 1, -1 };
	public static int N;

	public static class Point {
		int x, y, dir;

		Point(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N][2]; // top_x, top_y , direction

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				// 2 또는 5
				if (str.charAt(j) - '0' <= 1) {
					map[i][j] = str.charAt(j) - '0';
				} else {
					map[i][j] = str.charAt(j) - 'A' + 1;
					if (map[i][j] == 2) {
						if (src == null) {
							src = new Point(i, j, -1);
						} else {
							if (src.x == i) {
								src.dir = 0;
							} else {
								src.dir = 1;
							}
						}
					} else if (map[i][j] == 5) {
						if (dest == null) {
							dest = new Point(i, j, -1);
						} else {
							if (dest.x == i) {
								dest.dir = 0;
							} else {
								dest.dir = 1;
							}
						}
					}
				}
			}
		}
		int ans = bfs();
		System.out.println(ans);
	}

	public static int bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { src.x, src.y, src.dir, 0 }); // 통나무의 맨 위 x,y , 가로인지(0) 세로인지(1) , 최소 이동 횟수
		visit[src.x][src.y][src.dir] = true;

		while (!q.isEmpty()) {
			int tx = q.peek()[0];
			int ty = q.peek()[1];
			int dir = q.peek()[2];
			int cnt = q.peek()[3];
			q.poll(); // 큐 빼기
			if (tx == dest.x && ty == dest.y && dir == dest.dir) {
				return cnt;
			}
			int nx = 0, ny = 0, nDir = 0;
			for (int i = 0; i < 5; i++) {
				if (i == 4) { // 방향 회전
					// 중심축
					int mx = tx;
					int my = ty;
					nDir = dir;

					// 중심축 설정해주기
					if (dir == 0) { // 가로 일때,
						my = ty + 1;
					} else { // 세로 일때,
						mx = tx + 1;
					}

					// 중심축을 기준으로 회전가능한지 확인
					if (!doTurn(mx, my)) // 3*3이 범위를 넘거나 , 3*3에 나무가 있거나!
						continue;
					nDir ^= 1; // 방향 변경됨
					if (nDir == 0) { // 가로로 변경됨
						nx = mx;
						ny = my - 1;
					} else {
						nx = mx - 1;
						ny = my;
					}

				} else { // 나머지 상하좌우
					nx = tx + dx[i];
					ny = ty + dy[i];
					nDir = dir;
					if (!doMove(nx, ny, dir)) // 이동하는 위치에 1이 있는지 확인
						continue;
				}
				if (visit[nx][ny][nDir])
					continue;
				visit[nx][ny][nDir] = true;
				q.add(new int[] { nx, ny, nDir, cnt + 1 });
			}
		}
		return 0;
	}

	public static boolean doMove(int x, int y, int dir) {
		if (dir == 0) { // 가로인 경우
			if (x < 0 || y < 0 || x >= N || y >= N || y + 2 >= N)
				return false;
			if (map[x][y] == 1 || map[x][y + 1] == 1 || map[x][y + 2] == 1) // 가로 세칸 중 하나라도 1이면 나무가 있어서 불가
				return false;
		} else {
			if (x < 0 || y < 0 || x >= N || y >= N || x + 2 >= N)
				return false;
			if (map[x][y] == 1 || map[x + 1][y] == 1 || map[x + 2][y] == 1) // 세로 세칸 중 하나라도 1이면 나무가 있어서 불가
				return false;
		}
		return true;
	}

	public static boolean doTurn(int x, int y) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) // 칸을 넘어서 회전이 될 때
				return false;
			if (map[nx][ny] == 1) // 9칸중에 하나라도 1이면 회전 안됨
				return false;
		}
		return true;
	}

}

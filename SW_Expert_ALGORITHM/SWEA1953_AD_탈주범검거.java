import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953_AD_탈주범검거 {
	static int[][] map;
	static int N, M, R, C, L;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0, idx = 0; j < M; j++, idx += 2) {
					map[i][j] = str.charAt(idx) - '0';
				}
			}
			int res = bfs();
			sb.append('#').append(tc).append(' ').append(res).append('\n');
		}
		System.out.println(sb);
	}

	static int bfs() {
		boolean[][] visit = new boolean[N][M];
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(R, C));
		visit[R][C] = true;
		int time = 0;
		int ans = 0;

		while (!q.isEmpty()) {
			int size = q.size();
//			System.out.println(time + " 일때: " + size);
			ans += size;
			if (time == L - 1) {
				return ans;
			}

			while (size-- > 0) {
				Pos cur = q.poll();
				int type = map[cur.x][cur.y];

				for (int d = 0; d < 4; d++) {
					int nx = cur.x;
					int ny = cur.y;

					switch (type) {
					case 2:
						if (d == 2 || d == 3)
							continue;
						break;
					case 3:
						if (d == 0 || d == 1)
							continue;
						break;
					case 4:
						if (d == 1 || d == 2)
							continue;
						break;
					case 5:
						if (d == 0 || d == 2)
							continue;
						break;
					case 6:
						if (d == 0 || d == 3)
							continue;
						break;
					case 7:
						if (d == 1 || d == 3)
							continue;
						break;
					}
					nx += dx[d];
					ny += dy[d];

					if (!isIn(nx, ny))
						continue;
					if (map[nx][ny] == 0 || visit[nx][ny])
						continue;
					if (!isConnect(map[nx][ny], type, d))
						continue;
					q.add(new Pos(nx, ny));
					visit[nx][ny] = true;
				}
			}
			time++;
		}
		return ans;
	}

	static boolean isConnect(int nType, int type, int d) {
		switch (type) {
		case 1:
			if (nType == 1 || (d == 0 && (nType == 2 || nType == 5 || nType == 6))
					|| (d == 1 && (nType == 2 || nType == 4 || nType == 7))
					|| (d == 2 && (nType == 3 || nType == 4 || nType == 5))
					|| (d == 3 && (nType == 3 || nType == 6 || nType == 7)))
				return true;
			break;
		case 2:
			if (nType == 1 || nType == 2 || (d == 0 && (nType == 5 || nType == 6))
					|| (d == 1 && (nType == 4 || nType == 7)))
				return true;
			break;
		case 3:
			if (nType == 1 || nType == 3 || (d == 2 && (nType == 4 || nType == 5))
					|| (d == 3 && (nType == 6 || nType == 7)))
				return true;
			break;
		case 4:
			if (nType == 1 || (d == 0 && (nType == 2 || nType == 5 || nType == 6))
					|| (d == 3 && (nType == 3 || nType == 6 || nType == 7)))
				return true;
			break;
		case 5:
			if (nType == 1 || (d == 1 && (nType == 2 || nType == 4 || nType == 7))
					|| (d == 3 && (nType == 3 || nType == 6 || nType == 7)))
				return true;
			break;
		case 6:
			if (nType == 1 || (d == 1 && (nType == 2 || nType == 4 || nType == 7))
					|| (d == 2 && (nType == 3 || nType == 4 || nType == 5)))
				return true;
			break;
		case 7:
			if (nType == 1 || (d == 0 && (nType == 2 || nType == 5 || nType == 6))
					|| (d == 2 && (nType == 3 || nType == 4 || nType == 5)))
				return true;
			break;
		}

		return false;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}

/* 짧은 코드 ver.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953_AD_탈주범검거 {
	static int[][] map;
	static int N, M, si, sj, L; // 맵크기, 시작점 좌표, 제한시간
	// UP, RIGHT, DOWN, LEFT
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	static int[][] block_hole = { {}, // 0번 블록 없음.
			{ 1, 1, 1, 1 }, { 1, 0, 1, 0 }, { 0, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 1 },
			{ 1, 0, 0, 1 }, };

	static boolean[][] visit;
	static int ans;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visit = new boolean[N][M];

			si = Integer.parseInt(st.nextToken()); // 시작지점
			sj = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken()); // 제한시간

			for (int i = 0; i < N; i++) { // 전체 파이프 맵 구조 입력
				String str = br.readLine();
				for (int j = 0, idx = 0; j < M; j++, idx += 2) {
					map[i][j] = str.charAt(idx) - '0';
				}
			}

			bfs();
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}

	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(si, sj));
		visit[si][sj] = true;
		ans = 0;

		int time = 0;

		while (!queue.isEmpty() && time < L) { // 방문할 수 있는 좌표가 있는가 && 시간도 남았는가
			int size = queue.size(); // 시작 지점에서 같은 거리에 있는 좌표들 한꺼번에 방문처리
			ans += size;
			for (int s = 0; s < size; s++) {
				Point now = queue.poll(); // 현재 탈주범이 now에 서있다고 하면
				int block = map[now.i][now.j];

				for (int d = 0; d < 4; d++) {
					if (block_hole[block][d] == 1) { // 현재 서있는 블럭에 방향 d가 뚫려있는지!
						int ni = now.i + di[d];
						int nj = now.j + dj[d];
						// 반대 방향이 열려있는지 확인
						if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] > 0 && !visit[ni][nj]
								&& block_hole[map[ni][nj]][(d + 2) % 4] == 1) { 
							visit[ni][nj] = true;
							queue.add(new Point(ni, nj));
						}
					}
				}
			}
			time++;
		} // end while
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}

}
*/

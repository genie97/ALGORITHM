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

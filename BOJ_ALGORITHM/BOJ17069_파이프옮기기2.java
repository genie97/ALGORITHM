import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17069_파이프옮기기2 {
	static int N;
	static int[][] map;
	static long[][][] visit;

	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };
	static int[][] movable = { { 1, 0, 1 }, { 0, 1, 1 }, { 1, 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		visit = new long[N + 1][N + 1][3];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1, idx = 0; j <= N; j++, idx += 2) {
				map[i][j] = str.charAt(idx) - '0';
				visit[i][j][0] = -1;
				visit[i][j][1] = -1;
				visit[i][j][2] = -1;
			}
		}

		// 끝점을 시작으로 잡는다
		int sx = 1;
		int sy = 2;
		int d = 0; // 0: 가로 1: 세로 2: 대각선
		dfs(sx, sy, d);
		System.out.println(visit[1][2][0]);
	}

	static void dfs(int x, int y, int d) {
		if (visit[x][y][d] != -1)
			return;

		visit[x][y][d] = 0;

		if (x == N && y == N) {
			visit[x][y][d] = 1;
			return;
		}

		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!isIn(nx, ny))
				continue;
			if (isBlock(nx, ny, i) || movable[d][i] == 0)
				continue;
			dfs(nx, ny, i);
			visit[x][y][d] += visit[nx][ny][i];
		}
	}

	static boolean isBlock(int x, int y, int d) {
		switch (d) {
		case 0:
		case 1:
			if (map[x][y] == 0)
				return false;
			break;
		case 2:
			if (map[x - 1][y] == 0 && map[x][y - 1] == 0 && map[x][y] == 0)
				return false;
		}
		return true;
	}

	static boolean isIn(int x, int y) {
		return x > 0 && x <= N && y > 0 && y <= N;
	}

}

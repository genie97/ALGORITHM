import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17070_파이프옮기기1 {
	public static int N;
	public static int[][] map;
	public static boolean[][][] visit;
	public static int ans;
	public static int[] dx = { 0, 1, 1 };
	public static int[] dy = { 1, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx += 2) {
				map[i][j] = str.charAt(idx) - '0';
			}
		}
		visit = new boolean[N][N][3];
		// 파이프 위치 0,0 가로 방향
		visit[0][0][0] = true;
		ans = 0;
		dfs(0, 0, 0);
		System.out.println(ans);
	}

	public static void dfs(int x, int y, int dir) {
		if (x + dx[dir] == N - 1 && y + dy[dir] == N - 1) {
			ans++;
			return;
		}
		int nx = x, ny = y;
		switch (dir) {
		case 0:
			// 가로 방향
			for (int i = 0; i < 2; i++) {
				nx = x + dx[dir];
				ny = y + dy[dir];
				if (!isIn(nx, ny, i))
					continue;
				if (map[nx][ny] == 1 || map[nx + dx[i]][ny + dy[i]] == 1)
					continue;
				if (i == 1 && !doTurn(nx, ny))
					continue;
				if (visit[nx][ny][i])
					continue;
				visit[nx][ny][i] = true;
				dfs(nx, ny, i);
				visit[nx][ny][i] = false;
			}
			break;
		case 1:
			// 대각선 방향
			for (int i = 0; i < 3; i++) {
				nx = x + dx[dir];
				ny = y + dy[dir];
				if (!isIn(nx, ny, i))
					continue;
				if (map[nx][ny] == 1 || map[nx + dx[i]][ny + dy[i]] == 1)
					continue;
				if (i == 1 && !doTurn(nx, ny))
					continue;
				if (visit[nx][ny][i])
					continue;
				visit[nx][ny][i] = true;
				dfs(nx, ny, i);
				visit[nx][ny][i] = false;
			}
			break;
		default:
			// 세로 방향
			for (int i = 1; i < 3; i++) {
				nx = x + dx[dir];
				ny = y + dy[dir];
				if (!isIn(nx, ny, i))
					continue;
				if (map[nx][ny] == 1 || map[nx + dx[i]][ny + dy[i]] == 1)
					continue;
				if (i == 1 && !doTurn(nx, ny))
					continue;
				if (visit[nx][ny][i])
					continue;
				visit[nx][ny][i] = true;
				dfs(nx, ny, i);
				visit[nx][ny][i] = false;
			}
			break;
		}

	}

	public static boolean doTurn(int x, int y) {
		for (int i = x; i < x + 2; i++) {
			for (int j = y; j < y + 2; j++) {
				if (map[i][j] == 1) // 하나라도 1이 있으면 45도 회전 불가
					return false;
			}
		}
		return true;
	}

	public static boolean isIn(int x, int y, int d) {
		return x < N && y < N && x + dx[d] < N && y + dy[d] < N;
	}

}

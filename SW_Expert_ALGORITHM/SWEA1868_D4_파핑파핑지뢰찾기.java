import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1868_D4_파핑파핑지뢰찾기 {
	public static int N;
	public static char[][] map;
	public static boolean[][] visit;
	public static int[] dx = { -1, 1, 0, 0, 1, 1, -1, -1 };
	public static int[] dy = { 0, 0, -1, 1, 1, -1, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			int clickCnt = 0;
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*' || visit[i][j])
						continue;
					int cnt = count(i, j);
					if (cnt == 0) {
						clickCnt++;
						visit[i][j] = true;
						dfs(i, j);
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j] && map[i][j] == '.')
						clickCnt++;
				}
			}
			sb.append('#').append(tc).append(' ').append(clickCnt).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int x, int y) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (map[nx][ny] == '*' || visit[nx][ny])
				continue;
			visit[nx][ny] = true;
			if (count(nx, ny) == 0) {
				dfs(nx, ny);
			}
		}
	}

	public static int count(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (map[nx][ny] == '*')
				cnt++;
		}
		return cnt;
	}
}

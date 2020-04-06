import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10026_적록색약 {

	static int N;
	static char[][] map;
	static int[][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		// 색약이 아닌 경우
		int ridx = 0;
		visit = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] > 0)
					continue;
				ridx++;
				dfs(i, j, ridx, map[i][j]);

			}
		}

		// 색약인 경우
		colorWeak(); 
		int fidx = 0;
		visit = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] > 0)
					continue;
				fidx++;
				dfs(i, j, fidx, map[i][j]);
			}
		}

		System.out.println(ridx + " " + fidx);
	}

	// 색약으로 
	static void colorWeak() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'G')
					map[i][j] = 'R';
			}
		}
	}

	static void dfs(int x, int y, int area, char color) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (map[nx][ny] != color)
				continue;
			if (visit[nx][ny] != 0)
				continue;
			visit[nx][ny] = area;
			dfs(nx, ny, area, color);
		}
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6109_D4_추억의2048게임 {
	static int[][] map;
	static int N;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append('\n');

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			String com = st.nextToken();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[N][N];
			switch (com) {
			case "up":
				for (int j = 0; j < N; j++) {
					for (int i = 1; i < N; i++) {
						move(i, j, 0);
					}
				}
				break;
			case "down":
				for (int j = 0; j < N; j++) {
					for (int i = N - 2; i >= 0; i--) {
						move(i, j, 1);
					}
				}
				break;
			case "left":
				for (int i = 0; i < N; i++) {
					for (int j = 1; j < N; j++) {
						move(i, j, 2);
					}
				}
				break;
			case "right":
				for (int i = 0; i < N; i++) {
					for (int j = N - 2; j >= 0; j--) {
						move(i, j, 3);
					}
				}
				break;
			}
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					sb.append(map[i][j]).append(' ');
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

	static void move(int x, int y, int d) {
		int nx = x + dx[d];
		int ny = y + dy[d];
		if (isIn(nx, ny) && !visit[nx][ny]) {
			if (map[nx][ny] != 0 && map[x][y] == map[nx][ny] && !visit[x][y]) {
				map[nx][ny] = 2 * map[x][y];
				map[x][y] = 0;
				visit[nx][ny] = true; // 한 번만 합치기 가능
			} else if (map[nx][ny] == 0) {
				map[nx][ny] = map[x][y];
				map[x][y] = 0;
			}
			move(nx, ny, d);
		}
	}

	static boolean isIn(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < N;
	}
}

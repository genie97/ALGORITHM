import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4963_섬의개수 {
	public static int[][] map;
	public static boolean[][] visit;
	public static int w;
	public static int h;
	public static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	public static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) {
				break;
			}
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0, index = 0; j < w; j++, index += 2) {
					map[i][j] = str.charAt(index) - '0';
				}
			}

			visit = new boolean[h][w];
			int idx = 1;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (visit[i][j])
						continue;
					if (map[i][j] == 0)
						continue;
					visit[i][j] = true;
					map[i][j] = idx;
					dfs(i, j, idx);
					idx++;
				}
			}
			System.out.println(idx - 1);
		}
	}

	public static void dfs(int x, int y, int idx) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= h || ny >= w)
				continue;
			if (map[nx][ny] == 0)
				continue;
			if (visit[nx][ny])
				continue;
			visit[nx][ny] = true;
			map[nx][ny] = idx;
			dfs(nx, ny, idx);
		}
	}
}

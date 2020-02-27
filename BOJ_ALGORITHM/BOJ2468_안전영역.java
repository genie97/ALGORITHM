/**
구현시 주의사항
- 물에 아예 잠기지 않은 상황도 포함해야 한다 (따라서 안전 영역의 초기 시작 값은 1)
- 물의 높이는 1이상 100이하
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2468_안전영역 {
	public static int[][] map;
	public static int[][] copyMap;
	public static int N;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copyMap = new int[N][N];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (max < map[i][j]) {
					max = map[i][j];
				} else if (min > map[i][j]) {
					min = map[i][j];
				}
			}
		}
		int ans = 1;

		for (int i = min; i <= max; i++) {
			init();
			sink(i);
			int safe = safeArea();
			if (ans < safe)
				ans = safe;
		}
		System.out.println(ans);
	}

	public static int safeArea() {
		int area = 0;
		for (int i = 0; i < copyMap.length; i++) {
			for (int j = 0; j < copyMap.length; j++) {
				if (copyMap[i][j] == -1)
					continue;
				copyMap[i][j] = -1;
				area += dfs(i, j);
			}
		}
		return area;
	}

	public static int dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (copyMap[nx][ny] == -1)
				continue;
			copyMap[nx][ny] = -1;
			dfs(nx, ny);
		}
		return 1;
	}

	public static void init() {
		for (int i = 0; i < copyMap.length; i++) {
			copyMap[i] = map[i].clone();
		}
	}

	public static void sink(int rain) {
		for (int i = 0; i < copyMap.length; i++) {
			for (int j = 0; j < copyMap.length; j++) {
				if (copyMap[i][j] <= rain) {
					copyMap[i][j] = -1;
				}
			}
		}
	}
}

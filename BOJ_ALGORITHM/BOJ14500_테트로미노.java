import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 0, 0);
				peakShape(i, j);
			}
		}
		System.out.println(max);
	}

	static void dfs(int x, int y, int depth, int sum) {
		if (depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			if (visit[nx][ny])
				continue;
			visit[nx][ny] = true;
			dfs(nx, ny, depth + 1, sum + map[nx][ny]);
			visit[nx][ny] = false;
		}
	}

	// ㅗ 모양 (ㅗ ㅏ ㅜ ㅓ) 가능 + 모양에서 상, 하, 좌, 우 한 번씩 빼는 모양으로 보기!
	static void peakShape(int x, int y) {
		int peak = 4;
		int min = Integer.MAX_VALUE;
		int sum = map[x][y];
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (peak <= 2) // + 모양이 될 수 없음
				return;
			// 하나라도 밖으로 나가면 peak 빼주기
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				peak--;
				continue;
			}
			min = Math.min(min, map[nx][ny]); // 네 방향 peak중에서 제일 작은 값을 제외 시키자!
			sum += map[nx][ny];
		}
		if (peak == 4) {
			sum -= min;
		}
		max = Math.max(max, sum);
	}

}

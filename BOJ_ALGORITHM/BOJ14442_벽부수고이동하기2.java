import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14442_벽부수고이동하기2 {
	public static int N;
	public static int M;
	public static int K;
	public static int[][] map;
	public static int[][][] visit;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		visit = new int[N][M][11]; // x,y,break 방문 체크
		int ans = bfs();
		System.out.println(ans);
	}

	public static int bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { 0, 0, 0, 1 }); // x좌표, y좌표, break횟수, cnt
		visit[0][0][0] = 1;

		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			int b = q.peek()[2];
			int m = q.peek()[3];
			q.poll();
			if (x == N - 1 && y == M - 1) {
				return m;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (visit[nx][ny][b] == 1)
					continue;
				if (map[nx][ny] == 1) { // 벽이라면 벽 부순 횟수 체크하고 이동
					if (b < K) {
						visit[nx][ny][b + 1] = 1;
						q.add(new int[] { nx, ny, b + 1, m + 1 });
					}
				}else {
					visit[nx][ny][b] = 1;
					q.add(new int[] { nx, ny, b, m + 1 });
				}
			}
		}
		return -1;
	}
}

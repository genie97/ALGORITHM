import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178_미로탐색 {
	public static int[][] map;
	public static int N;
	public static int M;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		int ans = bfs(0, 0);
		System.out.println(ans);
	}

	public static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { x, y });
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] p = q.poll();
				int cx = p[0];
				int cy = p[1];
				map[cx][cy] = 0; // 방문처리
				if (cx == N - 1 && cy == M - 1) {
					return cnt + 1;
				}
				for (int j = 0; j < 4; j++) {
					int nx = cx + dx[j];
					int ny = cy + dy[j];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					if (map[nx][ny] == 0)
						continue;
					map[nx][ny] = 0;
					q.add(new int[] { nx, ny });
				}
			}
			cnt++;
		}
		return cnt + 1;
	}
}

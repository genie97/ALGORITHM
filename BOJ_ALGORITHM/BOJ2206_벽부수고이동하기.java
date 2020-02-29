import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206_벽부수고이동하기 {

	public static int N;
	public static int M;
	public static int[][] map;
	public static int[][] visit;
	public static int ans = 0;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			String str = br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = str.charAt(j) - '0';
				visit[i][j] = Integer.MAX_VALUE; // 움직인 이동거리 저장
			}
		}
		int cnt = bfs(0, 0);
		System.out.println(cnt);
	}

	public static int bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		// 항상 시작점은 0이라 안깨도 된다. (좌표, 이동거리, 벽부순횟수)
		q.add(new int[] { i, j, 1, 0 });
		visit[i][j] = 0; // 해당 좌표의 벽부순 횟수

		while (!q.isEmpty()) {
			int x = q.peek()[0]; // x좌표
			int y = q.peek()[1]; // y좌표
			int m = q.peek()[2]; // 이동횟수
			int b = q.peek()[3]; // 벽부순개수
			q.poll();

			if (x == N - 1 && y == M - 1) {
				return m;
			}
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (visit[nx][ny] <= b) // 벽을 이미 부셔서 더 부실 수 없는 경우
					continue;
				if (map[nx][ny] == 0) { // 벽이 아님
					q.add(new int[] { nx, ny, m + 1, b });
					visit[nx][ny] = b;
				} else { // 벽일 때
					if (b == 0) { // 벽 부순 횟수가 아직 없는 경우
						q.add(new int[] { nx, ny, m + 1, b + 1 });
						visit[nx][ny] = b + 1;
					}
				}
			}
		}
		return -1;
	}
}

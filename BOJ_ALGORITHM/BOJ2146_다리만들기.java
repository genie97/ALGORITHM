import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2146_다리만들기 {
	// 각 섬마다 번호를 매긴다 (dfs)
	// 번호를 매긴 순서로 최소로 연결되는 다리의 길이를 구한다(bfs)
	public static int N;
	public static int[][] map;
	public static boolean[][] visit;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < N; j++, index += 2) {
				map[i][j] = str.charAt(index) - '0';
			}
		}

		// 섬의 번호
		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] || map[i][j] == 0)
					continue;
				visit[i][j] = true;
				map[i][j] = num;
				dfs(i, j, num);
				num++;
			}
		}
		// 다리 연결 최소 길이 찾기
		ans = Integer.MAX_VALUE;
		for (int i = 1; i < num; i++) {
			visit = new boolean[N][N];
			bfs(i);
		}
		System.out.println(ans);
	}

	public static void bfs(int n) {
		Queue<int[]> q = new LinkedList<int[]>();
		// 섬의 좌표 큐에 다 넣기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == n) {
					q.add(new int[] { i, j, 0 });
					visit[i][j] = true;
				}
			}
		}
		while (!q.isEmpty()) {
			int size = q.size();
			for (int sz = 0; sz < size; sz++) {
				int data[] = q.poll();
				int x = data[0];
				int y = data[1];
				int cnt = data[2];
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;
					if (map[nx][ny] == n) // 자기 자신이라면 둘러쌓여 있는거니까 제외
						continue;
					if (visit[nx][ny])
						continue;
					if (map[nx][ny] > 0 && map[nx][ny] != n) { // 다른 섬이라면
						if (ans > cnt) {
							ans = cnt;
						}
					}
					visit[nx][ny] = true;
					q.add(new int[] { nx, ny, cnt + 1 });
				}
			}
		}

	}

	public static void dfs(int x, int y, int n) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (visit[nx][ny])
				continue;
			if (map[nx][ny] == 0)
				continue;
			visit[nx][ny] = true;
			map[nx][ny] = n;
			dfs(nx, ny, n);
		}
	}

}

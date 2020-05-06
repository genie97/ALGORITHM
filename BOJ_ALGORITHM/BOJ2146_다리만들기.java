/* 196ms */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2146_다리만들기 {
	static int N;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		// 육지를 0, 바다는 -1로 세팅
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx += 2) {
				map[i][j] = (str.charAt(idx) == '1') ? 0 : -1;
			}
		}

		//섬 번호를 1번부터 매긴다
		int idx = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					map[i][j] = idx;
					numbering(i, j, idx++);
				}
			}
		}

		res = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) {
					makeBridge(i, j);
				}
			}
		}
		System.out.println(res);
	}

	static void makeBridge(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		q.add(new int[] { x, y, 0 });
		visit[x][y] = true;
		int base = map[x][y];

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[2] >= res) break;
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if (isIn(nx, ny)) { // 맵 안에 있는 경우
					if (visit[nx][ny] || map[nx][ny] == base) // 방문했거나 같은 섬인 경우는 제외
						continue;
					if (map[nx][ny] == -1) {// 바다인 경우
						q.add(new int[] { nx, ny, cur[2] + 1 });
						visit[nx][ny] = true;
					} else if (map[nx][ny] > 0 && map[nx][ny] != base) { // 다른 땅 도착인 경우
						res = Math.min(res, cur[2]);
					}
				}
			}
		}
	}

	static void numbering(int x, int y, int idx) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!isIn(nx, ny))
				continue;
			if (map[nx][ny] == -1 || map[nx][ny] == idx)
				continue;
			map[nx][ny] = idx;
			numbering(nx, ny, idx);
		}
	}

	static boolean isIn(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < N;
	}

}

/* 256ms
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
				if (cnt >= ans) // 이미 거리가 답보다 큰 경우
					return;
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
*/

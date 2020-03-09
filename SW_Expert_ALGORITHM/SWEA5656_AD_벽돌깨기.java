import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5656_AD_벽돌깨기 {

	public static int N;
	public static int W;
	public static int H;
	public static int[][] map, copyMap;
	public static boolean[][] visit;
	public static int[] shooting;
	public static int res;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			copyMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0, idx = 0; j < W; j++, idx += 2) {
					map[i][j] = str.charAt(idx) - '0';
				}
			}
			shooting = new int[N];
			// W개의 row에서 N개의 쏠 곳 을 선택한다
			res = Integer.MAX_VALUE;
			dfs(0); // 순열
			sb.append("#").append(tc).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int cnt) {
		if (cnt == N) {
			// 맵 복사해서 쓰기
			copy();
			// 쏠 순서대로 구슬을 쏜다
			for (int i = 0; i < shooting.length; i++) {
				// 명중한 벽돌을 터뜨린다 (4방향으로 퍼진다)
				shootMarble(shooting[i]);
				downBlock();
			}
			// 이제 터질 벽돌이 없다면 바닥으로 내린다
			// 벽돌이 몇 개 남았는지 개수를 센다
			int ans = countBlock();
			if (res <= ans)
				return;
			if (res > ans) {
				res = ans;
			}
			return;
		}
		// 쏘는 곳 순서(조합)
		for (int i = 0; i < W; i++) {
			shooting[cnt] = i;
			dfs(cnt + 1);
		}
	}

	public static void shootMarble(int s) {
		Queue<int[]> q = new LinkedList<int[]>();
		visit = new boolean[H][W];
		// 제일 위에 있는 좌표 꺼내기
		for (int i = 0; i < H; i++) {
			if (copyMap[i][s] > 0) {
				q.add(new int[] { i, s, copyMap[i][s] - 1 }); // 좌표, 해당 좌표에 있는 숫자-1
				break;
			}
		}

		while (!q.isEmpty()) {
			int size = q.size();
			for (int sz = 0; sz < size; sz++) {
				int[] data = q.poll();
				int x = data[0];
				int y = data[1];
				int dis = data[2];
				if (dis == 0) { // 벽돌에 1이 써있는 경우는 자기만 깨지고 끝
					copyMap[x][y] = 0;
					continue;
				}
				for (int i = 0; i < 4; i++) {
					int nx = x + (dx[i] * dis);
					int ny = y + (dy[i] * dis);

					/* 맵 넘어가는 좌표 갱신 */
					if (nx < 0) { // 쐈는데 맵을 넘어간 만큼 나오면 0으로 바꿔주기
						nx = 0;
					}
					if (ny < 0) { // 쐈는데 맵을 넘어간 만큼 나오면 0으로 바꿔주기
						ny = 0;
					}
					if (nx >= H) { // 쐈는데 맵을 넘어간 만큼 나오면 H-1으로 바꿔주기
						nx = H - 1;
					}
					if (ny >= W) { // 쐈는데 맵을 넘어간 만큼 나오면 W-1으로 바꿔주기
						ny = W - 1;
					}

					// 하나씩 증가하면서 없앨 곳 보기
					if (i == 0) { // 세로로 깸
						for (int k = x - 1; k >= nx; k--) {
							if (copyMap[k][y] == 0)
								continue;
							if (visit[k][y])
								continue;
							visit[k][y] = true;
							if (copyMap[k][y] > 0) {
								q.add(new int[] { k, y, copyMap[k][y] - 1 });
							}
							copyMap[k][y] = 0;
						}
					} else if (i == 1) {
						for (int k = x + 1; k <= nx; k++) {
							if (copyMap[k][y] == 0)
								continue;
							if (copyMap[k][y] > 0) {
								q.add(new int[] { k, y, copyMap[k][y] - 1 });
							}
							if (visit[k][y])
								continue;
							visit[k][y] = true;
							copyMap[k][y] = 0;
						}
					} else if (i == 2) { // 가로로 깨기
						for (int k = y - 1; k >= ny; k--) {
							if (copyMap[x][k] == 0)
								continue;
							if (copyMap[x][k] > 0) {
								q.add(new int[] { x, k, copyMap[x][k] - 1 });
							}
							if (visit[x][k])
								continue;
							visit[x][k] = true;
							copyMap[x][k] = 0;
						}
					} else {
						for (int k = y + 1; k <= ny; k++) {
							if (copyMap[x][k] == 0)
								continue;
							if (visit[x][k])
								continue;
							visit[x][k] = true;
							if (copyMap[x][k] > 0) {
								q.add(new int[] { x, k, copyMap[x][k] - 1 });
							}
							copyMap[x][k] = 0;
						}
					}
				}

				// 4방향 다 깬다음에 중심점 깨기
				copyMap[x][y] = 0;
			}
		}
	}

	public static void downBlock() {
		// 밑에서 두번째 줄부터 보자!
		for (int i = 0; i < copyMap[0].length; i++) {
			for (int j = copyMap.length - 2; j >= 0; j--) {
				// 벽돌이 있다면
				if (copyMap[j][i] > 0) {
					int x = j;
					while (true) {
						if (x + 1 >= H || copyMap[x + 1][i] > 0)
							break;
						x++;
					}
					if (j == x)
						continue;
					copyMap[x][i] = copyMap[j][i];
					copyMap[j][i] = 0;
				}
			}
		}
	}

	public static int countBlock() {
		int cnt = 0;
		for (int i = 0; i < copyMap.length; i++) {
			for (int j = 0; j < copyMap[i].length; j++) {
				if (copyMap[i][j] > 0)
					cnt++;
			}
		}
		return cnt;
	}

	public static void copy() {
		for (int i = 0; i < map.length; i++) {
			copyMap[i] = map[i].clone();
		}
	}

//	public static void print() {
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(copyMap[i]));
//		}
//		System.out.println();
//	}
}

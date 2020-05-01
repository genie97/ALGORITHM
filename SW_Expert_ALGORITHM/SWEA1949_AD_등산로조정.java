import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1949_AD_등산로조정 {
	static int N, K;
	static int[][] map;
	static boolean[][] visit;
	static int res;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			ArrayList<int[]> peakList = new ArrayList<>();
			int max = 0;
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= max) {
						if (map[i][j] > max)
							peakList.clear();
						max = map[i][j];
						peakList.add(new int[] { i, j });
					}
				}
			}
			res = 0;
			for (int i = 0; i < peakList.size(); i++) {
				visit = new boolean[N][N];
				int x = peakList.get(i)[0];
				int y = peakList.get(i)[1];
				visit[x][y] = true;
				dfs(x, y, map[x][y], 0, false);
			}
			sb.append('#').append(tc).append(' ').append(res).append('\n');
		}
		System.out.println(sb);
	}

	static void dfs(int x, int y, int prePeak, int step, boolean isBroke) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (visit[nx][ny])
				continue;
			if (prePeak <= map[nx][ny]) {
				if (isBroke) { // 조정할 수 없으면 그냥 무조건 그 길은 갈 수 없음
					res = Math.max(res, step + 1);
					continue;
				}
				// 조정을 아직 안했다면?
				int heights = map[nx][ny] - prePeak + 1; // 조정했을 때 가능한 최소 높이
				if (heights > K) { // 최대 공사 가능보다 차이가 크면 조정불가
					res = Math.max(res, step + 1);
					continue;
				}
				for (int hd = heights; hd <= K; hd++) {
					visit[nx][ny] = true;
					map[nx][ny] -= hd;
					dfs(nx, ny, map[nx][ny], step + 1, true);
					map[nx][ny] += hd;
					visit[nx][ny] = false;
				}
			} else { // 조정할 필요가 없다면
				visit[nx][ny] = true;
				dfs(nx, ny, map[nx][ny], step + 1, isBroke);
				visit[nx][ny] = false;
			}
		}
	}
}

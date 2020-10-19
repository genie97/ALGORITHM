import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA15888_음식배달 {
	static int N;
	static int[][] map;
	static ArrayList<int[]> chicken;
	static int ans;
	static int[] comb;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			chicken = new ArrayList<>();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 2) {
						chicken.add(new int[] { i, j });
					}
				}
			}
			ans = Integer.MAX_VALUE;
			for (int i = 1; i <= chicken.size(); i++) {
				comb = new int[i];
				visit = new boolean[chicken.size()];
				delete(0, 0, i);
			}
			sb.append('#').append(tc+1).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}

	static void delete(int idx, int cnt, int M) {
		if (cnt == M) {
			int dis = calDis(M);
			for (int i = 0; i < comb.length; i++) {
				int x = chicken.get(comb[i])[0];
				int y = chicken.get(comb[i])[1];
				dis += map[x][y];
			}
			ans = Math.min(ans, dis);
			return;
		}
		for (int i = idx; i < chicken.size(); i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			comb[cnt] = i;
			delete(i, cnt + 1, M);
			visit[i] = false;
		}
	}

	static int calDis(int M) {
		int sum = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) {
					int min = Integer.MAX_VALUE;
					for (int k = 0; k < M; k++) {
						min = Math.min(min,
								Math.abs(chicken.get(comb[k])[0] - i) + Math.abs(chicken.get(comb[k])[1] - j));
					}
					sum += min;
				}
			}
		}
		return sum;
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10971_외판원순회2 {

	static int N;
	static int[][] map;
	static boolean[] visit;
	static boolean[] start;
	static int cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visit = new boolean[N];
		start = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cost = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			visit[i] = true;
			dfs(i, 0, 1, i);
			visit[i] = false;
		}
		System.out.println(cost);
	}

	static void dfs(int v, int sum, int cnt, int st) {
		if (cost < sum)
			return;

		if (cnt == N) {
			cost = Math.min(cost, sum + map[v][st]);
		}

		for (int i = 0; i < map[v].length; i++) {
			if (cnt < N) {
				if (map[v][i] != 0 && !visit[i]) {
					visit[i] = true;
					dfs(i, sum + map[v][i], cnt + 1, st);
					visit[i] = false;
				}
			}
		}
	}
}

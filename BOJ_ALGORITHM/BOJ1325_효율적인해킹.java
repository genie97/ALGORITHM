import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1325_효율적인해킹 {

	public static int N, M;
	public static ArrayList<Integer> map[];
	public static boolean visit[];
	public static int res[];

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x].add(y);
		}

		res = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			visit = new boolean[N + 1];
			dfs(i);
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (max < res[i]) {
				max = res[i];
			}
		}

		for (int i = 1; i <= N; i++) {
			if (res[i] == max) {
				System.out.print(i + " ");
			}
		}
	}

	public static void dfs(int v) {
		visit[v] = true;
		for (int i = 0; i < map[v].size(); i++) {
			int u = map[v].get(i);
			if (!visit[u]) {
				dfs(u);
				res[u]++;
			}
		}
	}
}

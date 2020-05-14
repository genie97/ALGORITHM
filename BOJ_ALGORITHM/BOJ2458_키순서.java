import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2458_키순서 {
	static int[][] map;
	static int cnt;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int small = Integer.parseInt(st.nextToken());
			int big = Integer.parseInt(st.nextToken());
			map[small][big] = 1;
		}
		int res = 0;

		for (int i = 1; i <= N; i++) {
			cnt = 0;
			visit = new boolean[N + 1];
			dfs_tall(i);
			dfs_small(i);
			if (cnt == N - 1)
				res++;
		}
		System.out.println(res);
	}

	static void dfs_small(int v) {
		visit[v] = true;
		for (int i = 1; i < map[v].length; i++) {
			if (visit[i])
				continue;
			if (map[i][v] == 0)
				continue;
			cnt++;
			dfs_small(i);
		}
	}

	static void dfs_tall(int v) {
		visit[v] = true;
		for (int i = 1; i < map[v].length; i++) {
			if (visit[i])
				continue;
			if (map[v][i] == 0)
				continue;
			cnt++;
			dfs_tall(i);
		}
	}

}

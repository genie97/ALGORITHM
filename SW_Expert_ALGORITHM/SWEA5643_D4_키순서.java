/* solution1. dfs (2,073 ms) */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA5643_D4_키순서 {
	static int[][] map;
	static int cnt;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];

			StringTokenizer st = null;
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

			sb.append(res).append('\n');
		}
		System.out.println(sb);
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

/* solution2. floyd warshall (2,225ms)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA5643_D4_키순서 {
	static int[][] map;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];

			for (int i = 1; i < map.length; i++) {
				Arrays.fill(map[i], INF);
			}

			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int small = Integer.parseInt(st.nextToken());
				int big = Integer.parseInt(st.nextToken());
				map[small][big] = 1;
			}

			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}

			int res = 0;
			int[] count = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] != INF) {
						count[i]++;
						count[j]++;
					}
				}
			}
			
			for (int i = 0; i < count.length; i++) {
				if (count[i] == N - 1)
					res++;
			}
			
			sb.append(res).append('\n');
		}
		System.out.println(sb);
	}

}
*/

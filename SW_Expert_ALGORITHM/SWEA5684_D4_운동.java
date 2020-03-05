import java.util.Scanner;

public class SWEA5684_D4_운동 {
	public static int[][] adj;
	public static long ans;
	public static boolean[] visit;
	public static int N;
	public static int M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			adj = new int[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int c = sc.nextInt();
				adj[s][e] = c;
			}
			ans = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				visit = new boolean[N + 1]; // 각 vertex에 대한 방문
				dfs(i, 0, i);
			}
			System.out.println("#" + tc + " " + (ans == Integer.MAX_VALUE ? -1 : ans));
		}
	}

	public static void dfs(int v, long sum, int start) {
		if (v == start && visit[v]) {
			ans = Math.min(ans, sum);
			return;
		}
		if (visit[v]) { // 출발점이 아닌 정점에서의 재방문은 제거
			return;
		}
		if (ans <= sum)
			return;
		visit[v] = true;
		for (int i = 1; i <= N; i++) {
			if (adj[v][i] > 0) {
				dfs(i, sum + adj[v][i], start);
			}
		}
	}
}
/**테스트 케이스
1
5 8
1 2 12
1 3 2
2 4 10
3 5 4
4 1 7
4 2 1
4 5 8
5 1 6
*/

/** floyd warshall (구현하면서 주의 사항 i~i를 가는 최소 비용을 찾아야함 i->j j->i 이런식의 접근은 일부 테스트케이스 오류
import java.util.Scanner;

public class SWEA5684_D4_운동 {
	public static int[][] adj;
	public static long ans;
	public static int N;
	public static int M;
	public static final int INF = 987654321;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		adj = new int[N + 1][N + 1];

		// 모든 정점에 대해 INF로 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adj[i][j] = INF;
			}
		}

		for (int i = 0; i < M; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int c = sc.nextInt();
			adj[s][e] = c;
		}
		floyd();
		// 플로이드 워샬
		ans = INF;
		for (int i = 1; i <= N; i++) {
			ans = Math.min(ans, adj[i][i]);
		}
		System.out.println(ans == INF ? -1 : ans);

	}

	public static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					}
				}
			}
		}

	}
}
*/

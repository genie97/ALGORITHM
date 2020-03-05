import java.util.Random;
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

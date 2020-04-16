import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11404번_플로이드 {
	static final int MAX = 999999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] adj = new int[n + 1][n + 1];
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj.length; j++) {
				if (i == j)
					adj[i][j] = 0;
				else
					adj[i][j] = MAX;
			}
		}
		StringTokenizer st = null;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a][b] = Math.min(adj[a][b], c);
		}

		// 플로이드 워샬
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				sb.append(adj[i][j] == MAX ? 0 : adj[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}

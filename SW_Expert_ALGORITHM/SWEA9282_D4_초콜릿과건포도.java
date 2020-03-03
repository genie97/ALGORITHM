import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA9282_D4_초콜릿과건포도 {
	public static int n;
	public static int m;
	public static int[][] choco;
	public static int[][][][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			choco = new int[n + 1][m + 1];
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= m; j++) {
					choco[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 가로로 누적
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j < m; j++) {
					choco[i][j + 1] += choco[i][j];
				}
			}
			// 세로로 누적
			for (int i = 1; i < n; i++) {
				for (int j = 1; j <= m; j++) {
					choco[i + 1][j] += choco[i][j];
				}
			}

			dp = new int[n + 1][m + 1][n + 1][m + 1];

			int ans = dfs(1, 1, n, m);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static int dfs(int r, int c, int rlen, int clen) {
		if (rlen == 1 && clen == 1) // 다 자름
			return 0;
		if (dp[r][c][rlen][clen] > 0)
			return dp[r][c][rlen][clen];

		int res = Integer.MAX_VALUE;

		int sum = choco[r + rlen - 1][c + clen - 1] - choco[r + rlen - 1][c - 1] - choco[r - 1][c + clen - 1]
				+ choco[r - 1][c - 1];
		
		// 한 번 자를 때 두조각으로 나뉜다
		for (int i = 1; i < rlen; i++) {
			res = Math.min(res, sum + dfs(r, c, i, clen) + dfs(r + i, c, rlen - i, clen));
			System.out.println(res);
		}
		for (int i = 1; i < clen; i++) {
			res = Math.min(res, sum + dfs(r, c, rlen, i) + dfs(r, c + i, rlen, clen - i));
			System.out.println(res);
		}
		dp[r][c][rlen][clen] = res;

		return res;
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2624_동전바꿔주기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int[] p = new int[k+1];
		int[] n = new int[k+1];
		StringTokenizer st = null;
		for (int i = 1; i <= k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p[i] = Integer.parseInt(st.nextToken());
			n[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[T + 1][k + 1];
		for (int i = 1; i <= k; i++) {
			dp[0][i-1] = 1;
			for (int j = 1; j <= n[i]; j++) {
				for (int l = p[i] * j; l <= T; l++) {
					dp[l][i]+= dp[l-(p[i] * j)][i-1];
				}
			}
			for (int j = 1; j <= T; j++) {
				dp[j][i] += dp[j][i-1];
			}
		}
		System.out.println(dp[T][k]);
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11057_오르막수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int q = 10007;
		long[][] dp = new long[1001][10];
		for (int i = 0; i < dp[1].length; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				for (int k = j; k < 10; k++) {
					dp[i][j] += dp[i - 1][k];
				}
				dp[i][j] %= q;
			}
		}

		long ans = 0;

		for (int i = 0; i < dp[N].length; i++) {
			ans += dp[N][i];
		}

		System.out.println(ans % q);
	}
}

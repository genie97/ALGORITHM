import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2839_설탕배달 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int M = Integer.parseInt(br.readLine());
			int[] salt = { 3, 5 };
			int[] dp = new int[5001];
			dp[0] = dp[1] = dp[2] = Integer.MAX_VALUE;
			dp[3] = dp[5] = 1;

			for (int i = 4; i <= M; i++) {
				if (i == 5)
					continue;
				dp[i] = Integer.MAX_VALUE; 
				for (int j = 0; j < salt.length; j++) {
					if (i - salt[j] < 0 || dp[i - salt[j]] == Integer.MAX_VALUE)
						continue;

					int cnt = dp[i - salt[j]] + dp[salt[j]]; 
					dp[i] = Math.min(dp[i], cnt); 
				}
			}
			System.out.println(dp[M] == Integer.MAX_VALUE ? -1 : dp[M]);
		}
}

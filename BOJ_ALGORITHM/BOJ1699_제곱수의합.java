import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1699_제곱수의합 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		for (int i = 1; i < dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		for (int i = 1; i * i <= N; i++) {
			for (int j = i * i; j <= N; j++) {
				dp[j] = Math.min(dp[j], dp[j - (i * i)] + 1);
			}
		}
		System.out.println(dp[N]);
	}
}

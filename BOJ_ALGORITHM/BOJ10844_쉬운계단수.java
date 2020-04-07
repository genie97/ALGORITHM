import java.util.Scanner;

public class BOJ10844_쉬운계단수 {
	static final int MOD = 1000000000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] dp = new int[101][10];
		dp[0][0] = 0; // 처음 0으로 만들 수 있는 수는 없음

		// 한자리인경우
		for (int i = 1; i < 10; i++) {
			dp[0][i] = 1;
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0)
					dp[i][j] = dp[i - 1][j + 1];
				else if(j==9)
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
			}
		}
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += dp[N - 1][i];
			sum %= MOD;
		}
		System.out.println(sum);
	}
}
